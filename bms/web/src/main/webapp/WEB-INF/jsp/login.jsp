<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>登录记账管理系统</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
    <form action="/j_spring_security_check" method="post">
        <div>
            <label for="username">帐号：</label>
            <input id="username" type="text" class="text" name="j_username" />
        </div>
        <div>
            <label for="password">密码：</label>
            <input id="password" type="password" class="text" name="j_password" />
        </div>
        <div class="form-item">
            <label>&nbsp;</label>
            <input type="submit" class="submit-btn" id="form-submit" value="登录"/>
        </div>
        </form>
</body>
</html>