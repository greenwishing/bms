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
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.2.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/3.3.2/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/datetimepicker/css/bootstrap-datetimepicker.min.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/global.js"></script>
    <decorator:head/>
</head>
<body>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">BMS</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li ${fn:containsIgnoreCase(pageContext.request.requestURI, '/system/billing/list') ? 'class="active"': ''}><a href="${pageContext.request.contextPath}/system/billing/list">账单</a></li>
                    <li ${fn:containsIgnoreCase(pageContext.request.requestURI, '/system/billing/statistics') ? 'class="active"': ''}><a href="${pageContext.request.contextPath}/system/billing/statistics">账单统计</a></li>
                    <li ${fn:containsIgnoreCase(pageContext.request.requestURI, '/system/article/list') ? 'class="active"': ''}><a href="${pageContext.request.contextPath}/system/article/list">文章</a></li>
                    <li ${fn:containsIgnoreCase(pageContext.request.requestURI, '/system/metro/list') ? 'class="active"': ''}><a href="${pageContext.request.contextPath}/system/metro/list">Metro</a></li>
                    <li ${fn:containsIgnoreCase(pageContext.request.requestURI, '/system/client/list') ? 'class="active"': ''}><a href="${pageContext.request.contextPath}/system/client/list">Client</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${pageContext.request.contextPath}/logout">退出</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container">
        <h3 class="page-header"><decorator:title default="bms"/></h3>
        <decorator:body/>
    </div>
</body>
</html>