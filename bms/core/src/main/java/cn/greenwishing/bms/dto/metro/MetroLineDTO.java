package cn.greenwishing.bms.dto.metro;

import cn.greenwishing.bms.domain.metro.MetroLine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank wu
 * @date 2016/6/5
 */
public class MetroLineDTO {

    private Integer id;
    private String guid;
    private String name;
    private String color;
    private boolean loop;

    public MetroLineDTO() {
    }

    public MetroLineDTO(MetroLine line) {
        this.id = line.id();
        this.guid = line.guid();
        this.name = line.name();
        this.color = line.color();
        this.loop = line.loop();
    }

    public Integer getId() {
        return id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public static List<MetroLineDTO> toDTOs(List<MetroLine> lines) {
        List<MetroLineDTO> lineDTOs = new ArrayList<>();
        for (MetroLine line : lines) {
            MetroLineDTO lineDTO = new MetroLineDTO(line);
            lineDTOs.add(lineDTO);
        }
        return lineDTOs;
    }
}
