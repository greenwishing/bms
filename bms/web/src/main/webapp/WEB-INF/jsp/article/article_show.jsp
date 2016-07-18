<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>${article.title}</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<div class="weui_article">
    <h1>${article.title}<a href="edit?guid=${article.guid}" style="float: right; font-weight: normal;">编辑</a></h1>
    <div>${article.content}</div>
</div>
</body>
</html>