package cn.greenwishing.bms.utils.grouper;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Wu Fan
 */
public class GroupResults<K, E> {
    private List<GroupResult<K, E>> groupResults = new ArrayList<>();

    public List<GroupResult<K, E>> getGroupResults() {
        return groupResults;
    }

    public boolean isEmpty() {
        return groupResults.isEmpty();
    }

    public void add(GroupResult<K, E> groupedResult) {
        groupResults.add(groupedResult);
    }

    public GroupResult<K, E> getGroupResult(K key) {
        for (GroupResult<K, E> groupResult : groupResults) {
            if (key.equals(groupResult.getKey())) {
                return groupResult;
            }
        }
        return null;
    }

    public List<K> getKeys() {
        List<K> keys = new ArrayList<>();
        for (GroupResult<K, E> groupResult : groupResults) {
            K key = groupResult.getKey();
            if (key != null) {
                keys.add(key);
            }
        }
        return keys;
    }
}
