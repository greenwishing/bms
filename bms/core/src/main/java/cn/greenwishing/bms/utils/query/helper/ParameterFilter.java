package cn.greenwishing.bms.utils.query.helper;

import org.hibernate.query.Query;

public abstract class ParameterFilter implements Filter {

    public abstract void setParameter(Query query);
}