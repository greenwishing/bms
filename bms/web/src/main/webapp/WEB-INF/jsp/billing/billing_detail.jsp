<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>账单详情</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/wepayui/wepayui.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/wepayui/wepayui.details.css">
    <style type="text/css">
        .page {
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<div class="weui-wepay-details">
    <div class="weui-wepay-details__hd">
        <div class="weui-wepay-details__state">
            <h2 class="weui-wepay-details__title">${billingDTO.type.label}</h2>
            <p class="weui-wepay-details__desc">￥${billingDTO.amount}</p>
        </div>
    </div>
    <div class="weui-wepay-details__bd">
        <div class="weui-wepay-detail">
            <div class="weui-wepay-detail__bd">项目</div>
            <div class="weui-wepay-detail__ft">${billingDTO.name}</div>
        </div>
        <c:if test="${not empty billingDTO.categoryName}">
            <div class="weui-wepay-detail">
                <div class="weui-wepay-detail__bd">分类</div>
                <div class="weui-wepay-detail__ft">${billingDTO.categoryName} ${billingDTO.subcategoryName}</div>
            </div>
        </c:if>
        <c:choose>
            <c:when test="${'EXPEND' eq billingDTO.type}">
                <div class="weui-wepay-detail">
                    <div class="weui-wepay-detail__bd">支出账户</div>
                    <div class="weui-wepay-detail__ft">${billingDTO.srcAccountName}</div>
                </div>
            </c:when>
            <c:when test="${'INCOME' eq billingDTO.type}">
                <div class="weui-wepay-detail">
                    <div class="weui-wepay-detail__bd">收入账户</div>
                    <div class="weui-wepay-detail__ft">${billingDTO.srcAccountName}</div>
                </div>
            </c:when>
            <c:when test="${'TRANSFER' eq billingDTO.type}">
                <div class="weui-wepay-detail">
                    <div class="weui-wepay-detail__bd">转出账户</div>
                    <div class="weui-wepay-detail__ft">${billingDTO.srcAccountName}</div>
                </div>
                <div class="weui-wepay-detail">
                    <div class="weui-wepay-detail__bd">转入账户</div>
                    <div class="weui-wepay-detail__ft">${billingDTO.targetAccountName}</div>
                </div>
            </c:when>
            <c:when test="${'BORROW' eq billingDTO.type}">
                <div class="weui-wepay-detail">
                    <div class="weui-wepay-detail__bd">借入账户</div>
                    <div class="weui-wepay-detail__ft">${billingDTO.srcAccountName}</div>
                </div>
                <div class="weui-wepay-detail">
                    <div class="weui-wepay-detail__bd">债权人</div>
                    <div class="weui-wepay-detail__ft">${billingDTO.targetAccountName}</div>
                </div>
            </c:when>
            <c:when test="${'LOAN' eq billingDTO.type}">
                <div class="weui-wepay-detail">
                    <div class="weui-wepay-detail__bd">借出账户</div>
                    <div class="weui-wepay-detail__ft">${billingDTO.srcAccountName}</div>
                </div>
                <div class="weui-wepay-detail">
                    <div class="weui-wepay-detail__bd">债务人</div>
                    <div class="weui-wepay-detail__ft">${billingDTO.targetAccountName}</div>
                </div>
            </c:when>
            <c:when test="${'RECEIVE' eq billingDTO.type}">
                <div class="weui-wepay-detail">
                    <div class="weui-wepay-detail__bd">收款账户</div>
                    <div class="weui-wepay-detail__ft">${billingDTO.srcAccountName}</div>
                </div>
                <div class="weui-wepay-detail">
                    <div class="weui-wepay-detail__bd">债务人</div>
                    <div class="weui-wepay-detail__ft">${billingDTO.targetAccountName}</div>
                </div>
            </c:when>
            <c:when test="${'PAYBACK' eq billingDTO.type}">
                <div class="weui-wepay-detail">
                    <div class="weui-wepay-detail__bd">还款账户</div>
                    <div class="weui-wepay-detail__ft">${billingDTO.srcAccountName}</div>
                </div>
                <div class="weui-wepay-detail">
                    <div class="weui-wepay-detail__bd">债务人</div>
                    <div class="weui-wepay-detail__ft">${billingDTO.targetAccountName}</div>
                </div>
            </c:when>
        </c:choose>
        <div class="weui-wepay-detail">
            <div class="weui-wepay-detail__bd">时间</div>
            <div class="weui-wepay-detail__ft">${billingDTO.occurredTime}</div>
        </div>
        <c:if test="${not empty billingDTO.description}">
            <div class="weui-wepay-detail">
                <div class="weui-wepay-detail__bd">描述</div>
                <div class="weui-wepay-detail__ft">${billingDTO.description}</div>
            </div>
        </c:if>
        <c:if test="${not empty billingDTO.settleTime}">
            <div class="weui-wepay-detail">
                <div class="weui-wepay-detail__bd">还款时间</div>
                <div class="weui-wepay-detail__ft">${billingDTO.settleTime} ${billingDTO.status.label}</div>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>