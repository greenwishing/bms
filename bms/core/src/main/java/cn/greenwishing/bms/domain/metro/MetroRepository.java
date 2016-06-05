package cn.greenwishing.bms.domain.metro;

import cn.greenwishing.bms.domain.Repository;

import java.util.List;

/**
 * @author Wufan
 * @date 2016/6/5
 */
public interface MetroRepository extends Repository {
    List<MetroLineStation> findMetroLineStations(String metroLineGuid);
}
