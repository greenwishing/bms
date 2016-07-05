<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>账单分类</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<div class="weui_tab">
    <div class="weui_tab_bd">
        <spring-form:form commandName="billingCategoryDTO" method="post" id="data-form" onsubmit="return false;">
        <div class="weui_cells weui_cells_form">
            <div class="weui_cell weui_cell_select weui_select_after">
                <div class="weui_cell_hd"><label class="weui_label">类型</label></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <spring-form:select id="type" cssClass="weui_select" path="type" items="${types}" itemValue="value" itemLabel="label"/>
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_hd"><label class="weui_label">名称</label></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <spring-form:input cssClass="weui_input" path="name" id="name" placeholder="名称"/>
                </div>
            </div>
        </div>
        </spring-form:form>
    </div>
    <div class="weui_tabbar">
        <a class="weui_tabbar_item" href="javascript:void(0)" onclick="WF.form.submit($('#data-form'))">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_icons.png" alt="">
            </div>
            <p class="weui_tabbar_label">保存</p>
        </a>
        <c:if test="${param.guid!=null}">
        <a class="weui_tabbar_item" href="subcategories?categoryGuid=${param.guid}">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_icons.png" alt="">
            </div>
            <p class="weui_tabbar_label">子分类</p>
        </a>
        </c:if>
        <a class="weui_tabbar_item" href="javascript:void(0)" onclick="WF.page.forward('categories')">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_dialog.png" alt="">
            </div>
            <p class="weui_tabbar_label">返回</p>
        </a>
    </div>
</div>
</body>
</html>