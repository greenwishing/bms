<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>记账</title>
    <script type="text/javascript">
        $(function(){
            WF.billing.categories('${billingDTO.type.value}');
            (function(){
                var $tplList = $('#tpl-list');
                WF.ajax.req({
                    type: 'post',
                    url: 'suggest_tpl',
                    data: {type: '${billingDTO.type.value}', size: 30},
                    success: function(result) {
                        var tpl = result.tplList;
                        var menus = [], templates = [];
                        for (var i in tpl) {
                            var template = tpl[i];
                            templates.push(template);
                            menus.push({
                                label: template.name + ' ' + template.amount,
                                onClick: function(){
                                    var index = $(this).index();
                                    var templates = $tplList.data('templates');
                                    applyTemplate(templates[index]);
                                }
                            });
                        }
                        $tplList.data({menus: menus, templates: templates});
                    }
                });
            })();
            mobiscroll.datetime('#occurredTime', {
                minDate: new Date(),
                format: 'yyyy-MM-dd HH:ii',
                lang: 'zh'
            });
        });

        function showSuggestTemplate(el) {
            var menus = $(el).data('menus');
            weui.actionSheet(menus, [{label: '取消', onClick: function(){}}]);
        }

        function applyTemplate(template) {
            if (!template) return;
            var type = template.type;
            $('#categoryGuid').attr({'default-value': template.categoryId});
            $('#subcategoryGuid').attr({'default-value': template.subcategoryId});
            WF.billing.categories(type);
            WF.billing.defaultValue($(':input[name=srcAccountGuid]'), template.srcAccountId);
            WF.billing.defaultValue($(':input[name=targetAccountGuid]'), template.targetAccountId);
            $('#name').val(template.name);
            $('#amount').val(template.amount);
        }
    </script>
</head>
<body>
<form id="data-form" action="record?type=${billingDTO.type.value}" method="post" onsubmit="return false;">
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell weui-cell_select weui-cell_select-after">
            <div class="weui-cell__hd">
                <label class="weui-label">分类</label>
            </div>
            <div class="weui-cell__bd">
                <select class="weui-select" id="categoryGuid" name="categoryGuid" onchange="WF.billing.subcategories(this)" targetId="subcategoryGuid">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="weui-cell weui-cell_select weui-cell_select-after">
            <div class="weui-cell__hd">
                <label class="weui-label">子分类</label>
            </div>
            <div class="weui-cell__bd">
                <select class="weui-select" id="subcategoryGuid" name="subcategoryGuid">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
    </div>
    <c:set var="type" value="${billingDTO.type}"/>
    <div class="weui-cells__title">${type.label}</div>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label class="weui-label">名称</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="text" name="name" id="name" placeholder="名称" value="${billingDTO.name}">
            </div>
        </div>
        <c:if test="${type.src.enabled}">
            <div class="weui-cell weui-cell_select weui-cell_select-after">
                <div class="weui-cell__hd">
                    <label class="weui-label">${type.src.name}</label>
                </div>
                <div class="weui-cell__bd">
                    <select class="weui-select" name="srcAccountGuid">
                        <c:forEach items="${type.src.loan ? loanAccountMap : accountMap}" var="group">
                            <optgroup label="${group.key.label}">
                                <c:forEach items="${group.value}" var="account">
                                    <option value="${account.guid}" data-id="${account.id}">${account.name}</option>
                                </c:forEach>
                            </optgroup>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </c:if>
        <c:if test="${type.target.enabled}">
            <div class="weui-cell weui-cell_select weui-cell_select-after">
                <div class="weui-cell__hd">
                    <label class="weui-label">${type.target.name}</label>
                </div>
                <div class="weui-cell__bd">
                    <select class="weui-select" name="targetAccountGuid">
                        <c:forEach items="${type.target.loan ? loanAccountMap : accountMap}" var="group">
                            <optgroup label="${group.key.label}">
                                <c:forEach items="${group.value}" var="account">
                                    <option value="${account.guid}" data-id="${account.id}">${account.name}</option>
                                </c:forEach>
                            </optgroup>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </c:if>
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label class="weui-label">时间</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" id="occurredTime" name="occurredTime" placeholder="时间" value="${billingDTO.occurredTime}" readonly/>
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label class="weui-label">金额</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="number" id="amount" name="amount" placeholder="金额" value="${billingDTO.amount}"/>
            </div>
        </div>
    </div>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <textarea class="weui-textarea" name="description" placeholder="描述" rows="3"></textarea>
            </div>
        </div>
    </div>
</form>
</body>
</html>