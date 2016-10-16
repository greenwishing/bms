<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>${article.title}</title>

</head>
<body>
<div>
    <div class="btn-group pull-right">
        <a class="btn btn-default" href="edit?guid=${article.guid}">编辑</a>
        <a class="btn btn-default" href="javascript:void(0)" onclick="history.back()">返回</a>
    </div>
</div>
<div>
    <h3>${article.title}</h3>
    <div>${article.content}</div>
</div>
</body>
</html>