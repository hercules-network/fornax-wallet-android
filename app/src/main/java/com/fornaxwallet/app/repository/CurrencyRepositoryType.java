package com.fornaxwallet.app.repository;

import com.fornaxwallet.app.entity.CurrencyItem;

import java.util.ArrayList;

public interface CurrencyRepositoryType {
    String getDefaultCurrency();

    void setDefaultCurrency(String currency);

    ArrayList<CurrencyItem> getCurrencyList();
}