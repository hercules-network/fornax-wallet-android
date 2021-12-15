package com.fornaxwallet.app.entity;

public interface BaseViewCallback {
    void queueUpdate(int complete);

    void pushToast(String message);
}