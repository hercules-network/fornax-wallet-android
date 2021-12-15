package com.fornaxwallet.app.entity;

public class ItemClick {
    public final String buttonText;
    public final int buttonId;

    public ItemClick(String text, int id) {
        buttonId = id;
        buttonText = text;
    }
}