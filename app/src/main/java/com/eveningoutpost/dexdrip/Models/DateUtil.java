package com.eveningoutpost.dexdrip.Models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

// from package info.nightscout.client.utils;

/**
 * Created by mike on 30.12.2015.
 */

/**
 * The Class DateUtil. A simple wrapper around SimpleDateFormat to ease the handling of iso date string &lt;-&gt; date obj
 * with TZ
 */
public class DateUtil {

    private static final String FORMAT_DATE_ISO = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    /**
     * Takes in an ISO date string of the following format:
     * yyyy-mm-ddThh:mm:ss.ms+HoMo
     *
     * @param isoDateString the iso date string
     * @return the date
     * @throws Exception the exception
     */
    public static Date fromISODateString(String isoDateString)
            throws Exception {
        SimpleDateFormat f = new SimpleDateFormat(FORMAT_DATE_ISO);
        f.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = f.parse(isoDateString);
        return date;
    }

    public static Date tolerantFromISODateString(String isoDateString)
            throws Exception {
      return fromISODateString(isoDateString.replaceFirst("\\.[0-9][0-9][0-9]Z$","Z"));
    }

    /**
     * Render date
     *
     * @param date   the date obj
     * @param format - if not specified, will use FORMAT_DATE_ISO
     * @param tz     - tz to set to, if not specified uses local timezone
     * @return the iso-formatted date string
     */
    public static String toISOString(Date date, String format, TimeZone tz) {
        if (format == null) format = FORMAT_DATE_ISO;
        if (tz == null) tz = TimeZone.getDefault();
        DateFormat f = new SimpleDateFormat(format);
        f.setTimeZone(tz);
        return f.format(date);
    }

    public static String toISOString(Date date) {
        return toISOString(date, FORMAT_DATE_ISO, TimeZone.getTimeZone("UTC"));
    }

    public static String toISOString(long date) {
        return toISOString(new Date(date), FORMAT_DATE_ISO, TimeZone.getTimeZone("UTC"));
    }
}