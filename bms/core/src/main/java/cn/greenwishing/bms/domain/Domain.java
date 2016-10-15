package cn.greenwishing.bms.domain;

import java.io.Serializable;

/**
 * User: Wu Fan
 */
public interface Domain extends Serializable {

    Serializable getId();

    String guid();
}
