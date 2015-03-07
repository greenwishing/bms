<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>文章</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<blockquote>
    <form id="search-form" action="list" class="form-inline">
        <a class="btn btn-success" href="add">写文章</a>
        <a class="btn btn-primary" href="/system/article_category/list">文章分类</a>
        <div class="form-group">
            <label class="control-label" for="key">关键字</label>
            <input class="form-control" id="key" name="key" type="text" value="${pagingDTO.key}" placeholder="关键字"/>
        </div>
        <button type="button" class="btn btn-default" onclick="WF.paging.GO($('#search-form'), 1)">查询</button>
    </form>
</blockquote>
<table class="table table-hover">
    <thead>
    <tr>
        <th>标题</th>
        <th>分类</th>
        <th>发布时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pagingDTO.list}" var="article">
        <tr>
            <td><div title="${article.title}">${article.title}</div></td>
            <td>${article.categoryName}</td>
            <td>${article.creationTime}</td>
            <td>
                <a href="edit?guid=${article.guid}">编辑</a>
                <a href="show?guid=${article.guid}">查看</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<tags:paging formName="search-form" paging="${pagingDTO}"/>
</body>
</html>