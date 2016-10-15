package cn.greenwishing.bms.utils;

import java.util.Collection;
import java.util.Iterator;

/**
 * User: Wufan
 * Date: 2015/11/7.
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
}
