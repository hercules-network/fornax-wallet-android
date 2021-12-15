package com.fornaxwallet.app.entity;

public interface ENSCallback {
    void ENSComplete();

    void displayCheckingDialog(boolean shouldShow);

    void ENSResolved(String address, String ens);

    void ENSName(String name);
}