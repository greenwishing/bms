package cn.greenwishing.bms.dto.moment;

import cn.greenwishing.bms.domain.moment.Moment;
import cn.greenwishing.bms.domain.moment.MomentType;
import cn.greenwishing.bms.utils.JodaUtils;
import org.joda.time.LocalTime;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank wu
 * @date 2017/5/7
 */
public class MomentDTO {

    private String guid;
    private String typeGuid;
    private String typeName;
    private String date = JodaUtils.localDateToString(JodaUtils.today());
    private String startTime = JodaUtils.localTimeToString(JodaUtils.now().toLocalTime().minusHours(1));
    private String endTime = JodaUtils.localTimeToString(JodaUtils.now().toLocalTime());
    private String description;

    private String friendlyTime; // for display

    public MomentDTO() {
    }

    public MomentDTO(Moment moment) {
        this.guid = moment.guid();
        MomentType type = moment.type();
        if (type != null) {
            this.typeGuid = type.guid();
            this.typeName = type.name();
        }
        this.date = JodaUtils.localDateToString(moment.date());
        LocalTime startTime = moment.startTime();
        LocalTime endTime = moment.endTime();
        this.startTime = JodaUtils.localTimeToString(startTime);
        this.endTime = JodaUtils.localTimeToString(endTime);
        Period period = Period.fieldDifference(startTime, endTime);
        int hours = period.getHours();
        int minutes = period.getMinutes();
        if (minutes < 0) {
            minutes = 60 + minutes;
            hours -= 1;
        }
        this.friendlyTime = hours > 0 ? hours + "小时" : minutes > 0 ? minutes + "分钟" : "";
        this.description = moment.description();
    }

    public static List<MomentDTO> toDTOs(List<Moment> moments) {
        List<MomentDTO> momentDTOs = new ArrayList<>();
        moments.forEach(moment -> momentDTOs.add(new MomentDTO(moment)));
        return momentDTOs;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getTypeGuid() {
        return typeGuid;
    }

    public void setTypeGuid(String typeGuid) {
        this.typeGuid = typeGuid;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFriendlyTime() {
        return friendlyTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
