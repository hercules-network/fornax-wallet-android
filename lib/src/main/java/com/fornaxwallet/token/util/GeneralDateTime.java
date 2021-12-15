package com.fornaxwallet.token.util;

import com.fornaxwallet.token.entity.NonFungibleToken;

import java.util.TimeZone;

public class GeneralDateTime extends DateTime {
    GeneralDateTime(NonFungibleToken.Attribute timeAttr)
    {
        this.timeZone = TimeZone.getTimeZone("GMT");
        time = timeAttr.value.longValue()*1000;
    }

    GeneralDateTime(String time)
    {
        this.timeZone = TimeZone.getTimeZone("GMT");
        Long timeConv = Long.valueOf(time);
        this.time = timeConv*1000;
    }
}
