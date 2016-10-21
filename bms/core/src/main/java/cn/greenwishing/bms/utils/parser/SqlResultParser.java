package cn.greenwishing.bms.utils.parser;

import cn.greenwishing.bms.shared.EnumUtils;
import cn.greenwishing.bms.utils.JodaUtils;
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
    private int index = 0;

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

    public Object next() {
        return get(index++);
    }

    public Object get(int index) {
        if (!check(index)) return null;
        return result[index];
    }

    public Number nextNumber() {
        return getNumber(index++);
    }

    public Number getNumber(int index) {
        if (!check(index)) return null;
        Object val = result[index];
        if (val instanceof Number) {
            return (Number) val;
        }
        return null;
    }

    public Integer nextInt() {
        return getInt(index++);
    }

    public Integer nextInt(Integer defaultValue) {
        return getInt(index++, defaultValue);
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

    public BigDecimal nextDecimal() {
        return getDecimal(index++);
    }

    public BigDecimal nextDecimal(BigDecimal defaultValue) {
        return getDecimal(index++, defaultValue);
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

    public BigInteger nextBigInt() {
        return getBigInt(index++);
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

    public String nextString() {
        return getString(index++);
    }

    public String nextString(String defaultValue) {
        return getString(index++, defaultValue);
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

    public Date nextDate() {
        return getDate(index++);
    }

    public Date getDate(int index) {
        DateTime dateTime = getDateTime(index);
        if (dateTime != null) return dateTime.toDate();
        return null;
    }

    public DateTime nextDateTime() {
        return getDateTime(index++);
    }

    public DateTime getDateTime(int index) {
        if (!check(index)) return null;
        Object val = result[index];
        if (val instanceof DateTime) {
            return (DateTime) val;
        } else if (val instanceof LocalDate) {
            return ((LocalDate) val).toDateTimeAtStartOfDay();
        } else if (val instanceof Date) {
            return new DateTime(((Date) val).getTime());
        }
        return null;
    }

    public LocalDate nextLocalDate() {
        return getLocalDate(index++);
    }

    public LocalDate getLocalDate(int index) {
        DateTime dateTime = getDateTime(index);
        if (dateTime != null) return dateTime.toLocalDate();
        return null;
    }

    public String nextLocalDateAsString() {
        return getLocalDateAsString(index++);
    }

    public String getLocalDateAsString(int index) {
        return JodaUtils.localDateToString(getLocalDate(index));
    }

    public String nextDateTimeAsString() {
        return getDateTimeAsString(index++);
    }

    public String getDateTimeAsString(int index) {
        return JodaUtils.dateTimeToString(getDateTime(index));
    }

    public List<String> nextStringList() {
        return getStringList(index++);
    }

    public List<String> nextStringList(String sp) {
        return getStringList(index++, sp);
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

    public List<Integer> nextIntList() {
        return getIntList(index++);
    }

    public List<Integer> nextIntList(String sp) {
        return getIntList(index++, sp);
    }

    public List<Integer> getIntList(int index) {
        return getIntList(index, ",");
    }

    public List<Integer> getIntList(int index, String sp) {
        if (!check(index)) return Collections.emptyList();
        Integer intVal = getInt(index);
        if (intVal != null) return Arrays.asList(intVal);
        List<String> intStrList = getStringList(index, sp);
        List<Integer> intList = new ArrayList<>();
        for (String intStr : intStrList) {
            intList.add(Integer.valueOf(intStr));
        }
        return intList;
    }

    public <E extends Enum> E nextEnumWithOrdinal(Class<E> enumClass) {
        return getEnumWithOrdinal(index++, enumClass);
    }

    public <E extends Enum> E nextEnumWithOrdinal(Class<E> enumClass, E defaultValue) {
        return getEnumWithOrdinal(index++, enumClass, defaultValue);
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

    public <E extends Enum> E nextEnumWithName(Class<E> enumClass) {
        return getEnumWithName(index++, enumClass);
    }

    public <E extends Enum> E getEnumWithName(int index, Class<E> enumClass) {
        if (!check(index)) return null;
        String name = getString(index);
        return EnumUtils.nameOf(enumClass, name);
    }

    public Long nextLong() {
        return getLong(index++);
    }

    public Long nextLong(Long defaultValue) {
        return getLong(index++, defaultValue);
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

    public Long getLong(int index, Long defaultValue) {
        Long value = getLong(index);
        return value == null ? defaultValue : value;
    }

    public boolean nextBoolean() {
        return getBoolean(index++);
    }

    public boolean getBoolean(int index) {
        if (!check(index)) return false;
        Object val = result[index];
        if (val instanceof Boolean) {
            return (Boolean) val;
        } else if (val instanceof Integer) {
            Integer valInt = (Integer) val;
            return valInt == 1;
        } else {
            String strVal = val.toString();
            return Boolean.valueOf(strVal);
        }
    }

    public Float nextFloat() {
        return getFloat(index++);
    }

    public Float nextFloat(Float defaultValue) {
        return getFloat(index++, defaultValue);
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
        return value == null ? defaultValue : value;
    }

    private boolean check(int index) {
        return result != null && result.length > index && result[index] != null;
    }

    private SqlResultParser resetIndex() {
        return setIndex(0);
    }

    private SqlResultParser setIndex(int index) {
        this.index = index;
        return this;
    }

    @Override
    public String toString() {
        return Arrays.toString(result);
    }
}
