<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>添加/编辑活动</title>
    <script type="text/javascript">
        $(function(){
            /* 图片自动上传 */
            weui.uploader('#cover_uploader', {
                url: '/common/upload/image',
                auto: true,
                type: 'file',
                fileVal: 'file',
                compress: {
                    width: 1920,
                    height: 1920,
                    quality: .8
                },
                onBeforeQueued: function onBeforeQueued(files) {
                    if (["image/jpg", "image/jpeg", "image/png", "image/gif"].indexOf(this.type) < 0) {
                        weui.alert('请上传图片');
                        return false;
                    }
                    var $files = $('.weui-uploader__file');
                    if ($files.length) { // 仅保留当前上传的图片
                        $files.remove();
                    }
                },
                onSuccess: function onSuccess(result) {
                    if (result.success) {
                        $('#cover').val(result.key);
                    } else {
                        weui.alert(result.message);
                    }
                },
                onError: function onError(err) {
                    weui.alert('上传文件出错啦!');
                }
            });

        });
    </script>
</head>
<body>
    <spring-form:form id="data-form" modelAttribute="activityDTO" method="post" onsubmit="return false;">
        <div class="weui-cells__title">名称</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <spring-form:input cssClass="weui-input" path="name" id="name" placeholder="名称"/>
                </div>
            </div>
        </div>
        <div class="weui-cells weui-cells_form" id="cover_uploader">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <div class="weui-uploader">
                        <div class="weui-uploader__hd">
                            <p class="weui-uploader__title">封面</p>
                        </div>
                        <div class="weui-uploader__bd">
                            <ul class="weui-uploader__files">
                                <c:if test="${not empty activityDTO.cover.url}"><li class="weui-uploader__file" style="background-image:url(${activityDTO.cover.url})"></li></c:if>
                            </ul>
                            <div class="weui-uploader__input-box">
                                <input id="uploaderInput" class="weui-uploader__input" type="file" accept="image/*"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="weui-cells__title">开始日期</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <input type="date" class="weui-input" name="dateFrom" id="dateFrom" placeholder="开始日期" value="${activityDTO.dateFrom}"/>
                </div>
            </div>
        </div>
        <div class="weui-cells__title">结束日期</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <input type="date" class="weui-input" name="dateTo" id="dateTo" placeholder="结束日期" value="${activityDTO.dateTo}"/>
                </div>
            </div>
        </div>
        <div class="weui-cells__title">备注</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <spring-form:input cssClass="weui-input" path="remark" id="remark" placeholder="备注"/>
                </div>
            </div>
        </div>
        <div class="weui-btn-area">
            <input type="hidden" id="cover" name="cover.key" value="${activityDTO.cover.key}"/>
            <a class="weui-btn weui-btn_primary" href="javascript:void(0)" onclick="WF.form.ajaxSubmit($('#data-form'))">保存</a>
        </div>
    </spring-form:form>
</body>
</html>