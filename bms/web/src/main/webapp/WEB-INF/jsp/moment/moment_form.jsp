<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>时间记账</title>
</head>
<body>
    <spring-form:form modelAttribute="momentDTO" method="post" id="data-form" onsubmit="return false;">
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell weui-cell_select weui-cell_select-after">
                <div class="weui-cell__hd">
                    <label class="weui-label">计划</label>
                </div>
                <div class="weui-cell__bd">
                    <spring-form:select cssClass="weui-select" id="typeGuid" path="typeGuid" items="${types}" itemValue="guid" itemLabel="name"/>
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <label class="weui-label">日期</label>
                </div>
                <div class="weui-cell__bd">
                    <input type="date" class="weui-input" name="date" id="date" value="${momentDTO.date}" placeholder="日期"/>
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <label class="weui-label">开始时间</label>
                </div>
                <div class="weui-cell__bd">
                    <input type="time" class="weui-input" name="startTime" id="startTime" value="${momentDTO.startTime}" placeholder="开始时间"/>
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <label class="weui-label">结束时间</label>
                </div>
                <div class="weui-cell__bd">
                    <input type="time" class="weui-input" name="endTime" id="endTime" value="${momentDTO.endTime}" placeholder="结束时间"/>
                </div>
            </div>
        </div>
        <div class="weui-cells__title">描述</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <textarea class="weui-textarea" name="description" placeholder="描述" rows="3">${momentDTO.description}</textarea>
                </div>
            </div>
        </div>
        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="javascript:void(0)" onclick="WF.form.ajaxSubmit($('#data-form'))">保存</a>
        </div>
    </spring-form:form>
</body>
</html>