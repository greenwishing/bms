<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>欢迎</title>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta content="telephone=no" name="format-detection"/>
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/weui/weui.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.2.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css" />
    <script type="text/javascript">
        $(function(){
            $(':input:first').focus();
        });
    </script>
</head>
<body>
    <form action="${pageContext.request.contextPath}/account_check" method="post">
        <div class="weui_cells_title">登录</div>
        <div class="weui_cells weui_cells_form">
            <div class="weui_cell">
                <div class="weui_cell_hd"><label class="weui_label">帐号</label></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input id="account" type="text" class="weui_input" name="account" placeholder="请输入帐号" />
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_hd"><label class="weui_label">密码</label></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input id="password" type="password" class="weui_input" name="password" placeholder="请输入密码" />
                </div>
            </div>
        </div>
        <div class="weui_cells weui_cells_form">
            <div class="weui_cell weui_cell_switch">
                <div class="weui_cell_hd weui_cell_primary">记住我</div>
                <div class="weui_cell_ft">
                    <input class="weui_switch" name="rememberMe" type="checkbox" value="true" checked>
                </div>
            </div>
        </div>
        <div class="weui_cells_tips">
        <c:choose>
            <c:when test="${SPRING_SECURITY_LAST_EXCEPTION!=null}">${SPRING_SECURITY_LAST_EXCEPTION.localizedMessage}!</c:when>
            <c:when test="${param.action==1}"><div class="weui_cells_tips">帐号或密码错误</div></c:when>
            <c:when test="${param.action==2}"><div class="weui_cells_tips">登录超时</div></c:when>
            <c:when test="${param.action==1}"><div class="weui_cells_tips">已退出</div></c:when>
        </c:choose>
        </div>
        <div class="weui_btn_area">
            <button class="weui_btn weui_btn_primary" type="submit">登录</button>
        </div>
    </form>
</body>
</html>