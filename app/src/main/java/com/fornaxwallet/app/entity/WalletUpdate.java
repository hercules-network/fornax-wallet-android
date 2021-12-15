package com.fornaxwallet.app.entity;

import java.util.Map;

public class WalletUpdate {
    public long lastBlock;
    public Map<String, Wallet> wallets;

    public WalletUpdate() {
        lastBlock = -1L;
    }
}