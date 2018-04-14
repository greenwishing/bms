<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>账户</title>
    <style type="text/css">
        .account {
            padding: 10px;
            background: #fff;
        }
        a {
            color: #888;
        }
    </style>
</head>
<body>
<div class="weui-tab">
    <div class="weui-tab__panel">
        <div class="weui-article">
            <h1>账户</h1>
        </div>
        <c:forEach items="${accounts}" var="account">
            <div class="weui-flex">
                <a class="weui-flex__item" href="edit_account?guid=${account.guid}" async-load="true">
                    <div class="account">
                        <p>${account.type.label} - ${account.name}</p>
                        <h3 class="signum signum_${account.signum}">${account.balance}</h3>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
    <div class="weui-tabbar">
        <a href="add_account" class="weui-tabbar__item" async-load="true">
            <img src="${pageContext.request.contextPath}/images/icons/icon_add.png" class="weui-tabbar__icon">
            <p class="weui-tabbar__label">添加</p>
        </a>
    </div>
</div>
</body>
</html>