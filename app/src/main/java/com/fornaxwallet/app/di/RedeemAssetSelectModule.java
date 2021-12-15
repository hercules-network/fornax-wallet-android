package com.fornaxwallet.app.di;

import com.fornaxwallet.app.interact.GenericWalletInteract;
import com.fornaxwallet.app.repository.WalletRepositoryType;
import com.fornaxwallet.app.router.RedeemSignatureDisplayRouter;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.viewmodel.RedeemAssetSelectViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class RedeemAssetSelectModule {
    @Provides
    RedeemAssetSelectViewModelFactory redeemTokenSelectViewModelFactory(
            RedeemSignatureDisplayRouter redeemSignatureDisplayRouter,
            AssetDefinitionService assetDefinitionService,
            TokensService tokensService,
            GenericWalletInteract genericWalletInteract) {

        return new RedeemAssetSelectViewModelFactory(redeemSignatureDisplayRouter, assetDefinitionService, tokensService, genericWalletInteract);
    }

    @Provides
    RedeemSignatureDisplayRouter provideRedeemSignatureDisplayRouter() {
        return new RedeemSignatureDisplayRouter();
    }

    @Provides
    GenericWalletInteract provideGenericWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }
}