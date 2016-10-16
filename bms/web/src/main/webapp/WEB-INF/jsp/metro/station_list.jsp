<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>站台</title>
</head>
<body>
<div>
    <div class="btn-group pull-right">
        <a class="btn btn-primary" href="add_station">添加</a>
        <a class="btn btn-default" href="javascript:void(0)" onclick="history.back();">返回</a>
    </div>
</div>
<table class="table table-hover">
    <thead>
    <tr>
        <th>站台</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${stations}" var="station">
        <tr>
            <td>${station.name}</td>
            <td>
                <a href="edit_station?guid=${station.guid}">编辑</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>