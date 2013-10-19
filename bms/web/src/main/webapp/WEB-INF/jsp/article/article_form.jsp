<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>写文章</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
    <div class="form">
        <spring-form:form commandName="articleDTO" method="post">
            <div class="items">
                <div class="item">
                    <label>标题</label>
                    <spring-form:input path="title" cssClass="text"/>
                    <spring-form:errors path="title"/>
                </div>
                <div class="item">
                    <label>内容</label>
                    <spring-form:textarea path="content" cssClass="textarea w400 h300"/>
                    <spring-form:errors path="content"/>
                </div>
                <div class="item">
                    <label>分类</label>
                    <spring-form:select path="categoryGuid" items="${categoryDTOs}" itemValue="guid" itemLabel="name" cssClass="select"/>
                </div>
                <div class="item">
                    <label>&nbsp;</label>
                    <input type="submit" value="保存"/>
                    <input type="button" value="返回" onclick="history.back()"/>
                </div>
            </div>
        </spring-form:form>
    </div>
</body>
</html>