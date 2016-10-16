<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>记账</title>
    <script type="text/javascript">
        $(function(){
            WF.billing.categories($('#type'));
            // WF.util.datePicker('occurredTime');

            (function(){
                var $selection = $('#template_selection');
                WF.ajax.req({
                    type: 'post',
                    url: 'templates',
                    data: {dataType: 'json'},
                    success: function(result) {
                        var templates = result.templates;
                        for (var i in templates) {
                            var template = templates[i];
                            var $option = $('<option></option>').html(template.name + ' ' + template.amount).attr(template);
                            $selection.append($option);
                        }
                    }
                });
            })();
        });

        function applyTemplate(elem) {
            var $option = $(elem).find('option:selected');
            $('#categoryGuid').attr({'default-value': $option.attr('categoryGuid')});
            $('#subcategoryGuid').attr({'default-value': $option.attr('subcategoryGuid')});
            $('#name').val($option.attr('name'));
            $('#amount').val($option.attr('amountFloat'));
            var type = $('#type');
            type.val($option.attr('type'));
            WF.billing.categories(type);
        }
    </script>
</head>
<body>
    <form id="data-form" action="add" method="post" onsubmit="return false;">
        <div class="form-group">
            <label class="form-control-static">从模板快速添加</label>
            <select class="form-control" id="template_selection" onchange="applyTemplate(this);">
                <option>选择模板</option>
            </select>
        </div>
        <div class="form-group">
            <label class="form-control-static">名称</label>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="form-control" type="text" name="name" id="name" placeholder="名称" value="${billingDTO.name}">
            </div>
        </div>
        <div class="form-group">
            <label class="form-control-static">类型</label>
            <select class="form-control" id="type" name="type" onchange="WF.billing.categories(this)" targetId="categoryGuid">
                <c:forEach items="${types}" var="type">
                    <option value="${type.value}" ${type==billingDTO.type?'selected':''}>${type.label}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label class="form-control-static">分类</label>
            <select class="form-control" id="categoryGuid" name="categoryGuid" onchange="WF.billing.subcategories(this)" default-value="${billingDTO.categoryGuid}" targetId="subcategoryGuid">
                <option value="">请选择</option>
            </select>
        </div>
        <div class="form-group">
            <label class="form-control-static">子分类</label>
            <select class="form-control" id="subcategoryGuid" name="subcategoryGuid" default-value="${billingDTO.subcategoryGuid}">
                <option value="">请选择</option>
            </select>
        </div>
        <div class="form-group">
            <div class="weui_cell_hd"><label class="form-control-static">日期</label></div>
            <input class="form-control" type="date" name="occurredTime" placeholder="日期" value="${billingDTO.occurredTime}"/>
        </div>
        <div class="form-group">
            <div class="weui_cell_hd"><label class="form-control-static">金额</label></div>
            <input class="form-control" type="number" name="amount" id="amount" placeholder="金额" value="${billingDTO.amount}"/>
        </div>
        <div class="form-group">
            <label class="form-control-static">描述</label>
            <textarea class="form-control" placeholder="描述" rows="3">${billingDTO.description}</textarea>
        </div>
        <div class="form-group">
            <label class="checkbox-inline"><input name="createTemplate" type="checkbox" value="true"/> 创建模板</label>
        </div>
        <div class="form-group">
            <a class="btn btn-primary" href="javascript:void(0)" onclick="WF.form.ajaxSubmit($('#data-form'))">保存</a>
            <a class="btn btn-default" href="javascript:void(0)" onclick="history.back();">返回</a>
        </div>
    </form>
</div>
</body>
</html>