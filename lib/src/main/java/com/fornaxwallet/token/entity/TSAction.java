package com.fornaxwallet.token.entity;

import java.util.Map;

public class TSAction {
    public int order;
    public String exclude;
    public TSTokenView view;
    public String style = "";
    public String name;

    public Map<String, Attribute> attributes;
    public FunctionDefinition function;
}
