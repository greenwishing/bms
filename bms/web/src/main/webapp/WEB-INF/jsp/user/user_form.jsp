<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>添加/编辑用户</title>
</head>
<body>
    <spring-form:form id="data-form" modelAttribute="userDTO" method="post" onsubmit="return false;">
        <div class="weui-cells__title">姓名</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <spring-form:input cssClass="weui-input" path="username" id="username" placeholder="姓名"/>
                </div>
            </div>
        </div>
        <c:if test="${userDTO.guid == null}">
            <div class="weui-cells__title">帐号</div>
            <div class="weui-cells weui-cells_form">
                <div class="weui-cell">
                    <div class="weui-cell__bd">
                        <spring-form:input cssClass="weui-input" path="account" id="account" placeholder="帐号"/>
                    </div>
                </div>
            </div>
            <div class="weui-cells__title">设置密码</div>
            <div class="weui-cells weui-cells_form">
                <div class="weui-cell">
                    <div class="weui-cell__bd">
                        <input class="weui-input" type="password" name="password" id="password" placeholder="设置密码">
                    </div>
                </div>
            </div>
            <div class="weui-cells__title">确认密码</div>
            <div class="weui-cells weui-cells_form">
                <div class="weui-cell">
                    <div class="weui-cell__bd">
                        <input class="weui-input" type="password" name="retypePassword" id="retypePassword" placeholder="确认密码">
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${userDTO.guid != null}">
            <div class="weui-cells__title">帐号</div>
            <div class="weui-cells weui-cells_form">
                <div class="weui-cell">
                    <div class="weui-cell__bd">${userDTO.account}</div>
                </div>
            </div>
        </c:if>
        <c:if test="${userDTO.admin}">
            <div class="weui-cells__title">状态</div>
            <div class="weui-cells weui-cells_form">
                <div class="weui-cell">
                    <div class="weui-cell__bd">${userDTO.status.label}</div>
                </div>
            </div>
        </c:if>
        <c:if test="${!userDTO.admin}">
            <div class="weui-cells__title">状态</div>
            <div class="weui-cells weui-cells_radio">
                <c:forEach items="${statusList}" var="status">
                    <label class="weui-cell weui-check__label">
                        <div class="weui-cell__bd">
                            <p>${status.label}</p>
                        </div>
                        <div class="weui-cell__ft">
                            <input type="radio" class="weui-check" name="status" value="${status.value}" ${status==userDTO.status?'checked':''}>
                            <span class="weui-icon-checked"></span>
                        </div>
                    </label>
                </c:forEach>
            </div>
        </c:if>
        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="javascript:void(0)" onclick="WF.form.ajaxSubmit($('#data-form'))">保存</a>
        </div>
    </spring-form:form>
</body>
</html>