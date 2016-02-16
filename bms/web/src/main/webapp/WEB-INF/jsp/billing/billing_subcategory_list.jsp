<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>账单子分类</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<table class="table table-hover">
    <thead>
    <tr>
        <th colspan="2" class="text-right">
            <div class="btn-group">
                <a class="btn btn-success" href="add_subcategory?categoryGuid=${param.categoryGuid}">添加</a>
                <a class="btn btn-default" href="categories">返回</a>
            </div>
        </th>
    </tr>
    <tr>
        <th>名称</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${subcategories}" var="subcategory">
        <tr>
            <td>${subcategory.name}</td>
            <td>
                <a href="edit_subcategory?categoryGuid=${param.categoryGuid}&guid=${subcategory.guid}&">编辑</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>