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
            $('#activities').eventShow({
                dist: moment('${activity.dateTo} 00:00'),
                data: {
                    activityGuid: '${activity.guid}'
                },
                action: 'plans',
                actions: [
                    {url: 'plan_view?activityGuid=${activity.guid}', label: '修改', name: 'planGuid', key: 'guid'}
                ],
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
        .container {
            color: rgb(255, 255, 255);
            text-shadow: 0 2px 2px rgba(0, 0, 0, .5);
        }
        @media (max-width: 375px) {
            .container p {
                font-size: 14px;
            }
        }
        blockquote {
            margin-top: 30px;
        }
        @media (min-width: 376px) {
            blockquote p {
                text-indent: 1em;
                line-height: 1.2em;
                margin-bottom: .8em;
            }
        }
        .event,
        .event.event-coming {
            color: #fff;
        }
        .event.event-gone:not(.event-done) {
            color: #faa;
        }
        .event.event-doing:not(.event-done) {
            color: #afa;
        }
        .event-icon {
            display: inline-block;
            margin-right: .3em;
        }
        .event .event-icon:after {
            content: '×';
        }
        .event.event-done {
            text-decoration: line-through;
        }
        .event.event-done .event-icon:after {
            content: '√';
        }
    </style>
</head>
<body>
<div class="container">
    <c:if test="${login and loginUserGuid eq activity.userGuid}">
        <a class="pull-right" href="edit?guid=${activity.guid}" async-load="true">编辑</a>
    </c:if>
    <blockquote id="activities"></blockquote>
    <a href="plan_add?activityGuid=${activity.guid}">添加计划</a>
</div>
<footer>
    <ul class="flex">
        <li class="flex-item text-right">@greenwishing</li>
    </ul>
</footer>
</body>
</html>
