package com.fornaxwallet.app.ui.widget.entity;

import java.math.BigInteger;

public interface GasSettingsCallback {
    void gasSettingsUpdate(BigInteger gasPrice, BigInteger gasLimit);
}
