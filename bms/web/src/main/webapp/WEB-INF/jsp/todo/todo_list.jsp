<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>待办事项</title>
    <script type="text/javascript">
        function removeDone() {
            var data = [];
            $(':checkbox[name="done"]:checked').each(function(){
                var guid = $(this).data('guid');
                data.push({name: 'guids', value: guid});
            });
            if (!data.length) return;
            WF.ajax.doAjax({
                url: 'remove_done',
                type: 'post',
                data: data,
                success: function() {
                    location.reload();
                }
            })
        }
        function toggleDone(el) {
            var guid = $(el).data('guid');
            WF.ajax.doAjax({
                url: 'done?guid=' + guid,
                success: function() {
                    location.reload();
                }
            })
        }
        function operation() {
            var as = weui.actionSheet([{label: '删除已完成事项', onClick: function(){
                removeDone();
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
            <h1>待办事项</h1>
        </div>
        <div class="weui-cells">
            <c:forEach items="${todos}" var="todo">
                <div class="weui-cell weui-cell_switch">
                    <div class="weui-cell__bd">
                        <p>${todo.content}</p>
                        <c:if test="${todo.done}">
                            <p class="color-grey text-small">于 ${todo.doneTime} 完成</p>
                        </c:if>
                    </div>
                    <div class="weui-cell__ft">
                        <input class="weui-switch" name="done" onclick="toggleDone(this)" data-guid="${todo.guid}" type="checkbox" ${todo.done ? 'checked': ''}>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="weui-tabbar">
        <a href="add" class="weui-tabbar__item">
            <img src="${pageContext.request.contextPath}/images/icons/icon_add.png" class="weui-tabbar__icon">
            <p class="weui-tabbar__label">添加</p>
        </a>
        <a href="javascript:void(0)" class="weui-tabbar__item" onclick="operation()">
            <img src="/images/icons/icon_more.png" class="weui-tabbar__icon">
            <p class="weui-tabbar__label">操作</p>
        </a>
    </div>
</div>
</body>
</html>