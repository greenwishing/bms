package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.domain.metro.MetroLine;
import cn.greenwishing.bms.domain.metro.MetroRepository;
import cn.greenwishing.bms.domain.metro.Station;
import cn.greenwishing.bms.dto.metro.MetroLineDTO;
import cn.greenwishing.bms.dto.metro.SimpleMetroLineStation;
import cn.greenwishing.bms.dto.metro.StationDTO;
import cn.greenwishing.bms.service.MetroService;
import cn.greenwishing.bms.utils.ValidationUtils;
import cn.greenwishing.bms.utils.parser.SqlResultParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: Wufan
 * Date: 2016/6/5
 */
@Service("metroService")
public class MetroServiceImpl implements MetroService {

    @Autowired
    private MetroRepository metroRepository;

    @Override
    public void saveOrUpdateMetroLine(MetroLineDTO metroLineDTO) {
        MetroLine metroLine;
        String guid = metroLineDTO.getGuid();
        if (ValidationUtils.isNotEmpty(guid)) {
            metroLine = metroRepository.findByGuid(MetroLine.class, guid);
        } else {
            metroLine = new MetroLine();
        }
        String name = metroLineDTO.getName();
        String color = metroLineDTO.getColor();
        boolean loop = metroLineDTO.isLoop();
        metroLine.update(name, color, loop);
        metroRepository.saveOrUpdate(metroLine);
    }

    @Override
    public List<MetroLineDTO> loadMetroLines() {
        List<MetroLine> lines = metroRepository.findAll(MetroLine.class);
        return MetroLineDTO.toDTOs(lines);
    }

    @Override
    public MetroLineDTO loadMetroLineByGuid(String guid) {
        MetroLine metroLine = metroRepository.findByGuid(MetroLine.class, guid);
        return new MetroLineDTO(metroLine);
    }

    @Override
    public List<StationDTO> loadStations() {
        List<Station> stations = metroRepository.findAll(Station.class);
        return StationDTO.toDTOs(stations);
    }

    @Override
    public void saveOrUpdateStation(StationDTO stationDTO) {
        Station station;
        String guid = stationDTO.getGuid();
        if (ValidationUtils.isNotEmpty(guid)) {
            station = metroRepository.findByGuid(Station.class, guid);
        } else {
            station = new Station();
        }
        String name = stationDTO.getName();
        String pinyin = stationDTO.getPinyin();
        BigDecimal longitude = null;
        BigDecimal latitude = null;
        String longitudeStr = stationDTO.getLongitude();
        if (ValidationUtils.isPositiveBigDecimal(longitudeStr)) {
            longitude = new BigDecimal(longitudeStr);
        }
        String latitudeStr = stationDTO.getLatitude();
        if (ValidationUtils.isPositiveBigDecimal(latitudeStr)) {
            latitude = new BigDecimal(latitudeStr);
        }
        station.update(name, pinyin, longitude, latitude);
    }

    @Override
    public StationDTO loadStationByGuid(String guid) {
        Station station = metroRepository.findByGuid(Station.class, guid);
        return new StationDTO(station);
    }

    @Override
    public boolean checkStationNameExists(String name) {
        Long count = (Long) metroRepository.singleResult("select count(*) from Station s where s.name=?", name);
        return count > 0;
    }

    @Override
    public List<SimpleMetroLineStation> loadSimpleStationByMetroLine(Integer metroLineId) {
        List<SqlResultParser> parsers = metroRepository.findSimpleMetroLineStations(metroLineId);
        return SimpleMetroLineStation.valueOf(parsers);
    }
}
