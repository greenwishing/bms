<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>添加/编辑活动计划预算</title>
</head>
<body>
    <spring-form:form id="data-form" modelAttribute="budgetDTO" method="post" onsubmit="return false;">
        <div class="weui-cells__title">名称</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <spring-form:input cssClass="weui-input" path="name" id="name" placeholder="名称"/>
                </div>
            </div>
        </div>
        <div class="weui-cells__title">预算金额</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <input type="number" class="weui-input" name="amount" id="amount" placeholder="预算金额" value="${budgetDTO.amount}"/>
                </div>
            </div>
        </div>
        <div class="weui-cells__title">实际金额</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <input type="number" class="weui-input" name="actualAmount" id="actualAmount" placeholder="实际金额" value="${budgetDTO.actualAmount}"/>
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