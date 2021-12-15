package com.fornaxwallet.token.entity;

public class XMLDsigVerificationResult {
    public boolean isValid;
    public String keyName;
    public String issuerPrincipal;
    public String subjectPrincipal;
    public String keyType;
    public String failureReason;

    public XMLDsigVerificationResult() {
        isValid = false;
        keyName = "";
        issuerPrincipal = "";
        subjectPrincipal = "";
        keyType = "";
    }
}