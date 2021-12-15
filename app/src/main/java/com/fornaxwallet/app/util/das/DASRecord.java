package com.fornaxwallet.app.util.das;

public class DASRecord {
    String key;
    String label;
    String value;
    String ttl;

    public String getAddress() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}