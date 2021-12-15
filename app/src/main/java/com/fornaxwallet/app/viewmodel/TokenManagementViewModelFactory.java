package com.fornaxwallet.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.fornaxwallet.app.interact.ChangeTokenEnableInteract;
import com.fornaxwallet.app.repository.TokenRepositoryType;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.TokensService;

import io.reactivex.annotations.NonNull;

public class TokenManagementViewModelFactory implements ViewModelProvider.Factory {

    private final TokenRepositoryType tokenRepository;
    private final ChangeTokenEnableInteract changeTokenEnableInteract;
    private final AssetDefinitionService assetDefinitionService;
    private final TokensService tokensService;

    public TokenManagementViewModelFactory(TokenRepositoryType tokenRepository,
                                           ChangeTokenEnableInteract changeTokenEnableInteract,
                                           AssetDefinitionService assetDefinitionService,
                                           TokensService tokensService) {
        this.tokenRepository = tokenRepository;
        this.changeTokenEnableInteract = changeTokenEnableInteract;
        this.assetDefinitionService = assetDefinitionService;
        this.tokensService = tokensService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TokenManagementViewModel(
                tokenRepository,
                changeTokenEnableInteract,
                assetDefinitionService,
                tokensService
        );
    }
}