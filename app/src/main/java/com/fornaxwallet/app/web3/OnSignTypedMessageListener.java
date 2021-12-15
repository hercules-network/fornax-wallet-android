package com.fornaxwallet.app.web3;

import com.fornaxwallet.token.entity.EthereumTypedMessage;

public interface OnSignTypedMessageListener {
    void onSignTypedMessage(EthereumTypedMessage message);
}