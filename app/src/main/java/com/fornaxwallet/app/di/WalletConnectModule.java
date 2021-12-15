package com.fornaxwallet.app.di;

import com.fornaxwallet.app.interact.CreateTransactionInteract;
import com.fornaxwallet.app.interact.FindDefaultNetworkInteract;
import com.fornaxwallet.app.interact.GenericWalletInteract;
import com.fornaxwallet.app.repository.EthereumNetworkRepositoryType;
import com.fornaxwallet.app.repository.TransactionRepositoryType;
import com.fornaxwallet.app.repository.WalletRepositoryType;
import com.fornaxwallet.app.service.AnalyticsServiceType;
import com.fornaxwallet.app.service.GasService;
import com.fornaxwallet.app.service.KeyService;
import com.fornaxwallet.app.service.RealmManager;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.viewmodel.WalletConnectViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class WalletConnectModule {
    @Provides
    WalletConnectViewModelFactory provideWalletConnectViewModelFactory(
            KeyService keyService,
            FindDefaultNetworkInteract findDefaultNetworkInteract,
            CreateTransactionInteract createTransactionInteract,
            GenericWalletInteract genericWalletInteract,
            RealmManager realmManager,
            GasService gasService,
            TokensService tokensService,
            AnalyticsServiceType analyticsServiceType) {
        return new WalletConnectViewModelFactory(
                keyService,
                findDefaultNetworkInteract,
                createTransactionInteract,
                genericWalletInteract,
                realmManager,
                gasService,
                tokensService,
                analyticsServiceType);
    }

    @Provides
    FindDefaultNetworkInteract provideFindDefaultNetworkInteract(
            EthereumNetworkRepositoryType ethereumNetworkRepositoryType) {
        return new FindDefaultNetworkInteract(ethereumNetworkRepositoryType);
    }

    @Provides
    CreateTransactionInteract provideCreateTransactionInteract(TransactionRepositoryType transactionRepository) {
        return new CreateTransactionInteract(transactionRepository);
    }

    @Provides
    GenericWalletInteract provideGenericWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }
}