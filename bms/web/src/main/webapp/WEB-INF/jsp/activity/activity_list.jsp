<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>活动</title>
</head>
<body>
<div class="weui-tab">
    <div class="weui-tab__panel">
        <div class="weui-panel weui-panel_access">
            <div class="weui-panel__hd">活动列表</div>
            <div class="weui-panel__bd">
                <c:forEach items="${pagingDTO.list}" var="activity">
                    <a href="view?guid=${activity.guid}" class="weui-media-box weui-media-box_appmsg">
                        <div class="weui-media-box__bd">
                            <h4 class="weui-media-box__title">${activity.name}</h4>
                            <p class="weui-media-box__desc">${activity.remark}</p>
                            <ul class="weui-media-box__info">
                                <li class="weui-media-box__info__meta">${activity.dateFrom}至${activity.dateTo}</li>
                            </ul>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
        <tags:paging formName="search-form" paging="${pagingDTO}"/>
    </div>
    <div class="weui-tabbar">
        <a href="add" class="weui-tabbar__item" async-load="true">
            <img src="${pageContext.request.contextPath}/images/icons/icon_add.png" class="weui-tabbar__icon">
            <p class="weui-tabbar__label">新活动</p>
        </a>
    </div>
</div>
</body>
</html>