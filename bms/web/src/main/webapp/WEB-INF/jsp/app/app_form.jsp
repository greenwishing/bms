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
    <spring-form:form id="data-form" commandName="appDTO" method="post" onsubmit="return false;">
        <c:if test="${appDTO.appId!=null}">
            <div class="form-group">
                <label class="control-label">应用ID</label>
                <div class="form-control-static">${appDTO.appId}</div>
            </div>
            <div class="form-group">
                <label class="control-label">应用密钥</label>
                <div class="form-control-static">${appDTO.appSecret}</div>
            </div>
        </c:if>
        <div class="form-group">
            <label class="control-label">资源列表</label>
            <c:forEach items="${resourceIdList}" var="resourceId">
                <label class="checkbox-inline">
                    <input type="checkbox" class="weui_check" name="resourceIds" value="${resourceId.value}" ${fn:contains(appDTO.resourceIds, resourceId.value) ? 'checked':''}/> ${resourceId.label}
                </label>
            </c:forEach>
        </div>
        <div class="form-group">
            <label class="control-label">范围</label>
            <c:forEach items="${scopeList}" var="scope">
                <label class="checkbox-inline">
                    <input type="checkbox" class="weui_check" name="scope" value="${scope.value}" ${fn:contains(appDTO.scope, scope.value) ? 'checked':''}/> ${scope.label}
                </label>
            </c:forEach>
        </div>
        <div class="form-group">
            <label class="control-label">授权类型</label>
            <c:forEach items="${grantTypeList}" var="grantType">
                <label class="checkbox-inline">
                    <input type="checkbox" class="weui_check" name="authorizedGrantTypes" value="${grantType.value}" ${fn:contains(appDTO.authorizedGrantTypes, grantType.value) ? 'checked':''}/> ${grantType.label}
                </label>
            </c:forEach>
        </div>
        <div class="form-group">
            <label class="control-label">权限</label>
            <c:forEach items="${authorityList}" var="authority">
                <label class="checkbox-inline">
                    <input type="checkbox" class="weui_check" name="authorities" value="${authority.value}" ${fn:contains(appDTO.authorities, authority.value) ? 'checked':''}/> ${authority.label}
                </label>
                <label class="checkbox-inline"></label>
            </c:forEach>
            <div class="help-block">指定应用所拥有的权限，若<code>授权类型</code>包含了<code>Implicit</code>或<code>Client credentials</code>，则必须选择<code>权限</code>，服务端将根据该权限来判断是否有权限访问对应的API</div>
        </div>
        <div class="form-group">
            <label class="control-label">Token有效期</label>
            <spring-form:input  cssClass="form-control" path="accessTokenValidity" id="accessTokenValidity" placeholder="授权Token有效期"/>
            <div class="help-block">应用的<code>access_token</code>的有效时间值(单位:秒)</div>
        </div>
        <div class="form-group">
            <label class="control-label">刷新Token有效期</label>
            <spring-form:input  cssClass="form-control" path="refreshTokenValidity" id="refreshTokenValidity" placeholder="刷新Token有效期"/>
            <div class="help-block">应用的<code>刷新Token</code>的有效时间值(单位:秒)</div>
        </div>
        <div class="form-group">
            <label class="control-label">回调地址</label>
            <spring-form:input  cssClass="form-control" path="webServerRedirectURI" id="webServerRedirectURI" placeholder="回调地址"/>
            <div class="help-block">若<code>授权类型</code>包含了<code>Authorization code</code>或<code>Implicit</code>，则必须填写<code>Redirect URI</code></div>
        </div>
        <div class="form-group">
            <label class="control-label">附加信息</label>
            <spring-form:textarea cssClass="form-control" path="additionalInformation" id="additionalInformation" placeholder="附加信息" cols="80" rows="3"/>
            <div class="help-block">JSON格式的附加数据</div>
        </div>
        <div class="form-group">
            <a class="btn btn-primary" href="javascript:void(0)" onclick="WF.form.ajaxSubmit($('#data-form'))">保存</a>
            <a class="btn btn-default" href="javascript:void(0)" onclick="history.back();">返回</a>
        </div>
    </spring-form:form>
</body>
</html>