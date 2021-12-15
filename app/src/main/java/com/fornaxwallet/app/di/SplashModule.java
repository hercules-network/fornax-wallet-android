package com.fornaxwallet.app.di;

import com.fornaxwallet.app.interact.FetchWalletsInteract;
import com.fornaxwallet.app.repository.PreferenceRepositoryType;
import com.fornaxwallet.app.repository.WalletRepositoryType;
import com.fornaxwallet.app.service.KeyService;
import com.fornaxwallet.app.viewmodel.SplashViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashModule {

    @Provides
    SplashViewModelFactory provideSplashViewModelFactory(FetchWalletsInteract fetchWalletsInteract,
                                                         PreferenceRepositoryType preferenceRepository,
                                                         KeyService keyService) {
        return new SplashViewModelFactory(
                fetchWalletsInteract,
                preferenceRepository,
                keyService);
    }

    @Provides
    FetchWalletsInteract provideFetchWalletInteract(WalletRepositoryType walletRepository) {
        return new FetchWalletsInteract(walletRepository);
    }
}