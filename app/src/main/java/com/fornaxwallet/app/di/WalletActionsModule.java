package com.fornaxwallet.app.di;

import dagger.Module;
import dagger.Provides;
import com.fornaxwallet.app.interact.DeleteWalletInteract;
import com.fornaxwallet.app.interact.ExportWalletInteract;
import com.fornaxwallet.app.interact.FetchWalletsInteract;
import com.fornaxwallet.app.repository.WalletRepositoryType;
import com.fornaxwallet.app.router.HomeRouter;
import com.fornaxwallet.app.viewmodel.WalletActionsViewModelFactory;

@Module
class WalletActionsModule {
    @Provides
    WalletActionsViewModelFactory provideWalletActionsViewModelFactory(
            HomeRouter homeRouter,
            DeleteWalletInteract deleteWalletInteract,
            ExportWalletInteract exportWalletInteract,
            FetchWalletsInteract fetchWalletsInteract) {
        return new WalletActionsViewModelFactory(
                homeRouter,
                deleteWalletInteract,
                exportWalletInteract,
                fetchWalletsInteract);
    }

    @Provides
    HomeRouter provideHomeRouter() {
        return new HomeRouter();
    }

    @Provides
    DeleteWalletInteract provideDeleteAccountInteract(
            WalletRepositoryType accountRepository) {
        return new DeleteWalletInteract(accountRepository);
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