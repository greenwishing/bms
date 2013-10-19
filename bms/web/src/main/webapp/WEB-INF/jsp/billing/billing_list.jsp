<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>账单</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<div class="operation">
    <a href="/system/billing/add">添加</a>
</div>
<div>
    <table class="content_table">
        <tr>
            <th class="w120">名称</th>
            <th>描述</th>
            <th class="w100">类型</th>
            <th class="w100">金额</th>
            <th class="w100">时间</th>
            <th class="w120">操作</th>
        </tr>
        <c:forEach items="${billingDTOs}" var="billing" varStatus="i">
            <tr class="${i.index%2==0?'tr_odd':''}">
                <td>${billing.name}</td>
                <td><div title="${billing.description}">${billing.description}</div></td>
                <td>${billing.type}</td>
                <td><span class="price ${billing.type}">${billing.amount}</span></td>
                <td>${billing.occurredTime}</td>
                <td><a href="delete?guid=${billing.guid}">删除</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>