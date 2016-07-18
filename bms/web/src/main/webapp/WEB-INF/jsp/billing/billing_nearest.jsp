<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>账单汇总</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts/4.0.3/highcharts.js"></script>
    <script type="text/javascript">
        $(function(){
            $.ajax({
                type: 'post',
                url: 'nearest_data',
                data: { size: 7},
                success: function(result){
                    for (var i in result.series)
                        renderHighcharts(result.series[i]);
                }
            });
        });
        function renderHighcharts(series) {
            var div = $('<div class="col-lg-6"></div>');
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
<div class="weui_tab">
    <div class="weui_tab_bd">
        <div class="weui_panel">
            <div class="weui_panel_hd">账单汇总</div>
            <div class="weui_panel_bd">
                <div>
                    <div class="nearest row"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="weui_tabbar">
        <a class="weui_tabbar_item" href="javascript:void(0)" onclick="history.back()">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/images/icons/icon_back.png" alt="">
            </div>
            <p class="weui_tabbar_label">返回</p>
        </a>
    </div>
</div>
</body>
</html>