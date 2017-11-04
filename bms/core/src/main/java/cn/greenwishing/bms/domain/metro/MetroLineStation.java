package cn.greenwishing.bms.domain.metro;

import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;

import javax.persistence.*;

/**
 * @author Frank wu
 * @date 2016/6/5
 */
@Entity
@Table(name = "metro_line_station")
public class MetroLineStation extends AbstractDomain {

    @JoinColumn(name = "metro_line_id")
    @ManyToOne(targetEntity = MetroLine.class)
    private MetroLine line;

    @JoinColumn(name = "station_id")
    @ManyToOne(targetEntity = Station.class)
    private Station station;

    @JoinColumn(name = "sort")
    private Integer sort;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private MetroLineStationStatus status = MetroLineStationStatus.UNKNOWN;

    public MetroLineStation() {
    }

    public MetroLineStation(MetroLine line, Station station) {
        this.line = line;
        this.station = station;
    }

    public void update(MetroLineStationStatus status) {
        this.status = status;
    }

    public MetroLine line() {
        return line;
    }

    public Station station() {
        return station;
    }

    public Integer sort() {
        return sort;
    }

    public MetroLineStationStatus status() {
        return status;
    }
}
