package cn.greenwishing.bms.domain;

import java.io.Serializable;

/**
 * @author Wu Fan
 */
public interface Domain extends Serializable {

    Serializable getId();

    String guid();
}
