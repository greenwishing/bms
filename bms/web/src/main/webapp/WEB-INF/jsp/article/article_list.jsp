<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>文章</title>

    <script type="text/javascript">
        (function(){
            WF.article.initSearch = function() {
                $('.weui_dialog_confirm').show();
            };
            WF.article.cancelSearch = function() {
                $('.weui_dialog_confirm').hide();
            };
        })();
    </script>
</head>
<body>
<div>
    <form class="form-inline pull-left" id="search-form" action="list" onsubmit="return false;">
        <div class="form-group">
            <label class="control-label">关键字</label>
            <input class="form-control" type="text" name="key" value="${pagingDTO.key}" placeholder="请输入关键字">
        </div>
        <div class="form-group">
            <a class="btn btn-primary" href="javascript:void(0);" onclick="WF.paging.GO($('#search-form'), 1);">确定</a>
        </div>
    </form>
    <div class="btn-group pull-right">
        <a class="btn btn-primary" href="add">写文章</a>
        <a class="btn btn-default" href="categories">分类</a>
        <a class="btn btn-default" href="javascript:void(0)" onclick="history.back();">返回</a>
    </div>
</div>
<table class="table table-hover">
    <thead>
    <tr>
        <th>标题</th>
        <th>分类</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pagingDTO.list}" var="article">
    <tr>
        <td>${article.title}</td>
        <td>
            <div>${article.categoryName}</div>
            <div>${article.creationTime}</div>
        </td>
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