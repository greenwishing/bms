<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>${activity.name}</title>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <link rel="stylesheet" type="text/css" href="/css/bms.css">
    <script type="text/javascript">
        $(function () {
            let actions = [];
            <c:if test="${me}">
            actions.push({url: 'plan_view?activityGuid=${activity.guid}', label: '修改', name: 'planGuid', key: 'guid'});
            </c:if>
            $('#activities').eventShow({
                dist: moment('${activity.dateTo} 00:00'),
                data: {
                    activityGuid: '${activity.guid}'
                },
                action: '${dataAction}',
                actions: actions,
                dataConverter: function(data) {
                    return {done: data.done, name: data.name, start: moment(data.dateFrom + ' 00:00'), end: moment(data.dateTo + ' 00:00')}
                }
            });
        });
    </script>
    <style>
        <c:if test="${not empty activity.cover.url}">
        body,
        body:before {
            background-image: url('${activity.cover.url}');
        }
        </c:if>
    </style>
</head>
<body>
<div class="container activity">
    <c:if test="${me}">
        <a class="event-action pull-right" href="/activity/${activity.guid}">预览</a>
        <a class="event-action pull-right" href="edit?guid=${activity.guid}" async-load="true">编辑</a>
    </c:if>
    <blockquote id="activities"></blockquote>
    <c:if test="${me}">
        <a class="event-action" href="plan_add?activityGuid=${activity.guid}">添加计划</a>
    </c:if>
</div>
<footer>
    <ul class="flex">
        <li class="flex-item text-right">@${not empty activity.username?activity.username:'greenwishing'}</li>
    </ul>
</footer>
</body>
</html>
