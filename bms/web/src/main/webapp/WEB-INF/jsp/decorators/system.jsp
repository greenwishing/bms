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
    <decorator:body/>
</div>
</body>
</html>