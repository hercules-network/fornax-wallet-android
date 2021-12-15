package com.fornaxwallet.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.fornaxwallet.app.entity.tokens.Token;
import com.fornaxwallet.app.interact.GenericWalletInteract;
import com.fornaxwallet.app.router.RedeemSignatureDisplayRouter;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.TokensService;

public class RedeemAssetSelectViewModelFactory implements ViewModelProvider.Factory {
    private final RedeemSignatureDisplayRouter redeemSignatureDisplayRouter;
    private final AssetDefinitionService assetDefinitionService;
    private final TokensService tokensService;
    private final GenericWalletInteract genericWalletInteract;

    public RedeemAssetSelectViewModelFactory(
            RedeemSignatureDisplayRouter redeemSignatureDisplayRouter,
            AssetDefinitionService assetDefinitionService,
            TokensService tokensService,
            GenericWalletInteract genericWalletInteract) {
        this.redeemSignatureDisplayRouter = redeemSignatureDisplayRouter;
        this.assetDefinitionService = assetDefinitionService;
        this.tokensService = tokensService;
        this.genericWalletInteract = genericWalletInteract;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RedeemAssetSelectViewModel(redeemSignatureDisplayRouter, assetDefinitionService, tokensService, genericWalletInteract);
    }
}