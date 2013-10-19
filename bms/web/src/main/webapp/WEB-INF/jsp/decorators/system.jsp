<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<html>
<head>
    <title><decorator:title default="bms"/></title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <link rel="stylesheet" href="/css/base.css" type="text/css" media="all" />
    <script type="text/javascript" src="/js/jquery/jquery-1.7.min.js"></script>
    <script type="text/javascript" src="/js/global.js"></script>
    <decorator:head/>
</head>
<body>
<div class="container">
    <div class="head">
        <div class="logo"><span>凡</span></div>
    </div>
    <div class="body">
        <div class="left">
            <ul class="menu">
                <li><a href="/system/billing/list">账单</a></li>
                <li><a href="/system/article/list">文章</a></li>
            </ul>
        </div>
        <div class="right"><decorator:body/></div>
    </div>
</div>
</body>
</html>