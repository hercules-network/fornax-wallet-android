package com.fornaxwallet.token.entity;

// A param to pass into a smart contract function call
public class MethodArg {
    public String parameterType; //type of param eg uint256, address etc
    public TokenscriptElement element; // contains either the value or reference to the value

    public boolean isTokenId() {
        return element.isToken();
    }

    public int getTokenIndex() {
        return element.getTokenIndex();
    }
}