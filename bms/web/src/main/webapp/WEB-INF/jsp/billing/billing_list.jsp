<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>账单列表</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/mobiscroll/mobiscroll.custom-3.0.0.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/mobiscroll/mobiscroll.custom-3.0.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/mobiscroll/mobiscroll.i18n.zh.js"></script>
    <script type="text/javascript">
        $(function(){
            WF.util.dateRangePicker($('#date-range-picker'), function(start, end){
                WF.paging.GO($('#search-form'), 1);
            });
            initCategorySelector();
            weui.searchBar('#searchBar');
        });
        function initCategorySelector() {
            var $el = $('#category-selector'), $label = $('#category-label');
            var $fieldType = $('#type'), $fieldCategory = $('#categoryGuid'), $fieldSubcategory = $('#subcategoryGuid');
            $el.mobiscroll().treelist({
                lang: 'zh',
                showInput: false,
                circular: false,
                buttons: ['set', 'clear', 'cancel'],
                clearText: '重置',
                onSet: function (event, inst) {
                    updateIndexValue(inst.getArrayVal());
                },
                onClear: function(event, inst) {
                    clearValue();
                }
            });
            initDefaultValue();
            $label.bind('click', function(){
                $el.mobiscroll('show');
            });

            function clearValue() {
                $fieldType.val('');
                $fieldCategory.val('');
                $fieldSubcategory.val('');
                WF.paging.GO($('#search-form'), 1);
            }

            function updateIndexValue(indexArray) {
                var typeIndex = indexArray[0], categoryIndex = typeIndex + '-' + indexArray[1], subcategoryIndex = categoryIndex + '-' + indexArray[2];
                var $type = $el.find('li[data-index="' + typeIndex + '"]');
                var $category = $el.find('li[data-index="' + categoryIndex + '"]');
                var $subcategory = $el.find('li[data-index="' + subcategoryIndex + '"]');
                updateValue($type, $category, $subcategory, true);
            }

            function initDefaultValue() {
                var $type = $el.find('li[data-value="' + $fieldType.val() + '"]');
                var $category = $el.find('li[data-value="' + $fieldCategory.val() + '"]');
                var $subcategory = $el.find('li[data-value="' + $fieldSubcategory.val() + '"]');
                updateValue($type, $category, $subcategory, false);
            }

            function updateValue($type, $category, $subcategory, search) {
                var type = $type.data();
                var category = $category.data();
                var subcategory = $subcategory.data();
                var label = '', enabled = false;
                if (type) {
                    $fieldType.val(type.value);
                    label += type.label;
                    if (category) {
                        $fieldCategory.val(category.value);
                        label += ' ' + category.label;
                        if (subcategory) {
                            $fieldSubcategory.val(subcategory.value);
                            label += ' ' + subcategory.label;
                        }
                    }
                    enabled = true;
                }
                if (enabled) {
                    $label.html(label);
                    if (search) {
                        WF.paging.GO($('#search-form'), 1);
                    }
                } else {
                    $label.html($label.data('default-label'));
                }
            }
        }
    </script>
    <style type="text/css">
        .weui-form-preview {
            margin-top: 15px;
        }
        .weui-search-bar__condition {
            position: relative;
            padding-right: 10px;
            cursor: pointer;
            color: #666;
        }
        .weui-search-bar__condition + .weui-search-bar__condition,
        .weui-search-bar__condition + .weui-search-bar__form:before {
            padding-left: 10px;
        }
        .weui-search-bar__condition + .weui-search-bar__condition:before {
            content: " ";
            position: absolute;
            left: 0;
            top: 0;
            right: 0;
            width: 1px;
            height: 200%;
            border-left: 1px solid #d9d9d9;
            color: #d9d9d9;
            -webkit-transform-origin: 0 0;
            transform-origin: 0 0;
            -webkit-transform: scaleY(.5);
            transform: scaleY(.5);
        }
        .caret {
            display: inline-block;
            width: 0;
            height: 0;
            vertical-align: middle;
            border-top: 4px dashed;
            border-top: 4px solid\9;
            border-right: 4px solid transparent;
            border-left: 4px solid transparent;
        }
    </style>
</head>
<body>
<div class="weui-search-bar" id="searchBar">
    <div class="weui-search-bar__condition">
        <span id="date-range-picker" data-empty-text="日期" data-start-input="#dateFrom" data-end-input="#dateTo">日期</span>
        <span class="caret"></span>
    </div>
    <div class="weui-search-bar__condition">
        <span id="category-label" data-default-label="分类">分类</span>
        <ul id="category-selector" style="display:none">
            <c:forEach items="${nodes}" var="type" varStatus="i">
                <li data-index="${i.index}" data-value="${type.value}" data-label="${type.label}"><span>${type.label}</span>
                    <c:if test="${not empty type.children}">
                    <ul>
                        <c:forEach items="${type.children}" var="category" varStatus="j">
                        <li data-index="${i.index}-${j.index}" data-value="${category.value}" data-label="${category.label}"><span>${category.label}</span>
                            <c:if test="${not empty category.children}">
                            <ul>
                                <c:forEach items="${category.children}" var="subcategory" varStatus="k">
                                    <li data-index="${i.index}-${j.index}-${k.index}" data-value="${subcategory.value}" data-label="${subcategory.label}"><span>${subcategory.label}</span></li>
                                </c:forEach>
                            </ul>
                            </c:if>
                        </li>
                        </c:forEach>
                    </ul>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
        <span class="caret"></span>
    </div>
    <form class="weui-search-bar__form" id="search-form" action="list" onsubmit="WF.paging.GO($('#search-form'), 1);return false;">
        <div class="weui-search-bar__box">
            <i class="weui-icon-search"></i>
            <input type="text" class="weui-search-bar__input" name="key" placeholder="搜索" value="${pagingDTO.key}">
            <a href="javascript:void(0)" class="weui-icon-clear"></a>
        </div>
        <label class="weui-search-bar__label">
            <i class="weui-icon-search"></i>
            <span>搜索</span>
        </label>
        <input type="hidden" id="dateFrom" name="dateFrom" value="${pagingDTO.dateFrom}">
        <input type="hidden" id="dateTo" name="dateTo" value="${pagingDTO.dateTo}">
        <input type="hidden" id="type" name="type" value="${pagingDTO.type}">
        <input type="hidden" id="categoryGuid" name="categoryGuid" value="${pagingDTO.categoryGuid}">
        <input type="hidden" id="subcategoryGuid" name="subcategoryGuid" value="${pagingDTO.subcategoryGuid}">
    </form>
    <a href="javascript:void(0)" class="weui-search-bar__cancel-btn">取消</a>
</div>
<div class="weui-cells">
<c:forEach items="${pagingDTO.list}" var="billing">
    <a class="weui-cell weui-cell_access" href="detail?guid=${billing.guid}" async-load="true">
        <div class="weui-cell__bd">
            <p>${billing.name}</p>
            <p class="text-small color-grey">${billing.occurredTimeFriendly} ${billing.subcategoryName} ${billing.type.label}</p>
        </div>
        <div class="weui-cell__ft"><span class="amount-sign amount-sign_${billing.type.value}">${billing.amount}</span></div>
    </a>
</c:forEach>
</div>
<tags:paging formName="search-form" paging="${pagingDTO}"/>
</body>
</html>