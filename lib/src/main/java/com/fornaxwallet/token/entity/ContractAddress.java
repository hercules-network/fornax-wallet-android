package com.fornaxwallet.token.entity;

public class ContractAddress {
    public final long chainId;
    public final String address;

    public ContractAddress(long chainId, String address) {
        this.chainId = chainId;
        this.address = address;
    }

    //TODO: Only allow FunctionDefinition to have one contract
    public ContractAddress(FunctionDefinition fd) {
        this.chainId = fd.contract.addresses.keySet().iterator().next();
        this.address = fd.contract.addresses.get(chainId).iterator().next();
    }
}
