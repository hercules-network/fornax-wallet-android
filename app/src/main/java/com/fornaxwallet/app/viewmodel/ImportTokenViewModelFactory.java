package com.fornaxwallet.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.fornaxwallet.app.interact.CreateTransactionInteract;
import com.fornaxwallet.app.interact.FetchTokensInteract;
import com.fornaxwallet.app.interact.FetchTransactionsInteract;
import com.fornaxwallet.app.interact.GenericWalletInteract;
import com.fornaxwallet.app.repository.EthereumNetworkRepositoryType;
import com.fornaxwallet.app.service.FornaxWalletService;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.GasService;
import com.fornaxwallet.app.service.KeyService;
import com.fornaxwallet.app.service.TokensService;

public class ImportTokenViewModelFactory implements ViewModelProvider.Factory {

    private final GenericWalletInteract genericWalletInteract;
    private final CreateTransactionInteract createTransactionInteract;
    private final FetchTokensInteract fetchTokensInteract;
    private final TokensService tokensService;
    private final FornaxWalletService fornaxWalletService;
    private final EthereumNetworkRepositoryType ethereumNetworkRepository;
    private final AssetDefinitionService assetDefinitionService;
    private final FetchTransactionsInteract fetchTransactionsInteract;
    private final GasService gasService;
    private final KeyService keyService;

    public ImportTokenViewModelFactory(GenericWalletInteract genericWalletInteract,
                                       CreateTransactionInteract createTransactionInteract,
                                       FetchTokensInteract fetchTokensInteract,
                                       TokensService tokensService,
                                       FornaxWalletService fornaxWalletService,
                                       EthereumNetworkRepositoryType ethereumNetworkRepository,
                                       AssetDefinitionService assetDefinitionService,
                                       FetchTransactionsInteract fetchTransactionsInteract,
                                       GasService gasService,
                                       KeyService keyService) {
        this.genericWalletInteract = genericWalletInteract;
        this.createTransactionInteract = createTransactionInteract;
        this.fetchTokensInteract = fetchTokensInteract;
        this.tokensService = tokensService;
        this.fornaxWalletService = fornaxWalletService;
        this.ethereumNetworkRepository = ethereumNetworkRepository;
        this.assetDefinitionService = assetDefinitionService;
        this.fetchTransactionsInteract = fetchTransactionsInteract;
        this.gasService = gasService;
        this.keyService = keyService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ImportTokenViewModel(genericWalletInteract, createTransactionInteract, fetchTokensInteract, tokensService, fornaxWalletService, ethereumNetworkRepository, assetDefinitionService, fetchTransactionsInteract, gasService, keyService);
    }
}