<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>账单模板</title>
</head>
<body>
<div>
    <div class="btn-group pull-right">
        <a class="btn btn-primary" href="add_template">添加</a>
        <a class="btn btn-default" href="list">返回</a>
    </div>
</div>
<table class="table table-hover">
    <thead>
    <tr>
        <th>金额</th>
        <th>名称</th>
        <th>分类</th>
        <th>&nbsp;</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${templates}" var="template">
        <tr>
            <td><span class="price">${template.amount}</span></td>
            <td>
                <div>${template.name}</div>
            </td>
            <td>
                <div>${template.type.label} - ${template.categoryName} - ${template.subcategoryName}</div>
            </td>
            <td>
                <a href="edit_template?guid=${template.guid}">编辑</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>