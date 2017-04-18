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
    </style>
</head>
<body>
<div class="weui-article">
    <h1>${article.title}</h1>
    <p class="desc">${article.categoryName} ${article.creationTime}</p>
    <c:if test="${login}">
        <div class="weui-btn-area text-right">
            <a class="weui-btn weui-btn_mini weui-btn_default" href="edit?guid=${article.guid}">编辑</a>
        </div>
    </c:if>
    <section>
        <p>${article.content}</p>
    </section>
</div>
</body>
</html>