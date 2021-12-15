package com.fornaxwallet.app.web3;

import com.fornaxwallet.app.web3.entity.Web3Call;

public interface OnEthCallListener {
    void onEthCall(Web3Call txdata);
}
