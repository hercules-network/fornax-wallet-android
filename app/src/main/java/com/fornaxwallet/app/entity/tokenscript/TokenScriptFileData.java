package com.fornaxwallet.app.entity.tokenscript;

import com.fornaxwallet.token.entity.XMLDsigDescriptor;

public class TokenScriptFileData {
    public String hash;
    public XMLDsigDescriptor sigDescriptor;

    public TokenScriptFileData() {
        hash = null;
        sigDescriptor = null;
    }
}