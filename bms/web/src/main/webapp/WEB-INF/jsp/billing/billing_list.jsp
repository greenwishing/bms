<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>账单列表</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <script type="text/javascript">
        (function(){
            var $html = null;
            WF.billing.initSearch = function() {
                $html = $($('#tpl-search-dialog').html());
                $('body').append($html);
                $html.find('.weui_btn_dialog.default').bind('click', function(){
                    WF.billing.cancelSearch();
                });
                $html.find('.weui_btn_dialog.primary').bind('click', function(){
                    WF.paging.GO($('#search-form'), 1);
                });
                WF.billing.categories($('#type'));
            };
            WF.billing.cancelSearch = function() {
                $html.remove();
            };
        })();

    </script>
    <style type="text/css">
        .status.RECEIVABLE,
        .status.PAYABLE { color: red;}
        .status.RECEIVED,
        .status.PAYED { color: green;}

        .price.ACCOUNT_RECEIVABLE,
        .price.ACCOUNT_PAYABLE { color: grey;}
        .price.EXPEND { color: red;}
        .price.EXPEND:before { content: '-';}
        .price.INCOME:before { content: '+';}
        .settled { text-decoration: line-through;}
    </style>
</head>
<body>
<div class="weui_tab">
    <div class="weui_tab_bd">
        <div class="weui_panel weui_panel_access">
            <div class="weui_panel_hd">账单列表</div>
            <div class="weui_panel_bd">
                <c:forEach items="${pagingDTO.list}" var="billing">
                    <div class="weui_media_box weui_media_text" data-id="${billing.guid}" title="${billing.settleTime} ${billing.status.label}">
                        <h4 class="weui_media_title"><span class="price ${billing.type} ${'RECEIVED'==billing.status or 'PAYED'==billing.status ? 'settled':''}">${billing.amount}</span></h4>
                        <p class="weui_media_desc">${billing.name}</p>
                        <ul class="weui_media_info">
                            <li class="weui_media_info_meta">${billing.type.label}</li>
                            <li class="weui_media_info_meta">${billing.categoryName} ${billing.subcategoryName}</li>
                            <li class="weui_media_info_meta weui_media_info_meta_extra">${billing.occurredTime}</li>
                        </ul>
                        <c:choose>
                            <c:when test="${'ACCOUNT_RECEIVABLE'==billing.type and 'RECEIVED' != billing.status}">
                                <a href="javascript:void(0)" onclick="WF.billing.changeStatus('${billing.guid}','RECEIVED')">标记为已还</a>
                            </c:when>
                            <c:when test="${'ACCOUNT_PAYABLE'==billing.type and 'PAYED' != billing.status}">
                                <a href="javascript:void(0)" onclick="WF.billing.changeStatus('${billing.guid}','PAYED')">标记为已付</a>
                            </c:when>
                        </c:choose>
                    </div>
                </c:forEach>
            </div>
            <tags:paging formName="search-form" paging="${pagingDTO}"/>
        </div>
    </div>
    <div class="weui_tabbar">
        <a class="weui_tabbar_item" href="${pageContext.request.contextPath}/system/index">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_dialog.png" alt="">
            </div>
            <p class="weui_tabbar_label">首页</p>
        </a>
        <a class="weui_tabbar_item" href="categories">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_article.png" alt="">
            </div>
            <p class="weui_tabbar_label">分类</p>
        </a>
        <a class="weui_tabbar_item" href="templates">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_icons.png" alt="">
            </div>
            <p class="weui_tabbar_label">模板</p>
        </a>
        <a class="weui_tabbar_item" href="javascript:void(0)" onclick="WF.billing.initSearch()">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_panel.png" alt="">
            </div>
            <p class="weui_tabbar_label">查询</p>
        </a>
    </div>
</div>
<script type="text/template" id="tpl-search-dialog">
    <div class="weui_dialog_confirm">
        <div class="weui_mask"></div>
        <div class="weui_dialog weui_dialog_form">
            <div class="weui_dialog_hd"><strong class="weui_dialog_title">查询账单</strong></div>
            <div class="weui_dialog_bd">
                <form id="search-form" action="list" onsubmit="return false;">
                    <div class="weui_cells weui_cells_form">
                        <div class="weui_cell">
                            <div class="weui_cell_hd"><label class="weui_label">关键字</label></div>
                            <div class="weui_cell_bd weui_cell_primary">
                                <input class="weui_input" type="text" name="key" value="${pagingDTO.key}" placeholder="请输入关键字">
                            </div>
                        </div>
                        <div class="weui_cell">
                            <div class="weui_cell_hd"><label class="weui_label">开始日期</label></div>
                            <div class="weui_cell_bd weui_cell_primary">
                                <input class="weui_input" type="date" name="dateFrom" value="${pagingDTO.dateFrom}" placeholder="请选择开始日期">
                            </div>
                        </div>
                        <div class="weui_cell">
                            <div class="weui_cell_hd"><label class="weui_label">结束日期</label></div>
                            <div class="weui_cell_bd weui_cell_primary">
                                <input class="weui_input" type="date" name="dateTo" value="${pagingDTO.dateTo}" placeholder="请选择结束日期">
                            </div>
                        </div>
                        <div class="weui_cell weui_cell_select weui_select_after">
                            <div class="weui_cell_hd">
                                <label class="weui_label">类型</label>
                            </div>
                            <div class="weui_cell_bd weui_cell_primary">
                                <select class="weui_select" name="type" id="type" onchange="WF.billing.categories(this)" targetId="categoryGuid" default-value="${param.type}">
                                    <option value="">请选择</option>
                                    <c:forEach items="${types}" var="type">
                                        <option value="${type.value}" ${type.value eq param.type ? 'selected':''}>${type.label}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="weui_cell weui_cell_select weui_select_after">
                            <div class="weui_cell_hd">
                                <label class="weui_label">分类</label>
                            </div>
                            <div class="weui_cell_bd weui_cell_primary">
                                <select id="categoryGuid" class="weui_select" name="categoryGuid" onchange="WF.billing.subcategories(this)" targetId="subcategoryGuid" default-value="${param.categoryGuid}">
                                    <option value="">请选择</option>
                                </select>
                            </div>
                        </div>
                        <div class="weui_cell weui_cell_select weui_select_after">
                            <div class="weui_cell_hd">
                                <label class="weui_label">子分类</label>
                            </div>
                            <div class="weui_cell_bd weui_cell_primary">
                                <select id="subcategoryGuid" class="weui_select" name="subcategoryGuid" default-value="${param.subcategoryGuid}">
                                    <option value="">请选择</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="weui_dialog_ft">
                <a href="javascript:void(0);" class="weui_btn_dialog primary">确定</a>
                <a href="javascript:void(0);" class="weui_btn_dialog default">取消</a>
            </div>
        </div>
    </div>
</script>
</body>
</html>