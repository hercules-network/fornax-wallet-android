package com.fornaxwallet.token.entity;

import java.math.BigInteger;

public class MessageData {
    public BigInteger priceWei;
    public int[] tickets;
    public byte[] signature = new byte[65];
}
