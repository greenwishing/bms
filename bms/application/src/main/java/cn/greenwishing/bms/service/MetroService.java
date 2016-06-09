package cn.greenwishing.bms.service;

import cn.greenwishing.bms.dto.metro.MetroLineDTO;
import cn.greenwishing.bms.dto.metro.SimpleMetroLineStation;
import cn.greenwishing.bms.dto.metro.StationDTO;

import java.util.List;

/**
 * @author Wufan
 * @date 2016/6/5
 */
public interface MetroService {
    void saveOrUpdateMetroLine(MetroLineDTO metroLineDTO);

    List<MetroLineDTO> loadMetroLines();

    MetroLineDTO loadMetroLineByGuid(String guid);

    List<StationDTO> loadStations();

    void saveOrUpdateStation(StationDTO stationDTO);

    StationDTO loadStationByGuid(String guid);

    boolean checkStationNameExists(String name);

    List<SimpleMetroLineStation> loadSimpleStationByMetroLine(Integer metroLineId);
}
