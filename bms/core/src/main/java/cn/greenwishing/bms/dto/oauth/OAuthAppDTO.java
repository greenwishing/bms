package cn.greenwishing.bms.dto.oauth;

import cn.greenwishing.bms.domain.oauth.*;
import cn.greenwishing.bms.dto.AbstractDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Frank wu
 * @date 2015/11/7.
 */
public class OAuthAppDTO extends AbstractDTO {

    private String appId;
    private String appSecret;
    private List<OAuthResourceId> resourceIds = new ArrayList<>();
    private List<OAuthScope> scope = new ArrayList<>();
    private List<OAuthGrantType> authorizedGrantTypes = new ArrayList<>();
    private String webServerRedirectURI;
    private List<OAuthAuthorities> authorities = new ArrayList<>();
    private Integer accessTokenValidity = 7200;
    private Integer refreshTokenValidity = 7200;
    private String additionalInformation;

    public OAuthAppDTO() {
        Collections.addAll(resourceIds, OAuthResourceId.values());
        Collections.addAll(scope, OAuthScope.values());
        Collections.addAll(authorizedGrantTypes, OAuthGrantType.values());
        Collections.addAll(authorities, OAuthAuthorities.values());
    }

    public OAuthAppDTO(App app) {
        this.appId = app.getAppId();
        this.appSecret = app.getAppSecret();
        this.resourceIds = app.getResourceIds();
        this.scope = app.getScope();
        this.authorizedGrantTypes = app.getAuthorizedGrantTypes();
        this.webServerRedirectURI = app.getWebServerRedirectURI();
        this.authorities = app.getAuthorities();
        this.accessTokenValidity = app.getAccessTokenValidity();
        this.refreshTokenValidity = app.getRefreshTokenValidity();
        this.additionalInformation = app.getAdditionalInformation();
    }

    public static List<OAuthAppDTO> toDTOs(List<App> apps) {
        List<OAuthAppDTO> appDTOs = new ArrayList<>();
        for (App app : apps) {
            OAuthAppDTO appDTO = new OAuthAppDTO(app);
            appDTOs.add(appDTO);
        }
        return appDTOs;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public List<OAuthResourceId> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<OAuthResourceId> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public List<OAuthScope> getScope() {
        return scope;
    }

    public void setScope(List<OAuthScope> scope) {
        this.scope = scope;
    }

    public List<OAuthGrantType> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(List<OAuthGrantType> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getWebServerRedirectURI() {
        return webServerRedirectURI;
    }

    public void setWebServerRedirectURI(String webServerRedirectURI) {
        this.webServerRedirectURI = webServerRedirectURI;
    }

    public List<OAuthAuthorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<OAuthAuthorities> authorities) {
        this.authorities = authorities;
    }

    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public App update(App details) {
        details.update(resourceIds, scope, authorizedGrantTypes, webServerRedirectURI, authorities, accessTokenValidity, refreshTokenValidity, additionalInformation);
        return details;
    }
}
