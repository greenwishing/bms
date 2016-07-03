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
                var $mask = $('#mask');
                var $list = $('#weui_actionsheet');
                var $menu = $list.find('.weui_actionsheet_menu');
                WF.ajax.req({
                    type: 'post',
                    url: 'templates',
                    data: {dataType: 'json'},
                    success: function(result) {
                        var templates = result.templates;
                        for (var i in templates) {
                            var template = templates[i];
                            var item = $('<div class="weui_actionsheet_cell"></div>').html(template.name + ' ' + template.amount).attr(template);
                            $menu.append(item);
                        }
                        $menu.find('.weui_actionsheet_cell').bind('click', function(){
                            applyTemplate(this);
                            hideActionSheet($list, $mask);
                        });
                    }
                });

                $('#add_from_template').bind('click', function () {
                    $list.addClass('weui_actionsheet_toggle');
                    $mask.show()
                            .focus()
                            .addClass('weui_fade_toggle').bind('click', function () {
                                hideActionSheet($list, $mask);
                            });
                    $('#actionsheet_cancel').bind('click', function () {
                        hideActionSheet($list, $mask);
                    });
                    $mask.unbind('transitionend').unbind('webkitTransitionEnd');
                });

                function hideActionSheet(weuiActionsheet, mask) {
                    weuiActionsheet.removeClass('weui_actionsheet_toggle');
                    mask.removeClass('weui_fade_toggle');
                    mask.on('transitionend', function () {
                        mask.hide();
                    }).on('webkitTransitionEnd', function () {
                        mask.hide();
                    })
                }

            })();
        });

        function applyTemplate(elem) {
            var template = $(elem);
            $('#categoryGuid').attr({'default-value': template.attr('categoryGuid')});
            $('#subcategoryGuid').attr({'default-value': template.attr('subcategoryGuid')});
            $('#name').val(template.attr('name'));
            $('#amount').val(template.attr('amountFloat'));
            var type = $('#type');
            type.val(template.attr('type'));
            WF.billing.categories(type);
        }
    </script>
</head>
<body>
<form id="data-form" action="add" method="post" onsubmit="return false;">
    <div class="weui_btn_area">
        <a class="weui_btn weui_btn_primary" href="javascript:void(0)" id="add_from_template">从模板添加</a>
    </div>
    <div id="actionSheet_wrap">
        <div class="weui_mask_transition" id="mask"></div>
        <div class="weui_actionsheet" id="weui_actionsheet">
            <div class="weui_actionsheet_menu"></div>
            <div class="weui_actionsheet_action">
                <div class="weui_actionsheet_cell" id="actionsheet_cancel">取消</div>
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
    <div class="weui_cells_title">创建模板</div>
    <div class="weui_cells weui_cells_radio">
        <label class="weui_cell weui_check_label" for="createTemplate_true">
            <div class="weui_cell_bd weui_cell_primary">
                <p>是</p>
            </div>
            <div class="weui_cell_ft">
                <input type="radio" name="createTemplate" class="weui_check" id="createTemplate_true" value="true" />
                <span class="weui_icon_checked"></span>
            </div>
        </label>
        <label class="weui_cell weui_check_label" for="createTemplate_false">
            <div class="weui_cell_bd weui_cell_primary">
                <p>否</p>
            </div>
            <div class="weui_cell_ft">
                <input type="radio" name="createTemplate" class="weui_check" id="createTemplate_false" value="false" checked="checked" />
                <span class="weui_icon_checked"></span>
            </div>
        </label>
    </div>
    <div class="weui_btn_area">
        <a class="weui_btn weui_btn_primary" href="javascript:void(0)" onclick="WF.form.ajaxSubmit($('#data-form'))">保存</a>
        <a class="weui_btn weui_btn_default" href="javascript:void(0)" onclick="WF.page.forward('list')">返回</a>
    </div>
</form>
</body>
</html>