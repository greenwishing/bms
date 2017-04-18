<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>记账</title>
    <script type="text/javascript">
        $(function(){
            var $tpl = $('#tpl-list');
            $tpl.bind('change', function($el){
                var $option = $tpl.find('option:selected');
                applyTemplate($option)
            });
            WF.billing.categories('${param.type}');
            (function(){
                var $tplList = $('#tpl-list');
                WF.ajax.req({
                    type: 'post',
                    url: 'suggest_tpl',
                    data: {type: '${param.type}', size: 30},
                    success: function(result) {
                        var tpl = result.tplList;
                        $tplList.empty();
                        for (var i in tpl) {
                            var template = tpl[i];
                            var $menu = $('<option></option>').attr({
                                'data-name': template.name,
                                'data-type': template.type,
                                'data-amount': template.amount,
                                'data-categoryGuid': template.categoryGuid,
                                'data-subcategoryGuid': template.subcategoryGuid,
                                'data-srcAccountGuid': template.srcAccountGuid,
                                'data-targetAccountGuid': template.targetAccountGuid
                            }).html(template.name + ' ' + template.amount);
                            $tplList.append($menu);
                        }
                    }
                });
            })();
        });

        function applyTemplate($option) {
            var type = $option.attr('data-type');
            $('#categoryGuid').attr({'default-value': $option.attr('data-categoryGuid')});
            $('#subcategoryGuid').attr({'default-value': $option.attr('data-subcategoryGuid')});
            WF.billing.categories(type);
            WF.billing.defaultValue($(':input[name=srcAccountGuid]'), $option.attr('data-srcAccountGuid'));
            WF.billing.defaultValue($(':input[name=targetAccountGuid]'), $option.attr('data-targetAccountGuid'));
            $('#name').val($option.attr('data-name'));
            $('#amount').val($option.attr('data-amount'));
        }
    </script>
