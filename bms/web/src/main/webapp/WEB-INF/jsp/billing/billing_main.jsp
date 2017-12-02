<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>记账</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/mobiscroll/mobiscroll.custom-3.0.0.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/mobiscroll/mobiscroll.custom-3.0.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/mobiscroll/mobiscroll.i18n.zh.js"></script>
    <script type="text/javascript">
        $(function(){
            var $type = $('#type'), types = [], menus = [];
            <c:forEach items="${types}" var="type">
            types.push({value: '${type.value}', label: '${type.label}'});
            menus.push({label: '${type.label}', onClick: function(){
                var index = $(this).index();
                changeType($type, index);
            }});
            </c:forEach>
            $type.data({types: types});
            $type.bind('click', function(){
                weui.actionSheet(menus, [{label: '取消', onClick: function(){}}]);
            });
            changeType($type, 0);
        });

        function changeType($el, index) {
            var types = $el.data('types');
            var type = types[index];
            if (!type) return;
            $el.data({index: index});
            $('#type-label').html(type.label);
            WF.ajax.req({
                url: 'record?type=' + type.value,
                type: 'GET',
                success: function(result) {
                    $('#form-wrapper').html(result);
                }
            });
        }

        function saveContinue() {
            WF.form.ajaxSubmit($('#data-form'), function(){
                // 保存并继续
                WF.util.topTip('添加成功', {
                    duration: 1000,
                    callback: function() {
                        $('body').scrollTop(0);
                    }
                });
                var $type = $('#type');
                changeType($type, $type.data('index') || 0);
            });
        }
    </script>
</head>
<body>
<div class="page">
    <div class="weui-tab">
        <div class="weui-tab__panel">
            <div class="weui-cells">
                <div class="weui-cell weui-cell_access" id="type">
                    <div class="weui-cell__bd">
                        <p id="type-label"></p>
                    </div>
                    <div class="weui-cell__ft">更换</div>
                </div>
            </div>
            <div id="form-wrapper"></div>
        </div>
        <div class="weui-tabbar">
            <a href="javascript:void(0)" class="weui-tabbar__item" id="tpl-list">
                <img src="/images/icons/icon_clock.png" class="weui-tabbar__icon">
                <p class="weui-tabbar__label">从模板添加</p>
            </a>
            <a href="javascript:void(0)" class="weui-tabbar__item weui-bar__item_on" onclick="saveContinue()">
                <img src="/images/icons/icon_save.png" class="weui-tabbar__icon">
                <p class="weui-tabbar__label">保存</p>
            </a>
        </div>
    </div>
</div>
</body>
</html>