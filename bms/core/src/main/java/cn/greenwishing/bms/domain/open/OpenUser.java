package cn.greenwishing.bms.domain.open;

import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.dto.open.WeAppUserInfo;
import cn.greenwishing.bms.utils.GsonUtil;
import cn.greenwishing.bms.utils.JodaUtils;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * @author Wufan
 * @date 2018/1/6
 */
@Entity
@Table(name = "`open_user`")
public class OpenUser extends AbstractDomain {

    @Column(name = "openid")
    private String openid;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "avatar")
    private String avatar;

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column(name = "ext_data")
    private String extData;

    @Column(name = "update_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime updateTime;

    public OpenUser() {
    }

    public OpenUser(String openid) {
        this.openid = openid;
    }

    public void update(WeAppUserInfo extData) {
        this.nickname = extData.getNickName();
        this.avatar = extData.getAvatarUrl();
        this.extData = GsonUtil.toJson(extData);
        this.updateTime = JodaUtils.now();
    }

    public void update(User user) {
        this.user = user;
        this.updateTime = JodaUtils.now();
    }

    public String openid() {
        return openid;
    }

    public String nickname() {
        return nickname;
    }

    public String avatar() {
        return avatar;
    }

    public User user() {
        return user;
    }

    public String extData() {
        return extData;
    }

    public DateTime updateTime() {
        return updateTime;
    }
}
