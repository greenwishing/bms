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
        WF.billing.initSearch = function() {
            $('.weui_dialog_confirm').show();
        };
        WF.billing.cancelSearch = function() {
            $('.weui_dialog_confirm').hide();
        };
    </script>
</head>
<body>
<div>
    <form id="search-form" class="form-inline pull-left" action="list" onsubmit="return false;">
        <div class="form-group">
            <label class="control-label">关键字</label>
            <input class="form-control" type="text" name="key" value="${pagingDTO.key}" placeholder="请输入关键字">
        </div>
        <div class="form-group">
            <label class="control-label">日期</label>
            <input class="form-control" type="date" name="dateFrom" value="${pagingDTO.dateFrom}" placeholder="请选择开始日期">
            <input class="form-control" type="date" name="dateTo" value="${pagingDTO.dateTo}" placeholder="请选择结束日期">
        </div>
        <div class="form-group">
            <label class="control-label">类型</label>
            <select class="form-control" name="type" id="type" onchange="WF.billing.categories(this)" targetId="categoryGuid" default-value="${param.type}">
                <option value="">请选择</option>
                <c:forEach items="${types}" var="type">
                    <option value="${type.value}" ${type.value eq param.type ? 'selected':''}>${type.label}</option>
                </c:forEach>
            </select>
            <select class="form-control" id="categoryGuid" name="categoryGuid" onchange="WF.billing.subcategories(this)" targetId="subcategoryGuid" default-value="${param.categoryGuid}">
                <option value="">请选择</option>
            </select>
            <select class="form-control" id="subcategoryGuid" name="subcategoryGuid" default-value="${param.subcategoryGuid}">
                <option value="">请选择</option>
            </select>
        </div>
        <div class="form-group">
            <a href="javascript:void(0);" class="btn btn-primary" onclick="WF.paging.GO($('#search-form'), 1);">确定</a>
        </div>
    </form>
    <div class="btn-group pull-right">
        <a class="btn btn-default" href="javascript:void(0)" onclick="history.back()">返回</a>
    </div>
</div>
<table class="table table-hover">
    <thead>
    <tr>
        <th>金额</th>
        <th>名称</th>
        <th class="hidden-sm">分类</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pagingDTO.list}" var="billing">
    <tr data-id="${billing.guid}" title="${billing.settleTime} ${billing.status.label}">
        <td>
            <span class="price">${billing.amount}</span>
        </td>
        <td>
            <div>${billing.name}</div>
            <div>${billing.occurredTime}</div>
        </td>
        <td class="hidden-sm">
            <div>${billing.type.label} - ${billing.categoryName} - ${billing.subcategoryName}</div>
        </td>
        </c:forEach>
    </tr>
    </tbody>
</table>
<tags:paging formName="search-form" paging="${pagingDTO}"/>
</body>
</html>