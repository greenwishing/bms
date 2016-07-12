<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>文章分类</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<div class="weui_tab">
    <div class="weui_tab_bd">
        <div class="weui_panel weui_panel_access">
            <div class="weui_panel_hd">文章分类</div>
            <div class="weui_panel_bd">
                <c:forEach items="${categoryDTOs}" var="category">
                    <a class="weui_media_box weui_media_appmsg" href="edit_category?guid=${category.guid}">
                        <div class="weui_media_bd">
                            <h4 class="weui_media_title">${category.name}</h4>
                        </div>
                    </a>
                </c:forEach>
            </div>
            <tags:paging formName="search-form" paging="${pagingDTO}"/>
        </div>
    </div>
    <div class="weui_tabbar">
        <a class="weui_tabbar_item" href="add_category">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_icons.png" alt="">
            </div>
            <p class="weui_tabbar_label">添加</p>
        </a>
        <a class="weui_tabbar_item" href="${pageContext.request.contextPath}/system/article/list">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/css/weui/images/icon_nav_dialog.png" alt="">
            </div>
            <p class="weui_tabbar_label">返回</p>
        </a>
    </div>
</div>
</body>
</html>