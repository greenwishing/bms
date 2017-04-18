<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>地铁</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <style type="text/css">
        .station {
            display: inline-block;
            vertical-align: middle;
            width: 5em;
            height: 1em;
            margin-right: 5px;
            background: transparent;
        }
    </style>
</head>
<body>
<div class="weui-tab">
    <div class="weui-tab__panel">
        <div class="weui-article">
            <h1>地铁</h1>
        </div>
        <div class="weui-cells">
            <c:forEach items="${lines}" var="line">
                <a class="weui-cell weui-cell_access" href="edit?guid=${line.guid}">
                    <div class="weui-cell__hd"><span class="station" style="background: ${line.color};"></span></div>
                    <div class="weui-cell__bd"><span style="color: ${line.color}">${line.name}</span></div>
                    <div class="weui-cell__ft"></div>
                </a>
            </c:forEach>
        </div>
    </div>
    <div class="weui-tabbar">
        <a href="add" class="weui-tabbar__item">
            <img src="${pageContext.request.contextPath}/images/icons/icon_add.png" class="weui-tabbar__icon">
            <p class="weui-tabbar__label">添加</p>
        </a>
        <a href="stations" class="weui-tabbar__item">
            <img src="${pageContext.request.contextPath}/images/icons/icon_metro.png" class="weui-tabbar__icon">
            <p class="weui-tabbar__label">站台</p>
        </a>
    </div>
</div>
</body>
</html>