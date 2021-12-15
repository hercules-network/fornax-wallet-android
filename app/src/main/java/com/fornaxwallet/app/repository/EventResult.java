package com.fornaxwallet.app.repository;

public class EventResult {
    public final String type;
    public final String value;

    public EventResult(String t, String v) {
        type = t;
        value = v;
    }
}