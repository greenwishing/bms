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
<div class="weui-article">
    <h1>${article.title}</h1>
    <p class="desc">${article.categoryName} ${article.creationTime}</p>
    <c:if test="${login}">
        <a class="weui-btn weui-btn_mini weui-btn_default edit-btn" href="edit?guid=${article.guid}">编辑</a>
    </c:if>
    <section>
        <p>${article.content}</p>
    </section>
</div>
</body>
</html>