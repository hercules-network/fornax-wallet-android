package com.fornaxwallet.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.fornaxwallet.app.interact.CreateTransactionInteract;
import com.fornaxwallet.app.interact.FetchTransactionsInteract;
import com.fornaxwallet.app.interact.GenericWalletInteract;
import com.fornaxwallet.app.service.AnalyticsServiceType;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.GasService;
import com.fornaxwallet.app.service.KeyService;
import com.fornaxwallet.app.service.TokensService;

public class TransferTicketDetailViewModelFactory implements ViewModelProvider.Factory {

    private final GenericWalletInteract genericWalletInteract;
    private final KeyService keyService;
    private final CreateTransactionInteract createTransactionInteract;
    private final FetchTransactionsInteract fetchTransactionsInteract;
    private final AssetDefinitionService assetDefinitionService;
    private final GasService gasService;
    private final TokensService tokensService;
    private final AnalyticsServiceType analyticsService;


    public TransferTicketDetailViewModelFactory(GenericWalletInteract genericWalletInteract,
                                                KeyService keyService,
                                                CreateTransactionInteract createTransactionInteract,
                                                FetchTransactionsInteract fetchTransactionsInteract,
                                                AssetDefinitionService assetDefinitionService,
                                                GasService gasService,
                                                AnalyticsServiceType analyticsService,
                                                TokensService tokensService) {
        this.genericWalletInteract = genericWalletInteract;
        this.keyService = keyService;
        this.createTransactionInteract = createTransactionInteract;
        this.fetchTransactionsInteract = fetchTransactionsInteract;
        this.assetDefinitionService = assetDefinitionService;
        this.gasService = gasService;
        this.analyticsService = analyticsService;
        this.tokensService = tokensService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TransferTicketDetailViewModel(genericWalletInteract, keyService, createTransactionInteract, fetchTransactionsInteract,
                assetDefinitionService, gasService, analyticsService, tokensService);
    }
}