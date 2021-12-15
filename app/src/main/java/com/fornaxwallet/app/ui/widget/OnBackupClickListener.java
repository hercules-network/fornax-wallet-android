package com.fornaxwallet.app.ui.widget;

import android.view.View;

import com.fornaxwallet.app.entity.Wallet;

public interface OnBackupClickListener {
    void onBackupClick(View view, Wallet wallet);
}