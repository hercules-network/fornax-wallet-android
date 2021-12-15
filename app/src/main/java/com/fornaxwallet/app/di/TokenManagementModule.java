package com.fornaxwallet.app.di;

import com.fornaxwallet.app.interact.ChangeTokenEnableInteract;
import com.fornaxwallet.app.repository.TokenRepositoryType;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.viewmodel.TokenManagementViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class TokenManagementModule {
    @Provides
    TokenManagementViewModelFactory provideTokenManagementViewModelFactory(
            TokenRepositoryType tokenRepository,
            ChangeTokenEnableInteract changeTokenEnableInteract,
            AssetDefinitionService assetDefinitionService,
            TokensService tokensService) {
        return new TokenManagementViewModelFactory(tokenRepository, changeTokenEnableInteract, assetDefinitionService, tokensService);
    }

    @Provides
    ChangeTokenEnableInteract provideChangeTokenEnableInteract(TokenRepositoryType tokenRepository) {
        return new ChangeTokenEnableInteract(tokenRepository);
    }
}