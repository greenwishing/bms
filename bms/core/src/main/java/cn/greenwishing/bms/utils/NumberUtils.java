package cn.greenwishing.bms.utils;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author Wu Fan
 */
public class NumberUtils {

    public static String priceFormat(Number number) {
        if (number == null) return "";
        NumberFormat format = NumberFormat.getInstance(Locale.CHINESE);
        format.setRoundingMode(RoundingMode.HALF_UP);
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        return format.format(number);
    }
}
