package com.fornaxwallet.token.entity;

import java.util.HashMap;
import java.util.Map;

public class TSTokenViewHolder {
    public Map<String, Attribute> localAttributeTypes = new HashMap<>();
    public Map<String, TSTokenView> views = new HashMap<>();
    public String globalStyle = "";

    public String getView(String viewName) {
        TSTokenView v = views.get(viewName);
        if (v != null) return v.tokenView;
        else return null;
    }

    public String getViewStyle(String viewName) {
        TSTokenView v = views.get(viewName);
        if (v != null) return globalStyle + v.style;
        else return null;
    }
}
