package com.fornaxwallet.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.fornaxwallet.app.service.TokensService;

public class GasSettingsViewModelFactory implements ViewModelProvider.Factory {

    private final TokensService tokensService;

    public GasSettingsViewModelFactory(TokensService svs) {
        this.tokensService = svs;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GasSettingsViewModel(tokensService);
    }
}