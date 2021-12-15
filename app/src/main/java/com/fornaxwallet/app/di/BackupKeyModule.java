package com.fornaxwallet.app.di;

import dagger.Module;
import dagger.Provides;

import com.fornaxwallet.app.interact.ExportWalletInteract;
import com.fornaxwallet.app.interact.FetchWalletsInteract;
import com.fornaxwallet.app.repository.WalletRepositoryType;
import com.fornaxwallet.app.service.KeyService;
import com.fornaxwallet.app.viewmodel.BackupKeyViewModelFactory;

@Module
public class BackupKeyModule {
    @Provides
    BackupKeyViewModelFactory provideBackupKeyViewModelFactory(
            KeyService keyService,
            ExportWalletInteract exportWalletInteract,
            FetchWalletsInteract fetchWalletsInteract) {
        return new BackupKeyViewModelFactory(
                keyService,
                exportWalletInteract,
                fetchWalletsInteract);
    }

    @Provides
    ExportWalletInteract provideExportWalletInteract(
            WalletRepositoryType walletRepository) {
        return new ExportWalletInteract(walletRepository);
    }

    @Provides
    FetchWalletsInteract provideFetchAccountsInteract(WalletRepositoryType accountRepository) {
        return new FetchWalletsInteract(accountRepository);
    }
}