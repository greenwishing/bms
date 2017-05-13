<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>发送邮件</title>
</head>
<body>
    <form method="post" action="send_mail" id="data-form" onsubmit="return false;">
        <div class="weui-cells__title">收件人</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <input type="email" class="weui-input" name="email" id="email" placeholder="收件人"/>
                </div>
            </div>
        </div>
        <div class="weui-cells__title">主题</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <input type="text" class="weui-input" name="subject" id="subject" placeholder="主题"/>
                </div>
            </div>
        </div>
        <div class="weui-cells__title">正文</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <textarea class="weui-textarea" name="content" placeholder="正文" rows="8"></textarea>
                </div>
            </div>
        </div>
        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="javascript:void(0)" onclick="WF.form.ajaxSubmit($('#data-form'))">发送</a>
        </div>
    </form>
</body>
</html>