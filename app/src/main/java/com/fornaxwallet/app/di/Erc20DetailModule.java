package com.fornaxwallet.app.di;

import com.fornaxwallet.app.interact.FetchTransactionsInteract;
import com.fornaxwallet.app.repository.OnRampRepositoryType;
import com.fornaxwallet.app.repository.TokenRepositoryType;
import com.fornaxwallet.app.repository.TransactionRepositoryType;
import com.fornaxwallet.app.router.MyAddressRouter;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.viewmodel.Erc20DetailViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class Erc20DetailModule {
    @Provides
    Erc20DetailViewModelFactory provideErc20DetailViewModelFactory(MyAddressRouter myAddressRouter,
                                                                   FetchTransactionsInteract fetchTransactionsInteract,
                                                                   AssetDefinitionService assetDefinitionService,
                                                                   TokensService tokensService,
                                                                   OnRampRepositoryType onRampRepository) {
        return new Erc20DetailViewModelFactory(myAddressRouter,
                fetchTransactionsInteract,
                assetDefinitionService,
                tokensService,
                onRampRepository);
    }

    @Provides
    MyAddressRouter provideMyAddressRouter() {
        return new MyAddressRouter();
    }

    @Provides
    FetchTransactionsInteract provideFetchTransactionsInteract(TransactionRepositoryType transactionRepositoryType,
                                                               TokenRepositoryType tokenRepositoryType) {
        return new FetchTransactionsInteract(transactionRepositoryType, tokenRepositoryType);
    }
}