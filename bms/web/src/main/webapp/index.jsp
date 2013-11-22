<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>欢迎</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <script type="text/javascript" src="/js/jquery/jquery-1.7.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $.ajax({
                type: 'post',
                url: '/api/month_in',
                success: function(result){
                    $('#month_in').text(result.monthCount);
                }
            });
            $.ajax({
                type: 'post',
                url: '/api/month_out',
                success: function(result){
                    $('#month_out').text(result.monthCount);
                }
            });
        });
    </script>
</head>
<body>
<div>
    <ul>
        <li>total in: <span id="month_in"></span></li>
        <li>total out: <span id="month_out"></span></li>
    </ul>
</div>
<div>
    <a href="/login">登录</a>
</div>
</body>
</html>