<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>记账</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <script type="text/javascript">
        $(function(){
            WF.billing.templates($('.template-container'), function(btn){
                var template = $(btn);
                $('#categoryGuid').attr({'default-value': template.attr('categoryGuid')});
                $('#subcategoryGuid').attr({'default-value': template.attr('subcategoryGuid')});
                $('#name').val(template.attr('name'));
                $('#amount').val(template.attr('amount'));
                var type = $('#type');
                type.val(template.attr('type'));
                WF.billing.categories(type);
            });
            WF.billing.categories($('#type'));
        });
    </script>
</head>
<body>
<form class="form-horizontal" id="data-form" action="add" method="post" onsubmit="return false;">
    <div class="form-group">
        <div class="col-sm-12">
            <div class="template-container"></div>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="type">类型</label>
        <div class="col-sm-10">
            <select id="type" class="form-control" name="type" onchange="WF.billing.categories(this)" targetId="categoryGuid">
                <c:forEach items="${types}" var="type">
                    <option value="${type.value}" ${type==billingDTO.type?'selected':''}>${type.label}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="categoryGuid">分类</label>
        <div class="col-sm-10">
            <select id="categoryGuid" class="form-control" name="categoryGuid" onchange="WF.billing.subcategories(this)" default-value="${billingDTO.categoryGuid}" targetId="subcategoryGuid">
                <option value="">请选择</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="subcategoryGuid">子分类</label>
        <div class="col-sm-10">
            <select id="subcategoryGuid" class="form-control" name="subcategoryGuid" default-value="${billingDTO.subcategoryGuid}">
                <option value="">请选择</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="name">名称</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="name" id="name" placeholder="名称" value="${billingDTO.name}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="occurredTime">时间</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="occurredTime" id="occurredTime" placeholder="时间" value="${billingDTO.occurredTime}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="amount">金额</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="amount" id="amount" placeholder="金额" value="${billingDTO.amount}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="description">描述</label>
        <div class="col-sm-10">
            <textarea class="form-control" name="description" id="description" placeholder="描述" cols="80" rows="3">${billingDTO.description}</textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2">创建模板</label>
        <div class="col-sm-10">
            <label class="radio-inline">
                <input type="radio" name="createTemplate" value="true" /> 是
            </label>
            <label class="radio-inline">
                <input type="radio" name="createTemplate" value="false" checked /> 否
            </label>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-10 col-sm-offset-2">
            <input class="btn btn-success" type="button" value="保存" onclick="WF.form.ajaxSubmit($('#data-form'))"/>
            <input class="btn btn-default" type="button" value="返回" onclick="WF.page.forward('list')"/>
        </div>
    </div>
</form>
</body>
</html>