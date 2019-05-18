<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>${plan.name}</title>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <link rel="stylesheet" type="text/css" href="/css/bms.css">
    <script type="text/javascript">
        $(function () {
            $('#plans').eventShow({
                data: {
                    activityGuid: '${param.activityGuid}',
                    planGuid: '${plan.guid}'
                },
                action: 'budgets',
                actions: [
                    {url: 'budget_edit?activityGuid=${param.activityGuid}&planGuid=${plan.guid}', label: '修改', key: 'guid'}
                ],
                dataConverter: function(data) {
                    return {done: data.done, name: data.name, start: moment(data.dateFrom + ' 00:00'), end: moment(data.dateTo + ' 00:00')}
                }
            });
        });
    </script>
    <style>
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
    <a class="pull-right" href="view?guid=${param.activityGuid}">返回活动</a>
    <c:if test="${login and loginUserGuid eq plan.userGuid}">
        <a class="pull-right" href="plan_edit?activityGuid=${param.activityGuid}&guid=${plan.guid}" async-load="true">编辑</a>
    </c:if>
    <blockquote id="plans"></blockquote>
    <a href="budget_add?activityGuid=${param.activityGuid}&planGuid=${plan.guid}">添加预算</a>
</div>
<footer>
    <ul class="flex">
        <li class="flex-item text-right">@greenwishing</li>
    </ul>
</footer>
</body>
</html>
