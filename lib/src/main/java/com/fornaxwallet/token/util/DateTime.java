package com.fornaxwallet.token.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTime {
    protected long time;
    protected TimeZone timeZone;
    protected boolean isZoned = false;

    public int getHour() {
        SimpleDateFormat format = new SimpleDateFormat("H");
        return Integer.valueOf(format(format));
    }
    public int getMinute() {
        SimpleDateFormat format = new SimpleDateFormat("m");
        return Integer.valueOf(format(format));
    }

    public String format(DateFormat format) {
        format.setTimeZone(timeZone);
        return format.format(new Date(time));
    }

    public boolean isZoned() {return isZoned;}

    public long toEpochSecond() {
        return time/1000L;
    }

    public long toEpoch()
    {
        return time;
    }
}
