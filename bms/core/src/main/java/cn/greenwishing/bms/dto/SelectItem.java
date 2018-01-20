package cn.greenwishing.bms.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wufan
 * @date 2018/1/13
 */
public class SelectItem implements Selectable {

    private Serializable value;
    private String label;

    public SelectItem(Selectable selectable) {
        this.value = selectable.getValue();
        this.label = selectable.getLabel();
    }

    public SelectItem(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static <T extends Selectable> List<SelectItem> valueOf (List<T> selectList) {
        List<SelectItem> items = new ArrayList<>();
        for (T select : selectList) {
            items.add(new SelectItem(select));
        }
        return items;
    }

    @Override
    public Serializable getValue() {
        return value;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SelectItem that = (SelectItem) o;

        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
