package com.cxe.commons;

import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Hours;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * <p>
 * Utility to provide the following to all layers:</p>
 * <ul>
 * <li>Provision of standard Joda time formatters and parsers</li>
 * </ul>
 * <p>
 * All times use the UTC time zone unless otherwise specified</p>
 *
 * @since 0.0.1
 */
public class DateUtils {

    /**
     * Produces "Sat, 01 Jan 2000 23:59:59 GMT"
     */
    private static final DateTimeFormatter rfc1123_timeOnly = DateTimeFormat
            .forPattern("HH:mm")
            .withLocale(Locale.US) // For common language
            .withZone(DateTimeZone.UTC); // For GMT

    /**
     * Produces "Sat, 01 Jan 2000 23:59:59 GMT"
     */
    private static final DateTimeFormatter rfc1123 = DateTimeFormat
            .forPattern("EEE, dd MMM yyyy HH:mm:ss 'GMT'")
            .withLocale(Locale.US) // For common language
            .withZone(DateTimeZone.UTC); // For GMT

    /**
     * Produces "01/01/2000"
     */
    private static final DateTimeFormatter dateOnly = DateTimeFormat
            .forPattern("dd/MM/yyyy")
            .withLocale(Locale.US) // For common language
            .withZone(DateTimeZone.UTC); // For GMT

    /**
     * Produces "01/01/2000"
     */
    private static final DateTimeFormatter cleanDateFormat = DateTimeFormat
            .forPattern("ddMMyyyy")
            .withLocale(Locale.US) // For common language
            .withZone(DateTimeZone.UTC); // For GMT

    /**
     * Produces "2000/01"
     */
    private static final DateTimeFormatter folderMaker_yearMonth = DateTimeFormat
            .forPattern("yyyy" + Strings.FILE_SEPERATOR + "MM")
            .withLocale(Locale.US); // For common language

    /**
     * Produces "2000/01/01"
     */
    private static final DateTimeFormatter folderMaker_yearMonthDay = DateTimeFormat
            .forPattern("yyyy" + Strings.FILE_SEPERATOR + "MM" + Strings.FILE_SEPERATOR + "dd")
            .withLocale(Locale.US); // For common language

    /**
     * Produces Sunday, January 01
     */
    private static final DateTimeFormatter friendlyDateFormatter = DateTimeFormat.forPattern("EEEE, MMMM dd");

    /**
     * Parses ISO8601_WEB without milliseconds (e.g.
     * "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
     */
    private static final DateTimeFormatter utcIso8601 = ISODateTimeFormat.dateTimeNoMillis().withZone(DateTimeZone.UTC);

    /**
     * @return The current instant in UTC
     */
    public static DateTime nowUtc() {
        return new DateTime().withZone(DateTimeZone.UTC);
    }

    /**
     * @param year   The year (e.g. 2000)
     * @param month  The month (e.g. January is 1, December is 12)
     * @param day    The day of the month (e.g. 1 through to 31)
     * @param hour   The hour of the day (e.g. 0 through to 23)
     * @param minute The minute of the day (e.g. 0 through to 59)
     * @param second The second of the day (e.g. 0 through to 59)
     * @return The given instant with a UTC timezone
     */
    public static DateTime thenUtc(
            int year,
            int month,
            int day,
            int hour,
            int minute,
            int second) {
        return new DateTime(year, month, day, hour, minute, second, 0).withZone(DateTimeZone.UTC);
    }

    /**
     * @param when The instant
     * @return The instant formatted as "yyyyMMdd"
     */
    public static String formatBasicDate(ReadableInstant when) {
        return ISODateTimeFormat.basicDate().print(when);
    }

    /**
     * @param when   The instant
     * @param locale The required locale
     * @return The instant formatted as "yyyyMMdd"
     */
    public static String formatBasicDate(ReadableInstant when, Locale locale) {
        return ISODateTimeFormat.basicDate().withLocale(locale).print(when);
    }

    /**
     * @param when The instant
     * @return The instant formatted as "ddd, MMM dd" (Saturday, January 01)
     */
    public static String formatFriendlyDate(ReadableInstant when) {
        return friendlyDateFormatter.print(when);
    }

