package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.domain.moment.*;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.domain.user.UserRepository;
import cn.greenwishing.bms.dto.moment.MomentDTO;
import cn.greenwishing.bms.dto.moment.MomentPagingDTO;
import cn.greenwishing.bms.dto.moment.MomentTypeDTO;
import cn.greenwishing.bms.service.MomentService;
import cn.greenwishing.bms.utils.SecurityHolder;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Frank wu
 * @date 2017/5/7
 */
@Service("momentService")
public class MomentServiceImpl implements MomentService {

    @Resource
    private MomentRepository momentRepository;
    @Resource
    private UserRepository userRepository;

    @Override
    public List<MomentTypeDTO> loadMomentTypes() {
        Integer userId = SecurityHolder.getUserId();
        List<MomentType> types = momentRepository.findMomentTypes(userId);
        return MomentTypeDTO.toDTOs(types);
    }

    @Override
    public MomentTypeDTO loadMomentTypeByGuid(String guid) {
        MomentType momentType = momentRepository.findByGuid(MomentType.class, guid);
        return new MomentTypeDTO(momentType);
    }

    @Override
    public void saveOrUpdateMomentType(MomentTypeDTO momentTypeDTO) {
        MomentType momentType;
        String guid = momentTypeDTO.getGuid();
        if (ValidationUtils.isEmpty(guid)) {
            String userGuid = SecurityHolder.getUserGuid();
            User user = userRepository.findByGuid(User.class, userGuid);
            momentType = new MomentType(user);
        } else {
            momentType = momentRepository.findByGuid(MomentType.class, guid);
        }
        String name = momentTypeDTO.getName();
        GoalType goalType = momentTypeDTO.getGoalType();
        Duration goal = null;
        if (GoalType.NONE != goalType) {
            goal = Duration.standardMinutes(momentTypeDTO.getGoal());
        }
        momentType.update(name, goalType, goal);
        momentRepository.saveOrUpdate(momentType);
    }

    @Override
    public MomentPagingDTO loadMomentPaging(MomentPagingDTO pagingDTO) {
        MomentPaging paging = momentRepository.findMomentPaging(pagingDTO.toPaging());
        return pagingDTO.convertResult(paging);
    }

    @Override
    public MomentDTO loadMomentByGuid(String guid) {
        Moment moment = momentRepository.findByGuid(Moment.class, guid);
        return new MomentDTO(moment);
    }

    @Override
    public void saveOrUpdateMoment(MomentDTO momentDTO) {
        Moment moment;
        MomentType type = null;
        String typeGuid = momentDTO.getTypeGuid();
        if (ValidationUtils.isNotEmpty(typeGuid)) {
            type = momentRepository.findByGuid(MomentType.class, typeGuid);
        }
        String guid = momentDTO.getGuid();
        if (ValidationUtils.isEmpty(guid)) {
            String userGuid = SecurityHolder.getUserGuid();
            User user = userRepository.findByGuid(User.class, userGuid);
            moment = new Moment(user);
        } else {
            moment = momentRepository.findByGuid(Moment.class, guid);
        }
        String dateStr = momentDTO.getDate();
        String startTimeStr = momentDTO.getStartTime();
        String endTimeStr = momentDTO.getEndTime();
        String description = momentDTO.getDescription();
        LocalDate date = new LocalDate(dateStr);
        LocalTime startTime = new LocalTime(startTimeStr);
        LocalTime endTime = new LocalTime(endTimeStr);
        moment.update(type, date, startTime, endTime, description);
        momentRepository.saveOrUpdate(moment);
    }
}
