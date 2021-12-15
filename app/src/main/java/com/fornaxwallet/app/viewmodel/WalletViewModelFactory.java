package com.fornaxwallet.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.fornaxwallet.app.interact.ChangeTokenEnableInteract;
import com.fornaxwallet.app.interact.FetchTokensInteract;
import com.fornaxwallet.app.interact.GenericWalletInteract;
import com.fornaxwallet.app.repository.PreferenceRepositoryType;
import com.fornaxwallet.app.router.AssetDisplayRouter;
import com.fornaxwallet.app.router.TokenDetailRouter;
import com.fornaxwallet.app.router.MyAddressRouter;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.TokensService;

public class WalletViewModelFactory implements ViewModelProvider.Factory {
    private final FetchTokensInteract fetchTokensInteract;
    private final TokenDetailRouter tokenDetailRouter;
    private final AssetDisplayRouter assetDisplayRouter;
    private final GenericWalletInteract genericWalletInteract;
    private final AssetDefinitionService assetDefinitionService;
    private final TokensService tokensService;
    private final ChangeTokenEnableInteract changeTokenEnableInteract;
    private final MyAddressRouter myAddressRouter;
    private final PreferenceRepositoryType preferenceRepository;

    public WalletViewModelFactory(FetchTokensInteract fetchTokensInteract,
                                  TokenDetailRouter tokenDetailRouter,
                                  AssetDisplayRouter assetDisplayRouter,
                                  GenericWalletInteract genericWalletInteract,
                                  AssetDefinitionService assetDefinitionService,
                                  TokensService tokensService,
                                  ChangeTokenEnableInteract changeTokenEnableInteract,
                                  MyAddressRouter myAddressRouter,
                                  PreferenceRepositoryType preferenceRepository) {
        this.fetchTokensInteract = fetchTokensInteract;
        this.tokenDetailRouter = tokenDetailRouter;
        this.assetDisplayRouter = assetDisplayRouter;
        this.genericWalletInteract = genericWalletInteract;
        this.assetDefinitionService = assetDefinitionService;
        this.tokensService = tokensService;
        this.changeTokenEnableInteract = changeTokenEnableInteract;
        this.myAddressRouter = myAddressRouter;
        this.preferenceRepository = preferenceRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new WalletViewModel(
                fetchTokensInteract,
                tokenDetailRouter,
                assetDisplayRouter,
                genericWalletInteract,
                assetDefinitionService,
                tokensService,
                changeTokenEnableInteract,
                myAddressRouter,
                preferenceRepository);
    }
}