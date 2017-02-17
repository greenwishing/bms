<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>账单子分类</title>
</head>
<body>
    <spring-form:form commandName="billingSubcategoryDTO" method="post" id="data-form" onsubmit="return false;">
        <div class="form-group">
            <label class="control-label">名称</label>
            <spring-form:input cssClass="form-control" path="name" id="name" placeholder="名称"/>
        </div>
        <div class="form-group">
            <a class="btn btn-primary" href="javascript:void(0)" onclick="WF.form.ajaxSubmit($('#data-form'))">保存</a>
            <a class="btn btn-default" href="javascript:void(0)" onclick="history.back();">返回</a>
        </div>
    </spring-form:form>
</body>
</html>