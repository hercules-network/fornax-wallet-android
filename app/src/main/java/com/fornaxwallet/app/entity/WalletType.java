package com.fornaxwallet.app.entity;

public enum WalletType {
    NOT_DEFINED,
    KEYSTORE,
    HDKEY,
    WATCH,
    TEXT_MARKER, // used as a separator in wallet view
    KEYSTORE_LEGACY  // to support keys created from old wallets
}