<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>欢迎</title>
    <meta charset="utf-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta content="telephone=no" name="format-detection"/>
    <meta name="Keywords" content="个人记账,个人日记,个人记事,文章发布,记账统计,记账分析,时间记账,时间管理,时间分析,待办事项,TODO,GREENWISHING,GREEN,WISHING,BMS">
    <meta name="Description" content="个人记账系统，用于个人记账，记账分析与统计，时间记账管理与分析，个人记事文章发布，待办事项提醒等">
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
    <link rel="apple-touch-icon" href="/images/wishing.png" />
    <link rel="apple-touch-icon" sizes="57x57" href="/images/apple-touch-icon-57.png" />
    <link rel="apple-touch-icon" sizes="72x72" href="/images/apple-touch-icon-72.png" />
    <link rel="apple-touch-icon" sizes="114x114" href="/images/apple-touch-icon-114.png" />
    <link rel="apple-touch-icon" sizes="144x144" href="/images/apple-touch-icon-144.png" />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/weui/weui.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.2.min.js"></script>
</head>
<body>
<div class="page">
    <a class="brand brand-text_hide" href="${pageContext.request.contextPath}/">
        <img class="brand-logo" src="${pageContext.request.contextPath}/images/wishing.png"/>
        <p class="brand-text">WISHING</p>
    </a>
    <div class="weui-article" style="text-align: center;">
        <h1>敬请期待 ...</h1>
        <br/>
        <a href="${pageContext.request.contextPath}/articles">到处看看</a>
    </div>
    <div class="weui-footer">
        <div class="weui-footer__text">GREEN WISHING &copy; 2017 <a href="http://www.miitbeian.gov.cn" target="_blank">蜀ICP备17012081号</a></div>
    </div>
</div>
<div style="display: none;"><script>var _hmt=_hmt||[];(function(){var hm=document.createElement("script");hm.src="https://tajs.qq.com/stats?sId=62361180";var s=document.getElementsByTagName("script")[0];s.parentNode.insertBefore(hm,s);})();</script></div>
</body>
</html>