package cn.greenwishing.bms.utils;

import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * @author Wu Fan
 */
public class NumberUtils {

    public static String toString(Number number) {
        return toString(number, 2);
    }

    public static String toString(Number number, int digits) {
        if (number == null) return "";
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMinimumFractionDigits(0);
        format.setMaximumFractionDigits(digits);
        format.setGroupingUsed(false);
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(number);
    }
}
