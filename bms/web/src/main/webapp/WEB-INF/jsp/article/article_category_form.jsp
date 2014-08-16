<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>文章分类</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
    <div class="p10">
        <spring-form:form commandName="articleCategoryDTO" method="post">
            <div class="form-items">
                <div class="item">
                    <label>名称</label>
                    <spring-form:input path="name" cssClass="text"/>
                    <spring-form:errors path="name"/>
                </div>
                <div class="form-item">
                    <label>&nbsp;</label>
                    <input type="submit" value="保存"/>
                    <input type="button" value="返回" onclick="WF.page.list()"/>
                </div>
            </div>
        </spring-form:form>
    </div>
</body>
</html>