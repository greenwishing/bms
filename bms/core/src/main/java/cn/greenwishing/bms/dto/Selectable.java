package cn.greenwishing.bms.dto;

import java.io.Serializable;

/**
 * @author Wufan
 * @date 2018/1/13
 */
public interface Selectable extends Serializable {

    Serializable getValue();

    String getLabel();
}
