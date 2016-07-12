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
<div class="weui_tab">
    <div class="weui_tab_bd">
        <form id="data-form" action="add" method="post" onsubmit="return false;">
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell weui_cell_select">
                    <div class="weui_cell_bd weui_cell_primary">
                        <select id="template_selection" class="weui_select" onchange="applyTemplate(this);">
                            <option>从模板快速添加</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="weui_cells_title">基本信息</div>
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell">
                    <div class="weui_cell_hd"><label class="weui_label">名称</label></div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <input class="weui_input" type="text" name="name" id="name" placeholder="名称" value="${billingDTO.name}">
                    </div>
                </div>
                <div class="weui_cell weui_cell_select weui_select_after">
                    <div class="weui_cell_hd">
                        <label class="weui_label">类型</label>
                    </div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <select id="type" class="weui_select" name="type" onchange="WF.billing.categories(this)" targetId="categoryGuid">
                            <c:forEach items="${types}" var="type">
                                <option value="${type.value}" ${type==billingDTO.type?'selected':''}>${type.label}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="weui_cell weui_cell_select weui_select_after">
                    <div class="weui_cell_hd">
                        <label class="weui_label">分类</label>
                    </div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <select id="categoryGuid" class="weui_select" name="categoryGuid" onchange="WF.billing.subcategories(this)" default-value="${billingDTO.categoryGuid}" targetId="subcategoryGuid">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
                <div class="weui_cell weui_cell_select weui_select_after">
                    <div class="weui_cell_hd">
                        <label class="weui_label">子分类</label>
                    </div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <select id="subcategoryGuid" class="weui_select" name="subcategoryGuid" default-value="${billingDTO.subcategoryGuid}">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
                <div class="weui_cell">
                    <div class="weui_cell_hd"><label class="weui_label">日期</label></div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <input type="date" class="weui_input" name="dateFrom" placeholder="日期" value="${billingDTO.occurredTime}"/>
                    </div>
                </div>
                <div class="weui_cell">
                    <div class="weui_cell_hd"><label class="weui_label">金额</label></div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <input type="number" class="weui_input" name="amount" id="amount" placeholder="金额" value="${billingDTO.amount}"/>
                    </div>
                </div>
            </div>
            <div class="weui_cells_title">描述</div>
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell">
                    <div class="weui_cell_bd weui_cell_primary">
                        <textarea class="weui_textarea" placeholder="描述" rows="3">${billingDTO.description}</textarea>
                    </div>
                </div>
            </div>
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell weui_cell_switch">
                    <div class="weui_cell_hd weui_cell_primary">创建模板</div>
                    <div class="weui_cell_ft">
                        <input class="weui_switch" name="createTemplate" type="checkbox" value="true"/>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="weui_tabbar">
        <a class="weui_tabbar_item" href="javascript:void(0)" onclick="WF.form.ajaxSubmit($('#data-form'))">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_icons.png" alt="">
            </div>
            <p class="weui_tabbar_label">保存</p>
        </a>
        <a class="weui_tabbar_item" href="javascript:void(0)" onclick="history.back();">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_dialog.png" alt="">
            </div>
            <p class="weui_tabbar_label">返回</p>
        </a>
    </div>
</div>
</body>
</html>