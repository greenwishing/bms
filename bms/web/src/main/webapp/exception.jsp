<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>${title==null?'500':title}</title>
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/weui/weui.min.css">
</head>
<body>
<div class="msg">
    <div class="weui_msg">
        <div class="weui_icon_area"><i class="weui_icon_warn weui_icon_msg"></i></div>
        <div class="weui_text_area">
            <h2 class="weui_msg_title">${title==null?'500':title}</h2>
            <p class="weui_msg_desc">${desc==null?'服务器内部错误':desc}</p>
        </div>
        <div class="weui_opr_area">
            <p class="weui_btn_area">
                <a href="javascript:void(0);" class="weui_btn weui_btn_primary" onclick="history.back()">确定</a>
            </p>
        </div>
    </div>
</div>
</body>
</html>