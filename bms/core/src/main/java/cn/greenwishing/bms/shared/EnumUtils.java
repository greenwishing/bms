package cn.greenwishing.bms.shared;

import cn.greenwishing.bms.domain.oauth.OAuthResourceId;
import cn.greenwishing.bms.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class EnumUtils {

    /**
     * 将指定枚举按序号转换成枚举类
     * @param clazz 枚举类Class
     * @param ordinal 序号
     * @param <T> 枚举
     * @return 枚举类序号对应值
     */
    @SuppressWarnings("unchecked")
    public static <T extends Enum> T ordinalOf(Class<T> clazz, int ordinal) {
        EnumSet enumSet = EnumSet.allOf(clazz);
        for (Object object : enumSet) {
            if (object instanceof Enum) {
                Enum e = (Enum) object;
                if (e.ordinal() == ordinal) {
                    return (T) e;
                }
            }
        }
        return null;
    }

    /**
     * 将指定枚举字符串转换成枚举类
     * @param clazz 枚举类Class
     * @param name 枚举字符串
     * @param <T> 枚举
     * @return 枚举类型
     */
    @SuppressWarnings("unchecked")
    public static <T extends Enum> T nameOf(Class<T> clazz, String name) {
        EnumSet enumSet = EnumSet.allOf(clazz);
        for (Object object : enumSet) {
            if (object instanceof Enum) {
                Enum e = (Enum) object;
                if (e.name().equals(name)) {
                    return (T) e;
                }
            }
        }
        return null;
    }

    /**
     * 将指定枚举以逗号分割的字符串转换成枚举类集合
     * @param clazz 枚举类Class
     * @param value 以逗号分割的字符串
     * @param <T> 枚举
     * @return 枚举类型
     */
    @SuppressWarnings("unchecked")
    public static <T extends Enum> List<T> fromValue(Class<T> clazz, String value) {
        List<T> elemList = new ArrayList<>();
        if (ValidationUtils.isNotEmpty(value)) {
            String[] list = value.split(",");
            for (String id : list) {
                T elem = nameOf(clazz, id);
                if (elem != null) {
                    elemList.add(elem);
                }
            }
        }
        return elemList;
    }
}
