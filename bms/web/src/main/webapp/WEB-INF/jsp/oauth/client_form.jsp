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
<spring-form:form cssClass="form-horizontal" id="data-form" commandName="clientDTO" method="post" onsubmit="return false;">
    <c:if test="${clientDTO.clientId!=null}">
        <div class="form-group">
            <label class="control-label col-sm-2">Client ID</label>
            <div class="col-sm-10 form-control-static">${clientDTO.clientId}</div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2">Client Secret</label>
            <div class="col-sm-10 form-control-static">${clientDTO.clientSecret}</div>
        </div>
    </c:if>
    <div class="form-group">
        <label class="control-label col-sm-2">Resource Ids</label>
        <div class="col-sm-10">
            <c:forEach items="${resourceIdList}" var="resourceId">
                <label class="checkbox-inline"><spring-form:checkbox path="resourceIds" value="${resourceId.value}"/> ${resourceId.label}</label>
            </c:forEach>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2">Scope</label>
        <div class="col-sm-10">
            <c:forEach items="${scopeList}" var="scope">
                <label class="checkbox-inline"><spring-form:checkbox path="scope" value="${scope.value}"/> ${scope.label}</label>
            </c:forEach>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2">Authorized Grant Types</label>
        <div class="col-sm-10">
            <c:forEach items="${grantTypeList}" var="grantType">
                <label class="checkbox-inline"><spring-form:checkbox path="authorizedGrantTypes" value="${grantType.value}"/> ${grantType.label}</label>
            </c:forEach>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="webServerRedirectURI">Redirect URI</label>
        <div class="col-sm-10">
            <spring-form:input cssClass="form-control" path="webServerRedirectURI" id="webServerRedirectURI" placeholder="Redirect URI"/>
            <span class="help-block">若<code>Authorized Grant Types</code>包含了<code>Authorization code</code>或<code>Implicit</code>，则必须填写<code>Redirect URI</code></span>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2">Authorities</label>
        <div class="col-sm-10">
            <c:forEach items="${authorityList}" var="authority">
                <label class="checkbox-inline"><spring-form:checkbox path="authorities" value="${authority.value}"/> ${authority.label}</label>
            </c:forEach>
            <span class="help-block">指定客户端所拥有的权限，若<code>Authorized Grant Types</code>包含了<code>Implicit</code>或<code>Client credentials</code>，则必须选择<code>Authorities</code>，服务端将根据该权限来判断是否有权限访问对应的API</span>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="accessTokenValidity">Access Token Validity</label>
        <div class="col-sm-10">
            <spring-form:input cssClass="form-control" path="accessTokenValidity" id="accessTokenValidity" placeholder="Access Token Validity"/>
            <span class="help-block">客户端的<code>access_token</code>的有效时间值(单位:秒)</span>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="refreshTokenValidity">Refresh Token Validity</label>
        <div class="col-sm-10">
            <spring-form:input cssClass="form-control" path="refreshTokenValidity" id="refreshTokenValidity" placeholder="Refresh Token Validity"/>
            <span class="help-block">客户端的<code>refresh_token</code>的有效时间值(单位:秒)</span>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="additionalInformation">Additional Information</label>
        <div class="col-sm-10">
            <spring-form:textarea class="form-control" path="additionalInformation" id="additionalInformation" placeholder="Additional Information" cols="80" rows="3"/>
            <span class="help-block">JSON格式的附加数据</span>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-10 col-sm-offset-2">
            <input class="btn btn-success" type="button" value="保存" onclick="WF.form.ajaxSubmit($('#data-form'))"/>
            <input class="btn btn-default" type="button" value="返回" onclick="WF.page.forward('list')"/>
        </div>
    </div>
</spring-form:form>
</body>
</html>