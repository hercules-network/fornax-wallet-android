package com.fornaxwallet.app.di;

import android.content.Context;

import com.fornaxwallet.app.interact.GenericWalletInteract;
import com.fornaxwallet.app.repository.PreferenceRepositoryType;
import com.fornaxwallet.app.repository.WalletRepositoryType;
import com.fornaxwallet.app.router.ManageWalletsRouter;
import com.fornaxwallet.app.router.MyAddressRouter;
import com.fornaxwallet.app.viewmodel.NameThisWalletViewModelFactory;
import com.fornaxwallet.app.viewmodel.NewSettingsViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class NameThisWalletModule {
    @Provides
    NameThisWalletViewModelFactory provideNameThisWalletViewModelFactory(
            GenericWalletInteract genericWalletInteract, Context context
    ) {
        return new NameThisWalletViewModelFactory(
                genericWalletInteract, context);
    }

    @Provides
    GenericWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }
}