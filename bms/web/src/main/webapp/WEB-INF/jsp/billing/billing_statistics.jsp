<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>账单统计</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/moment.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts/4.0.3/highcharts.js"></script>
    <script type="text/javascript">
        $(function(){
            loadBillingStatistics();
        });
        function loadBillingStatistics() {
            var $type = $('#type'), $mode = $('#mode'), $group = $('#group');
            var type = $type.val(), mode = $mode.val(), group = $group.val();
            var offset = parseInt($mode.attr('data-offset')) || 0;
            var from = moment(), to;
            if (mode == 'days') {
                from = moment().add(offset, 'days');
                to = from.clone();
            } else if (mode == 'weeks') {
                from = moment().add(offset, 'weeks').startOf('week');
                to = from.clone().endOf('week');
            } else if (mode == 'months') {
                from = moment().add(offset, 'months').startOf('month');
                to = from.clone().endOf('month');
            } else {
                return;
            }
            var dateFrom = from.format('YYYY-MM-DD');
            var dateTo = to.format('YYYY-MM-DD');
            $('#date').html(dateFrom == dateTo ? dateFrom : (dateFrom + '到' + dateTo));
            console.log(dateFrom);
            console.log(dateTo);
            WF.ajax.req({
                url: 'data',
                data: { type: type, group: group, from: dateFrom, to: dateTo },
                success: function(result) {
                    renderCharts(result.data);
                }
            });
        }
        function addModeOffset(val) {
            var $mode = $('#mode');
            var offset = parseInt($mode.attr('data-offset')) || 0;
            $mode.attr({'data-offset': (offset + val)});
            loadBillingStatistics();
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
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">账单统计 <span id="date"></span></h3>
            <div class="form-inline" style="margin-top: 10px;">
                <select id="type" class="form-control" onchange="loadBillingStatistics()">
                    <c:forEach items="${types}" var="type">
                        <option value="${type.value}">${type.label}</option>
                    </c:forEach>
                </select>
                <select id="group" class="form-control" onchange="loadBillingStatistics()">
                    <option value="category">分类</option>
                    <option selected value="subcategory">子分类</option>
                </select>
                <select id="mode" class="form-control" onchange="loadBillingStatistics()" data-offset="0">
                    <option value="days">按天</option>
                    <option value="weeks">按周</option>
                    <option value="months">按月</option>
                </select>
                <div class="btn-group">
                    <button class="btn btn-default" onclick="addModeOffset(-1)">&lt;</button>
                    <button class="btn btn-default" onclick="addModeOffset(1)">&gt;</button>
                </div>
            </div>
        </div>
        <div class="panel-body">
            <div id="billing-statistics"></div>
        </div>
    </div>
</div>
</body>
</html>