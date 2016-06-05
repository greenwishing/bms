<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Metro</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <style type="text/css">
        .metro { display: inline-block; height: 1em; width: 1em; color: white;}
    </style>
</head>
<body>
<table class="table table-hover">
    <thead>
    <tr>
        <th colspan="5" class="text-right">
            <div class="btn-group">
                <a class="btn btn-success" href="add">Add</a>
                <a class="btn btn-default" href="stations">Stations</a>
            </div>
        </th>
    </tr>
    <tr>
        <th>Color/Name</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${lines}" var="line">
        <tr>
            <td>
                <span class="metro" style="background: ${line.color};"></span>
                <span>${line.name}</span>
            </td>
            <td><a href="edit?guid=${line.guid}">Edit</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>