package cn.greenwishing.bms.dto.metro;

import cn.greenwishing.bms.domain.metro.MetroLineStation;
import cn.greenwishing.bms.domain.metro.MetroLineStationStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank wu
 * @date 2016/6/5
 */
public class MetroLineStationDTO {

    private String guid;
    private StationDTO station;
    private MetroLineStationStatus status;
    private Integer sort;

    public MetroLineStationDTO() {
    }

    public MetroLineStationDTO(MetroLineStation metroLineStation) {
        this.guid = metroLineStation.guid();
        this.station = new StationDTO(metroLineStation.station());
        this.status = metroLineStation.status();
        this.sort = metroLineStation.sort();
    }

    public String getGuid() {
        return guid;
    }

    public StationDTO getStation() {
        return station;
    }

    public MetroLineStationStatus getStatus() {
        return status;
    }

    public boolean isRunning() {
        return MetroLineStationStatus.RUNNING == status;
    }

    public Integer getSort() {
        return sort;
    }

    public static List<MetroLineStationDTO> toDTOs(List<MetroLineStation> metroLineStations) {
        List<MetroLineStationDTO> metroLineStationDTOs = new ArrayList<>();
        metroLineStations.forEach(metroLineStation -> {
            MetroLineStationDTO metroLineStationDTO = new MetroLineStationDTO(metroLineStation);
            metroLineStationDTOs.add(metroLineStationDTO);
        });
        return metroLineStationDTOs;
    }
}