    /**
     * @param when The instant
     * @return The instant formatted as "ddMMYY" (01092015)
     */
    public static String formatDate(ReadableInstant when) {
        return cleanDateFormat.print(when);
    }

    /**
     * @param when   The instant
     * @param locale The required locale
     * @return The instant formatted as "ddd, MMM dd" (Saturday, January 01)
     */
    public static String formatFriendlyDate(ReadableInstant when, Locale locale) {
        return friendlyDateFormatter.withLocale(locale).print(when);
    }

    /**
     * @param when The instant
     * @return The instant formatted as RFC 1123 e.g. "Sat, 01 Jan 2000 23:59:59
     * GMT"
     */
    public static String formatHttpDateHeader(ReadableInstant when) {
        return rfc1123.print(when);
    }

    /**
     * @return null if error else returns a valid date
     * @author Syam
     */
    public static Date parseTimeOnly(String time) {
        DateTime dateTime = rfc1123_timeOnly.parseDateTime(time);
        return dateTime.toDate();
    }

    /**
     * @param dateTimeStr
     * @return null if error else returns a valid date
     * @author Syam
     */
    public static Date parseTimeOnly(String time, DateTimeFormatter dtf) {
        DateTime dateTime = dtf.parseDateTime(time);
        return dateTime.toDate();
    }

    /**
     * @param dateTimeStr
     * @return null if error else returns a valid Date and Time
     * @author Syam
     */
    public static Date parseDateTime(String dateTimeStr) {
        try {
            DateTime dateTime = rfc1123.parseDateTime(dateTimeStr);
            return dateTime.toDate();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Syam
     * After hours of repeated errors, this is what it takes to convert web <form <input type="time">> to
     *
     * @param dateTimeStr
     * @return
     */
    public static DateTime parseISOWebDate(String dateTimeStr) {
        return DateTime.parse(dateTimeStr, ISODateTimeFormat.dateTimeParser());
    }

    /**
     * @param dateTimeStr
     * @return null if error else returns a valid Local Date And Time
     * @author Syam
     */
    public static LocalDate parseDateTimeToLocalDate(String dateTimeStr) {
        try {
            DateTime dateTime = rfc1123.parseDateTime(dateTimeStr);
            return dateTime.toLocalDate();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param date String
     * @return null if error else returns a valid Local Date
     * @author Syam
     */
    public static LocalDate parseDateToLocalDate(String dateTimeStr) {
        try {
            DateTime dateTime = dateOnly.parseDateTime(dateTimeStr);
            return dateTime.toLocalDate();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param LocalDate
     * @return age in years
     */
    public static int getAge(LocalDate birthDay) {
        LocalDate now = new LocalDate();
        Years age = Years.yearsBetween(birthDay, now);
        return age.getYears();
    }

    public static int getHoursBetween(LocalDateTime date) {
        LocalDateTime now = new LocalDateTime();
        Hours days = Hours.hoursBetween(date, now);
        return days.getHours();
    }

    /**
     * @param when The instant in its timezone
     * @return The instant formatted as ISO8601_WEB e.g. "2000-01-02T03:04:05Z"
     */
    public static String formatISO8601(ReadableInstant when) {
        return utcIso8601.print(when);
    }

    /**
     * @param when   The instant
     * @param locale The required locale
     * @return The instant formatted as ISO8601_WEB e.g. "2000-01-02T03:04:05Z"
     */
    public static String formatISO8601(ReadableInstant when, Locale locale) {
        return utcIso8601.withLocale(locale).print(when);
    }

    /**
     * @param text The text representing a date and time in ISO8601_WEB format
     * @return The DateTime
     * @throws IllegalArgumentException If the text cannot be parsed
     */
    public static DateTime parseISO8601(String text) {
        return utcIso8601.parseDateTime(text);
    }

    /**
     * @return The instant formatted as yyyy/MM from current Time (GOOD for creating folders for file uploads)
     */
    public static String getFilePathStyle_YearMonthNow() {
        return folderMaker_yearMonth.print(DateTime.now());
    }

    public static String getFilePathStyle_YearMonthDayNow() {
        return folderMaker_yearMonthDay.print(DateTime.now());
    }

}
