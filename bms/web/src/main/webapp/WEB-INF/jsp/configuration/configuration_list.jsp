<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>系统配置</title>
    <script type="text/javascript">
        function refreshConfigurationCache() {
            WF.ajax.doAjax({
                url: 'refresh_cache',
                type: 'post',
                success: function() {
                    location.reload();
                }
            })
        }
        function operation() {
            var as = weui.actionSheet([{label: '刷新缓存', onClick: function(){
                refreshConfigurationCache();
            }},{label: '发送邮件', onClick: function(){
                location.href = 'send_mail'
            }}],[{label: '取消', onClick: function(){
                as.hide();
            }}])
        }
    </script>
</head>
<body>
<div class="weui-tab">
    <div class="weui-tab__panel">
        <div class="weui-article">
            <h1>系统配置</h1>
        </div>
        <c:forEach items="${configurations}" var="configuration">
            <div class="weui-cells__title">${configuration.key}</div>
            <div class="weui-cells">
                <a class="weui-cell weui-cell_access" href="edit?guid=${configuration.guid}">
                    <div class="weui-cell__bd">
                        <p>${configuration.value}</p>
                    </div>
                    <div class="weui-cell__ft"></div>
                </a>
            </div>
            <c:if test="${configuration.description != null && configuration.description != ''}">
                <div class="weui-cells__tips">${configuration.description}</div>
            </c:if>
        </c:forEach>
    </div>
    <div class="weui-tabbar">
        <a href="add" class="weui-tabbar__item">
            <img src="${pageContext.request.contextPath}/images/icons/icon_add.png" class="weui-tabbar__icon">
            <p class="weui-tabbar__label">添加</p>
        </a>
        <a href="javascript:void(0)" class="weui-tabbar__item" onclick="operation()">
            <img src="${pageContext.request.contextPath}/images/icons/icon_more.png" class="weui-tabbar__icon">
            <p class="weui-tabbar__label">操作</p>
        </a>
    </div>
</div>
</body>
</html>