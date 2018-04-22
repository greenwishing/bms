<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>反馈</title>
</head>
<body>
<form style="display: none" id="search-form" action="list" onsubmit="WF.paging.GO($('#search-form'), 1);return false;"></form>
<c:forEach items="${pagingDTO.list}" var="feedback">
    <div class="weui-panel weui-panel_access">
        <div class="weui-panel__bd">
            <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
                <c:if test="${feedback.imageUrl != ''}">
                    <div class="weui-media-box__hd">
                        <img class="weui-media-box__thumb" src="${feedback.imageUrl}">
                    </div>
                </c:if>
                <div class="weui-media-box__bd">
                    <h4 class="weui-media-box__title">${feedback.content}</h4>
                    <ul class="weui-media-box__info">
                        <li class="weui-media-box__info__meta">${feedback.username}</li>
                        <li class="weui-media-box__info__meta">${feedback.creationTime}</li>
                    </ul>
                </div>
            </a>
            <c:forEach items="${feedback.replies}" var="reply">
                <div class="weui-media-box weui-media-box_appmsg">
                    <div class="weui-media-box__bd">
                        <p class="weui-media-box__desc">账小流：${reply.content} ${reply.creationTime}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="weui-panel__ft">
            <a href="reply?feedbackGuid=${feedback.guid}" class="weui-cell weui-cell_access weui-cell_link" async-load="true">
                <div class="weui-cell__bd">回复</div>
                <span class="weui-cell__ft"></span>
            </a>
        </div>
    </div>
</c:forEach>
<tags:paging formName="search-form" paging="${pagingDTO}"/>
</body>
</html>