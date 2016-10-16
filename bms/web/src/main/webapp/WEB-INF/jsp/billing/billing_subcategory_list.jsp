<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>账单子分类</title>

</head>
<body>
<div>
    <div class="btn-group pull-right">
        <a class="btn btn-primary" href="edit_subcategory?categoryGuid=${param.categoryGuid}">添加</a>
        <a class="btn btn-default" href="javasctip:void(0)" onclick="history.back()">返回</a>
    </div>
</div>
<table class="table table-hover">
    <thead>
    <tr>
        <th>子分类</th>
        <th>&nbsp;</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${subcategories}" var="subcategory">
        <tr>
            <td>${subcategory.name}</td>
            <td>
                <a href="edit_subcategory?categoryGuid=${param.categoryGuid}&guid=${subcategory.guid}">编辑</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>