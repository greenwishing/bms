<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
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
<spring-form:form cssClass="form-horizontal" id="data-form" commandName="billingDTO" method="post" onsubmit="return false;">
    <spring-form:errors path="type" element="div" cssClass="alert alert-danger"/>
    <spring-form:errors path="name" element="div" cssClass="alert alert-danger"/>
    <spring-form:errors path="occurredTime" element="div" cssClass="alert alert-danger"/>
    <spring-form:errors path="amount" element="div" cssClass="alert alert-danger"/>
    <div class="form-group">
        <div class="col-sm-12">
            <div class="template-container"></div>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="type">类型</label>
        <div class="col-sm-10">
            <spring-form:select id="type" cssClass="form-control" path="type" items="${types}" itemValue="value" itemLabel="label" onchange="WF.billing.categories(this)" targetId="categoryGuid"/>
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
            <spring-form:input cssClass="form-control" path="name" id="name" placeholder="名称"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="occurredTime">时间</label>
        <div class="col-sm-10">
            <spring-form:input cssClass="form-control" path="occurredTime" id="occurredTime" placeholder="时间"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="amount">金额</label>
        <div class="col-sm-10">
            <spring-form:input cssClass="form-control" path="amount" id="amount" placeholder="金额"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="description">描述</label>
        <div class="col-sm-10">
            <spring-form:textarea cssClass="form-control" path="description" id="description" placeholder="描述"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-10 col-sm-offset-2">
            <div class="checkbox">
                <label>
                    <spring-form:checkbox path="createTemplate"/> 创建模板
                </label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-10 col-sm-offset-2">
            <spring-form:hidden path="occurredUserGuid"/>
            <input class="btn btn-success" type="button" value="保存" onclick="WF.form.submit($('#data-form'))"/>
            <input class="btn btn-default" type="button" value="返回" onclick="WF.page.list()"/>
        </div>
    </div>
</spring-form:form>
</body>
</html>