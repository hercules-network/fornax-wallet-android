package com.fornaxwallet.app.di;

import android.content.Context;

import com.fornaxwallet.app.repository.EthereumNetworkRepository;
import com.fornaxwallet.app.repository.EthereumNetworkRepositoryType;
import com.fornaxwallet.app.repository.OnRampRepository;
import com.fornaxwallet.app.repository.OnRampRepositoryType;
import com.fornaxwallet.app.repository.PreferenceRepositoryType;
import com.fornaxwallet.app.repository.SharedPreferenceRepository;
import com.fornaxwallet.app.repository.TokenLocalSource;
import com.fornaxwallet.app.repository.TokenRepository;
import com.fornaxwallet.app.repository.TokenRepositoryType;
import com.fornaxwallet.app.repository.TokensRealmSource;
import com.fornaxwallet.app.repository.TransactionLocalSource;
import com.fornaxwallet.app.repository.TransactionRepository;
import com.fornaxwallet.app.repository.TransactionRepositoryType;
import com.fornaxwallet.app.repository.TransactionsRealmCache;
import com.fornaxwallet.app.repository.WalletDataRealmSource;
import com.fornaxwallet.app.repository.WalletRepository;
import com.fornaxwallet.app.repository.WalletRepositoryType;
import com.fornaxwallet.app.service.AccountKeystoreService;
import com.fornaxwallet.app.service.FornaxWalletService;
import com.fornaxwallet.app.service.AnalyticsService;
import com.fornaxwallet.app.service.AnalyticsServiceType;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.GasService;
import com.fornaxwallet.app.service.KeyService;
import com.fornaxwallet.app.service.KeystoreAccountService;
import com.fornaxwallet.app.service.NotificationService;
import com.fornaxwallet.app.service.OpenSeaService;
import com.fornaxwallet.app.service.RealmManager;
import com.fornaxwallet.app.service.TickerService;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.service.TransactionsNetworkClient;
import com.fornaxwallet.app.service.TransactionsNetworkClientType;
import com.fornaxwallet.app.service.TransactionsService;
import com.google.gson.Gson;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

import static com.fornaxwallet.app.service.KeystoreAccountService.KEYSTORE_FOLDER;

@Module
public class RepositoriesModule {
    @Singleton
    @Provides
    PreferenceRepositoryType providePreferenceRepository(Context context) {
        return new SharedPreferenceRepository(context);
    }

    @Singleton
    @Provides
    AccountKeystoreService provideAccountKeyStoreService(Context context, KeyService keyService) {
        File file = new File(context.getFilesDir(), KEYSTORE_FOLDER);
        return new KeystoreAccountService(file, context.getFilesDir(), keyService);
    }

    @Singleton
    @Provides
    TickerService provideTickerService(OkHttpClient httpClient, PreferenceRepositoryType sharedPrefs, TokenLocalSource localSource) {
        return new TickerService(httpClient, sharedPrefs, localSource);
    }

    @Singleton
    @Provides
    EthereumNetworkRepositoryType provideEthereumNetworkRepository(
            PreferenceRepositoryType preferenceRepository,
            Context context) {
        return new EthereumNetworkRepository(preferenceRepository, context);
    }

    @Singleton
    @Provides
    WalletRepositoryType provideWalletRepository(
            PreferenceRepositoryType preferenceRepositoryType,
            AccountKeystoreService accountKeystoreService,
            EthereumNetworkRepositoryType networkRepository,
            WalletDataRealmSource walletDataRealmSource,
            KeyService keyService) {
        return new WalletRepository(
                preferenceRepositoryType, accountKeystoreService, networkRepository, walletDataRealmSource, keyService);
    }

    @Singleton
    @Provides
    TransactionRepositoryType provideTransactionRepository(
            EthereumNetworkRepositoryType networkRepository,
            AccountKeystoreService accountKeystoreService,
            TransactionLocalSource inDiskCache,
            TransactionsService transactionsService) {
        return new TransactionRepository(
                networkRepository,
                accountKeystoreService,
                inDiskCache,
                transactionsService);
    }

