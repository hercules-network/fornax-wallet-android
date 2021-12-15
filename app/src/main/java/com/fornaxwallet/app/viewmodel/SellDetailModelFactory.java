package com.fornaxwallet.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.fornaxwallet.app.interact.CreateTransactionInteract;
import com.fornaxwallet.app.interact.FindDefaultNetworkInteract;
import com.fornaxwallet.app.interact.GenericWalletInteract;
import com.fornaxwallet.app.router.SellDetailRouter;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.KeyService;
import com.fornaxwallet.app.service.TokensService;

public class SellDetailModelFactory implements ViewModelProvider.Factory {

    private FindDefaultNetworkInteract findDefaultNetworkInteract;
    private TokensService tokensService;
    private CreateTransactionInteract createTransactionInteract;
    private SellDetailRouter sellDetailRouter;
    private KeyService keyService;
    private AssetDefinitionService assetDefinitionService;

    public SellDetailModelFactory(FindDefaultNetworkInteract findDefaultNetworkInteract,
                                  TokensService tokensService,
                                  CreateTransactionInteract createTransactionInteract,
                                  SellDetailRouter sellDetailRouter,
                                  KeyService keyService,
                                  AssetDefinitionService assetDefinitionService) {
        this.findDefaultNetworkInteract = findDefaultNetworkInteract;
        this.tokensService = tokensService;
        this.createTransactionInteract = createTransactionInteract;
        this.sellDetailRouter = sellDetailRouter;
        this.keyService = keyService;
        this.assetDefinitionService = assetDefinitionService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SellDetailViewModel(findDefaultNetworkInteract, tokensService, createTransactionInteract, sellDetailRouter, keyService, assetDefinitionService);
    }
}