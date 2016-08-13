<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>我的应用</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<div class="weui_tab">
    <div class="weui_tab_bd">
        <div class="weui_panel weui_panel_access">
            <div class="weui_panel_hd">我的应用</div>
            <div class="weui_panel_bd">
                <c:forEach items="${apps}" var="app">
                    <a class="weui_media_box weui_media_appmsg" href="edit?appId=${app.appId}">
                        <div class="weui_media_bd">
                            <h4 class="weui_media_title">${app.appId}</h4>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
        <div class="weui_cells_tips"><a href="${pageContext.request.contextPath}/wiki/app.jsp">接入文档</a></div>
    </div>
    <div class="weui_tabbar">
        <a class="weui_tabbar_item" href="reg">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/images/icons/icon_add.png" alt="">
            </div>
            <p class="weui_tabbar_label">创建</p>
        </a>
        <a class="weui_tabbar_item" href="javascript:void(0)" onclick="history.back();">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/images/icons/icon_back.png" alt="">
            </div>
            <p class="weui_tabbar_label">返回</p>
        </a>
    </div>
</div>
</body>
</html>