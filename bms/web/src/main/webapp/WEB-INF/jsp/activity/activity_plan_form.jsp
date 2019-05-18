<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>添加/编辑活动计划</title>
</head>
<body>
    <spring-form:form id="data-form" modelAttribute="planDTO" method="post" onsubmit="return false;">
        <div class="weui-cells__title">名称</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <spring-form:input cssClass="weui-input" path="name" id="name" placeholder="名称"/>
                </div>
            </div>
        </div>
        <div class="weui-cells__title">开始日期</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <input type="date" class="weui-input" name="dateFrom" id="dateFrom" placeholder="开始日期" value="${planDTO.dateFrom}"/>
                </div>
            </div>
        </div>
        <div class="weui-cells__title">结束日期</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <input type="date" class="weui-input" name="dateTo" id="dateTo" placeholder="结束日期" value="${planDTO.dateTo}"/>
                </div>
            </div>
        </div>
        <div class="weui-cells__title">已完成</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell weui-cell_switch">
                <div class="weui-cell__bd">已完成</div>
                <div class="weui-cell__ft">
                    <label for="done" class="weui-switch-cp">
                        <spring-form:checkbox id="done" path="done" cssClass="weui-switch-cp__input"/>
                        <span class="weui-switch-cp__box"></span>
                    </label>
                </div>
            </div>
        </div>
        <div class="weui-cells__title">备注</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <spring-form:input cssClass="weui-input" path="remark" id="remark" placeholder="备注"/>
                </div>
            </div>
        </div>
        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="javascript:void(0)" onclick="WF.form.ajaxSubmit($('#data-form'))">保存</a>
        </div>
    </spring-form:form>
</body>
</html>