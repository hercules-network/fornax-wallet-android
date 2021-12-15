package com.fornaxwallet.app.di;

import com.fornaxwallet.app.repository.CurrencyRepository;
import com.fornaxwallet.app.repository.CurrencyRepositoryType;
import com.fornaxwallet.app.repository.LocaleRepository;
import com.fornaxwallet.app.repository.LocaleRepositoryType;
import com.fornaxwallet.app.repository.PreferenceRepositoryType;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.TransactionsService;
import com.fornaxwallet.app.viewmodel.AdvancedSettingsViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class AdvancedSettingsModule {
    @Provides
    AdvancedSettingsViewModelFactory provideAdvancedSettingsViewModelFactory(
            LocaleRepositoryType localeRepository,
            CurrencyRepositoryType currencyRepository,
            AssetDefinitionService assetDefinitionService,
            PreferenceRepositoryType preferenceRepository,
            TransactionsService transactionsService
    ) {
        return new AdvancedSettingsViewModelFactory(
                localeRepository,
                currencyRepository,
                assetDefinitionService,
                preferenceRepository,
                transactionsService);
    }

    @Provides
    LocaleRepositoryType provideLocaleRepository(PreferenceRepositoryType preferenceRepository) {
        return new LocaleRepository(preferenceRepository);
    }

    @Provides
    CurrencyRepositoryType provideCurrencyRepository(PreferenceRepositoryType preferenceRepository) {
        return new CurrencyRepository(preferenceRepository);
    }
}