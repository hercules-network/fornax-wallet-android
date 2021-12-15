package com.fornaxwallet.app.di;

import com.fornaxwallet.app.interact.ImportWalletInteract;
import com.fornaxwallet.app.repository.WalletRepositoryType;
import com.fornaxwallet.app.service.AnalyticsServiceType;
import com.fornaxwallet.app.service.KeyService;
import com.fornaxwallet.app.viewmodel.ImportWalletViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class ImportModule {
    @Provides
    ImportWalletViewModelFactory provideImportWalletViewModelFactory(
            ImportWalletInteract importWalletInteract,
            KeyService keyService,
            AnalyticsServiceType analyticsService) {
        return new ImportWalletViewModelFactory(importWalletInteract, keyService, analyticsService);
    }

    @Provides
    ImportWalletInteract provideImportWalletInteract(
            WalletRepositoryType walletRepository) {
        return new ImportWalletInteract(walletRepository);
    }
}