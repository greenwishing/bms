<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>${pageContext.exception}</title>
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/weui/weui.min.css">
</head>
<body>
    <div class="msg">
        <div class="msg_icon_area"><i class="msg_icon_warn msg_icon_msg"></i></div>
        <div class="msg_text_area">
            <h2 class="msg_title">${pageContext.exception}</h2>
            <div style="display: none;">
                <c:forEach items="${pageContext.exception.stackTrace}" var="trace">
                    <p class="msg_desc">${trace}</p></c:forEach>
            </div>
        </div>
        <div class="msg_opr_area">
            <p class="msg_btn_area">
                <a href="javascript:void(0);" class="btn btn-primary" onclick="history.back()">确定</a>
            </p>
        </div>
    </div>
</body>
</html>