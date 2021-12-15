package com.fornaxwallet.app.entity;

public interface FragmentMessenger {
    void tokenScriptError(String message);

    void updateReady(int versionUpdate);
}