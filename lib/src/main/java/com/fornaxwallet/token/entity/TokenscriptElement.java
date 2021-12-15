package com.fornaxwallet.token.entity;

public class TokenscriptElement {
    public String localRef;
    public String ref;
    public String value;

    public boolean isToken() {
        return ref != null && ref.contains("tokenId");
    }

    public int getTokenIndex() {
        int index = -1;
        if (isToken()) {
            try {
                String[] split = ref.split("[\\[\\]]");
                if (split.length == 2) {
                    String indexStr = split[1];
                    index = Integer.parseInt(indexStr);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        return index;
    }
}
