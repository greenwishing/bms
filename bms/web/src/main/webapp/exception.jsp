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
<div class="weui-msg">
    <div class="weui-msg__icon-area"><i class="weui-icon-warn weui-icon_msg"></i></div>
    <div class="weui-msg__text-area">
        <h2 class="weui-msg__title">${pageContext.exception}</h2>
        <div class="stack-trace-list" style="display: none;">
            <c:forEach items="${pageContext.exception.stackTrace}" var="trace">
                <p class="weui-msg__desc">${trace}</p></c:forEach>
        </div>
    </div>
    <div class="weui-msg__opr-area">
        <p class="weui-btn-area">
            <a href="javascript:history.back();" class="weui-btn weui-btn_primary">确定</a>
            <a href="javascript:void(0);" class="weui-btn weui-btn_default" onclick="$('#stack-trace-list').toggle()">查看详情</a>
        </p>
    </div>
</div>
</body>
</html>