package com.fornaxwallet.app.entity.tokenscript;

public interface TokenScriptRenderCallback {
    void callToJSComplete(String function, String result);
}