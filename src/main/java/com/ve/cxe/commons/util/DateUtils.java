package com.ve.cxe.commons.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

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
     * @param when The instant
     * @return The instant formatted as "ddMMYYYYHHmmss" (01092015121011)
     */
    static DateTimeFormatter dateTimeFormatPattern = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");

    public static String formatDate_ddMMYYYYHHmmss(ZonedDateTime now) {
        return now.format(dateTimeFormatPattern);
    }


    /**
     * @param LocalDate
     * @return age in years
     */
    public static int getAge(LocalDate birthDay) {
        return LocalDate.now().getYear() - birthDay.getYear();
    }

    public static long getHoursElapsed(LocalDateTime fromDate) {
        return fromDate.until(LocalDateTime.now(), ChronoUnit.HOURS);
    }

    /**
     * @return The instant formatted as yyyy/MM from current Time (GOOD for creating folders for file uploads)
     */
    static DateTimeFormatter formatYearMonth = DateTimeFormatter.ofPattern("yyyy/MM");

    public static String getFilePathStyle_YearMonthNow() {
        LocalDate now = LocalDate.now();
        return now.format(formatYearMonth);
    }

    /**
     * @return The instant formatted as yyyy/MM/dd from current Time (GOOD for creating folders for file uploads)
     */
    static DateTimeFormatter formatYearMonthDay = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static String getFilePathStyle_YearMonthDayNow() {
        return LocalDate.now().format(formatYearMonthDay);
    }

}
