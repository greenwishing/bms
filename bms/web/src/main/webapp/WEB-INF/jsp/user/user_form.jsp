<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>添加/编辑用户</title>
</head>
<body>
    <spring-form:form id="data-form" commandName="userDTO" method="post" onsubmit="return false;">
        <div class="form-group">
            <label class="form-control-static">姓名</label>
            <input class="form-control" type="text" name="username" id="username" placeholder="姓名" value="${userDTO.username}">
        </div>
        <c:if test="${userDTO.guid == null}">
            <div class="form-group">
                <label class="form-control-static">帐号</label>
                <input class="form-control" type="text" name="account" id="account" placeholder="帐号" value="${userDTO.account}">
            </div>
            <div class="form-group">
                <label class="form-control-static">设置密码</label>
                <input class="form-control" type="password" name="password" id="password" placeholder="设置密码">
            </div>
            <div class="form-group">
                <label class="form-control-static">确认密码</label>
                <input class="form-control" type="password" name="retypePassword" id="retypePassword" placeholder="确认密码">
            </div>
        </c:if>
        <c:if test="${userDTO.guid != null}">
            <div class="form-group">
                <div class="weui_cell_hd"><label class="form-control-static">帐号</label></div>
                <div class="form-control-static">${userDTO.account}</div>
            </div>
        </c:if>
        <c:if test="${userDTO.admin}">
            <div class="form-group">
                <label class="form-control-static">状态</label>
                <div class="form-control-static">${userDTO.status.label}</div>
            </div>
        </c:if>
        <c:if test="${!userDTO.admin}">
            <div class="form-group">
                <label class="form-control-static">状态</label>
                <c:forEach items="${statusList}" var="status">
                    <label class="radio-inline"><input type="radio" name="status" value="${status.value}" ${status==userDTO.status?'checked':''}/> ${status.label}</label>
                </c:forEach>
            </div>
        </c:if>
        <div class="form-group">
            <a class="btn btn-primary" href="javascript:void(0)" onclick="WF.form.ajaxSubmit($('#data-form'))">保存</a>
            <a class="btn btn-default" href="javascript:void(0)" onclick="history.back();">返回</a>
        </div>
    </spring-form:form>
</body>
</html>