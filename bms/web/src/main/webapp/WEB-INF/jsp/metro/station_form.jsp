<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Station</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<form class="form-horizontal" id="data-form" action="add" method="post" onsubmit="return false;">
    <div class="form-group">
        <label class="control-label col-sm-2" for="name">Name</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="name" id="name" placeholder="Name" value="${stationDTO.name}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="pinyin">Pinyin</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="pinyin" id="pinyin" placeholder="Pinyin" value="${stationDTO.pinyin}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="longitude">Longitude</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="longitude" id="longitude" placeholder="Longitude" value="${stationDTO.longitude}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="latitude">Latitude</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="latitude" id="latitude" placeholder="Latitude" value="${stationDTO.latitude}"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-10 col-sm-offset-2">
            <input class="btn btn-success" type="button" value="保存" onclick="WF.form.ajaxSubmit($('#data-form'))"/>
            <input class="btn btn-default" type="button" value="返回" onclick="WF.page.forward('list')"/>
        </div>
    </div>
</form>
</body>
</html>