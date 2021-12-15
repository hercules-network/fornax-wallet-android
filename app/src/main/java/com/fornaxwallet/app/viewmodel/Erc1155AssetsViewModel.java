package com.fornaxwallet.app.viewmodel;

import android.content.Context;
import android.content.Intent;

import com.fornaxwallet.app.C;
import com.fornaxwallet.app.entity.Wallet;
import com.fornaxwallet.app.entity.nftassets.NFTAsset;
import com.fornaxwallet.app.entity.tokens.Token;
import com.fornaxwallet.app.interact.FetchTransactionsInteract;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.ui.Erc1155AssetDetailActivity;
import com.fornaxwallet.app.ui.Erc1155AssetListActivity;

import java.math.BigInteger;

public class Erc1155AssetsViewModel extends BaseViewModel {
    private final FetchTransactionsInteract fetchTransactionsInteract;
    private final AssetDefinitionService assetDefinitionService;
    private final TokensService tokensService;

    public Erc1155AssetsViewModel(FetchTransactionsInteract fetchTransactionsInteract,
                                  AssetDefinitionService assetDefinitionService,
                                  TokensService tokensService) {
        this.fetchTransactionsInteract = fetchTransactionsInteract;
        this.assetDefinitionService = assetDefinitionService;
        this.tokensService = tokensService;
    }

    public TokensService getTokensService() {
        return tokensService;
    }

    public Intent showAssetListDetails(Context context, Wallet wallet, Token token, NFTAsset asset) {
        Intent intent = new Intent(context, Erc1155AssetListActivity.class);
        intent.putExtra(C.Key.WALLET, wallet);
        intent.putExtra(C.EXTRA_CHAIN_ID, token.tokenInfo.chainId);
        intent.putExtra(C.EXTRA_ADDRESS, token.getAddress());
        intent.putExtra(C.EXTRA_NFTASSET_LIST, asset);
        return intent;
    }

    public Intent showAssetDetails(Context context, Wallet wallet, Token token, BigInteger tokenId) {
        Intent intent = new Intent(context, Erc1155AssetDetailActivity.class);
        intent.putExtra(C.Key.WALLET, wallet);
        intent.putExtra(C.EXTRA_CHAIN_ID, token.tokenInfo.chainId);
        intent.putExtra(C.EXTRA_ADDRESS, token.getAddress());
        intent.putExtra(C.EXTRA_TOKEN_ID, tokenId.toString());
        return intent;
    }
}