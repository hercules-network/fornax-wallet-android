package com.fornaxwallet.token.entity;

public interface ParseResult {
    enum ParseResultId
    {
        OK,
        XML_OUT_OF_DATE,
        PARSER_OUT_OF_DATE,
        PARSE_FAILED
    };

    void parseMessage(ParseResultId parseResult);
}
