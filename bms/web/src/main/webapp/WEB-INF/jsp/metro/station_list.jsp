<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>站台</title>
</head>
<body>
<div class="weui-tab">
    <div class="weui-tab__panel">
        <div class="weui-article">
            <h1>站台</h1>
        </div>
        <div class="weui-cells">
            <c:forEach items="${stations}" var="station">
                <a class="weui-cell weui-cell_access" href="edit_station?guid=${station.guid}">
                    <div class="weui-cell__bd">${station.name}</div>
                    <div class="weui-cell__ft"></div>
                </a>
            </c:forEach>
        </div>
    </div>
    <div class="weui-tabbar">
        <a href="add_station" class="weui-tabbar__item">
            <img src="${pageContext.request.contextPath}/images/icons/icon_add.png" class="weui-tabbar__icon">
            <p class="weui-tabbar__label">添加</p>
        </a>
    </div>
</div>
</body>
</html>