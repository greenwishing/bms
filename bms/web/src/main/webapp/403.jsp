<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>403</title>
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
</head>
<body>
<div class="msg">
    <div class="msg_icon_area"><i class="msg_icon_warn msg_icon_msg"></i></div>
    <div class="msg_text_area">
        <h2 class="msg_title">403</h2>
        <p class="msg_desc">你没有权限访问此页面</p>
    </div>
    <div class="msg_opr_area">
        <p class="msg_btn_area">
            <a href="javascript:void(0);" class="btn btn-primary" onclick="history.back()">确定</a>
        </p>
    </div>
</div>
</body>
</html>