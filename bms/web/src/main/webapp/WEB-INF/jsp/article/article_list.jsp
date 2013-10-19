<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>文章列表</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<div class="operation">
    <a href="/system/article/add">写文章</a>
    <a href="/system/article/category/list">文章分类</a>
</div>
<div>
    <table class="content_table">
        <tr>
            <th>标题</th>
            <th>内容</th>
            <th>分类</th>
            <th>发布时间</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${articleDTOs}" var="article">
            <tr>
                <td><div>${article.title}</div></td>
                <td><div>${article.content}</div></td>
                <td>${article.categoryName}</td>
                <td>${article.creationTime}</td>
                <td><a href="edit?guid=${article.guid}">编辑</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>