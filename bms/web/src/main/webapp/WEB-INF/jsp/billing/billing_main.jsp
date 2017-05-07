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
            changeType($('#type'));
        });

        function changeType(el) {
            var $option = $(el).find('option:selected');
            WF.ajax.req({
                url: $option.attr('data-url'),
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
                    callback: function() {
                        $('body').scrollTop(0);
                    }
                });
                changeType($('#type'));
            });
        }
    </script>
</head>
<body>
    <div class="weui-cells__title">记账类型</div>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell weui-cell_select">
            <div class="weui-cell__bd">
                <select class="weui-select" id="type" onchange="changeType(this)">
                    <c:forEach items="${types}" var="type">
                        <option value="${type.value}" data-url="record?type=${type.value}">${type.label}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div id="form-wrapper"></div>
</body>
</html>