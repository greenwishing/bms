<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>首页</title>
    <style type="text/css">
        .hd{padding:2em 0}
        .bd{padding-bottom:20px}
        .page_title{text-align:center;font-size:34px;color:#3cc51f;font-weight:400;margin:0 15%}
        .page_desc{text-align:center;color:#888;font-size:14px}
        .weui_grid_icon i{display: inline-block;height: 28px;width: 28px;vertical-align: middle}
    </style>
</head>
<body>
    <div class="hd">
        <div class="page_title">首页</div>
        <div class="page_desc">记账管理系统</div>
    </div>
    <div class="bd">
        <div class="weui_grids">
            <a href="${pageContext.request.contextPath}/system/billing/list" class="weui_grid">
                <div class="weui_grid_icon">
                    <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_cell.png" alt="">
                </div>
                <p class="weui_grid_label">账单</p>
            </a>
            <a href="${pageContext.request.contextPath}/system/billing/statistics" class="weui_grid">
                <div class="weui_grid_icon">
                    <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_msg.png" alt="">
                </div>
                <p class="weui_grid_label">统计</p>
            </a>
            <a href="${pageContext.request.contextPath}/system/article/list" class="weui_grid">
                <div class="weui_grid_icon">
                    <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_article.png" alt="">
                </div>
                <p class="weui_grid_label">文章</p>
            </a>
            <a href="${pageContext.request.contextPath}/system/metro/list" class="weui_grid">
                <div class="weui_grid_icon">
                    <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_tab.png" alt="">
                </div>
                <p class="weui_grid_label">Metro</p>
            </a>
            <a href="${pageContext.request.contextPath}/system/client/list" class="weui_grid">
                <div class="weui_grid_icon">
                    <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_button.png" alt="">
                </div>
                <p class="weui_grid_label">Client</p>
            </a>
            <a href="${pageContext.request.contextPath}/logout" class="weui_grid">
                <div class="weui_grid_icon">
                    <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_cancel.png" alt="">
                </div>
                <p class="weui_grid_label">退出</p>
            </a>
        </div>
    </div>
</body>
</html>