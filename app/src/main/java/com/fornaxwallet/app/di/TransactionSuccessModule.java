package com.fornaxwallet.app.di;

import com.fornaxwallet.app.repository.PreferenceRepositoryType;
import com.fornaxwallet.app.viewmodel.TransactionSuccessViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class TransactionSuccessModule {

    @Provides
    TransactionSuccessViewModelFactory provideTransactionSuccessViewModelFactory(PreferenceRepositoryType preferenceRepository) {
        return new TransactionSuccessViewModelFactory(
                preferenceRepository);
    }
}
