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
    <link rel="apple-touch-icon" href="/images/wishing.png" />
    <link rel="apple-touch-icon" sizes="57x57" href="/images/apple-touch-icon-57.png" />
    <link rel="apple-touch-icon" sizes="72x72" href="/images/apple-touch-icon-72.png" />
    <link rel="apple-touch-icon" sizes="114x114" href="/images/apple-touch-icon-114.png" />
    <link rel="apple-touch-icon" sizes="144x144" href="/images/apple-touch-icon-144.png" />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/weui/weui.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css" />
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
        .brand { display: flex; flex-direction: column; align-items: center; }
        .brand-logo { width: 128px; height: 128px; border-radius: 50%; box-shadow: 3px 3px 12px rgba(0,0,0,0.1); background-color: rgba(255,255,255,.5)  }
        .brand-text { color: #1aad19; font-size: 4.5em; font-weight: 300; text-shadow: 2px 2px 2px #fff }
        .brand-text_hide .brand-text { display: none; }
        .brand-text_hide .brand-logo {  margin: 20px; }
        .brand-logo_hide .brand-logo { display: none; }
        .brand-logo_hide .brand-text { margin: 20px; }
        .weui-footer {  margin-top: 50px; }
    </style>
</head>
<body>
<div class="page">
    <div class="brand brand-text_hide">
        <img class="brand-logo" src="${pageContext.request.contextPath}/images/wishing.png"/>
        <p class="brand-text">BMS</p>
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
            <input id="rememberMe" name="rememberMe" type="checkbox" class="weui-agree__checkbox" value="true" checked>
            <span class="weui-agree__text">记住我</span>
        </label>
        <div class="weui-btn-area">
            <button class="weui-btn weui-btn_primary" type="submit">登录</button>
        </div>
        <c:choose>
            <c:when test="${SPRING_SECURITY_LAST_EXCEPTION!=null}"><div class="weui-toptips weui-toptips_warn">${SPRING_SECURITY_LAST_EXCEPTION.localizedMessage}!</div></c:when>
            <c:when test="${param.action==1}"><div class="weui-toptips weui-toptips_warn">帐号或密码错误</div></c:when>
            <c:when test="${param.action==2}"><div class="weui-toptips weui-toptips_warn">登录超时</div></c:when>
            <c:when test="${param.action==3}"><div class="weui-toptips weui-toptips_warn">已退出</div></c:when>
        </c:choose>
        <div class="weui-footer">
            <div class="weui-footer__text">BMS &copy; 2017 蜀ICP备17012081号</div>
        </div>
    </form>
</div>
</body>
</html>