package com.fornaxwallet.app.di;

import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.viewmodel.TokenScriptManagementViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class TokenScriptManagementModule {
    @Provides
    TokenScriptManagementViewModelFactory tokenScriptManagementViewModelFactory(AssetDefinitionService assetDefinitionService) {
        return new TokenScriptManagementViewModelFactory(assetDefinitionService);
    }
}