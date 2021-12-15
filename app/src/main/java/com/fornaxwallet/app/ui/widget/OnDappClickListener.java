package com.fornaxwallet.app.ui.widget;

import java.io.Serializable;

import com.fornaxwallet.app.entity.DApp;

public interface OnDappClickListener extends Serializable {
    void onDappClick(DApp dapp);
}
