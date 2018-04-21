<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>写文章</title>
    <script type="text/javascript" src="/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="/js/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript">
        $(function(){
            WF.editor.init('article_content');
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
        function articleFormSubmit() {
            var content = WF.editor.getContent('article_content');
            $('#content').val(content);
            WF.form.ajaxSubmit($('#data-form'))
        }
    </script>
    <style type="text/css">
        #article_content .edui-editor,
        #article_content .edui-default { border-radius: 0;}
        #article_content .edui-editor-toolbarboxouter,
        #article_content .edui-editor-toolbarbox { background: #fff; box-shadow: none;}
    </style>
</head>
<body>
    <spring-form:form id="data-form" modelAttribute="articleDTO" method="post" onsubmit="return false;">
        <div class="weui-cells__title">标题</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <spring-form:input cssClass="weui-input" path="title" id="title" placeholder="标题"/>
                </div>
            </div>
        </div>
        <div class="weui-cells__title">分类</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell weui-cell_select">
                <div class="weui-cell__bd">
                    <spring-form:select cssClass="weui-select" id="categoryGuid" path="categoryGuid" items="${categoryDTOs}" itemValue="guid" itemLabel="name"/>
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
                                <c:if test="${not empty articleDTO.cover.url}"><li class="weui-uploader__file" style="background-image:url(${articleDTO.cover.url})"></li></c:if>
                            </ul>
                            <div class="weui-uploader__input-box">
                                <input id="uploaderInput" class="weui-uploader__input" type="file" accept="image/*"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="weui-cells__title">内容</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell__bd">
                <script type="text/plain" id="article_content">${articleDTO.content}</script>
                <spring-form:textarea cssClass="form-control" id="content" path="content" cssStyle="display: none;"/>
            </div>
        </div>
        <div class="weui-cells__title">权限</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell weui-cell_select">
                <div class="weui-cell__bd">
                    <spring-form:select cssClass="weui-select" id="access" path="access" items="${accessTypes}" itemValue="value" itemLabel="label"/>
                </div>
            </div>
        </div>
        <div class="weui-btn-area">
            <input type="hidden" id="cover" name="cover.key" value="${articleDTO.cover.key}"/>
            <a class="weui-btn weui-btn_primary" href="javascript:void(0)" onclick="articleFormSubmit()">保存</a>
        </div>
    </spring-form:form>
</body>
</html>