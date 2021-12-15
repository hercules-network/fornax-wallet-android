package com.fornaxwallet.app.router;

import android.content.Context;
import android.content.Intent;

import com.fornaxwallet.app.C;
import com.fornaxwallet.app.ui.RedeemAssetSelectActivity;
import com.fornaxwallet.app.entity.tokens.Token;

public class RedeemAssetSelectRouter {
    public void open(Context context, Token token) {
        Intent intent = new Intent(context, RedeemAssetSelectActivity.class);
        intent.putExtra(C.EXTRA_CHAIN_ID, token.tokenInfo.chainId);
        intent.putExtra(C.EXTRA_ADDRESS, token.getAddress());
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        context.startActivity(intent);
    }
}