<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Station</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<table class="table table-hover">
    <thead>
    <tr>
        <th colspan="5" class="text-right">
            <div class="btn-group">
                <a class="btn btn-success" href="add_station">Add</a>
            </div>
        </th>
    </tr>
    <tr>
        <th>Name</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${stations}" var="station">
        <tr>
            <td>${station.name}</td>
            <td><a href="edit_station?guid=${station.guid}">Edit</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>