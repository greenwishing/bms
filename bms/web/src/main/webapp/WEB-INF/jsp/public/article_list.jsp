<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>公开文章</title>
    <script type="text/javascript">
        $(function () {
            weui.searchBar('#searchBar');
        });
    </script>
</head>
<body>
<div class="page">
    <div class="weui-search-bar" id="searchBar">
        <form class="weui-search-bar__form" id="search-form" action="/articles"
              onsubmit="WF.paging.GO($('#search-form'), 1);return false;">
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
        <div class="weui-panel__hd">公开文章</div>
        <div class="weui-panel__bd">
            <c:choose>
                <c:when test="${not empty pagingDTO.list}">
                    <c:forEach items="${pagingDTO.list}" var="article">
                        <a href="/article/${article.guid}" class="weui-media-box weui-media-box_appmsg">
                            <c:if test="${not empty article.cover.url}">
                                <div class="weui-media-box__hd">
                                    <div class="weui-media-box__thumb" style="background-image: url(${article.cover.url});"></div>
                                </div>
                            </c:if>
                            <div class="weui-media-box__bd">
                                <h4 class="weui-media-box__title">${article.title}</h4>
                                <ul class="weui-media-box__info">
                                    <li class="weui-media-box__info__meta">${article.user.username}</li>
                                    <li class="weui-media-box__info__meta">${article.categoryName}</li>
                                    <li class="weui-media-box__info__meta weui-media-box__info__meta_extra">${article.creationTime}</li>
                                </ul>
                            </div>
                        </a>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="weui-media-box weui-media-box_appmsg">
                        <div class="weui-media-box__bd">
                            <h4 class="weui-media-box__title">敬请期待</h4>
                            <ul class="weui-media-box__info">
                                <li class="weui-media-box__info__meta">站长耍朋友去了...</li>
                            </ul>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <tags:paging formName="search-form" paging="${pagingDTO}"/>
</div>
<div style="display: none;">
    <script>var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "https://tajs.qq.com/stats?sId=62361180";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();</script>
</div>
</body>
</html>