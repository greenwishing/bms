<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>账单分类</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<spring-form:form cssClass="form-horizontal" commandName="billingCategoryDTO" method="post" id="data-form" onsubmit="return false;">
    <div class="form-group">
        <label class="control-label col-sm-2" for="type">类型</label>
        <div class="col-sm-10">
            <spring-form:select id="type" cssClass="form-control" path="type" items="${types}" itemValue="value" itemLabel="label"/>
            <spring-form:errors path="type" cssClass="help-block help-block-danger"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="name">名称</label>
        <div class="col-sm-10">
            <spring-form:input cssClass="form-control" path="name" id="name" placeholder="名称"/>
            <spring-form:errors path="name" cssClass="help-block help-block-danger"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-10 col-sm-offset-2">
            <input class="btn btn-success" type="button" value="保存" onclick="WF.form.submit($('#data-form'))"/>
            <input class="btn btn-default" type="button" value="返回" onclick="WF.page.forward('categories')"/>
        </div>
    </div>
</spring-form:form>
</body>
</html>