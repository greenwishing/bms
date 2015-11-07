<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>客户端列表</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<blockquote>
    <a class="btn btn-success" href="reg">注册客户端</a>
</blockquote>
<table class="table table-hover">
    <thead>
    <tr>
        <th>客户端ID</th>
        <th>认证类型</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${clients}" var="client">
        <tr>
            <td>${client.clientId}</td>
            <td>${client.authorizedGrantTypes}</td>
            <td>
                <a href="edit?clientId=${client.clientId}">编辑</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>