package com.fornaxwallet.app.web3.entity;

import org.web3j.protocol.core.DefaultBlockParameter;

public class Web3Call {
    public final Address to;
    public final DefaultBlockParameter blockParam;
    public final String payload;
    public final long leafPosition;

    public Web3Call(
            Address to,
            DefaultBlockParameter blockParam,
            String payload,
            long leafPosition) {
        this.to = to;
        this.blockParam = blockParam;
        this.payload = payload;
        this.leafPosition = leafPosition;
    }
}
