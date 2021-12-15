package com.fornaxwallet.app.interact;

import com.fornaxwallet.app.repository.TokenRepositoryType;
import com.fornaxwallet.app.ui.widget.entity.ENSHandler;
import com.fornaxwallet.token.tools.Numeric;

import java.math.BigInteger;

import io.reactivex.Single;

public class ENSInteract {
    private final TokenRepositoryType tokenRepository;

    public ENSInteract(TokenRepositoryType tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Single<String> checkENSAddress(long chainId, String name) {
        if (!ENSHandler.canBeENSName(name)) return Single.fromCallable(() -> "0");
        return tokenRepository.resolveENS(chainId, name)
                .map(this::checkAddress);
    }

    private String checkAddress(String returnedAddress) {
        BigInteger test = Numeric.toBigInt(returnedAddress);
        if (!test.equals(BigInteger.ZERO)) {
            return returnedAddress;
        } else {
            return "0";
        }
    }
}