package cn.greenwishing.bms.domain;

import java.io.Serializable;

/**
 * @author Frank wu
 */
public interface Domain extends Serializable {

    Serializable getId();

    String guid();
}
