package com.fornaxwallet.app.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.fornaxwallet.app.C;
import com.fornaxwallet.app.entity.Wallet;
import com.fornaxwallet.app.ui.AssetDisplayActivity;
import com.fornaxwallet.app.entity.tokens.Token;

public class AssetDisplayRouter {

    public void open(Activity activity, Token token, Wallet wallet) {
        Intent intent = new Intent(activity, AssetDisplayActivity.class);
        intent.putExtra(C.EXTRA_CHAIN_ID, token.tokenInfo.chainId);
        intent.putExtra(C.EXTRA_ADDRESS, token.getAddress());
        intent.putExtra(C.Key.WALLET, wallet);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        activity.startActivityForResult(intent, C.TERMINATE_ACTIVITY);
    }
}