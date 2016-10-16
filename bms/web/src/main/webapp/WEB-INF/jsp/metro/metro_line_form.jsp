<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>地铁${metroLineDTO.name}</title>
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

        .metro-map { position: relative; width: 100%; height: 360px; background: #f3f3f3;}
        .metro-map .station-marker { position: absolute; width: 4px; height: 4px; border-radius: 50%; background: #ccc;}
        .metro-map .station-marker.running { background-color: ${metroLineDTO.color};}

        svg.metro-map { fill: none; stroke-width: 2;}
        svg.metro-map .metro-line:hover { stroke-width: 3;}
        svg.metro-map .metro-line + .metro-line-text { display: none;}
        svg.metro-map .metro-line:hover + .metro-line-text { display: block;}
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
            var $map = $('#metro-map');
            var $el = $('.metro-line-station');
            var total = {x: 0, y: 0}, min = {x: 0, y: 0}, max = {x: 0, y: 0}, size = 0;
            $.each(mls, function (idx, ml) {
                if (ml.longitude > 0 && ml.latitude > 0) {
                    total.x += ml.longitude;
                    total.y += ml.latitude;
                    size ++;
                }
            });
            var avg = {x: total.x / size, y: total.y / size};
            var svgPoints = [];
            $.each(mls, function(idx, ml){
                var $li = $('<li></li>').attr({'data-url': 'edit_station?guid=' + ml.stationGuid}).html(ml.stationName).addClass('station');
                $li.toggleClass('running', ml.running);
                $el.append($li);
                if (ml.longitude > 0 && ml.latitude > 0) {
                    svgPoints.push(parseInt(-(avg.x - ml.longitude) * 1500 + 300) + ',' + parseInt((avg.y - ml.latitude) * 1500 + 180));
                }
            });
            $el.find('.station').bind('click', function(){
                var url = $(this).attr('data-url');
                WF.page.forward(url);
            });
            renderLine($map, svgPoints, '${metroLineDTO.color}', '${metroLineDTO.loop}');
        }

        (function($){
            var SVG_NS = "http://www.w3.org/2000/svg";
            $.svg = function(tagName) {
                return $(document.createElementNS(SVG_NS, tagName));
            };
        })($);

        function renderLine($map, points, color, loop) {
            if (loop == 'true') points.push(points[0]);
            var $polyline = $.svg('polyline').attr({'points': points.join(' '), 'class': 'metro-line'}).css({stroke: color});
            $map.append($polyline);
        }
    </script>
</head>
<body>
    <form class="form-horizontal" id="data-form" action="add" method="post" onsubmit="return false;">
        <div class="form-group">
            <label class="form-control-static">线路名称</label>
            <input class="form-control" type="text" name="name" id="name" placeholder="线路名称" value="${metroLineDTO.name}"/>
        </div>
        <div class="form-group">
            <label class="form-control-static">主题色</label>
            <input class="form-control" type="color" name="color" id="color" placeholder="主题色" value="${metroLineDTO.color}"/>
        </div>
        <div class="form-group">
            <label class="checkbox-inline"><input class="weui_switch" type="checkbox" value="true" ${metroLineDTO.loop?'checked':''}/> 是环线</label>
        </div>
        <c:if test="${metroLineDTO.guid != null}">
        <div class="form-group">
            <label class="form-control-static">${metroLineDTO.name}的站台</label>
            <ol class="metro-line-station"></ol>
            <svg id="metro-map" class="metro-map" xmlns="http://www.w3.org/2000/svg" version="1.1">
                <title>${metroLineDTO.name}</title>
            </svg>
        </div>
        </c:if>
        <div class="form-group">
            <a class="btn btn-primary" href="javascript:void(0)" onclick="WF.form.submit($('#data-form'))">保存</a>
            <a class="btn btn-default" href="javascript:void(0)" onclick="history.back();">返回</a>
        </div>
    </form>
</body>
</html>