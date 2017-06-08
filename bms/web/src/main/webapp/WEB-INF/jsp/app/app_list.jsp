<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>我的应用</title>
</head>
<body>
<div class="weui-tab">
    <div class="weui-tab__panel">
        <div class="weui-cells">
            <c:forEach items="${apps}" var="app">
                <a class="weui-cell weui-cell_access" href="edit?appId=${app.appId}">
                    <div class="weui-cell__bd">${app.appId}</div>
                    <div class="weui-cell__ft"></div>
                </a>
            </c:forEach>
        </div>
        <div class="weui-cells__tips"><a class="weui-cell_link" href="${pageContext.request.contextPath}/wiki/app.html">接入文档</a></div>
    </div>
    <div class="weui-tabbar">
        <a href="reg" class="weui-tabbar__item">
            <img src="/images/icons/icon_add.png" class="weui-tabbar__icon">
            <p class="weui-tabbar__label">添加</p>
        </a>
        <a href="javascript:void(0);" class="weui-tabbar__item" onclick="history.back();">
            <img src="/images/icons/icon_back.png" class="weui-tabbar__icon">
            <p class="weui-tabbar__label">返回</p>
        </a>
    </div>
</div>
</body>
</html>