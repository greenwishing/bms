package cn.greenwishing.bms.persistence.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

/**
 * User: WuFan
 */
public class EnumVarcharType implements UserType, ParameterizedType {

    private static Logger log = Logger.getLogger(EnumVarcharType.class);

    private String enumClass;
    private Class clazz;

    /**
     * Init enum class
     *
     * @return Whether or not init success
     */
    private boolean initClass() {
        if (clazz == null) {
            try {
                clazz = Class.forName(enumClass);
                return true;
            } catch (ClassNotFoundException e) {
                if (log.isDebugEnabled()) {
                    log.debug("Can not found class: " + enumClass, e);
                }
                return false;
            }
        }
        return true;
    }

    public int[] sqlTypes() {
        return new int[]{Types.INTEGER};
    }

    public Class returnedClass() {
        if (initClass()) {
            return clazz;
        }
        return null;
    }

    public boolean equals(Object o, Object o1) throws HibernateException {
        return o == o1 || !(o == null || o1 == null) && o.equals(o1);
    }

    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    public Object nullSafeGet(ResultSet resultSet, String[] strings, Object o) throws HibernateException, SQLException {
        final String val = resultSet.getString(strings[0]);
        Object obj = null;
        if (!resultSet.wasNull() && initClass()) {
            obj = Enum.valueOf(clazz, val.toUpperCase());
        }
        return obj;
    }

    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index) throws HibernateException, SQLException {
        if (null == value) {
            preparedStatement.setNull(index, Types.VARBINARY);
        } else {
            preparedStatement.setString(index, ((Enum) value).name());
        }
    }

    public Object deepCopy(Object o) throws HibernateException {
        return o;
    }

    public boolean isMutable() {
        return false;
    }

    public Serializable disassemble(Object o) throws HibernateException {
        return (Serializable) o;
    }

    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return serializable;
    }

    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return o;
    }

    public String getEnumClass() {
        return enumClass;
    }

    public void setEnumClass(String enumClass) {
        this.enumClass = enumClass;
    }

    public void setParameterValues(Properties properties) {
        enumClass = properties.getProperty("enumClass");
    }
}