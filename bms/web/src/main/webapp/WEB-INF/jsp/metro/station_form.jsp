<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Station</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=rZhunrvlnOt5iRYpxyy6EeMGOfIwwEQ2"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/pinyin.js"></script>
    <script type="text/javascript">
        $(function(){
            makePinyin($('#name'));
        });
        function makePinyin(b){var c=$(b).val();var a=$.pinyin(c);$("#pinyin").val(a)};
    </script>
</head>
<body>
<form class="form-horizontal" id="data-form" action="add_station" method="post" onsubmit="return false;">
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
            <div id="baidu-map" style="width: 100%; height: 300px;"></div>
            <script type="text/javascript">
                var lng = parseFloat('${stationDTO.longitude}'), lat = parseFloat('${stationDTO.latitude}');
                var alreadyLocated = !isNaN(lng) && !isNaN(lat) && lng > 0 && lat > 0;
                (function($){
                    $.baiduMap = {
                        version: '0.0.1'
                    };

                    $.baiduMap.defaultOptions = {
                        el: 'baidu-map',
                        keyword: '',
                        defaultPoint: new BMap.Point(104.07228, 30.663492),
                        level: 12,
                        callback: function(point) {}
                    };

                    $.baiduMap.localSearch = function(map, keyword, callback) {
                        new BMap.LocalSearch(map, {
                            renderOptions: {map: map},
                            onSearchComplete: function(result){
                                var poi = result.getPoi(0);
                                if (poi) {
                                    if (typeof callback === 'function') callback.apply(map, [poi.point]);
                                }
                            }
                        }).search(keyword, {forceLocal: true});
                    };

                    $.baiduMap.init = function(options){
                        options = $.extend({}, $.baiduMap.defaultOptions, options || {});
                        var map = new BMap.Map(options.el);
                        map.centerAndZoom(options.defaultPoint, options.level);
                        map.enableScrollWheelZoom();
                        map.enableContinuousZoom();

                        var callback = options.callback;
                        map.addEventListener("click", function(e){
                            if (typeof callback === 'function') callback.apply(map, [e.point]);
                        });
                        if (!alreadyLocated && options.keyword != '') {
                            $.baiduMap.localSearch(map, options.keyword, options.callback);
                        }
                    };
                })(jQuery);
                var options = {
                    el: 'baidu-map',
                    keyword: $('#name').val() + '-地铁站',
                    callback: function (point) {
                        $('#longitude').val(point.lng);
                        $('#latitude').val(point.lat);
                    }
                };
                if (alreadyLocated) {
                    options.defaultPoint = new BMap.Point(lng, lat);
                    options.level = 18;
                }
                $.baiduMap.init(options);
            </script>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-10 col-sm-offset-2">
            <input class="btn btn-success" type="button" value="保存" onclick="WF.form.ajaxSubmit($('#data-form'))"/>
            <input class="btn btn-default" type="button" value="返回" onclick="history.back()"/>
        </div>
    </div>
</form>
</body>
</html>