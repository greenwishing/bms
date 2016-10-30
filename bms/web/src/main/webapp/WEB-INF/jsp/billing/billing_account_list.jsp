<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>账户</title>
</head>
<body>
<div class="accounts">
    <c:forEach items="${accounts}" var="account">
    <div class="col-6">
        <div class="account">
            <p>${account.type.label} - ${account.name}</p>
            <h3 class="signum signum_${account.signum}">${account.balance}</h3>
            <a href="edit_account?guid=${account.guid}">编辑</a>
        </div>
    </div>
    </c:forEach>
    <div class="col-6">
        <div class="account">
            <a href="add_account">添加</a>
        </div>
    </div>
</div>
</body>
</html>