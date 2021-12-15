package com.fornaxwallet.app.di;

import com.fornaxwallet.app.repository.EthereumNetworkRepositoryType;
import com.fornaxwallet.app.repository.PreferenceRepositoryType;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.viewmodel.CustomNetworkViewModelFactory;
import com.fornaxwallet.app.viewmodel.SelectNetworkViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class CustomNetworkModule {
    @Provides
    CustomNetworkViewModelFactory provideSelectNetworkViewModelFactory(EthereumNetworkRepositoryType networkRepositoryType) {
        return new CustomNetworkViewModelFactory(networkRepositoryType);
    }
}