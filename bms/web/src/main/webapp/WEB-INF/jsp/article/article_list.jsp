<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>文章</title>
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
        <div class="weui-panel weui-panel_access">
            <div class="weui-panel__hd">文章列表</div>
            <div class="weui-panel__bd">
                <c:forEach items="${pagingDTO.list}" var="article">
                    <a href="show?guid=${article.guid}" class="weui-media-box weui-media-box_appmsg">
                        <div class="weui-media-box__bd">
                            <h4 class="weui-media-box__title">${article.title}</h4>
                            <ul class="weui-media-box__info">
                                <li class="weui-media-box__info__meta">${article.categoryName}</li>
                                <li class="weui-media-box__info__meta weui-media-box__info__meta_extra">${article.creationTime}</li>
                            </ul>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
        <tags:paging formName="search-form" paging="${pagingDTO}"/>
    </div>
    <div class="weui-tabbar">
        <a href="add" class="weui-tabbar__item">
            <img src="${pageContext.request.contextPath}/images/icons/icon_add.png" class="weui-tabbar__icon">
            <p class="weui-tabbar__label">写文章</p>
        </a>
        <a href="categories" class="weui-tabbar__item">
            <img src="${pageContext.request.contextPath}/images/icons/icon_category.png" class="weui-tabbar__icon">
            <p class="weui-tabbar__label">文章分类</p>
        </a>
    </div>
</div>
</body>
</html>