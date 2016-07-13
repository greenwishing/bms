<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>添加/编辑用户</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<div class="weui_tab">
    <div class="weui_tab_bd">
        <spring-form:form id="data-form" commandName="userDTO" method="post" onsubmit="return false;">
            <div class="weui_cells_title">用户信息</div>
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell">
                    <div class="weui_cell_hd"><label class="weui_label">姓名</label></div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <input class="weui_input" type="text" name="username" id="username" placeholder="姓名" value="${userDTO.username}">
                    </div>
                </div>
                <c:if test="${userDTO.guid == null}">
                    <div class="weui_cell">
                        <div class="weui_cell_hd"><label class="weui_label">帐号</label></div>
                        <div class="weui_cell_bd weui_cell_primary">
                            <input class="weui_input" type="text" name="account" id="account" placeholder="帐号" value="${userDTO.account}">
                        </div>
                    </div>
                    <div class="weui_cell">
                        <div class="weui_cell_hd"><label class="weui_label">设置密码</label></div>
                        <div class="weui_cell_bd weui_cell_primary">
                            <input class="weui_input" type="password" name="password" id="password" placeholder="设置密码">
                        </div>
                    </div>
                    <div class="weui_cell">
                        <div class="weui_cell_hd"><label class="weui_label">确认密码</label></div>
                        <div class="weui_cell_bd weui_cell_primary">
                            <input class="weui_input" type="password" name="retypePassword" id="retypePassword" placeholder="确认密码">
                        </div>
                    </div>
                </c:if>
                <c:if test="${userDTO.guid != null}">
                    <div class="weui_cell">
                        <div class="weui_cell_hd"><label class="weui_label">帐号</label></div>
                        <div class="weui_cell_bd weui_cell_primary">${userDTO.account}</div>
                    </div>
                </c:if>
                <c:if test="${userDTO.admin}">
                    <div class="weui_cell">
                        <div class="weui_cell_hd"><label class="weui_label">状态</label></div>
                        <div class="weui_cell_bd weui_cell_primary">${userDTO.status.label}</div>
                    </div>
                </c:if>
            </div>
            <c:if test="${!userDTO.admin}">
                <div class="weui_cells_title">状态</div>
                <div class="weui_cells weui_cells_radio">
                    <c:forEach items="${statusList}" var="status">
                        <label class="weui_cell weui_check_label">
                            <div class="weui_cell_bd weui_cell_primary">
                                <p>${status.label}</p>
                            </div>
                            <div class="weui_cell_ft">
                                <input type="radio" class="weui_check" name="status" value="${status.value}" ${status==userDTO.status?'checked':''}/>
                                <span class="weui_icon_checked"></span>
                            </div>
                        </label>
                    </c:forEach>
                </div>
            </c:if>
        </spring-form:form>
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