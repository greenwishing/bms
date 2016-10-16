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
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $(':input:first').focus();
        });
    </script>
    <style type="text/css">
        .login-form {
            width: 450px;
            margin: 35px auto;
        }

        .login-form .title {
            margin-bottom: 35px;
        }

        .login-form .form-group .form-control {
            width: 100%;
            height: 46px;
            padding: 10px 16px;
            font-size: 18px;
            line-height: 1.3333333;
        }

        .login-form .form-group .btn {
            width: 100%;
            padding: 10px 16px;
            font-size: 18px;
            line-height: 1.3333333;
        }
    </style>
</head>
<body>
<div class="container">
    <form class="login-form" action="${pageContext.request.contextPath}/account_check" method="post">
        <h3 class="title">登录</h3>
        <div class="form-group">
            <input id="account" type="text" class="form-control" name="account" placeholder="帐号" />
        </div>
        <div class="form-group">
            <input id="password" type="password" class="form-control" name="password" placeholder="密码" />
        </div>
        <div class="form-group">
            <label class="checkbox-inline"><input class="weui_switch" name="rememberMe" type="checkbox" value="true" checked> 记住我</label>
        </div>
        <div class="form-group">
            <button class="btn btn-primary" type="submit">登录</button>
        </div>
        <div class="form-group">
            <c:choose>
                <c:when test="${SPRING_SECURITY_LAST_EXCEPTION!=null}">${SPRING_SECURITY_LAST_EXCEPTION.localizedMessage}!</c:when>
                <c:when test="${param.action==1}"><p>帐号或密码错误</p></c:when>
                <c:when test="${param.action==2}"><p>登录超时</p></c:when>
                <c:when test="${param.action==1}"><p>已退出</p></c:when>
            </c:choose>
        </div>
    </form>
</div>
</body>
</html>