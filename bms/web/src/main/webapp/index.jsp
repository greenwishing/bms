<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>登录</title>
    <meta charset="utf-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta content="telephone=no" name="format-detection"/>
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/weui/weui.min.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $(':input:first').focus();
            var $toptips = $('.weui-toptips');
            $toptips.fadeIn();
            setTimeout(function () {
                $toptips.fadeOut();
            }, 2000);
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
    </style>
</head>
<body>
<div class="weui-article">
    <h1>登录</h1>
</div>
<form class="login-form" action="${pageContext.request.contextPath}/account_check" method="post">
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <input class="weui-input" type="text" name="account" placeholder="帐号">
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <input class="weui-input" type="password" name="password" placeholder="密码">
            </div>
        </div>
    </div>
    <label for="rememberMe" class="weui-agree">
        <input id="rememberMe" type="checkbox" class="weui-agree__checkbox" value="true" checked>
        <span class="weui-agree__text">记住我</span>
    </label>
    <div class="weui-btn-area">
        <button class="weui-btn weui-btn_primary" type="submit">登录</button>
    </div>
    <c:choose>
        <c:when test="${SPRING_SECURITY_LAST_EXCEPTION!=null}"><div class="weui-toptips weui-toptips_warn">${SPRING_SECURITY_LAST_EXCEPTION.localizedMessage}!</div></c:when>
        <c:when test="${param.action==1}"><div class="weui-toptips weui-toptips_warn">帐号或密码错误</div></c:when>
        <c:when test="${param.action==2}"><div class="weui-toptips weui-toptips_warn">登录超时</div></c:when>
        <c:when test="${param.action==1}"><div class="weui-toptips weui-toptips_warn">已退出</div></c:when>
    </c:choose>
    <div class="weui-footer weui-footer_fixed-bottom">
        <div class="weui-footer__text">wishing &copy; 2017 蜀ICP备17012081号</div>
    </div>
</form>
</body>
</html>