package cn.greenwishing.bms.domain.oauth;

import cn.greenwishing.bms.domain.Domain;
import cn.greenwishing.bms.shared.EnumUtils;
import cn.greenwishing.bms.utils.GuidGenerator;
import cn.greenwishing.bms.utils.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Wufan
 * @date 2015/11/7.
 */
@Entity
@Table(name = "oauth_client_details")
public class OAuthClientDetails implements Domain {

    public static final String CLIENT_ID_PREFIX = "bms";
    public static final String PUBLIC_CLIENT_ID = "test888888";
    public static final String PUBLIC_CLIENT_SECRET = "d25f622a6f0c4d34a27ac2864f9bb091";

    @Id
    @Column(name = "client_id")
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "assigned")
    private String clientId = CLIENT_ID_PREFIX + GuidGenerator.generate(13);
    @Column(name = "client_secret")
    private String clientSecret = GuidGenerator.generate();
    /**
     * {@link OAuthResourceId}
     */
    @Column(name = "resource_ids")
    private String resourceIds;
    /**
     * {@link OAuthScope}
     */
    @Column(name = "scope")
    private String scope;
    /**
     * {@link OAuthGrantType}
     */
    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;
    /**
     * <p>若<code>authorizedGrantTypes</code>包括<em>AUTHORIZATION_CODE</em>或<em>IMPLICIT</em>，
     * 则必须填写<code>webServerRedirectURI</code></p>
     */
    @Column(name = "web_server_redirect_uri")
    private String webServerRedirectURI;
    /**
     * <p>指定客户端所拥有的Spring Security的权限值,可选;
     * 若<code>authorizedGrantTypes</code>为<em>IMPLICIT</em>或<em>CLIENT_CREDENTIALS</em>，
     * 则必须填写authorities，服务端将根据该字段值的权限来判断是否有权限访问对应的API</p>
     */
    @Column(name = "authorities")
    private String authorities;
    /**
     * <p>客户端的access_token的有效时间值(单位:秒)</p>
     */
    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;
    /**
     * <p>客户端的refresh_token的有效时间值(单位:秒)</p>
     */
    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;
    /**
     * <p>JSON格式的附加数据</p>
     */
    @Column(name = "additional_information")
    private String additionalInformation;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Domain)) {
            return false;
        }
        Domain that = (Domain) o;
        if (!guid().equals(that.guid())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    public void update(
            List<OAuthResourceId> resourceIds,
            List<OAuthScope> scope,
            List<OAuthGrantType> authorizedGrantTypes,
            String webServerRedirectURI,
            List<OAuthAuthorities> authorities,
            Integer accessTokenValidity,
            Integer refreshTokenValidity,
            String additionalInformation) {
        this.resourceIds = StringUtils.join(resourceIds, ",");
        this.scope = StringUtils.join(scope, ",");
        this.authorizedGrantTypes = StringUtils.join(authorizedGrantTypes, ",");
        this.webServerRedirectURI = webServerRedirectURI;
        this.authorities = StringUtils.join(authorities, ",");
        this.accessTokenValidity = accessTokenValidity;
        this.refreshTokenValidity = refreshTokenValidity;
        this.additionalInformation = additionalInformation;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public List<OAuthResourceId> getResourceIds() {
        return EnumUtils.fromValue(OAuthResourceId.class, this.resourceIds);
    }

    public List<OAuthScope> getScope() {
        return EnumUtils.fromValue(OAuthScope.class, this.scope);
    }

    public List<OAuthGrantType> getAuthorizedGrantTypes() {
        return EnumUtils.fromValue(OAuthGrantType.class, this.authorizedGrantTypes);
    }

    public String getWebServerRedirectURI() {
        return webServerRedirectURI;
    }

    public List<OAuthAuthorities> getAuthorities() {
        return EnumUtils.fromValue(OAuthAuthorities.class, this.authorities);
    }

    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    @Override
    public Serializable getId() {
        return clientId;
    }

    @Override
    public String guid() {
        return clientId;
    }
}
