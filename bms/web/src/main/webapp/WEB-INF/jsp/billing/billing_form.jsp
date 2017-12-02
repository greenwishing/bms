<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>记账</title>
    <script type="text/javascript">
        $(function(){
            applyTemplate($('#tpl-list'));
            WF.billing.categories('${param.type}');
            (function(){
                var $tplList = $('#tpl-list');
                WF.ajax.req({
                    type: 'post',
                    url: 'suggest_tpl',
                    data: {type: '${param.type}', size: 30},
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
                        $tplList.data({templates: templates});
                        $tplList.bind('click', function(){
                            weui.actionSheet(menus, [{label: '取消', onClick: function(){}}]);
                        })
                    }
                });
            })();
            mobiscroll.datetime('#occurredTime', {
                minDate: new Date(),
                format: 'yyyy-MM-dd HH:ii',
                lang: 'zh'
            });
        });

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
<form id="data-form" action="record?type=${param.type}" method="post" onsubmit="return false;">
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
    <div class="weui-cells__title">${billingDTO.type.label}</div>
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