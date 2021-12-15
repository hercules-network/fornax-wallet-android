package com.fornaxwallet.app.di;

import com.fornaxwallet.app.interact.CreateTransactionInteract;
import com.fornaxwallet.app.interact.FetchTransactionsInteract;
import com.fornaxwallet.app.interact.GenericWalletInteract;
import com.fornaxwallet.app.repository.TokenRepositoryType;
import com.fornaxwallet.app.repository.TransactionRepositoryType;
import com.fornaxwallet.app.repository.WalletRepositoryType;
import com.fornaxwallet.app.service.AnalyticsServiceType;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.GasService;
import com.fornaxwallet.app.service.KeyService;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.viewmodel.TransferTicketDetailViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class TransferTicketDetailModule {

    @Provides
    TransferTicketDetailViewModelFactory transferTicketDetailViewModelFactory(
            GenericWalletInteract genericWalletInteract,
            KeyService keyService,
            CreateTransactionInteract createTransactionInteract,
            FetchTransactionsInteract fetchTransactionsInteract,
            AssetDefinitionService assetDefinitionService,
            GasService gasService,
            AnalyticsServiceType analyticsService,
            TokensService tokensService) {
        return new TransferTicketDetailViewModelFactory(genericWalletInteract,
                keyService,
                createTransactionInteract,
                fetchTransactionsInteract,
                assetDefinitionService,
                gasService,
                analyticsService,
                tokensService);
    }

    @Provides
    GenericWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }

    @Provides
    CreateTransactionInteract provideCreateTransactionInteract(TransactionRepositoryType transactionRepository) {
        return new CreateTransactionInteract(transactionRepository);
    }
    @Provides
    FetchTransactionsInteract provideFetchTransactionsInteract(TransactionRepositoryType transactionRepository,
                                                               TokenRepositoryType tokenRepositoryType) {
        return new FetchTransactionsInteract(transactionRepository, tokenRepositoryType);
    }
}