package com.fornaxwallet.app.di;

import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.viewmodel.GasSettingsViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class GasSettingsModule {

    @Provides
    public GasSettingsViewModelFactory provideGasSettingsViewModelFactory(TokensService svs) {
        return new GasSettingsViewModelFactory(svs);
    }
}