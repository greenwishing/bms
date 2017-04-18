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
    <spring-form:form id="data-form" commandName="articleDTO" method="post" onsubmit="return false;">
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
        <div class="weui-cells__title">内容</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell__bd">
                <script type="text/plain" id="article_content">${articleDTO.content}</script>
                <spring-form:textarea cssClass="form-control" id="content" path="content" cssStyle="display: none;"/>
            </div>
        </div>
        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="javascript:void(0)" onclick="articleFormSubmit()">保存</a>
        </div>
    </spring-form:form>
</body>
</html>