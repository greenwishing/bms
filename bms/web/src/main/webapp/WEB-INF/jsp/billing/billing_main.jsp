<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>记账</title>
    <script type="text/javascript">
        $(function(){
            var $wrapper = $('#form-wrapper');
            var $menu = $('.weui-tabbar').find('a[data-url]');
            $menu.bind('click', function(){
                var $a = $(this);
                $a.addClass('weui-bar__item_on').siblings().removeClass('weui-bar__item_on');
                WF.ajax.req({
                    url: $a.attr('data-url'),
                    type: 'GET',
                    success: function(result) {
                        $wrapper.html(result);
                    }
                });
            });
            $($menu[0]).trigger('click');
        });

        function saveContinue() {
            WF.form.ajaxSubmit($('#data-form'), function(){
                // 保存并继续
                WF.util.topTip('添加成功', {
                    callback: function() {
                        $('body').scrollTop(0);
                    }
                });
                $('.weui-tabbar').find('a.weui-bar__item_on[data-url]').trigger('click');

            });
        }
    </script>
</head>
<body style="height: 100%;">
<div class="weui-tab">
    <div class="weui-tab__panel">
        <div id="form-wrapper"></div>
    </div>
    <div class="weui-tabbar">
        <c:forEach items="${types}" var="type">
            <a href="javascript:void(0);" class="weui-tabbar__item" data-type="${type.value}" data-url="record?type=${type.value}">
                <img src="/images/icons/icon_add.png" class="weui-tabbar__icon">
                <p class="weui-tabbar__label">${type.label}</p>
            </a>
        </c:forEach>
    </div>
</div>
</body>
</html>