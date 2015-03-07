<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<html lang="zh-CN">
<head>
    <title><decorator:title default="bms"/></title>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script type="text/javascript" src="/js/jquery/jquery-1.11.2.min.js"></script>

    <link rel="stylesheet" href="/js/bootstrap/3.3.2/css/bootstrap.min.css">
    <script src="/js/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="/js/bootstrap/datetimepicker/css/bootstrap-datetimepicker.min.css">
    <script src="/js/bootstrap/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script src="/js/bootstrap/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

    <link rel="stylesheet" href="/css/base.css" />
    <script type="text/javascript" src="/js/global.js"></script>
    <decorator:head/>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
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
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/logout">退出</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container">
        <div class="col-lg-2 list-group">
            <a class="list-group-item" href="/system/billing/list">账单</a>
            <a class="list-group-item" href="/system/article/list">文章</a>
        </div>
        <div class="col-lg-10">
            <decorator:body/>
        </div>
    </div>
</div>
</body>
</html>