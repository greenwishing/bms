package cn.greenwishing.bms.utils;

import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * @author Wu Fan
 */
public class NumberUtils {

    public static final NumberFormat DEFAULT_NUMBER_FORMAT = buildNumberFormat(2);

    public static String toString(Number number) {
        if (number == null) return "";
        return DEFAULT_NUMBER_FORMAT.format(number);
    }

    public static String toString(Number number, int digits) {
        if (number == null) return "";
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
}
