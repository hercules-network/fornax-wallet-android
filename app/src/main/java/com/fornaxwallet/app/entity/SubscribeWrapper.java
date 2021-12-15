package com.fornaxwallet.app.entity;

import org.web3j.protocol.core.methods.response.Transaction;

public interface SubscribeWrapper {
    void scanReturn(Transaction tx);
}