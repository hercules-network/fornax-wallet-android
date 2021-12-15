package com.fornaxwallet.app.viewmodel;

import android.app.Activity;

import com.fornaxwallet.app.repository.PreferenceRepositoryType;
import com.fornaxwallet.app.util.RateApp;

public class TransactionSuccessViewModel extends BaseViewModel {

    private final PreferenceRepositoryType preferenceRepository;

    public TransactionSuccessViewModel(
            PreferenceRepositoryType preferenceRepository
    ) {
        this.preferenceRepository = preferenceRepository;
    }

    public void tryToShowRateAppDialog(Activity context) {
        RateApp.showRateTheApp(context, preferenceRepository, true);
    }
}