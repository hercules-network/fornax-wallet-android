package com.fornaxwallet.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.fornaxwallet.app.interact.CreateTransactionInteract;
import com.fornaxwallet.app.interact.FetchTransactionsInteract;
import com.fornaxwallet.app.interact.FindDefaultNetworkInteract;
import com.fornaxwallet.app.repository.TokenRepositoryType;
import com.fornaxwallet.app.router.ExternalBrowserRouter;
import com.fornaxwallet.app.service.AnalyticsServiceType;
import com.fornaxwallet.app.service.GasService;
import com.fornaxwallet.app.service.KeyService;
import com.fornaxwallet.app.service.TokensService;

public class TransactionDetailViewModelFactory implements ViewModelProvider.Factory {

    private final FindDefaultNetworkInteract findDefaultNetworkInteract;
    private final ExternalBrowserRouter externalBrowserRouter;
    private final TokensService tokensService;
    private final TokenRepositoryType tokenRepository;
    private final FetchTransactionsInteract fetchTransactionsInteract;
    private final KeyService keyService;
    private final GasService gasService;
    private final CreateTransactionInteract createTransactionInteract;
    private final AnalyticsServiceType analyticsService;

    public TransactionDetailViewModelFactory(
            FindDefaultNetworkInteract findDefaultNetworkInteract,
            ExternalBrowserRouter externalBrowserRouter,
            TokenRepositoryType tokenRepository,
            TokensService tokensService,
            FetchTransactionsInteract fetchTransactionsInteract,
            KeyService keyService,
            GasService gasService,
            CreateTransactionInteract createTransactionInteract,
            AnalyticsServiceType analyticsService) {
        this.findDefaultNetworkInteract = findDefaultNetworkInteract;
        this.externalBrowserRouter = externalBrowserRouter;
        this.tokensService = tokensService;
        this.tokenRepository = tokenRepository;
        this.fetchTransactionsInteract = fetchTransactionsInteract;
        this.keyService = keyService;
        this.gasService = gasService;
        this.createTransactionInteract = createTransactionInteract;
        this.analyticsService = analyticsService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TransactionDetailViewModel(
                findDefaultNetworkInteract,
                externalBrowserRouter,
                tokenRepository,
                tokensService,
                fetchTransactionsInteract,
                keyService,
                gasService,
                createTransactionInteract,
                analyticsService);
    }
}