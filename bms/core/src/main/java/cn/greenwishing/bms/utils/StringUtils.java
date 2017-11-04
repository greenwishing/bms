package cn.greenwishing.bms.utils;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Frank wu
 * @date 2015/11/7.
 */
public class StringUtils {

    public static String join(Collection list, String sp) {
        if (list == null || list.isEmpty()) return "";
        if (sp == null) sp = "";
        Iterator iterator = list.iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            String val = "";
            if (next instanceof BmsEnum) {
                val = ((BmsEnum) next).getValue();
            } else {
                val = String.valueOf(next);
            }
            sb.append(val);
            if (iterator.hasNext()) {
                sb.append(sp);
            }
        }
        return sb.toString();
    }

    /**
     * 生成bit位的随机数
     *
     * @param bit 位数
     * @return 随机数
     */
    public static String random(int bit) {
        int min = (int) (Math.pow(10, bit - 1));
        int max = (int) (Math.pow(10, bit) - 1);
        return String.valueOf(random(min, max));
    }

    /**
     * 生成bit位的随机数，加前缀
     *
     * @param bit    位数
     * @param prefix 前缀
     * @return 随机数
     */
    public static String random(int bit, String prefix) {
        return prefix + random(bit);
    }

    /**
     * 生成min到max之间的随机数
     *
     * @param min 最小值
     * @param max 最大值
     * @return 随机数
     */
    public static int random(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }
}
