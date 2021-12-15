package com.fornaxwallet.app.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.fornaxwallet.app.entity.Wallet;
import com.fornaxwallet.app.entity.tokens.Token;
import com.fornaxwallet.app.interact.GenericWalletInteract;
import com.fornaxwallet.app.router.RedeemSignatureDisplayRouter;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.ui.widget.entity.TicketRangeParcel;
import com.fornaxwallet.token.entity.TicketRange;

public class RedeemAssetSelectViewModel extends BaseViewModel {
    private final RedeemSignatureDisplayRouter redeemSignatureDisplayRouter;
    private final AssetDefinitionService assetDefinitionService;
    private final TokensService tokensService;
    private final GenericWalletInteract genericWalletInteract;

    public RedeemAssetSelectViewModel(
            RedeemSignatureDisplayRouter redeemSignatureDisplayRouter,
            AssetDefinitionService assetDefinitionService,
            TokensService tokensService,
            GenericWalletInteract genericWalletInteract) {
        this.redeemSignatureDisplayRouter = redeemSignatureDisplayRouter;
        this.assetDefinitionService = assetDefinitionService;
        this.tokensService = tokensService;
        this.genericWalletInteract = genericWalletInteract;
    }

    public void showRedeemSignature(Context ctx, TicketRange range, Token token) {
        genericWalletInteract.find()
                .subscribe(wallet -> onWallet(wallet, ctx, range, token), this::onError)
                .isDisposed();
    }

    private void onWallet(Wallet wallet, Context ctx, TicketRange range, Token token) {
        TicketRangeParcel parcel = new TicketRangeParcel(range);
        redeemSignatureDisplayRouter.open(ctx, wallet, token, parcel);
    }

    public TokensService getTokensService() {
        return tokensService;
    }

    public AssetDefinitionService getAssetDefinitionService() {
        return assetDefinitionService;
    }
}