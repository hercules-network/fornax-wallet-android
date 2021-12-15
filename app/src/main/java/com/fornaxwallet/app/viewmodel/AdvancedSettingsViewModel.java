package com.fornaxwallet.app.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import com.fornaxwallet.app.entity.CurrencyItem;
import com.fornaxwallet.app.entity.LocaleItem;
import com.fornaxwallet.app.repository.CurrencyRepositoryType;
import com.fornaxwallet.app.repository.LocaleRepositoryType;
import com.fornaxwallet.app.repository.PreferenceRepositoryType;
import com.fornaxwallet.app.service.AssetDefinitionService;
import com.fornaxwallet.app.service.TokensService;
import com.fornaxwallet.app.service.TransactionsService;
import com.fornaxwallet.app.ui.HomeActivity;
import com.fornaxwallet.app.util.LocaleUtils;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.Single;

public class AdvancedSettingsViewModel extends BaseViewModel {
    private final LocaleRepositoryType localeRepository;
    private final CurrencyRepositoryType currencyRepository;
    private final AssetDefinitionService assetDefinitionService;
    private final PreferenceRepositoryType preferenceRepository;
    private final TransactionsService transactionsService;

    AdvancedSettingsViewModel(
            LocaleRepositoryType localeRepository,
            CurrencyRepositoryType currencyRepository,
            AssetDefinitionService assetDefinitionService,
            PreferenceRepositoryType preferenceRepository,
            TransactionsService transactionsService) {
        this.localeRepository = localeRepository;
        this.currencyRepository = currencyRepository;
        this.assetDefinitionService = assetDefinitionService;
        this.preferenceRepository = preferenceRepository;
        this.transactionsService = transactionsService;
    }

    public ArrayList<LocaleItem> getLocaleList(Context context) {
        return localeRepository.getLocaleList(context);
    }

    public void setLocale(Context activity) {
        String currentLocale = localeRepository.getActiveLocale();
        LocaleUtils.setLocale(activity, currentLocale);
    }

    public void updateLocale(String newLocale, Context context) {
        localeRepository.setUserPreferenceLocale(newLocale);
        localeRepository.setLocale(context, newLocale);
        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public String getDefaultCurrency() {
        return currencyRepository.getDefaultCurrency();
    }

    public ArrayList<CurrencyItem> getCurrencyList() {
        return currencyRepository.getCurrencyList();
    }

    public Single<Boolean> updateCurrency(String currencyCode) {
        currencyRepository.setDefaultCurrency(currencyCode);
        //delete tickers from realm
        return transactionsService.wipeTickerData();
    }

    public boolean createDirectory() {
        //create XML repository directory
        File directory = new File(
                Environment.getExternalStorageDirectory()
                        + File.separator + HomeViewModel.FORNAXWALLET_DIR);

        if (!directory.exists()) {
            return directory.mkdir();
        } else {
            return directory.exists();
        }
    }

    public void startFileListeners() {
        assetDefinitionService.startFornaxWalletListener();
    }

    public String getActiveLocale() {
        return localeRepository.getActiveLocale();
    }

    public void setFullScreenState(boolean state) {
        preferenceRepository.setFullScreenState(state);
    }

    public boolean getFullScreenState() {
        return preferenceRepository.getFullScreenState();
    }

    public void blankFilterSettings() {
        preferenceRepository.blankHasSetNetworkFilters();
    }

    public Single<Boolean> resetTokenData() {
        return transactionsService.wipeDataForWallet();
    }
}