package com.fornaxwallet.app.entity;

public interface PinAuthenticationCallbackInterface {
    void completeAuthentication(Operation taskCode);

    void failedAuthentication(Operation taskCode);
}