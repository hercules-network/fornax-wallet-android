package com.fornaxwallet.app.ui.widget.entity;

import java.math.BigDecimal;

public interface AmountReadyCallback {
    void amountReady(BigDecimal value, BigDecimal gasFee);
    default void updateCryptoAmount(BigDecimal value) { };
}
