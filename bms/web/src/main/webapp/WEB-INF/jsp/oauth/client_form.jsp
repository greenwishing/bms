<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>客户端</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<div class="weui_tab">
    <div class="weui_tab_bd">
        <spring-form:form cssClass="form-horizontal" id="data-form" commandName="clientDTO" method="post" onsubmit="return false;">
            <div class="weui_cells weui_cells_form">
                <c:if test="${clientDTO.clientId!=null}">
                    <div class="weui_cell">
                        <div class="weui_cell_hd"><label class="weui_label">Client ID</label></div>
                        <div class="weui_cell_bd weui_cell_primary">${clientDTO.clientId}</div>
                    </div>
                    <div class="weui_cell">
                        <div class="weui_cell_hd"><label class="weui_label">Client Secret</label></div>
                        <div class="weui_cell_bd weui_cell_primary">${clientDTO.clientSecret}</div>
                    </div>
                </c:if>
            </div>
            <div class="weui_cells_title">ResourceIds</div>
            <div class="weui_cells weui_cells_checkbox">
                <c:forEach items="${resourceIdList}" var="resourceId">
                    <label class="weui_cell weui_check_label">
                        <div class="weui_cell_hd">
                            <spring-form:checkbox cssClass="weui_check" path="resourceIds" value="${resourceId.value}"/>
                            <i class="weui_icon_checked"></i>
                        </div>
                        <div class="weui_cell_bd weui_cell_primary">
                            <p>${resourceId.label}</p>
                        </div>
                    </label>
                </c:forEach>
            </div>
            <div class="weui_cells_title">Scope</div>
            <div class="weui_cells weui_cells_checkbox">
                <c:forEach items="${scopeList}" var="scope">
                    <label class="weui_cell weui_check_label">
                        <div class="weui_cell_hd">
                            <spring-form:checkbox cssClass="weui_check" path="scope" value="${scope.value}"/>
                            <i class="weui_icon_checked"></i>
                        </div>
                        <div class="weui_cell_bd weui_cell_primary">
                            <p>${scope.label}</p>
                        </div>
                    </label>
                </c:forEach>
            </div>
            <div class="weui_cells_title">GrantTypes</div>
            <div class="weui_cells weui_cells_checkbox">
                <c:forEach items="${grantTypeList}" var="grantType">
                    <label class="weui_cell weui_check_label">
                        <div class="weui_cell_hd">
                            <spring-form:checkbox cssClass="weui_check" path="authorizedGrantTypes" value="${grantType.value}"/>
                            <i class="weui_icon_checked"></i>
                        </div>
                        <div class="weui_cell_bd weui_cell_primary">
                            <p>${grantType.label}</p>
                        </div>
                    </label>
                </c:forEach>
            </div>
            <div class="weui_cells_title">Authorities</div>
            <div class="weui_cells weui_cells_checkbox">
                <c:forEach items="${authorityList}" var="authority">
                    <label class="weui_cell weui_check_label">
                        <div class="weui_cell_hd">
                            <spring-form:checkbox cssClass="weui_check" path="authorities" value="${authority.value}"/>
                            <i class="weui_icon_checked"></i>
                        </div>
                        <div class="weui_cell_bd weui_cell_primary">
                            <p>${authority.label}</p>
                        </div>
                    </label>
                    <label class="checkbox-inline"></label>
                </c:forEach>
            </div>
            <div class="weui_cells_tips">指定客户端所拥有的权限，若<code>Authorized Grant Types</code>包含了<code>Implicit</code>或<code>Client credentials</code>，则必须选择<code>Authorities</code>，服务端将根据该权限来判断是否有权限访问对应的API</div>
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell">
                    <div class="weui_cell_hd"><label class="weui_label">RedirectURI</label></div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <spring-form:input cssClass="weui_input" path="webServerRedirectURI" id="webServerRedirectURI" placeholder="Redirect URI"/>
                    </div>
                </div>
            </div>
            <div class="weui_cells_tips">若<code>Grant Types</code>包含了<code>Authorization code</code>或<code>Implicit</code>，则必须填写<code>Redirect URI</code></div>
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell">
                    <div class="weui_cell_hd">
                        <label class="weui_label">Token</label>
                    </div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <spring-form:input cssClass="weui_input" path="accessTokenValidity" id="accessTokenValidity" placeholder="Access Token Validity"/>
                    </div>
                </div>
            </div>
            <div class="weui_cells_tips">客户端的<code>access_token</code>的有效时间值(单位:秒)</div>
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell">
                    <div class="weui_cell_hd">
                        <label class="weui_label">Refresh</label>
                    </div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <spring-form:input cssClass="weui_input" path="refreshTokenValidity" id="refreshTokenValidity" placeholder="Refresh Token Validity"/>
                    </div>
                </div>
            </div>
            <div class="weui_cells_tips">客户端的<code>refresh_token</code>的有效时间值(单位:秒)</div>
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell">
                    <div class="weui_cell_hd">
                        <label class="weui_label">Additional</label>
                    </div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <spring-form:textarea class="weui_textarea" path="additionalInformation" id="additionalInformation" placeholder="Additional Information" cols="80" rows="3"/>
                    </div>
                </div>
            </div>
            <div class="weui_cells_tips">JSON格式的附加数据</div>
        </spring-form:form>
    </div>
    <div class="weui_tabbar" style="z-index: 1000;">
        <a class="weui_tabbar_item" href="javascript:void(0)" onclick="WF.form.ajaxSubmit($('#data-form'))">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_icons.png" alt="">
            </div>
            <p class="weui_tabbar_label">保存</p>
        </a>
        <a class="weui_tabbar_item" href="javascript:void(0)" onclick="history.back();">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_dialog.png" alt="">
            </div>
            <p class="weui_tabbar_label">返回</p>
        </a>
    </div>
</div>
</body>
</html>