<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>${article.title}</title>
    <meta charset="utf-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta content="telephone=no" name="format-detection"/>
    <meta name="Keywords" content="${article.user.username},文章写作">
    <meta name="Description" content="${article.title}">
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
    <link rel="apple-touch-icon" href="/images/wishing.png" />
    <link rel="apple-touch-icon" sizes="57x57" href="/images/apple-touch-icon-57.png" />
    <link rel="apple-touch-icon" sizes="72x72" href="/images/apple-touch-icon-72.png" />
    <link rel="apple-touch-icon" sizes="114x114" href="/images/apple-touch-icon-114.png" />
    <link rel="apple-touch-icon" sizes="144x144" href="/images/apple-touch-icon-144.png" />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/weui/weui.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.2.min.js"></script>
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
<div class="page">
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
                <a class="weui-btn weui-btn_mini weui-btn_default edit-btn" href="edit?guid=${article.guid}">编辑</a>
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
</div>
</body>
</html>