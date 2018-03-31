package cn.greenwishing.bms.dto.metro;

import cn.greenwishing.bms.domain.metro.MetroLineStationStatus;
import cn.greenwishing.bms.shared.EnumUtils;
import cn.greenwishing.bms.utils.parser.SqlResultParser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank wu
 * @date 2016/6/9
 */
public class SimpleMetroLineStation {

    // ms.id, s.name, s.longitude, s.latitude, ms.status
    private Integer id;
    private String guid;
    private String stationGuid;
    private String stationName;
    private Double longitude;
    private Double latitude;
    private MetroLineStationStatus status;

    public SimpleMetroLineStation() {
    }

    public SimpleMetroLineStation(SqlResultParser parser) {
        this.id = parser.getInt(0);
        this.guid = parser.getString(1);
        this.stationGuid = parser.getString(2);
        this.stationName = parser.getString(3);
        this.longitude = parser.getDecimal(4, BigDecimal.ZERO).doubleValue();
        this.latitude = parser.getDecimal(5, BigDecimal.ZERO).doubleValue();
        this.status = EnumUtils.nameOf(MetroLineStationStatus.class, parser.getString(6));
    }

    public static List<SimpleMetroLineStation> valueOf(List<SqlResultParser> parsers) {
        List<SimpleMetroLineStation> ms = new ArrayList<>();
        parsers.forEach(parser -> {
            SimpleMetroLineStation s = new SimpleMetroLineStation(parser);
            ms.add(s);
        });
        return ms;
    }

    public Integer getId() {
        return id;
    }

    public String getGuid() {
        return guid;
    }

    public String getStationGuid() {
        return stationGuid;
    }

    public String getStationName() {
        return stationName;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public MetroLineStationStatus getStatus() {
        return status;
    }

    public boolean isRunning() {
        return MetroLineStationStatus.RUNNING == status;
    }
}
