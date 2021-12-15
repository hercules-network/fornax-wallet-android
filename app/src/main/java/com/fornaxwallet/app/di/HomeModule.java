package com.fornaxwallet.app.di;

import com.fornaxwallet.app.interact.FetchWalletsInteract;
import com.fornaxwallet.app.interact.GenericWalletInteract;
import com.fornaxwallet.app.repository.CurrencyRepository;
import com.fornaxwallet.app.repository.CurrencyRepositoryType;
import com.fornaxwallet.app.repository.EthereumNetworkRepositoryType;
import com.fornaxwallet.app.repository.LocaleRepository;
import com.fornaxwallet.app.repository.LocaleRepositoryType;
import com.fornaxwallet.app.repository.PreferenceRepositoryType;
import com.fornaxwallet.app.repository.WalletRepositoryType;
import com.fornaxwallet.app.router.ExternalBrowserRouter;
import com.fornaxwallet.app.router.ImportTokenRouter;
import com.fornaxwallet.app.router.MyAddressRouter;
import com.fornaxwallet.app.service.AnalyticsServiceType;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.TickerService;
import com.fornaxwallet.app.service.TransactionsService;
import com.fornaxwallet.app.viewmodel.HomeViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class HomeModule {
    @Provides
    HomeViewModelFactory provideHomeViewModelFactory(
            PreferenceRepositoryType preferenceRepository,
            LocaleRepositoryType localeRepository,
            ImportTokenRouter importTokenRouter,
            AssetDefinitionService assetDefinitionService,
            GenericWalletInteract genericWalletInteract,
            FetchWalletsInteract fetchWalletsInteract,
            CurrencyRepositoryType currencyRepository,
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            MyAddressRouter myAddressRouter,
            TransactionsService transactionsService,
            TickerService tickerService,
            AnalyticsServiceType analyticsService,
            ExternalBrowserRouter externalBrowserRouter) {
        return new HomeViewModelFactory(
                preferenceRepository,
                localeRepository,
                importTokenRouter,
                assetDefinitionService,
                genericWalletInteract,
                fetchWalletsInteract,
                currencyRepository,
                ethereumNetworkRepository,
                myAddressRouter,
                transactionsService,
                tickerService,
                analyticsService,
                externalBrowserRouter);
    }

    @Provides
    LocaleRepositoryType provideLocaleRepository(PreferenceRepositoryType preferenceRepository) {
        return new LocaleRepository(preferenceRepository);
    }

    @Provides
    ImportTokenRouter providesImportTokenRouter() {
        return new ImportTokenRouter();
    }

    @Provides
    GenericWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }

    @Provides
    FetchWalletsInteract provideFetchWalletInteract(WalletRepositoryType walletRepository) {
        return new FetchWalletsInteract(walletRepository);
    }

    @Provides
    CurrencyRepositoryType provideCurrencyRepository(PreferenceRepositoryType preferenceRepository) {
        return new CurrencyRepository(preferenceRepository);
    }

    @Provides
    MyAddressRouter provideMyAddressRouter() {
        return new MyAddressRouter();
    }

    @Provides
    ExternalBrowserRouter provideBrowserRouter() {
        return new ExternalBrowserRouter();
    }
}