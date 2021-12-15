package com.fornaxwallet.app.di;

import com.fornaxwallet.app.interact.FetchTransactionsInteract;
import com.fornaxwallet.app.repository.TokenRepositoryType;
import com.fornaxwallet.app.repository.TransactionRepositoryType;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.viewmodel.TokenActivityViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class TokenActivityModule {
    @Provides
    TokenActivityViewModelFactory provideTokenActivityViewModelFactory(AssetDefinitionService assetDefinitionService,
                                                                       FetchTransactionsInteract fetchTransactionsInteract,
                                                                       TokensService tokensService) {
        return new TokenActivityViewModelFactory(
                assetDefinitionService,
                fetchTransactionsInteract,
                tokensService);
    }

    @Provides
    FetchTransactionsInteract provideFetchTransactionsInteract(TransactionRepositoryType transactionRepositoryType,
                                                               TokenRepositoryType tokenRepositoryType) {
        return new FetchTransactionsInteract(transactionRepositoryType, tokenRepositoryType);
    }
}