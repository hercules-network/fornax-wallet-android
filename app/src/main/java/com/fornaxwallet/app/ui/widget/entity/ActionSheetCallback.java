package com.fornaxwallet.app.ui.widget.entity;

import android.app.Activity;

import com.fornaxwallet.app.entity.SignAuthenticationCallback;
import com.fornaxwallet.app.web3.entity.Web3Transaction;

public interface ActionSheetCallback {
    void getAuthorisation(SignAuthenticationCallback callback);
    void sendTransaction(Web3Transaction tx);
    void dismissed(String txHash, long callbackId, boolean actionCompleted);
    void notifyConfirm(String mode);
    default void signTransaction(Web3Transaction tx) { }; // only WalletConnect uses this so far
}