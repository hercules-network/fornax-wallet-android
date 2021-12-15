package com.fornaxwallet.app.web3.entity;

import com.fornaxwallet.app.entity.DAppFunction;
import com.fornaxwallet.token.entity.Signable;

public interface FunctionCallback {
    void signMessage(Signable sign, DAppFunction dAppFunction);

    void functionSuccess();

    void functionFailed();
}