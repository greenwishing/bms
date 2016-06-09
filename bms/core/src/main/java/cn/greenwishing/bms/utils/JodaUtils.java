package cn.greenwishing.bms.utils;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Wu Fan
 */
public class JodaUtils {

    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    public static final String DATE_TIME_MS_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String DATE_SHORT_CN_FORMAT = "MM月dd日";
    public static final String MONTH_CN_FORMAT = "MM月";

    public static DateTime now() {
        return new DateTime();
    }

    public static LocalDate today() {
        return new LocalDate();
    }

    public static LocalDate parseLocalDate(String dateAsString, String format) {
        if (StringUtils.hasText(dateAsString)) {
            Date date = parseDate(dateAsString, format);
            return new LocalDate(date);
        }
        return null;
    }

    public static Date parseDate(String dateAsString, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(dateAsString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date string can not be parsed:" + dateAsString, e);
        }
    }

    public static LocalDate parseLocalDate(String localDateAsString) {
        return parseLocalDate(localDateAsString, DATE_FORMAT);
    }

    public static DateTime parseDateTime(String val) {
        return parseDateTime(val, DEFAULT_DATE_TIME_FORMAT);
    }

    public static DateTime parseDateTime(String text, String patten) {
        if (StringUtils.hasText(text)) {
            return null;
        }
        return DateTimeFormat.forPattern(patten).parseDateTime(text);
    }

    public static LocalDate yesterday() {
        return today().minusDays(1);
    }

    public static DateTime dayOfCurrentMonth(int day) {
        LocalDate today = JodaUtils.today();
        return new DateTime(today.getYear(), today.getMonthOfYear(), day, 0, 0, 0, 0);
    }

    public static String localDateToString(LocalDate localDate) {
        if (localDate == null) return "";
        return localDate.toString(DATE_FORMAT);
    }

    public static String dateTimeToString(DateTime dateTime) {
        if (dateTime == null) return "";
        return dateTime.toString(DATE_TIME_FORMAT);
    }
}
