package com.fornaxwallet.app.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityMeta {
    protected final long timeStamp;
    public final String hash;

    public ActivityMeta(long ts, String txHash) {
        timeStamp = ts * 1000;
        hash = txHash;
    }

    public ActivityMeta(long ts, String txHash, boolean tokenTransfer) {
        timeStamp = ts;
        hash = txHash;
    }

    public long getTimeStampSeconds() {
        return timeStamp / 1000;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
