package com.fornaxwallet.app.entity;

import com.fornaxwallet.app.web3.entity.Web3Transaction;

public interface SendTransactionInterface {
    void transactionSuccess(Web3Transaction web3Tx, String hashData);

    void transactionError(long callbackId, Throwable error);
}