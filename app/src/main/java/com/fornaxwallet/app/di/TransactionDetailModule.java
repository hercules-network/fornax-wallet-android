package com.fornaxwallet.app.di;

import com.fornaxwallet.app.interact.CreateTransactionInteract;
import com.fornaxwallet.app.interact.FetchTransactionsInteract;
import com.fornaxwallet.app.interact.FindDefaultNetworkInteract;
import com.fornaxwallet.app.interact.GenericWalletInteract;
import com.fornaxwallet.app.repository.EthereumNetworkRepositoryType;
import com.fornaxwallet.app.repository.TokenRepositoryType;
import com.fornaxwallet.app.repository.TransactionRepositoryType;
import com.fornaxwallet.app.repository.WalletRepositoryType;
import com.fornaxwallet.app.router.ExternalBrowserRouter;
import com.fornaxwallet.app.service.AnalyticsServiceType;
import com.fornaxwallet.app.service.GasService;
import com.fornaxwallet.app.service.KeyService;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.viewmodel.TransactionDetailViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class TransactionDetailModule {

    @Provides
    TransactionDetailViewModelFactory provideTransactionDetailViewModelFactory(
            FindDefaultNetworkInteract findDefaultNetworkInteract,
            ExternalBrowserRouter externalBrowserRouter,
            TokenRepositoryType tokenRepository,
            TokensService tokensService,
            FetchTransactionsInteract fetchTransactionsInteract,
            KeyService keyService,
            GasService gasService,
            CreateTransactionInteract createTransactionInteract,
            AnalyticsServiceType analyticsService) {
        return new TransactionDetailViewModelFactory(
                findDefaultNetworkInteract, externalBrowserRouter, tokenRepository, tokensService, fetchTransactionsInteract, keyService, gasService, createTransactionInteract, analyticsService);
    }

    @Provides
    FindDefaultNetworkInteract provideFindDefaultNetworkInteract(
            EthereumNetworkRepositoryType ethereumNetworkRepository) {
        return new FindDefaultNetworkInteract(ethereumNetworkRepository);
    }

    @Provides
    ExternalBrowserRouter externalBrowserRouter() {
        return new ExternalBrowserRouter();
    }

    @Provides
    GenericWalletInteract findDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }

    @Provides
    FetchTransactionsInteract provideFetchTransactionsInteract(TransactionRepositoryType transactionRepository,
                                                               TokenRepositoryType tokenRepositoryType) {
        return new FetchTransactionsInteract(transactionRepository, tokenRepositoryType);
    }

    @Provides
    CreateTransactionInteract provideCreateTransactionInteract(TransactionRepositoryType transactionRepository) {
        return new CreateTransactionInteract(transactionRepository);
    }
}