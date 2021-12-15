package com.fornaxwallet.app.viewmodel;

import android.app.Activity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.content.Context;

import com.fornaxwallet.app.entity.CryptoFunctions;
import com.fornaxwallet.app.entity.NetworkInfo;
import com.fornaxwallet.app.entity.Operation;
import com.fornaxwallet.app.entity.SignAuthenticationCallback;
import com.fornaxwallet.app.entity.cryptokeys.SignatureFromKey;
import com.fornaxwallet.app.entity.tokens.Token;
import com.fornaxwallet.app.entity.Wallet;
import com.fornaxwallet.app.entity.tokens.TokenTicker;
import com.fornaxwallet.app.interact.CreateTransactionInteract;
import com.fornaxwallet.app.interact.FindDefaultNetworkInteract;
import com.fornaxwallet.app.interact.GenericWalletInteract;
import com.fornaxwallet.app.repository.EthereumNetworkRepository;
import com.fornaxwallet.app.router.SellDetailRouter;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.KeyService;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.ui.SellDetailActivity;
import com.fornaxwallet.app.util.Utils;
import com.fornaxwallet.token.entity.SalesOrderMalformed;
import com.fornaxwallet.token.entity.SignableBytes;
import com.fornaxwallet.token.tools.ParseMagicLink;

import java.math.BigInteger;
import java.util.List;

public class SellDetailViewModel extends BaseViewModel {
    private final MutableLiveData<Wallet> defaultWallet = new MutableLiveData<>();
    private final MutableLiveData<Double> ethereumPrice = new MutableLiveData<>();
    private final MutableLiveData<String> universalLinkReady = new MutableLiveData<>();

    private Token token;
    private ParseMagicLink parser;

    private final FindDefaultNetworkInteract findDefaultNetworkInteract;
    private final TokensService tokensService;
    private final CreateTransactionInteract createTransactionInteract;
    private final SellDetailRouter sellDetailRouter;
    private final KeyService keyService;
    private final AssetDefinitionService assetDefinitionService;

    private byte[] linkMessage;

    SellDetailViewModel(FindDefaultNetworkInteract findDefaultNetworkInteract,
                        TokensService tokensService,
                        CreateTransactionInteract createTransactionInteract,
                        SellDetailRouter sellDetailRouter,
                        KeyService keyService,
                        AssetDefinitionService assetDefinitionService) {
        this.findDefaultNetworkInteract = findDefaultNetworkInteract;
        this.tokensService = tokensService;
        this.createTransactionInteract = createTransactionInteract;
        this.sellDetailRouter = sellDetailRouter;
        this.keyService = keyService;
        this.assetDefinitionService = assetDefinitionService;
    }

    private void initParser() {
        if (parser == null) {
            parser = new ParseMagicLink(new CryptoFunctions(), EthereumNetworkRepository.extraChains());
        }
    }

    public LiveData<Wallet> defaultWallet() {
        return defaultWallet;
    }

    public LiveData<Double> ethereumPrice() {
        return ethereumPrice;
    }

    public LiveData<String> universalLinkReady() {
        return universalLinkReady;
    }

    public String getSymbol() {
        return findDefaultNetworkInteract.getNetworkInfo(token.tokenInfo.chainId).symbol;
    }

    public TokensService getTokensService() {
        return tokensService;
    }

    public NetworkInfo getNetwork() {
        return findDefaultNetworkInteract.getNetworkInfo(token.tokenInfo.chainId);
    }

    public void prepare(Token token, Wallet wallet) {
        this.token = token;
        this.defaultWallet.setValue(wallet);
        //now get the ticker
        /*disposable = findDefaultNetworkInteract
                .getTicker(token)
                .subscribe(this::onTicker, this::onError);*/
    }

    private void onTicker(TokenTicker ticker) {
        if (ticker != null && ticker.updateTime != 0) {
            ethereumPrice.postValue(Double.parseDouble(ticker.price));
        }
    }

    public void generateUniversalLink(List<BigInteger> ticketSendIndexList, String contractAddress, BigInteger price, long expiry) {
        initParser();
        if (ticketSendIndexList == null || ticketSendIndexList.size() == 0)
            return; //TODO: Display error message

        int[] indexList = new int[ticketSendIndexList.size()];
        for (int i = 0; i < ticketSendIndexList.size(); i++)
            indexList[i] = ticketSendIndexList.get(i).intValue();

        SignableBytes tradeBytes = new SignableBytes(parser.getTradeBytes(indexList, contractAddress, price, expiry));
        try {
            linkMessage = ParseMagicLink.generateLeadingLinkBytes(indexList, contractAddress, price, expiry);
        } catch (SalesOrderMalformed e) {
            //TODO: Display appropriate error to user
        }

        //sign this link
        disposable = createTransactionInteract
                .sign(defaultWallet().getValue(), tradeBytes, token.tokenInfo.chainId)
                .subscribe(this::gotSignature, this::onError);
    }

    public void openUniversalLinkSetExpiry(Context context, List<BigInteger> selection, double price) {
        sellDetailRouter.openUniversalLink(context, token, Utils.bigIntListToString(selection, false), defaultWallet.getValue(), SellDetailActivity.SET_EXPIRY, price);
    }

    private void gotSignature(SignatureFromKey signature) {
        initParser();
        String universalLink = parser.completeUniversalLink(token.tokenInfo.chainId, linkMessage, signature.signature);
        //Now open the share icon
        universalLinkReady.postValue(universalLink);
    }

    public AssetDefinitionService getAssetDefinitionService() {
        return assetDefinitionService;
    }

    public void getAuthorisation(Activity activity, SignAuthenticationCallback callback) {
        if (defaultWallet.getValue() != null) {
            keyService.getAuthenticationForSignature(defaultWallet.getValue(), activity, callback);
        }
    }

    public void resetSignDialog() {
        keyService.resetSigningDialog();
    }

    public void completeAuthentication(Operation signData) {
        keyService.completeAuthentication(signData);
    }

    public void failedAuthentication(Operation signData) {
        keyService.failedAuthentication(signData);
    }
}