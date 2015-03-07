<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>记账</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<spring-form:form cssClass="form-horizontal" commandName="billingDTO" method="post">
    <spring-form:errors path="type" element="div" cssClass="alert alert-danger"/>
    <spring-form:errors path="name" element="div" cssClass="alert alert-danger"/>
    <spring-form:errors path="occurredTime" element="div" cssClass="alert alert-danger"/>
    <spring-form:errors path="amount" element="div" cssClass="alert alert-danger"/>
    <div class="form-group">
        <label class="control-label col-sm-2" for="type">类型</label>
        <div class="col-sm-10">
            <spring-form:select id="type" cssClass="form-control" path="type" items="${types}" itemValue="value" itemLabel="label"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="name">名称</label>
        <div class="col-sm-10">
            <spring-form:input cssClass="form-control" path="name" id="name" placeholder="名称"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="occurredTime">时间</label>
        <div class="col-sm-10">
            <spring-form:input cssClass="form-control" path="occurredTime" id="occurredTime" placeholder="时间"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="amount">金额</label>
        <div class="col-sm-10">
            <spring-form:input cssClass="form-control" path="amount" id="amount" placeholder="金额"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="description">描述</label>
        <div class="col-sm-10">
            <spring-form:textarea cssClass="form-control" path="description" id="description" placeholder="描述"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-10 col-sm-offset-2">
            <spring-form:hidden path="occurredUserGuid"/>
            <input class="btn btn-primary" type="submit" value="保存"/>
            <input class="btn btn-default" type="button" value="返回" onclick="WF.page.list()"/>
        </div>
    </div>
</spring-form:form>
</body>
</html>