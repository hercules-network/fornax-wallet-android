package com.fornaxwallet.token.entity;

public class SalesOrderMalformed extends Exception {
    // Parameterless Constructor
    public SalesOrderMalformed() {
    }

    // Constructor that accepts a message
    public SalesOrderMalformed(String message) {
        super(message);
    }
}
