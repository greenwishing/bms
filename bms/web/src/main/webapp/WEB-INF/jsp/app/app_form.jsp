<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>应用设置</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<div class="weui_tab">
    <div class="weui_tab_bd">
        <spring-form:form cssClass="form-horizontal" id="data-form" commandName="appDTO" method="post" onsubmit="return false;">
            <div class="weui_cells weui_cells_form">
                <c:if test="${appDTO.appId!=null}">
                    <div class="weui_cell">
                        <div class="weui_cell_hd"><label class="weui_label">应用ID</label></div>
                        <div class="weui_cell_bd weui_cell_primary">${appDTO.appId}</div>
                    </div>
                    <div class="weui_cell">
                        <div class="weui_cell_hd"><label class="weui_label">应用密钥</label></div>
                        <div class="weui_cell_bd weui_cell_primary">${appDTO.appSecret}</div>
                    </div>
                </c:if>
            </div>
            <div class="weui_cells_title">资源列表</div>
            <div class="weui_cells weui_cells_checkbox">
                <c:forEach items="${resourceIdList}" var="resourceId">
                    <label class="weui_cell weui_check_label">
                        <div class="weui_cell_hd">
                            <input type="checkbox" class="weui_check" name="resourceIds" value="${resourceId.value}" ${fn:contains(appDTO.resourceIds, resourceId.value) ? 'checked':''}/>
                            <i class="weui_icon_checked"></i>
                        </div>
                        <div class="weui_cell_bd weui_cell_primary">
                            <p>${resourceId.label}</p>
                        </div>
                    </label>
                </c:forEach>
            </div>
            <div class="weui_cells_title">范围</div>
            <div class="weui_cells weui_cells_checkbox">
                <c:forEach items="${scopeList}" var="scope">
                    <label class="weui_cell weui_check_label">
                        <div class="weui_cell_hd">
                            <input type="checkbox" class="weui_check" name="scope" value="${scope.value}" ${fn:contains(appDTO.scope, scope.value) ? 'checked':''}/>
                            <i class="weui_icon_checked"></i>
                        </div>
                        <div class="weui_cell_bd weui_cell_primary">
                            <p>${scope.label}</p>
                        </div>
                    </label>
                </c:forEach>
            </div>
            <div class="weui_cells_title">授权类型</div>
            <div class="weui_cells weui_cells_checkbox">
                <c:forEach items="${grantTypeList}" var="grantType">
                    <label class="weui_cell weui_check_label">
                        <div class="weui_cell_hd">
                            <input type="checkbox" class="weui_check" name="authorizedGrantTypes" value="${grantType.value}" ${fn:contains(appDTO.authorizedGrantTypes, grantType.value) ? 'checked':''}/>
                            <i class="weui_icon_checked"></i>
                        </div>
                        <div class="weui_cell_bd weui_cell_primary">
                            <p>${grantType.label}</p>
                        </div>
                    </label>
                </c:forEach>
            </div>
            <div class="weui_cells_title">权限</div>
            <div class="weui_cells weui_cells_checkbox">
                <c:forEach items="${authorityList}" var="authority">
                    <label class="weui_cell weui_check_label">
                        <div class="weui_cell_hd">
                            <input type="checkbox" class="weui_check" name="authorities" value="${authority.value}" ${fn:contains(appDTO.authorities, authority.value) ? 'checked':''}/>
                            <i class="weui_icon_checked"></i>
                        </div>
                        <div class="weui_cell_bd weui_cell_primary">
                            <p>${authority.label}</p>
                        </div>
                    </label>
                    <label class="checkbox-inline"></label>
                </c:forEach>
            </div>
            <div class="weui_cells_tips">指定应用所拥有的权限，若<code>授权类型</code>包含了<code>Implicit</code>或<code>Client credentials</code>，则必须选择<code>权限</code>，服务端将根据该权限来判断是否有权限访问对应的API</div>
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell">
                    <div class="weui_cell_hd">
                        <label class="weui_label">Token有效期</label>
                    </div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <spring-form:input cssClass="weui_input" path="accessTokenValidity" id="accessTokenValidity" placeholder="授权Token有效期"/>
                    </div>
                </div>
            </div>
            <div class="weui_cells_tips">应用的<code>access_token</code>的有效时间值(单位:秒)</div>
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell">
                    <div class="weui_cell_hd">
                        <label class="weui_label">刷新Token有效期</label>
                    </div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <spring-form:input cssClass="weui_input" path="refreshTokenValidity" id="refreshTokenValidity" placeholder="刷新Token有效期"/>
                    </div>
                </div>
            </div>
            <div class="weui_cells_tips">应用的<code>刷新Token</code>的有效时间值(单位:秒)</div>
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell">
                    <div class="weui_cell_hd"><label class="weui_label">回调地址</label></div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <spring-form:input cssClass="weui_input" path="webServerRedirectURI" id="webServerRedirectURI" placeholder="回调地址"/>
                    </div>
                </div>
            </div>
            <div class="weui_cells_tips">若<code>授权类型</code>包含了<code>Authorization code</code>或<code>Implicit</code>，则必须填写<code>Redirect URI</code></div>
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell">
                    <div class="weui_cell_hd">
                        <label class="weui_label">附加信息</label>
                    </div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <spring-form:textarea class="weui_textarea" path="additionalInformation" id="additionalInformation" placeholder="附加信息" cols="80" rows="3"/>
                    </div>
                </div>
            </div>
            <div class="weui_cells_tips">JSON格式的附加数据</div>
        </spring-form:form>
    </div>
    <div class="weui_tabbar" style="z-index: 1000;">
        <a class="weui_tabbar_item" href="javascript:void(0)" onclick="WF.form.ajaxSubmit($('#data-form'))">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/images/icons/icon_save.png" alt="">
            </div>
            <p class="weui_tabbar_label">保存</p>
        </a>
        <a class="weui_tabbar_item" href="javascript:void(0)" onclick="history.back();">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/images/icons/icon_back.png" alt="">
            </div>
            <p class="weui_tabbar_label">返回</p>
        </a>
    </div>
</div>
</body>
</html>