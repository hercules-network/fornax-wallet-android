package com.fornaxwallet.app.di;

import com.fornaxwallet.app.interact.ChangeTokenEnableInteract;
import com.fornaxwallet.app.interact.FetchTokensInteract;
import com.fornaxwallet.app.interact.GenericWalletInteract;
import com.fornaxwallet.app.repository.PreferenceRepositoryType;
import com.fornaxwallet.app.repository.TokenRepositoryType;
import com.fornaxwallet.app.repository.WalletRepositoryType;
import com.fornaxwallet.app.router.AssetDisplayRouter;
import com.fornaxwallet.app.router.TokenDetailRouter;
import com.fornaxwallet.app.router.MyAddressRouter;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.viewmodel.WalletViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class WalletModule {
    @Provides
    WalletViewModelFactory provideWalletViewModelFactory(
            FetchTokensInteract fetchTokensInteract,
            TokenDetailRouter tokenDetailRouter,
            AssetDisplayRouter assetDisplayRouter,
            GenericWalletInteract genericWalletInteract,
            AssetDefinitionService assetDefinitionService,
            TokensService tokensService,
            ChangeTokenEnableInteract changeTokenEnableInteract,
            MyAddressRouter myAddressRouter,
            PreferenceRepositoryType preferenceRepository) {
        return new WalletViewModelFactory(
                fetchTokensInteract,
                tokenDetailRouter,
                assetDisplayRouter,
                genericWalletInteract,
                assetDefinitionService,
                tokensService,
                changeTokenEnableInteract,
                myAddressRouter,
                preferenceRepository);
    }

    @Provides
    FetchTokensInteract provideFetchTokensInteract(TokenRepositoryType tokenRepository) {
        return new FetchTokensInteract(tokenRepository);
    }

    @Provides
    TokenDetailRouter provideErc20DetailRouterRouter() {
        return new TokenDetailRouter();
    }

    @Provides
    AssetDisplayRouter provideAssetDisplayRouter() {
        return new AssetDisplayRouter();
    }

    @Provides
    GenericWalletInteract provideGenericWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }

    @Provides
    ChangeTokenEnableInteract provideChangeTokenEnableInteract(TokenRepositoryType tokenRepository) {
        return new ChangeTokenEnableInteract(tokenRepository);
    }

    @Provides
    MyAddressRouter provideMyAddressRouter() {
        return new MyAddressRouter();
    }
}