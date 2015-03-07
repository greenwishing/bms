<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${article.title}</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<div>
    <div>${article.content}</div>
</div>
<blockquote>
    <a class="btn btn-default" href="list">返回</a>
</blockquote>
</body>
</html>