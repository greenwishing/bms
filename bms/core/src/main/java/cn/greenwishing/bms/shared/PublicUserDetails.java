package cn.greenwishing.bms.shared;

import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.domain.user.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Wu Fan
 */
public class PublicUserDetails implements UserDetails {

    private String guid;
    private String username; // account
    private String nickname; // username
    private String password;
    private UserStatus status;

    private List<GrantedAuthority> authorities = new ArrayList<>();

    public PublicUserDetails() {
        addAuthority("ROLE_GUEST");
    }

    public PublicUserDetails(User user) {
        this();

        this.guid = user.guid();
        this.username = user.account();
        this.nickname = user.username();
        this.password = user.password();
        this.status = user.status();

        if (User.ADMIN_GUID.equals(this.guid)) {
            addAuthority("ROLE_USER");
            addAuthority("ROLE_METRO");
            addAuthority("ROLE_OAUTH");
        }
        addAuthority("ROLE_SYSTEM");
        addAuthority("ROLE_BILLING");
        addAuthority("ROLE_ARTICLE");

    }

    public void addAuthority(String authorityString) {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(authorityString);
        this.authorities.add(authority);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserStatus.EXPIRED != status;
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserStatus.LOCKED != status;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return UserStatus.ENABLED == status;
    }

    public String getGuid() {
        return guid;
    }

    public String getNickname() {
        return nickname;
    }

    public UserStatus getStatus() {
        return status;
    }
}
