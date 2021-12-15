package com.fornaxwallet.app.entity;

import com.fornaxwallet.app.entity.cryptokeys.KeyEncodingType;
import com.fornaxwallet.app.service.KeyService;

public interface ImportWalletCallback {
    void walletValidated(String address, KeyEncodingType type, KeyService.AuthenticationLevel level);
}