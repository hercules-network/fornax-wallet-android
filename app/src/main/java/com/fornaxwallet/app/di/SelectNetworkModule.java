package com.fornaxwallet.app.di;

import com.fornaxwallet.app.repository.EthereumNetworkRepositoryType;
import com.fornaxwallet.app.repository.PreferenceRepositoryType;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.viewmodel.SelectNetworkViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class SelectNetworkModule {
    @Provides
    SelectNetworkViewModelFactory provideSelectNetworkViewModelFactory(EthereumNetworkRepositoryType networkRepositoryType,
                                                                       TokensService tokensService,
                                                                       PreferenceRepositoryType preferenceRepositoryType) {
        return new SelectNetworkViewModelFactory(networkRepositoryType, tokensService, preferenceRepositoryType);
    }
}