<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>用户列表</title>
    <script type="text/javascript">
        $(function(){
            weui.searchBar('#searchBar');
        });
    </script>
</head>
<body>
<div class="weui-tab">
    <div class="weui-tab__panel">
        <div class="weui-search-bar" id="searchBar">
            <form class="weui-search-bar__form" id="search-form" action="list" onsubmit="WF.paging.GO($('#search-form'), 1);return false;">
                <div class="weui-search-bar__box">
                    <i class="weui-icon-search"></i>
                    <input type="text" class="weui-search-bar__input" name="key" placeholder="搜索" value="${pagingDTO.key}">
                    <a href="javascript:void(0)" class="weui-icon-clear"></a>
                </div>
                <label class="weui-search-bar__label">
                    <i class="weui-icon-search"></i>
                    <span>搜索</span>
                </label>
            </form>
            <a href="javascript:void(0)" class="weui-search-bar__cancel-btn">取消</a>
        </div>
        <div class="weui-cells__title">用户列表</div>
        <div class="weui-cells">
            <c:forEach items="${pagingDTO.list}" var="user">
                <a class="weui-cell weui-cell_access" href="edit?guid=${user.guid}">
                    <div class="weui-cell__bd">${user.username}（${user.account}）</div>
                    <div class="weui-cell__ft">${user.status.label}</div>
                </a>
            </c:forEach>
        </div>
        <tags:paging formName="search-form" paging="${pagingDTO}"/>
    </div>
    <div class="weui-tabbar">
        <a href="add" class="weui-tabbar__item">
            <img src="${pageContext.request.contextPath}/images/icons/icon_add.png" class="weui-tabbar__icon">
            <p class="weui-tabbar__label">添加</p>
        </a>
    </div>
</div>
</body>
</html>