    @Singleton
    @Provides
    OnRampRepositoryType provideOnRampRepository(Context context, AnalyticsServiceType analyticsServiceType) {
        return new OnRampRepository(context, analyticsServiceType);
    }

    @Singleton
    @Provides
    TransactionLocalSource provideTransactionInDiskCache(RealmManager realmManager) {
        return new TransactionsRealmCache(realmManager);
    }

    @Singleton
    @Provides
    TransactionsNetworkClientType provideBlockExplorerClient(
            OkHttpClient httpClient,
            Gson gson,
            RealmManager realmManager) {
        return new TransactionsNetworkClient(httpClient, gson, realmManager);
    }

    @Singleton
    @Provides
    TokenRepositoryType provideTokenRepository(
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            TokenLocalSource tokenLocalSource,
            OkHttpClient httpClient,
            Context context,
            TickerService tickerService) {
        return new TokenRepository(
                ethereumNetworkRepository,
                tokenLocalSource,
                httpClient,
                context,
                tickerService);
    }

    @Singleton
    @Provides
    TokenLocalSource provideRealmTokenSource(RealmManager realmManager, EthereumNetworkRepositoryType ethereumNetworkRepository) {
        return new TokensRealmSource(realmManager, ethereumNetworkRepository);
    }

    @Singleton
    @Provides
    WalletDataRealmSource provideRealmWalletDataSource(RealmManager realmManager) {
        return new WalletDataRealmSource(realmManager);
    }

    @Singleton
    @Provides
    TokensService provideTokensService(EthereumNetworkRepositoryType ethereumNetworkRepository,
                                       TokenRepositoryType tokenRepository,
                                       TickerService tickerService,
                                       OpenSeaService openseaService,
                                       AnalyticsServiceType analyticsService) {
        return new TokensService(ethereumNetworkRepository, tokenRepository, tickerService, openseaService, analyticsService);
    }

    @Singleton
    @Provides
    TransactionsService provideTransactionsService(TokensService tokensService,
                                                   EthereumNetworkRepositoryType ethereumNetworkRepositoryType,
                                                   TransactionsNetworkClientType transactionsNetworkClientType,
                                                   TransactionLocalSource transactionLocalSource) {
        return new TransactionsService(tokensService, ethereumNetworkRepositoryType, transactionsNetworkClientType, transactionLocalSource);
    }

    @Singleton
    @Provides
    GasService provideGasService2(EthereumNetworkRepositoryType ethereumNetworkRepository, OkHttpClient client, RealmManager realmManager) {
        return new GasService(ethereumNetworkRepository, client, realmManager);
    }

    @Singleton
    @Provides
    OpenSeaService provideOpenseaService() {
        return new OpenSeaService();
    }

    @Singleton
    @Provides
    FornaxWalletService provideFeemasterService(OkHttpClient okHttpClient,
                                               TransactionRepositoryType transactionRepository,
                                               Gson gson) {
        return new FornaxWalletService(okHttpClient, transactionRepository, gson);
    }

    @Singleton
    @Provides
    NotificationService provideNotificationService(Context ctx) {
        return new NotificationService(ctx);
    }

    @Singleton
    @Provides
    AssetDefinitionService provideAssetDefinitionService(OkHttpClient okHttpClient, Context ctx, NotificationService notificationService, RealmManager realmManager,
                                                         TokensService tokensService, TokenLocalSource tls, TransactionRepositoryType trt,
                                                         FornaxWalletService fornaxService) {
        return new AssetDefinitionService(okHttpClient, ctx, notificationService, realmManager, tokensService, tls, trt, fornaxService);
    }

    @Singleton
    @Provides
    KeyService provideKeyService(Context ctx) {
        return new KeyService(ctx);
    }

    @Singleton
    @Provides
    AnalyticsServiceType provideAnalyticsService(Context ctx) {
        return new AnalyticsService(ctx);
    }
}