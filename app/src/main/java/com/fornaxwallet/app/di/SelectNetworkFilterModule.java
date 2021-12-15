package com.fornaxwallet.app.di;

import com.fornaxwallet.app.repository.EthereumNetworkRepositoryType;
import com.fornaxwallet.app.repository.PreferenceRepositoryType;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.viewmodel.SelectNetworkFilterViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class SelectNetworkFilterModule {
    @Provides
    SelectNetworkFilterViewModelFactory provideSelectNetworkFilterViewModelFactory(EthereumNetworkRepositoryType networkRepositoryType,
                                                                                   TokensService tokensService,
                                                                                   PreferenceRepositoryType preferenceRepositoryType) {
        return new SelectNetworkFilterViewModelFactory(networkRepositoryType, tokensService, preferenceRepositoryType);
    }
}