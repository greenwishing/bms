<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>时间管理</title>
</head>
<body>
<div class="weui-tab">
    <div class="weui-tab__panel">
        <div class="weui-article">
            <h1>时间管理</h1>
        </div>
        <div class="weui-cells">
            <c:forEach items="${pagingDTO.list}" var="moment">
                <a class="weui-cell weui-cell_access" href="edit_moment?guid=${moment.guid}" async-load="true">
                    <div class="weui-cell__bd">
                        <p>${moment.typeName}</p>
                        <p class="color-grey text-small">${moment.date} ${moment.startTime} ${moment.friendlyTime}</p>
                    </div>
                    <div class="weui-cell__ft"></div>
                </a>
            </c:forEach>
        </div>
        <tags:paging formName="search-form" paging="${pagingDTO}"/>
    </div>
    <div class="weui-tabbar">
        <a href="add_moment" class="weui-tabbar__item" async-load="true">
            <img src="${pageContext.request.contextPath}/images/icons/icon_add.png" class="weui-tabbar__icon">
            <p class="weui-tabbar__label">添加</p>
        </a>
        <a href="types" class="weui-tabbar__item">
            <img src="${pageContext.request.contextPath}/images/icons/icon_category.png" class="weui-tabbar__icon">
            <p class="weui-tabbar__label">计划</p>
        </a>
    </div>
</div>
</body>
</html>