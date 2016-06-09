package cn.greenwishing.bms.utils.parser;

import cn.greenwishing.bms.shared.EnumUtils;
import cn.greenwishing.bms.utils.JodaUtils;
import cn.greenwishing.bms.utils.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * User: WuFan
 * Date: 2016/02/03
 */
public class SqlResultParser {

    private Object[] result;

    public SqlResultParser(Object[] result) {
        this.result = result;
    }

    public static List<SqlResultParser> valueOf(List<Object[]> results) {
        List<SqlResultParser> parsers = new ArrayList<>();
        if (results == null) return parsers;
        for (Object[] result : results) {
            SqlResultParser parser = new SqlResultParser(result);
            parsers.add(parser);
        }
        return parsers;
    }

    public Object[] getResult() {
        return result;
    }

    public Object get(int index) {
        if (!check(index)) return null;
        return result[index];
    }

    public Number getNumber(int index) {
        if (!check(index)) return null;
        Object val = result[index];
        if (val instanceof Number) {
            return (Number) val;
        }
        return null;
    }

    public String getNumberAsString(int index) {
        return NumberUtils.toString(getNumber(index), 2);
    }

    public Integer getInt(int index, Integer defaultVal) {
        Integer anInt = getInt(index);
        if (anInt != null) return anInt;
        return defaultVal;
    }

    public Integer getInt(int index) {
        if (!check(index)) return null;
        Object val = result[index];
        if (val instanceof Integer) {
            return (Integer) val;
        } else if (val instanceof Number) {
            return ((Number) val).intValue();
        }
        return null;
    }

    public BigDecimal getDecimal(int index, BigDecimal defaultVal) {
        BigDecimal decimal = getDecimal(index);
        if (decimal != null) return decimal;
        return defaultVal;
    }

    public BigDecimal getDecimal(int index) {
        if (!check(index)) return null;
        Object val = result[index];
        if (val instanceof BigDecimal) {
            return (BigDecimal) val;
        } else if (val instanceof Number) {
            double doubleVal = ((Number) val).doubleValue();
            return new BigDecimal(doubleVal);
        }
        return null;
    }

    public BigInteger getBigInt(int index) {
        if (!check(index)) return null;
        Object val = result[index];
        if (val instanceof BigInteger) {
            return (BigInteger) val;
        } else if (val instanceof Number) {
            long longVal = ((Number) val).longValue();
            return BigInteger.valueOf(longVal);
        }
        return null;
    }

    public String getString(int index, String defaultValue) {
        String string = getString(index);
        return string == null ? defaultValue : string;
    }

    public String getString(int index) {
        if (!check(index)) return null;
        Object val = result[index];
        if (val instanceof String) {
            return (String) val;
        }
        return val.toString();
    }

    public Date getDate(int index) {
        if (!check(index)) return null;
        Object val = result[index];
        if (val instanceof Date) {
            return (Date) val;
        }
        return null;
    }

    public DateTime getDateTime(int index) {
        Date date = getDate(index);
        if (date != null) return new DateTime(date.getTime());
        return null;
    }

    public LocalDate getLocalDate(int index) {
        Date date = getDate(index);
        if (date != null) return new LocalDate(date.getTime());
        return null;
    }

    private boolean check(int index) {
        return result != null && result.length > index && result[index] != null;
    }

    public String getLocalDateAsString(int index) {
        return JodaUtils.localDateToString(getLocalDate(index));
    }

    public String getDateTimeAsString(int index) {
        return JodaUtils.dateTimeToString(getDateTime(index));
    }

    public List<String> getStringList(int index, String sp) {
        if (!check(index)) return Collections.emptyList();
        Object val = result[index];
        if (val instanceof String) {
            String[] strArray = ((String) val).split(sp);
            return Arrays.asList(strArray);
        }
        return Collections.emptyList();
    }

    public List<String> getStringList(int index) {
        return getStringList(index, ",");
    }

    public List<Integer> getIntList(int index) {
        return getIntList(index, ",");
    }

    public List<Integer> getIntList(int index, String sp) {
        if (!check(index)) return Collections.emptyList();
        Integer intVal = getInt(index);
        if (intVal != null) return Collections.singletonList(intVal);
        List<String> intStrList = getStringList(index, sp);
        List<Integer> intList = new ArrayList<>();
        for (String intStr : intStrList) {
            intList.add(Integer.valueOf(intStr));
        }
        return intList;
    }

    public <E extends Enum> E getEnumWithOrdinal(int index, Class<E> enumClass) {
        if (!check(index)) return null;
        Integer ordinal = getInt(index);
        return EnumUtils.ordinalOf(enumClass, ordinal);
    }

    public <E extends Enum> E getEnumWithOrdinal(int index, Class<E> enumClass, E defaultValue) {
        if (!check(index)) return defaultValue;
        Integer ordinal = getInt(index);
        E enumVal = EnumUtils.ordinalOf(enumClass, ordinal);
        return enumVal == null ? defaultValue : enumVal;
    }

    public <E extends Enum> E getEnumWithName(int index, Class<E> enumClass) {
        if (!check(index)) return null;
        String name = getString(index);
        return EnumUtils.nameOf(enumClass, name);
    }

    public Long getLong(int index) {
        if (!check(index)) return null;
        Object val = result[index];
        if (val instanceof Long) {
            return (Long) val;
        } else if (val instanceof Number) {
            return ((Number) val).longValue();
        }
        return null;
    }

    @Override
    public String toString() {
        return Arrays.toString(result);
    }

    public boolean getBoolean(int index) {
        if (!check(index)) return false;
        Object val = result[index];
        if (val instanceof Boolean) {
            return (Boolean) val;
        } else {
            String strVal = val.toString();
            return Boolean.valueOf(strVal);
        }
    }

    public Float getFloat(int index) {
        if (!check(index)) return null;
        Object val = result[index];
        if (val instanceof Float) {
            return (Float) val;
        } else if (val instanceof Number) {
            return ((Number) val).floatValue();
        }
        return null;
    }

    public Float getFloat(int index, float defaultValue) {
        Float value = getFloat(index);
        return value == null ? defaultValue: value;
    }
}
