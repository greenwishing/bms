<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${stationDTO.name}站台</title>
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
    <form id="data-form" action="add_station" method="post" onsubmit="return false;">
        <div class="form-group">
            <label class="form-control-static">名称</label>
            <input class="form-control" type="text" name="name" id="name" placeholder="名称" value="${stationDTO.name}"/>
        </div>
        <div class="form-group">
            <label class="form-control-static">拼音</label>
            <input class="form-control" type="text" name="pinyin" id="pinyin" placeholder="拼音" value="${stationDTO.pinyin}"/>
        </div>
        <div class="form-group">
            <label class="form-control-static">经度</label>
            <input class="form-control" type="text" name="longitude" id="longitude" placeholder="经度" value="${stationDTO.longitude}"/>
        </div>
        <div class="form-group">
            <label class="form-control-static">纬度</label>
            <input class="form-control" type="text" name="latitude" id="latitude" placeholder="纬度" value="${stationDTO.latitude}"/>
        </div>
        <c:if test="${stationDTO.editWithMetroLine}">
            <div class="form-group">
                <label class="form-control-static">状态</label>
                <select name="status" class="form-control">
                    <c:forEach items="${lineStationStatusList}" var="lineStationStatus">
                        <option value="${lineStationStatus.value}" ${stationDTO.status eq lineStationStatus ? 'selected' : ''}>${lineStationStatus.label}</option>
                    </c:forEach>
                </select>
                <input type="hidden" name="editWithMetroLine" value="${stationDTO.editWithMetroLine}"/>
            </div>
        </c:if>
        <div class="form-group">
            <a class="btn btn-primary" href="javascript:void(0)" onclick="WF.form.ajaxSubmit($('#data-form'))">保存</a>
            <a class="btn btn-default" href="javascript:void(0)" onclick="history.back();">返回</a>
        </div>
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
    </form>
</body>
</html>