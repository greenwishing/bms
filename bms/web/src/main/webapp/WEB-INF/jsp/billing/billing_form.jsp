<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>记账</title>
    <script type="text/javascript">
        $(function(){
            <c:if test="${'EXPEND' eq param.type or 'INCOME' eq param.type}">
            WF.billing.categories('${param.type}');
            // WF.util.datePicker('occurredTime');
            </c:if>
            (function(){
                var $selection = $('#template_selection');
                WF.ajax.req({
                    type: 'post',
                    url: 'suggest_tpl',
                    data: {type: '${param.type}', size: 10},
                    success: function(result) {
                        var templates = result.tplList;
                        for (var i in templates) {
                            var template = templates[i];
                            var $option = $('<option></option>')
                                    .html(template.name + ' ' + template.amount)
                                    .attr({
                                        'data-name': template.name,
                                        'data-type': template.type,
                                        'data-amount': template.amount,
                                        'data-categoryGuid': template.categoryGuid,
                                        'data-subcategoryGuid': template.subcategoryGuid,
                                        'data-srcAccountGuid': template.srcAccountGuid,
                                        'data-targetAccountGuid': template.targetAccountGuid
                                    });
                            $selection.append($option);
                        }
                    }
                });
            })();
        });

        function applyTemplate(elem) {
            var $option = $(elem).find('option:selected');
            $('#categoryGuid').attr({'default-value': $option.attr('data-categoryGuid')});
            $('#subcategoryGuid').attr({'default-value': $option.attr('data-subcategoryGuid')});
            WF.billing.defaultValue($(':input[name=srcAccountGuid]'), $option.attr('data-srcAccountGuid'));
            WF.billing.defaultValue($(':input[name=targetAccountGuid]'), $option.attr('data-targetAccountGuid'));
            $('#name').val($option.attr('data-name'));
            $('#amount').val($option.attr('data-amount'));

            var type = $option.attr('data-type');
            if ('INCOME' == type || 'EXPEND' == type) {
                WF.billing.categories(type);
            }
        }

        function buildName() {
            var category = $('#categoryGuid').find('option:selected').text();
            var subcategory = $('#subcategoryGuid').find('option:selected').text();
            $('#name').val(category + ' ' + subcategory);
        }
    </script>
</head>
<body>
    <form id="data-form" action="add?type=${param.type}" method="post" onsubmit="return false;">
        <div class="form-group">
            <label class="form-control-static">从模板快速添加</label>
            <select class="form-control" id="template_selection" onchange="applyTemplate(this);">
                <option>选择模板</option>
            </select>
        </div>
        <c:if test="${'EXPEND' eq param.type or 'INCOME' eq param.type}">
        <div class="form-group">
            <label class="form-control-static">分类</label>
            <div class="input-group">
                <select class="form-control" id="categoryGuid" name="categoryGuid" onchange="WF.billing.subcategories(this)" targetId="subcategoryGuid">
                    <option value="">请选择</option>
                </select>
                <select class="form-control" id="subcategoryGuid" name="subcategoryGuid" onchange="buildName()">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        </c:if>
        <div class="form-group">
            <label class="form-control-static">名称</label>
            <div class="weui_cell_bd weui_cell_primary">
                <input class="form-control" type="text" name="name" id="name" placeholder="名称" value="${billingDTO.name}">
            </div>
        </div>
        <c:choose>
            <c:when test="${'EXPEND' eq param.type}">
                <div class="form-group">
                    <label class="form-control-static">支出账户</label>
                    <div class="input-group">
                        <select class="form-control" name="srcAccountGuid">
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
                <div class="form-group">
                    <label class="form-control-static">收入账户</label>
                    <div class="input-group">
                        <select class="form-control" name="srcAccountGuid">
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
                <div class="form-group">
                    <label class="form-control-static">转出账户</label>
                    <div class="input-group">
                        <select class="form-control" name="srcAccountGuid">
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
                <div class="form-group">
                    <label class="form-control-static">转入账户</label>
                    <div class="input-group">
                        <select class="form-control" name="targetAccountGuid">
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
                <div class="form-group">
                    <label class="form-control-static">借入账户</label>
                    <div class="input-group">
                        <select class="form-control" name="srcAccountGuid">
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
                <div class="form-group">
                    <label class="form-control-static">债权人</label>
                    <div class="input-group">
                        <select class="form-control" name="targetAccountGuid">
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
                <div class="form-group">
                    <label class="form-control-static">借出账户</label>
                    <div class="input-group">
                        <select class="form-control" name="srcAccountGuid">
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
                <div class="form-group">
                    <label class="form-control-static">债务人</label>
                    <div class="input-group">
                        <select class="form-control" name="targetAccountGuid">
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
                <div class="form-group">
                    <label class="form-control-static">收款账户</label>
                    <div class="input-group">
                        <select class="form-control" name="srcAccountGuid">
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
                <div class="form-group">
                    <label class="form-control-static">债务人</label>
                    <div class="input-group">
                        <select class="form-control" name="targetAccountGuid">
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
                <div class="form-group">
                    <label class="form-control-static">还款账户</label>
                    <div class="input-group">
                        <select class="form-control" name="srcAccountGuid">
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
                <div class="form-group">
                    <label class="form-control-static">债权人</label>
                    <div class="input-group">
                        <select class="form-control" name="targetAccountGuid">
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