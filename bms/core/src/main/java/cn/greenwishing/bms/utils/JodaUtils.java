package cn.greenwishing.bms.utils;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Frank wu
 */
public class JodaUtils {

    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    public static final String TIME_FORMAT = "HH:mm";

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
        if (!StringUtils.hasText(text)) {
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
        if (localDate == null) {
            return null;
        }
        return localDate.toString(DATE_FORMAT);
    }

    public static String dateTimeToString(DateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.toString(DATE_TIME_FORMAT);
    }

    public static String localTimeToString(LocalTime localTime) {
        if (localTime == null) {
            return null;
        }
        return localTime.toString(TIME_FORMAT);
    }

    public static Integer days(LocalDate from, LocalDate to) {
        Period period = new Period(to, from, PeriodType.days());
        return period.getDays();
    }

    public static String dateTimeFriendly(DateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        LocalDate today = JodaUtils.today();
        LocalDate localDate = dateTime.toLocalDate();
        Integer days = days(localDate, today);
        String date = "";
        String time = "HH:mm";
        if (days == 0) {
            date = "今天";
        } else if (days == 1) {
            date = "昨天";
        } else if (days == 2) {
            date = "前天";
        } else if (today.getYear() == localDate.getYear()) {
            if (today.getMonthOfYear() == localDate.getMonthOfYear()) {
                date = "d日";
            } else {
                date = "M月d日";
            }
        } else {
            date = "yy年M月d日";
        }
        return dateTime.toString(date + " " + time);
    }
}
