package com.fornaxwallet.app.entity;

public interface AuthenticationCallback {
    void authenticatePass(Operation callbackId);

    void authenticateFail(String fail, AuthenticationFailType failType, Operation callbackId);

    void legacyAuthRequired(Operation callbackId, String dialogTitle, String desc);
}