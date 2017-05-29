<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>登录</title>
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
</head>
<body>
<div class="page">
    <a class="brand brand-text_hide" href="${pageContext.request.contextPath}/">
        <img class="brand-logo" src="${pageContext.request.contextPath}/images/wishing.png"/>
        <p class="brand-text">BMS</p>
    </a>
    <form class="login-form" action="${pageContext.request.contextPath}/system/account_check" method="post">
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
    </form>
</div>
</body>
</html>