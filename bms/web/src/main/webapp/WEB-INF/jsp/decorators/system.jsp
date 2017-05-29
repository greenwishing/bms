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
    <meta name="Keywords" content="个人记账系统,个人日记,个人记事,文章发布,记账统计,记账分析,时间记账,时间管理,时间分析,待办事项,TODO,GREENWISHING,GREEN,WISHING,BMS">
    <meta name="Description" content="个人记账系统，用于个人记账，记账分析与统计，时间记账管理与分析，个人记事文章发布，待办事项提醒等">
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
    <link rel="apple-touch-icon" href="/images/wishing.png" />
    <link rel="apple-touch-icon" sizes="57x57" href="/images/apple-touch-icon-57.png" />
    <link rel="apple-touch-icon" sizes="72x72" href="/images/apple-touch-icon-72.png" />
    <link rel="apple-touch-icon" sizes="114x114" href="/images/apple-touch-icon-114.png" />
    <link rel="apple-touch-icon" sizes="144x144" href="/images/apple-touch-icon-144.png" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.2.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/weui/weui.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css" />

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
    <decorator:head/>
</head>
<body>
<div class="page">
    <decorator:body/>
</div>
<div style="display: none;"><script>var _hmt=_hmt||[];(function(){var hm=document.createElement("script");hm.src="https://tajs.qq.com/stats?sId=62361180";var s=document.getElementsByTagName("script")[0];s.parentNode.insertBefore(hm,s);})();</script></div>
</body>
</html>