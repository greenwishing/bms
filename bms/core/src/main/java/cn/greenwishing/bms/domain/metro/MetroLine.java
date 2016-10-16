package cn.greenwishing.bms.domain.metro;

import cn.greenwishing.bms.domain.AbstractDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: Wufan
 * Date: 2016/6/5
 */
@Entity
@Table(name = "metro_line")
public class MetroLine extends AbstractDomain {

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "`loop`")
    private boolean loop;

    public MetroLine() {
    }

    public void update(String name, String color, boolean loop) {
        this.name = name;
        this.color = color;
        this.loop = loop;
    }

    public String name() {
        return name;
    }

    public String color() {
        return color;
    }

    public boolean loop() {
        return loop;
    }
}
