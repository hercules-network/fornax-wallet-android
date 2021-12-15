package com.fornaxwallet.app.di;

import com.fornaxwallet.app.viewmodel.HelpViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class HelpModule {
    @Provides
    HelpViewModelFactory provideMarketplaceViewModelFactory() {
        return new HelpViewModelFactory();
    }
}