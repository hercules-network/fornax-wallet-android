package com.fornaxwallet.app.web3;

import com.fornaxwallet.token.entity.EthereumMessage;

public interface OnSignMessageListener {
    void onSignMessage(EthereumMessage message);
}