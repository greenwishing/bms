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
            var $menu = $('.menubar').find('a[data-url]');
            $menu.bind('click', function(){
                var $a = $(this), $li = $a.closest('li');
                $li.siblings('li').removeClass('active');
                $li.addClass('active');
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
                WF.util.topTip('添加成功，添加下一单！', {
                    callback: function() {
                        $('body').scrollTop(0);
                    }
                });
                $('.menubar').find('li.active>a[data-url]').trigger('click');

            });
        }
    </script>
</head>
<body>
<div class="menubar">
    <ul class="navbar">
        <c:forEach items="${types}" var="type">
            <li><a href="javascript:void(0)" data-type="${type.value}" data-url="record?type=${type.value}">${type.label}</a></li>
        </c:forEach>
    </ul>
</div>
<div id="form-wrapper"></div>
</body>
</html>