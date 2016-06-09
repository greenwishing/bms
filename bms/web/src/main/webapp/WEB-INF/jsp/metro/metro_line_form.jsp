<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Metro</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <style type="text/css">
        <c:if test="${metroLineDTO.guid != null}">
        .metro-line-station { padding: 8px 15px; margin-bottom: 20px; list-style: none; }
        .metro-line-station .station { display: inline-block; position: relative; line-height: 1.5em; margin-bottom: .6em; padding: 2px 8px; border-radius: 3px; background-color: #ddd;}
        .metro-line-station .station { transition: background .3s; -moz-transition: background .3s; -webkit-transition: background .3s; -o-transition: background .3s; cursor: pointer; text-decoration: none;}
        .metro-line-station .station.running { background-color: ${metroLineDTO.color}; color: #fff; }
        .metro-line-station .station.running:hover,
        .metro-line-station .station.running:focus { background-color: ${metroLineDTO.color}; box-shadow: 0 0 5px ${metroLineDTO.color}; }
        .metro-line-station > .station + .station { margin-left: 1.5em;}
        .metro-line-station > .station + .station:before { content: "→"; position: absolute; left: -1.6em; padding: 0 5px; color: #ccc;}

        .metro-map-container { position: relative; width: 720px; height: 360px; background: #f3f3f3;}
        .metro-map-container .metro-map { position: absolute; width: 720px; height: 360px; top: 50%; left: 50%;}
        .metro-map-container .metro-map .station-marker { position: absolute; width: 4px; height: 4px; border-radius: 50%; background: #ccc;}
        .metro-map-container .metro-map .station-marker.running { background-color: ${metroLineDTO.color};}
        </c:if>
    </style>
    <script type="text/javascript">
        $(function(){
            var mlId = '${metroLineDTO.id}';
            if (WF.validation.isEmpty(mlId)) return;
            WF.ajax.req({
                url: 'metro_line_stations',
                data: {metroLineId: mlId},
                success: function(result) {
                    renderMetroLineStations(result.metroLineStations);
                }
            });
        });
        function renderMetroLineStations(mls) {
            var $el = $('.metro-line-station');
            var $map = $('.metro-map');
            var total = {x: 0, y: 0}, size = 0;
            $.each(mls, function (idx, ml) {
                if (ml.longitude > 0 && ml.latitude > 0) {
                    total.x += ml.longitude;
                    total.y += ml.latitude;
                    size ++;
                }
            });
            var avg = {x: total.x / size, y: total.y / size};
            console.log('avg: ');
            console.log(avg);
            $.each(mls, function(idx, ml){
                var $li = $('<li></li>').attr({'data-url': 'edit_station?guid=' + ml.stationGuid}).html(ml.stationName).addClass('station');
                $li.toggleClass('running', ml.running);
                $el.append($li);

                console.log('lng: ' + ml.longitude + ', lat: ' + ml.latitude);
                if (ml.longitude > 0 && ml.latitude > 0) {
                    var styles = {
                        top: (avg.y - ml.latitude) * 1000,
                        left: -(avg.x - ml.longitude) * 1000
                    };
                    console.log(ml.stationName + ': ');
                    console.log(styles);
                    var $p = $('<div></div>').addClass('station-marker').attr({title: ml.stationName}).css(styles);
                    $p.toggleClass('running', ml.running);
                    $map.append($p);
                }
            });
            $el.find('.station').bind('click', function(){
                var url = $(this).attr('data-url');
                WF.page.forward(url);
            });
        }
    </script>
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
                <ol class="metro-line-station"></ol>
            </div>
        </div>
    </c:if>
    <div class="form-group">
        <div class="col-sm-10 col-sm-offset-2">
            <input class="btn btn-success" type="button" value="保存" onclick="WF.form.ajaxSubmit($('#data-form'))"/>
            <input class="btn btn-default" type="button" value="返回" onclick="WF.page.forward('list')"/>
        </div>
    </div>
    <c:if test="${metroLineDTO.guid != null}">
        <div class="metro-map-container">
            <div class="metro-map"></div>
        </div>
    </c:if>
</form>
</body>
</html>