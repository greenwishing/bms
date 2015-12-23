<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>欢迎</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.2.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/3.3.2/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts/4.0.3/highcharts.js"></script>

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
    <style type="text/css">
        html, body, h1, h2, h3, h4, h5, h6 { font-family: "microsoft yahei",serif;}
    </style>
</head>
<body>
<div class="container">
    <div class="nearest row"></div>
    <form action="${pageContext.request.contextPath}/account_check" class="form-horizontal" method="post">
        <div class="form-group form-group-lg">
            <div class="col-lg-3"><label for="account" class="form-control-static">帐号</label></div>
            <div class="col-lg-9">
                <input id="account" type="text" class="form-control" name="account" />
            </div>
        </div>
        <div class="form-group form-group-lg">
            <div class="col-lg-3"><label for="password" class="form-control-static">密码</label></div>
            <div class="col-lg-9">
                <input id="password" type="password" class="form-control" name="password" />
            </div>
        </div>
        <div class="form-group form-group-lg">
            <div class="col-lg-offset-3 col-lg-9">
                <input type="submit" class="btn btn-primary btn-block btn-lg" value="登录"/>
                <c:choose>
                    <c:when test="${param.action==1}"><div class="help-block help-block-danger">帐号或密码错误</div></c:when>
                    <c:when test="${param.action==2}"><div class="help-block help-block-danger">登录超时</div></c:when>
                    <c:when test="${param.action==1}"><div class="help-block">已退出</div></c:when>
                </c:choose>
            </div>
        </div>
    </form>
</div>
</body>
</html>