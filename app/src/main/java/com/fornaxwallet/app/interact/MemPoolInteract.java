package com.fornaxwallet.app.interact;

import com.fornaxwallet.app.repository.TokenRepositoryType;

import io.reactivex.Observable;

import com.fornaxwallet.app.entity.TransferFromEventResponse;

public class MemPoolInteract {
    private final TokenRepositoryType tokenRepository;

    public MemPoolInteract(TokenRepositoryType tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    //create an observable
    public Observable<TransferFromEventResponse> burnListener(String contractAddress) {
        return tokenRepository.burnListenerObservable(contractAddress);
    }
}