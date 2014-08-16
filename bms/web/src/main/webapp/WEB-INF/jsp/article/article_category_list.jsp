<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>文章分类</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<div class="p10">
    <a class="btn" href="/system/article/category/add">添加分类</a>
    <a class="btn" href="../list">返回</a>
</div>
<div>
    <table class="content_table">
        <tr>
            <th>名称</th>
            <th class="w100">操作</th>
        </tr>
        <c:forEach items="${categoryDTOs}" var="category" varStatus="i">
            <tr class="${i.index%2==0?'tr_odd':''}">
                <td>${category.name}</td>
                <td><a href="edit?guid=${category.guid}">编辑</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>