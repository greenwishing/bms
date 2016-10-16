<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文章分类</title>

    <script type="text/javascript">
        function gen() {
            WF.ajax.req({
                url: 'gen',
                success: function() {
                    location.reload();
                }
            });
        }
    </script>
</head>
<body>
<div>
    <div class="btn-group pull-right">
        <a class="btn btn-primary" href="add_category">添加</a>
        <c:if test="${empty categoryDTOs}">
            <a class="btn btn-default" href="javascript:void(0)" onclick="gen()">生成默认分类</a>
        </c:if>
        <a class="btn btn-default" href="javascript:void(0)" onclick="history.back();">返回</a>
    </div>
</div>
<table class="table table-hover">
    <thead>
    <tr>
        <th>分类</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${categoryDTOs}" var="category">
    <tr>
        <td>${category.name}</td>
        <td>
            <a href="edit_category?guid=${category.guid}">编辑</a>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>