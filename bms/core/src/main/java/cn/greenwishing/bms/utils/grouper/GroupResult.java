package cn.greenwishing.bms.utils.grouper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User: Wu Fan
 */
public class GroupResult<K, E> {
    private K key;
    private List<E> results = new ArrayList<>();

    public GroupResult(K key) {
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    public List<E> getResults() {
        return results;
    }

    public void add(E element) {
        results.add(element);
    }

    public void addAll(Collection<E> elements) {
        results.addAll(elements);
    }
}
