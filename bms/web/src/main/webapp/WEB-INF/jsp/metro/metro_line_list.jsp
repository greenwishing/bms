<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Metro</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <style type="text/css">
        .weui_media_title { color: white;}
    </style>
</head>
<body>
<div class="weui_tab">
    <div class="weui_tab_bd">
        <div class="weui_panel weui_panel_access">
            <div class="weui_panel_hd">Metro line</div>
            <div class="weui_panel_bd">
                <c:forEach items="${lines}" var="line">
                    <a class="weui_media_box weui_media_appmsg" data-id="${article.guid}" href="edit?guid=${line.guid}" style="background: ${line.color};">
                        <div class="weui_media_bd">
                            <h4 class="weui_media_title">${line.name}</h4>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="weui_tabbar">
        <a class="weui_tabbar_item" href="add">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/images/icons/icon_add.png" alt="">
            </div>
            <p class="weui_tabbar_label">Add</p>
        </a>
        <a class="weui_tabbar_item" href="stations">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/images/icons/icon_category.png" alt="">
            </div>
            <p class="weui_tabbar_label">Stations</p>
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