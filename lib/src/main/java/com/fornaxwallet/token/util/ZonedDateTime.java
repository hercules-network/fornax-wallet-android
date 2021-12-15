package com.fornaxwallet.token.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;

public class ZonedDateTime extends DateTime {
    ZonedDateTime(long unixTime,TimeZone timeZone){
        this.time = unixTime * 1000L;
        this.timeZone = timeZone;
        isZoned = true;
    }

    ZonedDateTime(String time,Matcher m) throws ParseException, IllegalArgumentException {
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyyMMddHHmmssZZZZ");
        this.timeZone = TimeZone.getTimeZone("GMT"+m.group(1));
        isoFormat.setTimeZone(this.timeZone);
        Date date = isoFormat.parse(time);
        this.time = date.getTime();
        isZoned = true;
    }
}
