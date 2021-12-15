package com.fornaxwallet.app.walletconnect.entity;

import com.fornaxwallet.app.entity.walletconnect.WCRequest;

public interface WalletConnectCallback {
    boolean receiveRequest(WCRequest request);
}