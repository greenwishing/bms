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
    <div class="form">
        <spring-form:form commandName="billingDTO" method="post">
            <div class="items">
                <div class="item">
                    <label>类型</label>
                    <spring-form:select path="type" items="${types}" itemValue="value" itemLabel="label" cssClass="select"/>
                    <spring-form:errors path="type"/>
                </div>
                <div class="item">
                    <label>名称</label>
                    <spring-form:input path="name" cssClass="text"/>
                    <spring-form:errors path="name"/>
                </div>
                <div class="item">
                    <label>描述</label>
                    <spring-form:textarea path="description" cssClass="textarea w400"/>
                    <spring-form:errors path="description"/>
                </div>
                <div class="item">
                    <label>金额</label>
                    <spring-form:input path="amount" cssClass="text"/>
                    <spring-form:errors path="amount"/>
                </div>
                <div class="item">
                    <label>时间</label>
                    <spring-form:input path="occurredTime" cssClass="text"/>
                    <spring-form:errors path="occurredTime"/>
                </div>
                <div class="item">
                    <label>&nbsp;</label>
                    <spring-form:hidden path="occurredUserGuid"/>
                    <input type="submit" value="保存"/>
                    <input type="button" value="返回" onclick="history.back()"/>
                </div>
            </div>
        </spring-form:form>
    </div>
</body>
</html>