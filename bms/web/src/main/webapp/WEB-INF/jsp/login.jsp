<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>登录</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <script type="text/javascript">
        $(function(){
            $(':input:first').focus();
        });
    </script>
    <style type="text/css">
        * { font-family: "microsoft yahei",serif;}
        html, body { width: 100%; height: 100%; padding: 0; margin: 0; background: rgb(150,150,150);}
        .login-frame { position: absolute; width: 380px; height: 220px; top: 35%; left: 50%; margin: -110px auto auto -190px;
            border: 2px solid rgb(60,90,180); background: rgb(245,245,245); border-radius: 5px; box-shadow: 5px 5px 12px rgba(0,0,0,0.3);}
        .t { padding: 10px;  background: rgb(60,90,180); color: rgb(245,245,245);}
        .c { padding: 20px 25px;}
        .e { padding: 5px; color: rgb(255,60,80);}
        .item { margin: 3px;}
        .item label { display: inline-block; width: 75px;}
        .text { border: 1px solid rgb(189,189,189); padding: 3px 5px; width: 220px;}
        .r { text-align: right; padding: 0 10px;}
    </style>
</head>
<body>
<div class="login-frame">
    <div class="t">登录</div>
    <div class="c">
        <form action="/account_check" method="post">
            <div class="item">
                <label for="account">帐号：</label>
                <input id="account" type="text" class="text" name="account" />
            </div>
            <div class="item">
                <label for="password">密码：</label>
                <input id="password" type="password" class="text" name="password" />
            </div>
            <div class="item r">
                <input type="submit" value="登录"/>
            </div>
        </form>
        <div class="e">${param.action==1?'帐号或密码错误':''}${param.action==2?'登录超时':''}${param.action==3?'已退出':''}</div>
    </div>
</div>
</body>
</html>