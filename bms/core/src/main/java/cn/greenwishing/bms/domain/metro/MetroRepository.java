package cn.greenwishing.bms.domain.metro;

import cn.greenwishing.bms.domain.Repository;
import cn.greenwishing.bms.utils.parser.SqlResultParser;

import java.util.List;

/**
 * @author Wufan
 * @date 2016/6/5
 */
public interface MetroRepository extends Repository {
    List<MetroLineStation> findMetroLineStations(String metroLineGuid);

    List<SqlResultParser> findSimpleMetroLineStations(Integer metroLineId);
}
