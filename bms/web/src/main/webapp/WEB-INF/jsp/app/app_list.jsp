<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>我的应用</title>
</head>
<body>
<div>
    <div class="btn-group pull-right">
        <a class="btn btn-primary" href="reg">添加</a>
        <a class="btn btn-default" href="javascript:void(0)" onclick="history.back();">返回</a>
    </div>
</div>
<table class="table table-hover">
    <thead>
    <tr>
        <th>应用</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${apps}" var="app">
        <tr>
            <td>${app.appId}</td>
            <td>
                <a href="edit?appId=${app.appId}">编辑</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="${pageContext.request.contextPath}/wiki/app.jsp">接入文档</a></p>
</body>
</html>