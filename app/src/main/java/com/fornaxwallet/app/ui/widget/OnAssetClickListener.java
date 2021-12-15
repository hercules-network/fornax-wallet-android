package com.fornaxwallet.app.ui.widget;

import android.util.Pair;

import com.fornaxwallet.app.entity.nftassets.NFTAsset;

import java.math.BigInteger;

public interface OnAssetClickListener {
    void onAssetClicked(Pair<BigInteger, NFTAsset> item);
}
