package com.fornaxwallet.app.router;

import android.content.Context;
import android.content.Intent;

import com.fornaxwallet.app.C;
import com.fornaxwallet.app.ui.ImportTokenActivity;

public class ImportTokenRouter {
    public void open(Context context, String importTxt) {
        Intent intent = new Intent(context, ImportTokenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(C.IMPORT_STRING, importTxt);
        context.startActivity(intent);
    }
}