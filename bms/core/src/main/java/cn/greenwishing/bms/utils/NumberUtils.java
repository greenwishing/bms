package cn.greenwishing.bms.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;

/**
 * User: Wu Fan
 */
public class NumberUtils {

    public static final NumberFormat DEFAULT_NUMBER_FORMAT = buildNumberFormat(2);

    public static String toString(Number number) {
        if (number == null) return "";
        return DEFAULT_NUMBER_FORMAT.format(number);
    }

    public static String toString(Number number, String defaultVal) {
        if (number == null) return defaultVal;
        return DEFAULT_NUMBER_FORMAT.format(number);
    }

    public static String toString(Number number, int digits) {
        if (number == null) return "";
        return buildNumberFormat(digits).format(number);
    }

    public static String toString(Number number, int digits, String defaultVal) {
        if (number == null) return defaultVal;
        return buildNumberFormat(digits).format(number);
    }

    private static NumberFormat buildNumberFormat(int digits) {
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMinimumFractionDigits(0);
        format.setMaximumFractionDigits(digits);
        format.setGroupingUsed(false);
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format;
    }

    public static BigDecimal parseDecimal(String numberString) {
        NumberFormat numberFormat = buildNumberFormat(2);
        numberFormat.setGroupingUsed(true);
        try {
            Number parse = numberFormat.parse(numberString);
            return new BigDecimal(parse.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
        } catch (ParseException e) {
            return null;
        }
    }

    public static BigDecimal parseDecimal(String numberString, BigDecimal defaultValue) {
        BigDecimal decimal = parseDecimal(numberString);
        if (decimal == null) return defaultValue;
        return decimal;
    }
}
