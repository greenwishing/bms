package cn.greenwishing.bms.domain;

import cn.greenwishing.bms.utils.GuidGenerator;
import cn.greenwishing.bms.utils.JodaUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * User: Wu Fan
 */
@MappedSuperclass
public abstract class AbstractDomain implements Domain {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "native")
    protected Integer id;

    @Column(name = "guid")
    protected String guid = GuidGenerator.generate();

    @Column(name = "creation_time")
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    protected DateTime creationTime = JodaUtils.now();

    protected AbstractDomain() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractDomain)) {
            return false;
        }
        AbstractDomain that = (AbstractDomain) o;
        if (!guid().equals(that.guid())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public Serializable getId() {
        return id;
    }

    public Integer id() {
        return id;
    }

    public String guid() {
        return guid;
    }

    public DateTime creationTime() {
        return creationTime;
    }
}
