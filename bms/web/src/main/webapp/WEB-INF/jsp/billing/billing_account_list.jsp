<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>账户</title>
</head>
<body>
<div>
    <div class="btn-group pull-right">
        <a class="btn btn-primary" href="add_account">添加</a>
        <a class="btn btn-default" href="javascript:void(0)" onclick="history.back()">返回</a>
    </div>
</div>
<table class="table table-hover">
    <thead>
    <tr>
        <th>账户</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${accounts}" var="account">
        <tr>
            <td>${account.type.label} - ${account.name}</td>
            <td>
                <a href="edit_account?guid=${account.guid}">编辑</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>