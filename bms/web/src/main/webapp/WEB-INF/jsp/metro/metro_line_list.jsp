<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>地铁</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <style type="text/css">
        .station {
            display: inline-block;
            vertical-align: middle;
            width: 5em;
            height: 1em;
            margin-right: 5px;
            background: transparent;
        }
    </style>
</head>
<body>
<div>
    <div class="btn-group pull-right">
        <a class="btn btn-primary" href="add">添加</a>
        <a class="btn btn-default" href="stations">站台</a>
        <a class="btn btn-default" href="javascript:void(0)" onclick="history.back();">返回</a>
    </div>
</div>
<table class="table table-hover">
    <thead>
    <tr>
        <th>地铁</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${lines}" var="line">
    <tr>
        <td><span class="station" style="background: ${line.color};"></span><span style="color: ${line.color}">${line.name}</span></td>
        <td>
            <a href="edit?guid=${line.guid}">编辑</a>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>