package com.fornaxwallet.app.ui.widget;

import com.fornaxwallet.app.entity.tokens.Token;

public interface OnTokenManageClickListener {
    void onTokenClick(Token token, int position, boolean isChecked);
}