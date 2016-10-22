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
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.2.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/global.js"></script>
    <script type="text/javascript">
        $(function(){
            WF.page.menu($('#nav-bar'), '${pageContext.request.requestURI}');
        });
    </script>
    <decorator:head/>
</head>
<body>
<header>
    <div class="container">
        <ul id="nav-bar" class="nav-bar">
            <li><a href="/system/billing/main">记账</a></li>
            <li><a href="/system/billing/list">账单</a></li>
            <li><a href="/system/billing/accounts">账户</a></li>
            <li><a href="/system/billing/categories">分类</a></li>
            <li><a href="/system/article/list">文章</a></li>
            <li><a href="/system/metro/list">地铁</a></li>
            <li><a href="/system/user/list">用户</a></li>
            <li><a href="/system/app/list">应用</a></li>
        </ul>
        <ul class="nav-bar pull-right">
            <li><a href="/logout">退出</a></li>
        </ul>
    </div>
</header>
<div class="container" style="padding-top: 60px;">
    <decorator:body/>
</div>
</body>
</html>