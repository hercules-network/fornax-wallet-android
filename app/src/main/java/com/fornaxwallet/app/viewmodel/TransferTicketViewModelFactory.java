package com.fornaxwallet.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.fornaxwallet.app.interact.GenericWalletInteract;
import com.fornaxwallet.app.router.TransferTicketDetailRouter;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.TokensService;

public class TransferTicketViewModelFactory implements ViewModelProvider.Factory {

    private final TokensService tokensService;
    private final GenericWalletInteract genericWalletInteract;
    private final TransferTicketDetailRouter transferTicketDetailRouter;
    private final AssetDefinitionService assetDefinitionService;

    public TransferTicketViewModelFactory(
            TokensService tokensService,
            GenericWalletInteract genericWalletInteract,
            TransferTicketDetailRouter transferTicketDetailRouter,
            AssetDefinitionService assetDefinitionService) {
        this.tokensService = tokensService;
        this.genericWalletInteract = genericWalletInteract;
        this.transferTicketDetailRouter = transferTicketDetailRouter;
        this.assetDefinitionService = assetDefinitionService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TransferTicketViewModel(tokensService, genericWalletInteract, transferTicketDetailRouter, assetDefinitionService);
    }
}