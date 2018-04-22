<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>回复</title>
</head>
<body>
    <spring-form:form modelAttribute="replyDTO" method="post" id="data-form" onsubmit="return false;">
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <spring-form:textarea cssClass="weui-textarea" path="content" id="content" rows="3" placeholder="回复内容"/>
                </div>
            </div>
        </div>
        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="javascript:void(0)" onclick="WF.form.ajaxSubmit($('#data-form'))">保存</a>
        </div>
    </spring-form:form>
</body>
</html>