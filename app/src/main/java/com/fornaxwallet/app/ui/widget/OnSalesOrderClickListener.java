package com.fornaxwallet.app.ui.widget;

import android.view.View;

import com.fornaxwallet.token.entity.MagicLinkData;

public interface OnSalesOrderClickListener {
    void onOrderClick (View view, MagicLinkData range);
}
