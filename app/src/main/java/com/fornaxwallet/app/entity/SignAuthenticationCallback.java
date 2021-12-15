package com.fornaxwallet.app.entity;

import com.fornaxwallet.token.entity.Signable;

public interface SignAuthenticationCallback {
    void gotAuthorisation(boolean gotAuth);

    default void gotAuthorisationForSigning(boolean gotAuth, Signable messageToSign) { }; //if you implement message signing

    default void createdKey(String keyAddress) { };

    void cancelAuthentication();
}