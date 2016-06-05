package cn.greenwishing.bms.domain.metro;

/**
 * @author Wufan
 * @date 2016/6/5
 */
public enum MetroLineStationStatus {
    UNKNOWN("未知"),
    PLANNING("计划中"),
    BUILDING("在建中"),
    RUNNING("运营中"),
    ;

    private String label;

    MetroLineStationStatus(String label) {
        this.label = label;
    }

    public String getValue() {
        return name();
    }

    public String getLabel() {
        return label;
    }
}
