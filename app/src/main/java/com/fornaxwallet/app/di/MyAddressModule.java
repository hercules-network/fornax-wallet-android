package com.fornaxwallet.app.di;

import com.fornaxwallet.app.repository.EthereumNetworkRepositoryType;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.viewmodel.MyAddressViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class MyAddressModule {
    @Provides
    MyAddressViewModelFactory provideMyAddressViewModelFactory(
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            TokensService tokensService,
            AssetDefinitionService assetDefinitionService) {
        return new MyAddressViewModelFactory(
                ethereumNetworkRepository,
                tokensService,
                assetDefinitionService);
    }
}