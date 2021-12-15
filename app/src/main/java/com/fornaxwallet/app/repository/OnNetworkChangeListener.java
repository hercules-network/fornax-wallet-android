package com.fornaxwallet.app.repository;

import com.fornaxwallet.app.entity.NetworkInfo;

public interface OnNetworkChangeListener {
    void onNetworkChanged(NetworkInfo networkInfo);
}