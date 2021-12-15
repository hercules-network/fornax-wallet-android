package com.fornaxwallet.app.router;

import android.content.Context;
import android.content.Intent;

import com.fornaxwallet.app.ui.HelpActivity;

public class HelpRouter {
    public void open(Context context) {
        Intent intent = new Intent(context, HelpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }
}