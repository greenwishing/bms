package cn.greenwishing.bms.domain.metro;

import cn.greenwishing.bms.domain.AbstractDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * User: Wufan
 * Date: 2016/6/5
 */
@Entity
@Table(name = "station")
public class Station extends AbstractDomain {

    @Column(name = "name")
    private String name;

    @Column(name = "pinyin")
    private String pinyin;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Column(name = "latitude")
    private BigDecimal latitude;

    public Station() {
    }

    public void update(String name, String pinyin, BigDecimal longitude, BigDecimal latitude) {
        this.name = name;
        this.pinyin = pinyin;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String name() {
        return name;
    }

    public String pinyin() {
        return pinyin;
    }

    public BigDecimal longitude() {
        return longitude;
    }

    public BigDecimal latitude() {
        return latitude;
    }
}
