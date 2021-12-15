package com.fornaxwallet.app.entity;

public class UnknownToken {
    public long chainId;
    public String name;
    public String address;
    public boolean isPopular;

    public UnknownToken(long chainId, String address, boolean isPopular) {
        this.chainId = chainId;
        this.address = address;
        this.isPopular = isPopular;
    }
}