package com.fornaxwallet.app.ui.widget.entity;

import com.fornaxwallet.app.entity.BackupTokenCallback;
import com.fornaxwallet.app.entity.Wallet;

public class WarningData {
    public String title;
    public String detail;
    public String buttonText;
    public Wallet wallet;
    public int colour;
    public int buttonColour;
    public final BackupTokenCallback callback;

    public WarningData(BackupTokenCallback tCallback) {
        callback = tCallback;
    }
}