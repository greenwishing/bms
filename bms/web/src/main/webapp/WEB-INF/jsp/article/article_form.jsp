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
        <div class="form-group">
            <label class="form-control-static">标题</label>
            <spring-form:input cssClass="form-control" path="title" id="title" placeholder="标题"/>
        </div>
        <div class="form-group">
            <label class="form-control-static">分类</label>
            <spring-form:select cssClass="form-control" id="categoryGuid" path="categoryGuid" items="${categoryDTOs}" itemValue="guid" itemLabel="name"/>
        </div>
        <div class="form-group">
            <label class="form-control-static">内容</label>
            <script type="text/plain" id="article_content">${articleDTO.content}</script>
            <spring-form:textarea cssClass="form-control" id="content" path="content" cssStyle="display: none;"/>
        </div>
        <div class="form-group">
            <a class="btn btn-primary" href="javascript:void(0)" onclick="articleFormSubmit()">保存</a>
            <a class="btn btn-default" href="javascript:void(0)" onclick="history.back();">返回</a>
        </div>
    </spring-form:form>
</body>
</html>