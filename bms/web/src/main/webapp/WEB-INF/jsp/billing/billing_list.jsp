<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>账单列表</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <script type="text/javascript">
        $(function(){
            WF.util.dateFromToPicker('dateFrom', 'dateTo');
            WF.billing.categories($('#type'));
        });
    </script>
</head>
<body>
<blockquote>
    <form id="search-form" action="list" class="form-inline" onsubmit="return false;">
        <a class="btn btn-success" href="add">添加</a>
        <a class="btn btn-primary" href="categories">分类管理</a>
        <a class="btn btn-primary" href="templates">模板管理</a>
        <div class="form-group">
            <label class="control-label" for="key">关键字</label>
            <input id="key" name="key" type="text" class="form-control" value="${pagingDTO.key}" placeholder="关键字"/>
        </div>
        <div class="form-group">
        </div>
        <div class="form-group form-more" style="display: none;">
            <label class="control-label" for="dateFrom">时间</label>
            <input class="form-control" type="text" id="dateFrom" name="dateFrom" value="${pagingDTO.dateFrom}" placeholder="开始时间"/>
            <input class="form-control" type="text" id="dateTo" name="dateTo" value="${pagingDTO.dateTo}" placeholder="结束时间"/>
        </div>
        <div class="form-group form-more" style="display: none;">
            <label class="control-label" for="type">类型/分类</label>
            <select id="type" name="type" class="form-control" onchange="WF.billing.categories(this)" targetId="categoryGuid" default-value="${param.type}">
                <option value="">请选择</option>
                <c:forEach items="${types}" var="type">
                    <option value="${type.value}" ${type.value eq param.type ? 'selected':''}>${type.label}</option>
                </c:forEach>
            </select>
            <select id="categoryGuid" class="form-control" name="categoryGuid" onchange="WF.billing.subcategories(this)" targetId="subcategoryGuid" default-value="${param.categoryGuid}">
                <option value="">请选择</option>
            </select>
            <select id="subcategoryGuid" class="form-control" name="subcategoryGuid" default-value="${param.subcategoryGuid}">
                <option value="">请选择</option>
            </select>
        </div>
        <button type="button" class="btn btn-default" onclick="$('.form-more').toggle()">&gt;</button>
        <button type="button" class="btn btn-default" onclick="WF.paging.GO($('#search-form'), 1)">查询</button>
    </form>
</blockquote>
<table class="table table-hover">
    <thead>
    <tr>
        <th>名称</th>
        <th>类型</th>
        <th>分类</th>
        <th>金额</th>
        <th>时间</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pagingDTO.list}" var="billing" varStatus="i">
        <tr>
            <td>${billing.name}</td>
            <td>${billing.type.label}</td>
            <td title="${billing.description}">${billing.categoryName} ${billing.subcategoryName}</td>
            <td><span class="price ${billing.type}">${billing.amount}</span></td>
            <td>${billing.occurredTime}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<tags:paging formName="search-form" paging="${pagingDTO}"/>
</body>
</html>