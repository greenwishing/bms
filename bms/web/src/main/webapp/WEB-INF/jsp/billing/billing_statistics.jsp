<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>账单统计</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts/4.0.3/highcharts.js"></script>
    <script type="text/javascript">
        $(function(){
            loadBillingStatistics();
        });
        function loadBillingStatisticsByType(type) {
            $('#type').val(type);
            var group = $('#group').find('option:selected').val();
            WF.ajax.req({
                url: 'data?type=' + type + '&group=' + group,
                success: function(result) {
                    renderCharts(result.data);
                }
            });
        }
        function loadBillingStatistics() {
            var type = $('#type').val();
            loadBillingStatisticsByType(type);
        }
        function renderCharts(records) {
            var group = $('#group').find('option:selected').val();
            console.log(group);
            var data = [];
            var total = 0;
            for (var i in records) {
                var record = records[i];
                total += record.amount;
            }
            for (var i in records) {
                var record = records[i];
                data.push({name: record[group], y: (record.amount/total*100), amount: record.amount});
            }
            $('#billing-statistics').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false
                },
                title: {
                    text: 'Billing statistics'
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b><br/>金额: <b>{point.amount} 元</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            color: '#000000',
                            connectorColor: '#000000',
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %<br/>金额: <b>{point.amount} 元</b>'
                        }
                    }
                },
                series: [{
                    type: 'pie',
                    name: '占比',
                    data: data
                }],
                credits: {enabled: false}
            });
        }
    </script>
</head>
<body>
<div>
    <div class="col-sm-10">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">账单统计
                    <select id="group" onchange="loadBillingStatistics()">
                        <option value="type">类型</option>
                        <option value="category">分类</option>
                        <option selected value="subcategory">子分类</option>
                    </select>
                </h3>
            </div>
            <div class="panel-body">
                <div id="billing-statistics"></div>
            </div>
        </div>
    </div>
    <div class="list-group col-sm-2">
        <input type="hidden" id="type" value="day"/>
        <a class="list-group-item" href="javascript:loadBillingStatisticsByType('day')">今天</a>
        <a class="list-group-item" href="javascript:loadBillingStatisticsByType('week')">本周</a>
        <a class="list-group-item" href="javascript:loadBillingStatisticsByType('month')">本月</a>
    </div>
</div>
</body>
</html>