package com.fornaxwallet.token.util;

import com.fornaxwallet.token.entity.NonFungibleToken;
import java.text.ParseException;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DateTimeFactory {
    public static DateTime getDateTime(long unixTime, TimeZone timeZone) {
        return new ZonedDateTime(unixTime, timeZone);
    }
    public static DateTime getDateTime(NonFungibleToken.Attribute timeAttr) throws ParseException, IllegalArgumentException
    {
        String eventTimeText = timeAttr.text;
        if (eventTimeText != null) {
            return initZonedTime(eventTimeText);
        } else {
            return new GeneralDateTime(timeAttr);
        }
    }

    public static DateTime getDateTime(String time) throws ParseException, IllegalArgumentException {
        return initZonedTime(time);
    }

    public static DateTime getCurrentTime() {
        return new GeneralDateTime(String.valueOf(System.currentTimeMillis()));
    }

    private static DateTime initZonedTime(String time) throws ParseException, IllegalArgumentException {
        Pattern p = Pattern.compile("(\\+\\d{4}|\\-\\d{4})");
        Matcher m = p.matcher(time);
        if (m.find())
        {
            return new ZonedDateTime(time, m);
        }
        else if (isNumeric(time))
        {
            return new GeneralDateTime(time);
        }
        else
        {
            return new GeneralDateTime(String.valueOf(System.currentTimeMillis()));
        }
    }

    private static boolean isNumeric(String testStr)
    {
        boolean result = false;
        if (testStr != null && testStr.length() > 0)
        {
            result = true;
            for (int i = 0; i < testStr.length(); i++)
            {
                char c = testStr.charAt(i);
                if (!Character.isDigit(c))
                {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
