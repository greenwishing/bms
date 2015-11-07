package cn.greenwishing.bms.dto.oauth;

import cn.greenwishing.bms.domain.oauth.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wufan
 * @date 2015/11/7.
 */
public class OAuthClientDetailsDTO {

    private String clientId;
    private String clientSecret;
    private List<OAuthResourceId> resourceIds = new ArrayList<>();
    private List<OAuthScope> scope = new ArrayList<>();
    private List<OAuthGrantType> authorizedGrantTypes = new ArrayList<>();
    private String webServerRedirectURI;
    private List<OAuthAuthorities> authorities = new ArrayList<>();
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String additionalInformation;

    public OAuthClientDetailsDTO() {
    }

    public OAuthClientDetailsDTO(OAuthClientDetails client) {
        this.clientId = client.getClientId();
        this.clientSecret = client.getClientSecret();
        this.resourceIds = client.getResourceIds();
        this.scope = client.getScope();
        this.authorizedGrantTypes = client.getAuthorizedGrantTypes();
        this.webServerRedirectURI = client.getWebServerRedirectURI();
        this.authorities = client.getAuthorities();
        this.accessTokenValidity = client.getAccessTokenValidity();
        this.refreshTokenValidity = client.getRefreshTokenValidity();
        this.additionalInformation = client.getAdditionalInformation();
    }

    public static List<OAuthClientDetailsDTO> toDTOs(List<OAuthClientDetails> clients) {
        List<OAuthClientDetailsDTO> clientDTOs = new ArrayList<>();
        for (OAuthClientDetails client : clients) {
            OAuthClientDetailsDTO clientDTO = new OAuthClientDetailsDTO(client);
            clientDTOs.add(clientDTO);
        }
        return clientDTOs;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
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

    public OAuthClientDetails updateOAuthClientDetails(OAuthClientDetails details) {
        details.update(resourceIds, scope, authorizedGrantTypes, webServerRedirectURI, authorities, accessTokenValidity, refreshTokenValidity, additionalInformation);
        return details;
    }
}
