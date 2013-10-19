package cn.greenwishing.bms.utils;

import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ValidationUtils {
    private static final Logger log = Logger.getLogger(ValidationUtils.class);

    private static final String BIG_DECIMAL_REGEX = "^\\d+(\\.\\d+)?$";

    private static final String ALL_BIG_DECIMAL_REGEX = "^(-?\\d+)||(-?\\d+\\.\\d+)$";

    private static final String PASSWORD_REGEX = "^[a-zA-Z]\\w{3,14}$";

    private static final String POSITIVE_INTEGER_REGEX = "^\\d+$";

    private static final String NUMBER_REGEX = "^-?\\d+$";

    private static final String TELEPHONE_REGEX = "^0?1(3|5|8)\\d{9}$";

    private static final String EMAIL_REGEX = ".+@.+\\.[a-z]+";

    private static final String NUMBER_DECIMAL = "^(\\d+\\.\\d{1,2}|\\d+)$";

    private static final String[] VALID_PICTURE_TYPES = new String[]{".JPG", ".PNG"};

    private static final String DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}";

    private static final String TIME_REGEX = "^([0-1]?[0-9]|2[0-3]):([0-5][0-9])$";

    private static final String ACCURATE_DATE_REGEX = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

    private static final String PERCENTAGE_NUMBER_REGEX = "^((\\d|[1-9]\\d)(\\.\\d+)?|100(\\.[0]+)?)$";

    private static final String PERCENTAGE_REGEX = "^\\d+(\\.\\d+)?\\%$";

    private static final String POSITIVE_BIG_DECIMAL_REGEX = "^([1-9]\\d*)||(\\d+\\.\\d+)$";

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    public static boolean isValidEmail(String email) {
        return !isEmpty(email) && email.matches(EMAIL_REGEX);
    }

    public static boolean isValidPassword(String password) {
        return !isEmpty(password) && password.matches(PASSWORD_REGEX);
    }

    public static boolean isBigDecimal(String bigDecimalText) {
        return !isEmpty(bigDecimalText) && bigDecimalText.matches(BIG_DECIMAL_REGEX);
    }


    public static boolean isPrice(String priceText) {
        return priceText.trim().isEmpty() || (priceText.matches(BIG_DECIMAL_REGEX) && priceText.matches(NUMBER_DECIMAL));
    }

    public static boolean isPriceBigDecimal(String text) {
        return isPrice(text);
    }


    public static boolean isStandardCost(String text) {
        return !isEmpty(text) && isBigDecimal(text);
    }

    public static boolean isEmptyOrBigDecimal(String text) {
        return (isEmpty(text) || isBigDecimal(text));
    }

    public static boolean isPositiveNumber(String numberText) {
        return !isEmpty(numberText) && numberText.matches(POSITIVE_INTEGER_REGEX);
    }

    public static boolean isValidPictureTypes(String suffix) {
        for (String validPictureType : VALID_PICTURE_TYPES) {
            if (suffix.equalsIgnoreCase(validPictureType)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumber(String text) {
        return !isEmpty(text) && text.matches(NUMBER_REGEX);
    }

    public static boolean isTelephone(String text) {
        return !isEmpty(text) && text.matches(TELEPHONE_REGEX);
    }

    public static boolean isValidValue(String regularExpression, String value) {
        if (isEmpty(regularExpression)) {
            return true;
        }
        try {
            return Pattern.matches(regularExpression, value);
        } catch (PatternSyntaxException pse) {
            log.error("The regular expression \"" + regularExpression + "\" is not valid, it causes error happens, please check and modify it!");
            return false;
        }
    }

    public static boolean isFailedLength(Integer length, String value) {
        return value.length() > length;
    }

    public static boolean isValidDate(String sDate) {
        if ((sDate != null)) {
            Pattern pattern = Pattern.compile(DATE_REGEX);
            Matcher match = pattern.matcher(sDate);
            if (match.matches()) {
                pattern = Pattern.compile(ACCURATE_DATE_REGEX);
                match = pattern.matcher(sDate);
                return match.matches();
            } else {
                return false;
            }
        }
        return false;
    }

    public static boolean isValidTime(String sTime) {
        return !isEmpty(sTime) && sTime.matches(TIME_REGEX);
    }

    public static boolean isValidDateTime(String dateTime) {
        if (!isEmpty(dateTime) && dateTime.length() == 16) {
            String date = dateTime.substring(0, 10);
            String time = dateTime.substring(11);
            if (ValidationUtils.isValidDate(date) && ValidationUtils.isValidTime(time)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPercentage(String decimal) {
        return !isEmpty(decimal) && decimal.matches(PERCENTAGE_REGEX);
    }

    public static boolean isPercentageNumber(String decimal) {
        return !isEmpty(decimal) && decimal.matches(PERCENTAGE_NUMBER_REGEX);
    }

    public static boolean isAllNumber(String value) {
        return !isEmpty(value) && value.matches(ALL_BIG_DECIMAL_REGEX);
    }

    public static boolean isPositiveBigDecimal(String value) {
        return !isEmpty(value) && value.matches(POSITIVE_BIG_DECIMAL_REGEX);
    }
}
