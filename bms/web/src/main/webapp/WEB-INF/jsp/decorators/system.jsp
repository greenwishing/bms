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

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/weui/weui.min.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/weui/weui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/global.js"></script>
    <script type="text/javascript">
        $(function(){
            WF.page.menu($('#navbar'), '${pageContext.request.requestURI}');
            $('.navbar-toggle').bind('click', function(){
                var $el = $(this);
                var toggleClass = $el.attr('data-toggle');
                var toggleTarget = $el.attr('data-target');
                var $target = $(toggleTarget);
                $target.toggleClass(toggleClass);
                $('body').toggleClass('has-navbar-collapse', $target.is('.' + toggleClass));
            });
            if ($('.menubar').length) {
                $('body').addClass('has-menubar');
            }
        });
    </script>
    <style type="text/css">
        body, html {
            height: 100%;
            -webkit-tap-highlight-color: transparent;
        }
        body {
            background-color: #f8f8f8;
        }
        .page {
            position: absolute;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
        }
        .text-left { text-align: left;}
        .text-center { text-align: center;}
        .text-right { text-align: right;}
    </style>
    <decorator:head/>
</head>
<body>
<div class="page">
    <decorator:body/>
</div>
</body>
</html>