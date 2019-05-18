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
            let actions = [];
            <c:if test="${me}">
            actions.push({url: 'budget_edit?activityGuid=${param.activityGuid}&planGuid=${plan.guid}', label: '修改', key: 'guid'});
            </c:if>
            $('#plans').eventShow({
                data: {
                    activityGuid: '${param.activityGuid}',
                    planGuid: '${plan.guid}'
                },
                action: '${dataAction}',
                actions: actions,
                dataConverter: function(data) {
                    return {done: data.done, name: data.name, start: moment(data.dateFrom + ' 00:00'), end: moment(data.dateTo + ' 00:00')}
                }
            });
        });
    </script>
</head>
<body>
<div class="container activity">
    <c:if test="${me}">
        <a class="event-action pull-right" href="view?guid=${param.activityGuid}">返回活动</a>
        <a class="event-action pull-right" href="plan_edit?activityGuid=${param.activityGuid}&guid=${plan.guid}"
           async-load="true">编辑</a>
    </c:if>
    <blockquote id="plans"></blockquote>
    <c:if test="${me}">
        <a class="event-action" href="budget_add?activityGuid=${param.activityGuid}&planGuid=${plan.guid}">添加预算</a>
    </c:if>
</div>
<footer>
    <ul class="flex">
        <li class="flex-item text-right">@greenwishing</li>
    </ul>
</footer>
</body>
</html>
