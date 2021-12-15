package com.fornaxwallet.app.di;

import com.fornaxwallet.app.interact.GenericWalletInteract;
import com.fornaxwallet.app.repository.WalletRepositoryType;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.viewmodel.Erc1155AssetDetailViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class Erc1155AssetDetailModule {
    @Provides
    Erc1155AssetDetailViewModelFactory provideErc1155AssetDetailViewModelFactory(
            AssetDefinitionService assetDefinitionService,
            TokensService tokensService,
            GenericWalletInteract walletInteract) {
        return new Erc1155AssetDetailViewModelFactory(assetDefinitionService, tokensService, walletInteract);
    }

    @Provides
    GenericWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }
}