</head>
<body>
<form id="data-form" action="record?type=${param.type}" method="post" onsubmit="return false;">
    <div class="weui-cells__title">从模板添加</div>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell weui-cell_select">
            <div class="weui-cell__bd">
                <select class="weui-select" id="tpl-list"></select>
            </div>
        </div>
    </div>
    <div class="weui-cells__title">分类</div>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell weui-cell_select">
            <div class="weui-cell__bd">
                <select class="weui-select" id="categoryGuid" name="categoryGuid" onchange="WF.billing.subcategories(this)" targetId="subcategoryGuid">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
    </div>
    <div class="weui-cells__title">子分类</div>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell weui-cell_select">
            <div class="weui-cell__bd">
                <select class="weui-select" id="subcategoryGuid" name="subcategoryGuid">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
    </div>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label class="weui-label">名称</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="text" name="name" id="name" placeholder="名称" value="${billingDTO.name}">
            </div>
        </div>
        <c:choose>
            <c:when test="${'EXPEND' eq param.type}">
                <div class="weui-cell weui-cell_select weui-cell_select-after">
                    <div class="weui-cell__hd">
                        <label class="weui-label">支出账户</label>
                    </div>
                    <div class="weui-cell__bd">
                        <select class="weui-select" name="srcAccountGuid">
                            <c:forEach items="${accountMap}" var="group">
                                <optgroup label="${group.key.label}">
                                    <c:forEach items="${group.value}" var="account">
                                        <option value="${account.guid}" data-id="${account.id}">${account.name}</option>
                                    </c:forEach>
                                </optgroup>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </c:when>
            <c:when test="${'INCOME' eq param.type}">
                <div class="weui-cell weui-cell_select weui-cell_select-after">
                    <div class="weui-cell__hd">
                        <label class="weui-label">收入账户</label>
                    </div>
                    <div class="weui-cell__bd">
                        <select class="weui-select" name="srcAccountGuid">
                            <c:forEach items="${accountMap}" var="group">
                                <optgroup label="${group.key.label}">
                                    <c:forEach items="${group.value}" var="account">
                                        <option value="${account.guid}" data-id="${account.id}">${account.name}</option>
                                    </c:forEach>
                                </optgroup>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </c:when>
            <c:when test="${'TRANSFER' eq param.type}">
                <div class="weui-cell weui-cell_select weui-cell_select-after">
                    <div class="weui-cell__hd">
                        <label class="weui-label">转出账户</label>
                    </div>
                    <div class="weui-cell__bd">
                        <select class="weui-select" name="srcAccountGuid">
                            <c:forEach items="${accountMap}" var="group">
                                <optgroup label="${group.key.label}">
                                    <c:forEach items="${group.value}" var="account">
                                        <option value="${account.guid}" data-id="${account.id}">${account.name}</option>
                                    </c:forEach>
                                </optgroup>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="weui-cell weui-cell_select weui-cell_select-after">
                    <div class="weui-cell__hd">
                        <label class="weui-label">转入账户</label>
                    </div>
                    <div class="weui-cell__bd">
                        <select class="weui-select" name="targetAccountGuid">
                            <c:forEach items="${accountMap}" var="group">
                                <optgroup label="${group.key.label}">
                                    <c:forEach items="${group.value}" var="account">
                                        <option value="${account.guid}" data-id="${account.id}">${account.name}</option>
                                    </c:forEach>
                                </optgroup>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </c:when>
            <c:when test="${'BORROW' eq param.type}">
                <div class="weui-cell weui-cell_select weui-cell_select-after">
                    <div class="weui-cell__hd">
                        <label class="weui-label">借入账户</label>
                    </div>
                    <div class="weui-cell__bd">
                        <select class="weui-select" name="srcAccountGuid">
                            <c:forEach items="${accountMap}" var="group">
                                <optgroup label="${group.key.label}">
                                    <c:forEach items="${group.value}" var="account">
                                        <option value="${account.guid}" data-id="${account.id}">${account.name}</option>
                                    </c:forEach>
                                </optgroup>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="weui-cell weui-cell_select weui-cell_select-after">
                    <div class="weui-cell__hd">
                        <label class="weui-label">债权人</label>
                    </div>
                    <div class="weui-cell__bd">
                        <select class="weui-select" name="targetAccountGuid">
                            <c:forEach items="${loanAccountMap}" var="group">
                                <optgroup label="${group.key.label}">
                                    <c:forEach items="${group.value}" var="account">
                                        <option value="${account.guid}" data-id="${account.id}">${account.name}</option>
                                    </c:forEach>
                                </optgroup>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </c:when>
            <c:when test="${'LOAN' eq param.type}">
                <div class="weui-cell weui-cell_select weui-cell_select-after">
                    <div class="weui-cell__hd">
                        <label class="weui-label">借出账户</label>
                    </div>
                    <div class="weui-cell__bd">
                        <select class="weui-select" name="srcAccountGuid">
                            <c:forEach items="${accountMap}" var="group">
                                <optgroup label="${group.key.label}">
                                    <c:forEach items="${group.value}" var="account">
                                        <option value="${account.guid}" data-id="${account.id}">${account.name}</option>
                                    </c:forEach>
                                </optgroup>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="weui-cell weui-cell_select weui-cell_select-after">
                    <div class="weui-cell__hd">
                        <label class="weui-label">债务人</label>
                    </div>
                    <div class="weui-cell__bd">
                        <select class="weui-select" name="targetAccountGuid">
                            <c:forEach items="${loanAccountMap}" var="group">
                                <optgroup label="${group.key.label}">
                                    <c:forEach items="${group.value}" var="account">
                                        <option value="${account.guid}" data-id="${account.id}">${account.name}</option>
                                    </c:forEach>
                                </optgroup>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </c:when>
            <c:when test="${'RECEIVE' eq param.type}">
                <div class="weui-cell weui-cell_select weui-cell_select-after">
                    <div class="weui-cell__hd">
                        <label class="weui-label">收款账户</label>
                    </div>
                    <div class="weui-cell__bd">
                        <select class="weui-select" name="srcAccountGuid">
                            <c:forEach items="${accountMap}" var="group">
                                <optgroup label="${group.key.label}">
                                    <c:forEach items="${group.value}" var="account">
                                        <option value="${account.guid}" data-id="${account.id}">${account.name}</option>
                                    </c:forEach>
                                </optgroup>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="weui-cell weui-cell_select weui-cell_select-after">
                    <div class="weui-cell__hd">
                        <label class="weui-label">债务人</label>
                    </div>
                    <div class="weui-cell__bd">
                        <select class="weui-select" name="targetAccountGuid">
                            <c:forEach items="${loanAccountMap}" var="group">
                                <optgroup label="${group.key.label}">
                                    <c:forEach items="${group.value}" var="account">
                                        <option value="${account.guid}" data-id="${account.id}">${account.name}</option>
                                    </c:forEach>
                                </optgroup>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </c:when>
            <c:when test="${'PAYBACK' eq param.type}">
                <div class="weui-cell weui-cell_select weui-cell_select-after">
                    <div class="weui-cell__hd">
                        <label class="weui-label">还款账户</label>
                    </div>
                    <div class="weui-cell__bd">
                        <select class="weui-select" name="srcAccountGuid">
                            <c:forEach items="${accountMap}" var="group">
                                <optgroup label="${group.key.label}">
                                    <c:forEach items="${group.value}" var="account">
                                        <option value="${account.guid}" data-id="${account.id}">${account.name}</option>
                                    </c:forEach>
                                </optgroup>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="weui-cell weui-cell_select weui-cell_select-after">
                    <div class="weui-cell__hd">
                        <label class="weui-label">债权人</label>
                    </div>
                    <div class="weui-cell__bd">
                        <select class="weui-select" name="targetAccountGuid">
                            <c:forEach items="${loanAccountMap}" var="group">
                                <optgroup label="${group.key.label}">
                                    <c:forEach items="${group.value}" var="account">
                                        <option value="${account.guid}" data-id="${account.id}">${account.name}</option>
                                    </c:forEach>
                                </optgroup>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </c:when>
        </c:choose>
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label class="weui-label">日期</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="date" name="occurredTime" placeholder="日期" value="${billingDTO.occurredTime}"/>
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label class="weui-label">金额</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="number" name="amount" placeholder="金额" value="${billingDTO.amount}"/>
            </div>
        </div>
    </div>
    <div class="weui-cells__title">描述</div>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <textarea class="weui-textarea" name="description" placeholder="描述" rows="3"></textarea>
            </div>
        </div>
    </div>
    <%--<label for="createTemplate" class="weui-agree">
        <input id="createTemplate" type="checkbox" class="weui-agree__checkbox" value="true">
        <span class="weui-agree__text">创建模板</span>
    </label>--%>
    <div class="weui-btn-area">
        <a class="weui-btn weui-btn_primary" href="javascript:void(0)" onclick="saveContinue()">保存</a>
    </div>
</form>
</body>
</html>