package com.fornaxwallet.app.entity.opensea;

import com.fornaxwallet.app.entity.ErrorEnvelope;

public class OpenseaServiceError extends Exception {
    public final ErrorEnvelope error;

    public OpenseaServiceError(String message) {
        super(message);

        error = new ErrorEnvelope(message);
    }
}