package cn.greenwishing.bms.utils.grouper;

/**
 * User: WuFan
 * Date: 14-11-26 下午1:41
 */
public interface GroupCallback<K, E> {

    void onKeyAdd(K key);

    void onElementAdd(K key, E element);
}
