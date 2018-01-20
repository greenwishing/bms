package cn.greenwishing.bms.utils.grouper;

import java.util.ArrayList;
import java.util.List;

/**
 * User: frank wu
 * Date: 2017/9/27
 */
public class Grouper {

    public static <K, E extends GroupItem<K>> GroupResults<K, E> group(List<E> elements) {
        return group(elements, null, null);
    }

    public static <K, E extends GroupItem<K>> GroupResults<K, E> group(List<E> elements, GroupFilter<K, E> filter) {
        return group(elements, null, filter);
    }

    public static <K, E extends GroupItem<K>> GroupResults<K, E> group(List<E> elements, GroupCallback<K, E> callback) {
        return group(elements, callback, null);
    }

    /**
     * 将传入的 elements 通过 key 进行分组
     *
     * @param elements 待分组的集合
     * @param callback 回调函数
     * @param filter   过滤器
     * @param <K>      分组
     * @param <E>      分组元素
     * @return 分组后 key list
     */
    public static <K, E extends GroupItem<K>> GroupResults<K, E> group(List<E> elements, GroupCallback<K, E> callback, GroupFilter<K, E> filter) {
        GroupResults<K, E> results = new GroupResults<>();
        for (E element : elements) {
            K key = element.key();
            GroupResult<K, E> result = results.getGroupResult(key);
            if (result == null) {
                result = new GroupResult<>(key);
                if (callback != null) {
                    callback.onKeyAdd(key);
                }
                results.add(result);
            } else {
                key = result.getKey(); // first key object, keep one key only
            }
            if (filter == null || filter.supported(key, element)) {
                if (callback != null) {
                    callback.onElementAdd(key, element);
                }
                result.add(element);
            }
        }
        return results;
    }

    public static <H extends GroupHolder<H, E>, E extends GroupItem<H>> List<H> groupByHolder(List<E> elements) {
        return groupByHolder(elements, null, null);
    }

    public static <H extends GroupHolder<H, E>, E extends GroupItem<H>> List<H> groupByHolder(List<E> elements, GroupCallback<H, E> callback) {
        return groupByHolder(elements, callback, null);
    }

    public static <H extends GroupHolder<H, E>, E extends GroupItem<H>> List<H> groupByHolder(List<E> elements, GroupFilter<H, E> filter) {
        return groupByHolder(elements, null, filter);
    }

    /**
     * 将传入的 elements 通过 key 进行分组
     *
     * @param elements 待分组的集合
     * @param callback 回调函数
     * @param filter   过滤器
     * @param <H>      可添加子元素的分组
     * @param <E>      分组元素
     * @return 分组后 key list
     */
    public static <H extends GroupHolder<H, E>, E extends GroupItem<H>> List<H> groupByHolder(List<E> elements, GroupCallback<H, E> callback, GroupFilter<H, E> filter) {
        List<H> holders = new ArrayList<>();
        GroupResults<H, E> results = group(elements, callback, filter);
        for (GroupResult<H, E> result : results.getGroupResults()) {
            H holder = result.getKey();
            holder.addAll(result.getResults());
            holders.add(holder);
        }
        return holders;
    }
}
