package com.fornaxwallet.app.entity;

import com.fornaxwallet.token.entity.Signable;

public interface DAppFunction {
    void DAppError(Throwable error, Signable message);

    void DAppReturn(byte[] data, Signable message);
}