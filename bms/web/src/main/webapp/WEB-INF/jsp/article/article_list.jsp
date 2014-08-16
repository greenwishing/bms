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
<div class="p10">
    <form id="article_search_form" action="list">
        <a class="btn" href="/system/article/add">写文章</a>
        <a class="btn" href="/system/article/category/list">文章分类</a>
        <label>关键字</label>
        <input name="key" type="text" value="${pagingDTO.key}"/>
        <input type="button" value="查询" onclick="WF.paging.GO($('#article_search_form'), 1)"/>
    </form>
</div>
<div>
    <table class="content_table">
        <tr>
            <th class="w200">标题</th>
            <th>内容</th>
            <th class="w100">分类</th>
            <th class="w120">发布时间</th>
            <th class="w100">操作</th>
        </tr>
        <c:forEach items="${pagingDTO.list}" var="article" varStatus="i">
            <tr class="${i.index%2==0?'tr_odd':''}">
                <td><div class="nowrap" title="${article.title}">${article.title}</div></td>
                <td><div class="nowrap" title="${article.contentText}">${article.contentText}</div></td>
                <td>${article.categoryName}</td>
                <td>${article.creationTime}</td>
                <td><a href="edit?guid=${article.guid}">编辑</a></td>
            </tr>
        </c:forEach>
    </table>
    <div class="p10">
        <tags:paging formName="article_search_form" paging="${pagingDTO}"/>
    </div>
</div>
</body>
</html>