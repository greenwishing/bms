<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>账单列表</title>
    <script type="text/javascript">
        $(function(){
            WF.billing.categories($('#type'));
        });
    </script>
    <style type="text/css">
        .weui-form-preview {
            margin-top: 15px;
        }
    </style>
</head>
<body>
<form id="search-form" action="list" onsubmit="return false;">
    <div class="weui-cells__title">查询</div>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label class="weui-label">关键字</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="text" name="key" value="${pagingDTO.key}" placeholder="请输入关键字">
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label class="weui-label">日期从</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="date" name="dateFrom" value="${pagingDTO.dateFrom}" placeholder="请选择开始日期">
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label class="weui-label">到</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="date" name="dateTo" value="${pagingDTO.dateTo}" placeholder="请选择结束日期">
            </div>
        </div>
        <div class="weui-cell weui-cell_select weui-cell_select-after">
            <div class="weui-cell__hd">
                <label class="weui-label">类型</label>
            </div>
            <div class="weui-cell__bd">
                <select class="weui-select" name="type" id="type" onchange="WF.billing.categories(this)" targetId="categoryGuid" default-value="${param.type}">
                    <option value="">请选择</option>
                    <c:forEach items="${types}" var="type">
                        <option value="${type.value}" ${type.value eq param.type ? 'selected':''}>${type.label}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="weui-cell weui-cell_select weui-cell_select-after">
            <div class="weui-cell__hd">
                <label class="weui-label">分类</label>
            </div>
            <div class="weui-cell__bd">
                <select class="weui-select" id="categoryGuid" name="categoryGuid" onchange="WF.billing.subcategories(this)" targetId="subcategoryGuid" default-value="${param.categoryGuid}">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="weui-cell weui-cell_select weui-cell_select-after">
            <div class="weui-cell__hd">
                <label class="weui-label">子分类</label>
            </div>
            <div class="weui-cell__bd">
                <select class="weui-select" id="subcategoryGuid" name="subcategoryGuid" default-value="${param.subcategoryGuid}">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
    </div>
    <div class="weui-btn-area">
        <a href="javascript:void(0);" class="weui-btn weui-btn_primary" onclick="WF.paging.GO($('#search-form'), 1);">查询</a>
    </div>
</form>
<c:forEach items="${pagingDTO.list}" var="billing">
    <div class="weui-form-preview">
        <div class="weui-form-preview__hd">
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">金额</label>
                <em class="weui-form-preview__value">￥${billing.amount}</em>
            </div>
        </div>
        <div class="weui-form-preview__bd">
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">类型</label>
                <span class="weui-form-preview__value">${billing.type.label}</span>
            </div>
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">项目</label>
                <span class="weui-form-preview__value">${billing.name}</span>
            </div>
            <c:if test="${not empty billing.categoryName}">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">分类</label>
                    <span class="weui-form-preview__value">${billing.categoryName} ${billing.subcategoryName}</span>
                </div>
            </c:if>
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">时间</label>
                <span class="weui-form-preview__value">${billing.occurredTime}</span>
            </div>
            <c:if test="${not empty billing.description}">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">描述</label>
                    <span class="weui-form-preview__value">${billing.description}</span>
                </div>
            </c:if>
            <c:if test="${not empty billing.settleTime}">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">描述</label>
                    <span class="weui-form-preview__value">${billing.settleTime} ${billing.status.label}</span>
                </div>
            </c:if>
        </div>
        <%--<div class="weui-form-preview__ft">
            <a class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:">操作</a>
        </div>--%>
    </div>
</c:forEach>
<tags:paging formName="search-form" paging="${pagingDTO}"/>
</body>
</html>