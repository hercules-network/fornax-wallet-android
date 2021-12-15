package com.fornaxwallet.app.interact;

import com.fornaxwallet.app.repository.WalletRepositoryType;
import com.fornaxwallet.app.entity.Wallet;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SetDefaultWalletInteract {

    private WalletRepositoryType accountRepository;

    public SetDefaultWalletInteract(WalletRepositoryType walletRepositoryType) {
        this.accountRepository = walletRepositoryType;
    }

    public Completable set(Wallet wallet) {
        return accountRepository
                .setDefaultWallet(wallet)
                .observeOn(AndroidSchedulers.mainThread());
    }
}