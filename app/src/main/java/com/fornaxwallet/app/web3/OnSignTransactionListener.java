package com.fornaxwallet.app.web3;

import com.fornaxwallet.app.web3.entity.Web3Transaction;

public interface OnSignTransactionListener {
    void onSignTransaction(Web3Transaction transaction, String url);
}