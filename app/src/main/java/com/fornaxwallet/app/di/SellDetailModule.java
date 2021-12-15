package com.fornaxwallet.app.di;

import com.fornaxwallet.app.interact.CreateTransactionInteract;
import com.fornaxwallet.app.interact.FindDefaultNetworkInteract;
import com.fornaxwallet.app.interact.GenericWalletInteract;
import com.fornaxwallet.app.repository.EthereumNetworkRepositoryType;
import com.fornaxwallet.app.repository.TransactionRepositoryType;
import com.fornaxwallet.app.repository.WalletRepositoryType;
import com.fornaxwallet.app.router.SellDetailRouter;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.KeyService;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.viewmodel.SellDetailModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class SellDetailModule {

    @Provides
    SellDetailModelFactory sellDetailModelFactory(
            FindDefaultNetworkInteract findDefaultNetworkInteract,
            TokensService tokensService,
            CreateTransactionInteract createTransactionInteract,
            SellDetailRouter sellDetailRouter,
            KeyService keyService,
            AssetDefinitionService assetDefinitionService) {
        return new SellDetailModelFactory(
                findDefaultNetworkInteract, tokensService, createTransactionInteract, sellDetailRouter, keyService, assetDefinitionService);
    }

    @Provides
    FindDefaultNetworkInteract provideFindDefaultNetworkInteract(
            EthereumNetworkRepositoryType networkRepository) {
        return new FindDefaultNetworkInteract(networkRepository);
    }

    @Provides
    CreateTransactionInteract provideCreateTransactionInteract(TransactionRepositoryType transactionRepository) {
        return new CreateTransactionInteract(transactionRepository);
    }

    @Provides
    SellDetailRouter provideSellDetailRouter() {
        return new SellDetailRouter();
    }
}