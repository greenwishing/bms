<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>用户列表</title>

    <script type="text/javascript">
        WF.user.initSearch = function() {
            $('.weui_dialog_confirm').show();
        };
        WF.user.cancelSearch = function() {
            $('.weui_dialog_confirm').hide();
        };
    </script>
</head>
<body>
<div>
    <form id="search-form" class="form-inline pull-left" action="list" onsubmit="return false;">
        <div class="form-group">
            <label class="form-control-static">关键字</label>
            <input class="form-control" type="text" name="key" value="${pagingDTO.key}" placeholder="请输入关键字">
        </div>
        <div class="form-group">
            <a href="javascript:void(0);" class="btn btn-primary" onclick="WF.paging.GO($('#search-form'), 1);">确定</a>
        </div>
    </form>
    <div class="btn-group pull-right">
        <a class="btn btn-primary" href="add">添加</a>
        <a class="btn btn-default" href="javascript:void(0)" onclick="history.back()">返回</a>
    </div>
</div>
<table class="table table-hover">
    <thead>
    <tr>
        <th>用户名</th>
        <th>状态</th>
        <th>&nbsp;</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pagingDTO.list}" var="user">
    <tr>
        <td>${user.username}（${user.account}）</td>
        <td>${user.status.label}</td>
        <td>
            <a href="edit?guid=${user.guid}">编辑</a>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<tags:paging formName="search-form" paging="${pagingDTO}"/>
</body>
</html>