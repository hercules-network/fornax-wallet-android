package com.fornaxwallet.app.di;

import com.fornaxwallet.app.interact.GenericWalletInteract;
import com.fornaxwallet.app.repository.WalletRepositoryType;
import com.fornaxwallet.app.router.TransferTicketDetailRouter;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.viewmodel.TransferTicketViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class TransferTicketModule
{
    @Provides
    TransferTicketViewModelFactory transferTicketViewModelFactory(
            TokensService tokensService,
            GenericWalletInteract genericWalletInteract,
            TransferTicketDetailRouter transferTicketDetailRouter,
            AssetDefinitionService assetDefinitionService) {
        return new TransferTicketViewModelFactory(
                tokensService, genericWalletInteract, transferTicketDetailRouter, assetDefinitionService);
    }

    @Provides
    GenericWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }

    @Provides
    TransferTicketDetailRouter provideTransferTicketDetailRouter() {
        return new TransferTicketDetailRouter();
    }
}