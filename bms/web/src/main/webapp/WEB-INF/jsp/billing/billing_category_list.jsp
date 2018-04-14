<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>账单分类</title>

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
<div class="weui-tab">
    <div class="weui-tab__panel">
        <div class="weui-article">
            <h1>账单分类</h1>
        </div>
        <div class="weui-cells">
            <c:forEach items="${categories}" var="category">
                <a class="weui-cell weui-cell_access" href="edit_category?guid=${category.guid}" async-load="true">
                    <div class="weui-cell__bd">${category.type.label} - ${category.name}</div>
                    <div class="weui-cell__ft"></div>
                </a>
            </c:forEach>
        </div>
    </div>
    <div class="weui-tabbar">
        <a href="add_category" class="weui-tabbar__item" async-load="true">
            <img src="${pageContext.request.contextPath}/images/icons/icon_add.png" class="weui-tabbar__icon">
            <p class="weui-tabbar__label">添加</p>
        </a>
        <c:if test="${empty categories}">
            <a href="javascript:void(0)" class="weui-tabbar__item" onclick="gen()">
                <img src="${pageContext.request.contextPath}/images/icons/icon_add.png" class="weui-tabbar__icon">
                <p class="weui-tabbar__label">生成默认分类</p>
            </a>
        </c:if>
    </div>
</div>
</body>
</html>