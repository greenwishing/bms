<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="zh-CN">
<head>
    <title><decorator:title default="bms"/></title>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta content="telephone=no" name="format-detection"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.2.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/weui/weui.min.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/global.js"></script>
    <decorator:head/>
</head>
<body>
<div class="container">
    <div class="weui_tab">
        <div class="weui_tab_bd">
            <decorator:body/>
        </div>
        <div class="weui_tabbar">
            <a href="${pageContext.request.contextPath}/system/billing/list"
               class="weui_tabbar_item ${fn:containsIgnoreCase(pageContext.request.requestURI, '/system/billing/list') ? 'weui_bar_item_on': ''}">
                <div class="weui_tabbar_icon">
                    <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_cell.png" alt="">
                </div>
                <p class="weui_tabbar_label">账单</p>
            </a>
            <a href="${pageContext.request.contextPath}/system/billing/statistics"
               class="weui_tabbar_item ${fn:containsIgnoreCase(pageContext.request.requestURI, '/system/billing/statistics') ? 'weui_bar_item_on': ''}">
                <div class="weui_tabbar_icon">
                    <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_msg.png" alt="">
                </div>
                <p class="weui_tabbar_label">账单统计</p>
            </a>
            <a href="${pageContext.request.contextPath}/system/article/list"
               class="weui_tabbar_item ${fn:containsIgnoreCase(pageContext.request.requestURI, '/system/article/list') ? 'weui_bar_item_on': ''}">
                <div class="weui_tabbar_icon">
                    <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_article.png" alt="">
                </div>
                <p class="weui_tabbar_label">文章</p>
            </a>
            <a href="${pageContext.request.contextPath}/system/metro/list"
               class="weui_tabbar_item ${fn:containsIgnoreCase(pageContext.request.requestURI, '/system/metro/list') ? 'weui_bar_item_on': ''}">
                <div class="weui_tabbar_icon">
                    <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_tab.png" alt="">
                </div>
                <p class="weui_tabbar_label">Metro</p>
            </a>
            <a href="${pageContext.request.contextPath}/system/client/list"
               class="weui_tabbar_item ${fn:containsIgnoreCase(pageContext.request.requestURI, '/system/client/list') ? 'weui_bar_item_on': ''}">
                <div class="weui_tabbar_icon">
                    <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_button.png" alt="">
                </div>
                <p class="weui_tabbar_label">Client</p>
            </a>
            <a href="${pageContext.request.contextPath}/logout" class="weui_tabbar_item">
                <div class="weui_tabbar_icon">
                    <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_cancel.png" alt="">
                </div>
                <p class="weui_tabbar_label">退出</p>
            </a>
        </div>
    </div>
</div>
</body>
</html>