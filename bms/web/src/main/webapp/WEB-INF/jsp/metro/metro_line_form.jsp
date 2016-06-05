<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Metro</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <style type="text/css">
        .metro-line-station {
            padding: 8px 15px;
            margin-bottom: 20px;
            list-style: none;
        }
        .metro-line-station > li {
            display: inline-block;
            line-height: 1.5em;
            margin-bottom: .6em;
        }
        .metro-line-station > li:not(.active) {
            cursor: pointer;
        }
        .metro-line-station .label { font-weight: normal; }
        .metro-line-station > li + li:before {
            padding: 0 5px;
            color: #ccc;
            content: "→";
        }
        <c:if test="${metroLineDTO.guid != null}">
        .metro-line-station .label { border: 1px solid ${metroLineDTO.color}; transition: background .3s; -moz-transition: background .3s; -webkit-transition: background .3s; -o-transition: background .3s; }
        .metro-line-station .label { background-color: ${metroLineDTO.color}; color: #ffffff; }
        .metro-line-station .label[href]:hover,
        .metro-line-station .label[href]:focus { background-color: #ffffff; color: ${metroLineDTO.color}; }
        </c:if>
    </style>
</head>
<body>
<form class="form-horizontal" id="data-form" action="add" method="post" onsubmit="return false;">
    <div class="form-group">
        <label class="control-label col-sm-2" for="name">Name</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="name" id="name" placeholder="Name" value="${metroLineDTO.name}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="color">Color</label>
        <div class="col-sm-10">
            <input type="color" class="form-control" name="color" id="color" placeholder="Color" value="${metroLineDTO.color}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2">Loop</label>
        <div class="col-sm-10">
            <label class="radio-inline">
                <input type="radio" name="loop" value="true" ${metroLineDTO.loop?'checked':''} /> Yes
            </label>
            <label class="radio-inline">
                <input type="radio" name="loop" value="false" ${metroLineDTO.loop?'':'checked'} /> No
            </label>
        </div>
    </div>
    <c:if test="${metroLineDTO.guid != null}">
        <div class="form-group">
            <label class="control-label col-sm-2">Stations</label>
            <div class="col-sm-10">
                <ol class="metro-line-station">
                    <c:forEach items="${metroLineStations}" var="metroLineStation">
                        <c:choose>
                            <c:when test="${metroLineStation.running}">
                                <li title="${metroLineStation.status.label}"><a class="label label-primary" href="#${metroLineStation.guid}">${metroLineStation.station.name}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li title="${metroLineStation.status.label}" class="active">${metroLineStation.station.name}</li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ol>
            </div>
        </div>
    </c:if>
    <div class="form-group">
        <div class="col-sm-10 col-sm-offset-2">
            <input class="btn btn-success" type="button" value="保存" onclick="WF.form.ajaxSubmit($('#data-form'))"/>
            <input class="btn btn-default" type="button" value="返回" onclick="WF.page.forward('list')"/>
        </div>
    </div>
</form>
</body>
</html>