package com.fornaxwallet.app.repository;

import com.fornaxwallet.app.entity.OnRampContract;
import com.fornaxwallet.app.entity.tokens.Token;

public interface OnRampRepositoryType {
    String getUri(String address, Token token);

    OnRampContract getContract(Token token);
}