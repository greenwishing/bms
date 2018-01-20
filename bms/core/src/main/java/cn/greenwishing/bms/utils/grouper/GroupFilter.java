package cn.greenwishing.bms.utils.grouper;

/**
 * User: WuFan
 * Date: 15-4-8 下午4:46
 */
public interface GroupFilter<K, E> {

    public boolean supported(K key, E element);
}
