package cn.greenwishing.bms.domain;

import cn.greenwishing.bms.commons.domain.Domain;
import cn.greenwishing.bms.utils.GuidGenerator;
import cn.greenwishing.bms.utils.JodaUtils;
import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * @author Wu Fan
 */
public abstract class AbstractDomain implements Domain {

    protected Integer id;
    protected String guid = GuidGenerator.generate();
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
        return id().hashCode();
    }

    @Override
    public Serializable getId() {
        return guid;
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
