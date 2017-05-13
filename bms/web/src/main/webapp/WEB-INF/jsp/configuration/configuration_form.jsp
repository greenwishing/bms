<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>系统配置</title>
</head>
<body>
    <spring-form:form commandName="configurationDTO" method="post" id="data-form" onsubmit="return false;">
        <div class="weui-cells__title">键</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <c:choose>
                        <c:when test="${configurationDTO.guid == null}">
                            <input type="text" class="weui-input" name="key" id="key" value="${configurationDTO.key}" placeholder="键"/>
                        </c:when>
                        <c:otherwise>
                            <p>${configurationDTO.key}</p>
                            <input type="hidden" name="key" id="key" value="${configurationDTO.key}"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <div class="weui-cells__title">值</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <input type="text" class="weui-input" name="value" id="value" value="${configurationDTO.value}" placeholder="值"/>
                </div>
            </div>
        </div>
        <div class="weui-cells__title">配置描述</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <textarea class="weui-textarea" name="description" placeholder="配置描述" rows="3">${configurationDTO.description}</textarea>
                </div>
            </div>
        </div>
        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="javascript:void(0)" onclick="WF.form.ajaxSubmit($('#data-form'))">保存</a>
        </div>
    </spring-form:form>
</body>
</html>