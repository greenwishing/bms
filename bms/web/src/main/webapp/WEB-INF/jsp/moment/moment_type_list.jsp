<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>时间计划</title>
</head>
<body>
<div class="weui-tab">
    <div class="weui-tab__panel">
        <div class="weui-article">
            <h1>时间计划</h1>
        </div>
        <div class="weui-cells">
            <c:forEach items="${types}" var="type">
                <a class="weui-cell weui-cell_access" href="edit_moment_type?guid=${type.guid}" async-load="true">
                    <div class="weui-cell__bd">${type.name}</div>
                    <div class="weui-cell__ft">${type.goalType.label}<c:if test="${type.goalType.value != 'NONE'}">${type.goal}分钟</c:if></div>
                </a>
            </c:forEach>
        </div>
    </div>
    <div class="weui-tabbar">
        <a href="add_moment_type" class="weui-tabbar__item" async-load="true">
            <img src="${pageContext.request.contextPath}/images/icons/icon_add.png" class="weui-tabbar__icon">
            <p class="weui-tabbar__label">添加</p>
        </a>
    </div>
</div>
</body>
</html>