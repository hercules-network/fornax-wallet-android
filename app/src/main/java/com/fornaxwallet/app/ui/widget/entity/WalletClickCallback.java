package com.fornaxwallet.app.ui.widget.entity;

import com.fornaxwallet.app.entity.Wallet;

public interface WalletClickCallback {
    void onWalletClicked(Wallet wallet);
    void ensAvatar(Wallet wallet);
}
