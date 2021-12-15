package com.fornaxwallet.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.fornaxwallet.app.repository.EthereumNetworkRepositoryType;
import com.fornaxwallet.app.repository.PreferenceRepositoryType;
import com.fornaxwallet.app.service.TokensService;

import io.reactivex.annotations.NonNull;

public class SelectNetworkViewModelFactory implements ViewModelProvider.Factory {

    private final EthereumNetworkRepositoryType networkRepository;
    private final TokensService tokensService;
    private final PreferenceRepositoryType preferenceRepository;

    public SelectNetworkViewModelFactory(EthereumNetworkRepositoryType networkRepository,
                                         TokensService tokensService,
                                         PreferenceRepositoryType preferenceRepository) {
        this.networkRepository = networkRepository;
        this.tokensService = tokensService;
        this.preferenceRepository = preferenceRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SelectNetworkViewModel(networkRepository, tokensService, preferenceRepository);
    }
}