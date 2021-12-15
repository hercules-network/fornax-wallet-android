package com.fornaxwallet.app.di;

import com.fornaxwallet.app.interact.FetchTransactionsInteract;
import com.fornaxwallet.app.repository.TokenRepositoryType;
import com.fornaxwallet.app.repository.TransactionRepositoryType;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.viewmodel.Erc1155AssetSelectViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class Erc1155AssetSelectModule {
    @Provides
    Erc1155AssetSelectViewModelFactory provideErc1155ViewModelFactory(FetchTransactionsInteract fetchTransactionsInteract,
                                                                      AssetDefinitionService assetDefinitionService,
                                                                      TokensService tokensService) {
        return new Erc1155AssetSelectViewModelFactory(
                fetchTransactionsInteract,
                assetDefinitionService,
                tokensService);
    }

    @Provides
    FetchTransactionsInteract provideFetchTransactionsInteract(TransactionRepositoryType transactionRepositoryType,
                                                               TokenRepositoryType tokenRepositoryType) {
        return new FetchTransactionsInteract(transactionRepositoryType, tokenRepositoryType);
    }
}