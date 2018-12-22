<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>${article.title}</title>
    <style type="text/css">
        p:not(.desc) {
            text-indent: 2em;
        }
        .desc {
            display: inline-block;
            color: #888;
        }
        .weui-article {
            position: relative;
        }
        .edit-btn {
            position: absolute;
            top: 20px;
            right: 15px;
        }
    </style>
</head>
<body>
<c:choose>
    <c:when test="${article == null}">
        <div class="weui-msg">
            <div class="weui-msg__icon-area"><i class="weui-icon-warn weui-icon_msg"></i></div>
            <div class="weui-msg__text-area">
                <h2 class="weui-msg__title">文章不存在</h2>
            </div>
            <div class="weui-msg__opr-area">
                <p class="weui-btn-area">
                    <a href="javascript:history.back();" class="weui-btn weui-btn_primary">确定</a>
                </p>
            </div>
        </div>
    </c:when>
    <c:when test="${'PUBLIC' ne article.access and (loginUserGuid == null ? true : (loginUserGuid ne article.user.guid))}">
        <div class="weui-msg">
            <div class="weui-msg__icon-area"><i class="weui-icon-warn weui-icon_msg"></i></div>
            <div class="weui-msg__text-area">
                <h2 class="weui-msg__title">无权查看此文章</h2>
            </div>
            <div class="weui-msg__opr-area">
                <div class="weui-btn-area">
                    <a href="javascript:history.back();" class="weui-btn weui-btn_primary">确定</a>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="weui-article">
            <h1>${article.title}</h1>
            <p class="desc">${article.creationTime} ${article.categoryName} <a href="/articles/${article.user.guid}">${article.user.username}</a></p>
            <c:if test="${login}">
                <a class="weui-btn weui-btn_mini weui-btn_default edit-btn" href="edit?guid=${article.guid}" async-load="true">编辑</a>
            </c:if>
            <c:if test="${not empty article.cover.url}">
                <img src="${article.cover.url}"/>
            </c:if>
            <section>
                <p>${article.content}</p>
            </section>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>