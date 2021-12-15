package com.fornaxwallet.app.entity;

import com.fornaxwallet.app.entity.tokens.Token;

public interface BuyCryptoInterface {
    void handleBuyFunction(Token token);
    void handleGeneratePaymentRequest(Token token);
}