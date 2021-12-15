package com.fornaxwallet.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.fornaxwallet.app.interact.CreateTransactionInteract;
import com.fornaxwallet.app.interact.FetchTransactionsInteract;
import com.fornaxwallet.app.repository.EthereumNetworkRepositoryType;
import com.fornaxwallet.app.repository.PreferenceRepositoryType;
import com.fornaxwallet.app.router.MyAddressRouter;
import com.fornaxwallet.app.service.AnalyticsServiceType;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.GasService;
import com.fornaxwallet.app.service.KeyService;
import com.fornaxwallet.app.service.TokensService;

import io.reactivex.annotations.NonNull;

public class SendViewModelFactory implements ViewModelProvider.Factory {

    private final MyAddressRouter myAddressRouter;
    private final EthereumNetworkRepositoryType networkRepository;
    private final TokensService tokensService;
    private final FetchTransactionsInteract fetchTransactionsInteract;
    private final CreateTransactionInteract createTransactionInteract;
    private final GasService gasService;
    private final AssetDefinitionService assetDefinitionService;
    private final KeyService keyService;
    private final AnalyticsServiceType analyticsService;

    public SendViewModelFactory(MyAddressRouter myAddressRouter,
                                EthereumNetworkRepositoryType networkRepository,
                                TokensService tokensService,
                                FetchTransactionsInteract fetchTransactionsInteract,
                                CreateTransactionInteract createTransactionInteract,
                                GasService gasService,
                                AssetDefinitionService assetDefinitionService,
                                KeyService keyService,
                                AnalyticsServiceType analyticsService) {
        this.myAddressRouter = myAddressRouter;
        this.networkRepository = networkRepository;
        this.tokensService = tokensService;
        this.gasService = gasService;
        this.fetchTransactionsInteract = fetchTransactionsInteract;
        this.assetDefinitionService = assetDefinitionService;
        this.keyService = keyService;
        this.createTransactionInteract = createTransactionInteract;
        this.analyticsService = analyticsService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SendViewModel(myAddressRouter, networkRepository, tokensService,
                fetchTransactionsInteract, createTransactionInteract, gasService,
                assetDefinitionService, keyService, analyticsService);
    }
}