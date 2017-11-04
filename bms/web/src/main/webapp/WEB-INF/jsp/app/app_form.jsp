<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>应用设置</title>
</head>
<body>
    <spring-form:form id="data-form" modelAttribute="appDTO" method="post" onsubmit="return false;">
        <c:if test="${appDTO.appId!=null}">
            <div class="weui-cells__title">应用ID</div>
            <div class="weui-cells weui-cells_form">
                <div class="weui-cell">
                    <div class="weui-cell__bd">${appDTO.appId}</div>
                </div>
            </div>
            <div class="weui-cells__title">应用密钥</div>
            <div class="weui-cells weui-cells_form">
                <div class="weui-cell">
                    <div class="weui-cell__bd">${appDTO.appSecret}</div>
                </div>
            </div>
        </c:if>
        <div class="weui-cells__title">资源列表</div>
        <div class="weui-cells weui-cells_checkbox">
            <c:forEach items="${resourceIdList}" var="resourceId">
                <label class="weui-cell weui-check__label">
                    <div class="weui-cell__hd">
                        <input type="checkbox" class="weui-check" name="resourceIds" value="${resourceId.value}" ${fn:contains(appDTO.resourceIds, resourceId.value) ? 'checked':''}>
                        <i class="weui-icon-checked"></i>
                    </div>
                    <div class="weui-cell__bd">
                        <p>${resourceId.label}</p>
                    </div>
                </label>
            </c:forEach>
        </div>
        <div class="weui-cells__title">范围</div>
        <div class="weui-cells weui-cells_checkbox">
            <c:forEach items="${scopeList}" var="scope">
                <label class="weui-cell weui-check__label">
                    <div class="weui-cell__hd">
                        <input type="checkbox" class="weui-check" name="scope" value="${scope.value}" ${fn:contains(appDTO.scope, scope.value) ? 'checked':''}>
                        <i class="weui-icon-checked"></i>
                    </div>
                    <div class="weui-cell__bd">
                        <p>${scope.label}</p>
                    </div>
                </label>
            </c:forEach>
        </div>
        <div class="weui-cells__title">授权类型</div>
        <div class="weui-cells weui-cells_checkbox">
            <c:forEach items="${grantTypeList}" var="grantType">
                <label class="weui-cell weui-check__label">
                    <div class="weui-cell__hd">
                        <input type="checkbox" class="weui-check" name="grantType" value="${grantType.value}" ${fn:contains(appDTO.authorizedGrantTypes, grantType.value) ? 'checked':''}>
                        <i class="weui-icon-checked"></i>
                    </div>
                    <div class="weui-cell__bd">
                        <p>${grantType.label}</p>
                    </div>
                </label>
            </c:forEach>
        </div>
        <div class="weui-cells__title">权限</div>
        <div class="weui-cells weui-cells_checkbox">
            <c:forEach items="${authorityList}" var="authority">
                <label class="weui-cell weui-check__label">
                    <div class="weui-cell__hd">
                        <input type="checkbox" class="weui-check" name="authorities" value="${authority.value}" ${fn:contains(appDTO.authorities, authority.value) ? 'checked':''}>
                        <i class="weui-icon-checked"></i>
                    </div>
                    <div class="weui-cell__bd">
                        <p>${authority.label}</p>
                    </div>
                </label>
            </c:forEach>
        </div>
        <div class="weui-cells__tips">指定应用所拥有的权限，若<code>授权类型</code>包含了<code>Implicit</code>或<code>Client credentials</code>，则必须选择<code>权限</code>，服务端将根据该权限来判断是否有权限访问对应的API</div>
        <div class="weui-cells__title">应用密钥</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <spring-form:input cssClass="weui-input" path="accessTokenValidity" id="accessTokenValidity" placeholder="授权Token有效期"/>
                </div>
            </div>
        </div>
        <div class="weui-cells__tips">应用的<code>access_token</code>的有效时间值(单位:秒)</div>
        <div class="weui-cells__title">刷新Token有效期</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <spring-form:input cssClass="weui-input" path="refreshTokenValidity" id="refreshTokenValidity" placeholder="刷新Token有效期"/>
                </div>
            </div>
        </div>
        <div class="weui-cells__tips">应用的<code>刷新Token</code>的有效时间值(单位:秒)</div>
        <div class="weui-cells__title">回调地址</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <spring-form:textarea cssClass="weui-textarea" path="webServerRedirectURI" id="webServerRedirectURI" placeholder="回调地址"/>
                </div>
            </div>
        </div>
        <div class="weui-cells__tips">若<code>授权类型</code>包含了<code>Authorization code</code>或<code>Implicit</code>，则必须填写<code>Redirect URI</code></div>
        <div class="weui-cells__title">附加信息</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <spring-form:textarea cssClass="weui-textarea" path="additionalInformation" id="additionalInformation" placeholder="附加信息"/>
                </div>
            </div>
        </div>
        <div class="weui-cells__tips">JSON格式的附加数据</div>
        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="javascript:void(0)" onclick="WF.form.ajaxSubmit($('#data-form'))">保存</a>
        </div>
    </spring-form:form>
</body>
</html>