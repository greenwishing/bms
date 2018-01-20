package cn.greenwishing.bms.dto.moment;

import cn.greenwishing.bms.domain.moment.GoalType;
import cn.greenwishing.bms.domain.moment.MomentType;
import cn.greenwishing.bms.dto.AbstractDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank wu
 * @date 2017/5/7
 */
public class MomentTypeDTO extends AbstractDTO {

    private String guid;
    private String name;
    private GoalType goalType;
    private Long goal;

    public MomentTypeDTO() {

    }

    public MomentTypeDTO(MomentType type) {
        this.guid = type.guid();
        this.name = type.name();
        this.goalType = type.goalType();
        this.goal = type.goal().getStandardSeconds() / 60;
    }

    public static List<MomentTypeDTO> toDTOs(List<MomentType> types) {
        List<MomentTypeDTO> typeDTOs = new ArrayList<>();
        for (MomentType type : types) {
            typeDTOs.add(new MomentTypeDTO(type));
        }
        return typeDTOs;
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

    public GoalType getGoalType() {
        return goalType;
    }

    public void setGoalType(GoalType goalType) {
        this.goalType = goalType;
    }

    public Long getGoal() {
        return goal;
    }

    public void setGoal(Long goal) {
        this.goal = goal;
    }
}
