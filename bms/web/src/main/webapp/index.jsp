<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>欢迎</title>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta content="telephone=no" name="format-detection"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/weui/weui.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.2.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts/4.0.3/highcharts.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css" />

    <script type="text/javascript">
        $(function(){
            $(':input:first').focus();
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath}/api/nearest',
                data: { size: 7},
                success: function(result){
                    for (var i in result.series)
                    renderHighcharts(result.series[i]);
                }
            });
        });
        function renderHighcharts(series) {
            var div = $('<div class="col-lg-6"></div>');
            Highcharts.numberFormat()
            $('.nearest').append(div);
            var data = [];
            data.push(series);
            var categories = [];
            for (var i in series.data) {
                categories.push(series.data[i].name);
            }
            var color = (series.name == '收入' ? 'green' : 'red');
            div.highcharts({
                chart: { height: 220, type: 'areaspline' },
                title: { text: series.name },
                tooltip: {
                    headerFormat: '',
                    pointFormat: '{point.name}{series.name} {point.y}元'
                },
                plotOptions: {
                    areaspline: { borderWidth: 0, lineWidth: 1, lineColor: color, fillOpacity: 0.1, states: { hover: { enabled: false } }, marker: { radius: 2} }
                },
                xAxis: {
                    title: { text: ''},
                    categories: categories,
                    labels: { rotation: -45}
                },
                yAxis: {
                    title: {text: '金额'},
                    min: 0
                },
                series: data,
                legend: { enabled: false, verticalAlign: 'top' },
                credits: { enabled: false}
            });
        }
    </script>
</head>
<body>
    <form action="${pageContext.request.contextPath}/account_check" method="post">
        <div class="weui_cells_title">登录</div>
        <div class="weui_cells weui_cells_form">
            <div class="weui_cell">
                <div class="weui_cell_hd"><label class="weui_label">帐号</label></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input id="account" type="text" class="weui_input" name="account" placeholder="请输入帐号" />
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_hd"><label class="weui_label">密码</label></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input id="password" type="password" class="weui_input" name="password" placeholder="请输入密码" />
                </div>
            </div>
            <c:choose>
                <c:when test="${param.action==1}"><div class="weui_cells_tips">帐号或密码错误</div></c:when>
                <c:when test="${param.action==2}"><div class="weui_cells_tips">登录超时</div></c:when>
                <c:when test="${param.action==1}"><div class="weui_cells_tips">已退出</div></c:when>
            </c:choose>
        </div>
        <div class="weui_btn_area">
            <button class="weui_btn weui_btn_primary" type="submit">登录</button>
        </div>
    </form>
    <div class="nearest row"></div>
</body>
</html>