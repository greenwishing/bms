package cn.greenwishing.bms.dto.metro;

import cn.greenwishing.bms.domain.metro.Station;
import cn.greenwishing.bms.utils.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wufan
 * @date 2016/6/5
 */
public class StationDTO {

    private String guid;
    private String name;
    private String pinyin;
    private String longitude;
    private String latitude;

    public StationDTO() {
    }

    public StationDTO(Station station) {
        this.guid = station.guid();
        this.name = station.name();
        this.pinyin = station.pinyin();
        this.longitude = NumberUtils.toString(station.longitude());
        this.latitude = NumberUtils.toString(station.latitude());
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public static List<StationDTO> toDTOs(List<Station> stations) {
        List<StationDTO> stationDTOs = new ArrayList<>();
        for (Station station : stations) {
            StationDTO stationDTO = new StationDTO(station);
            stationDTOs.add(stationDTO);
        }
        return stationDTOs;
    }
}
