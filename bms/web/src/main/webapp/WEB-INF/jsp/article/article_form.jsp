<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>写文章</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <script type="text/javascript" src="/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="/js/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript">
        $(function(){
            WF.editor.init('article_content');
        });
        function articleFormSubmit() {
            WF.form.submit($('#data-form'), {first: function(){
                var content = WF.editor.getContent('article_content');
                $('#content').val(content);
            }});
        }
    </script>
</head>
<body>
<spring-form:form id="data-form" cssClass="form-horizontal" commandName="articleDTO" method="post" onsubmit="return false;">
    <spring-form:errors path="title" element="div" cssClass="alert alert-danger"/>
    <spring-form:errors path="content" element="div" cssClass="alert alert-danger"/>
    <div class="form-group">
        <label class="control-label col-sm-2" for="title">标题</label>
        <div class="col-sm-10">
            <spring-form:input cssClass="form-control" path="title" id="title" placeholder="标题"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="categoryGuid">分类</label>
        <div class="col-sm-10">
            <spring-form:select cssClass="form-control" id="categoryGuid" path="categoryGuid" items="${categoryDTOs}" itemValue="guid" itemLabel="name"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="title">内容</label>
        <div class="col-sm-10">
            <script type="text/plain" id="article_content">${articleDTO.content}</script>
            <spring-form:textarea cssClass="form-control" id="content" path="content" cssStyle="display: none;"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-10 col-sm-offset-2">
            <input type="button" class="btn btn-success" value="保存" onclick="articleFormSubmit();"/>
            <input type="button" class="btn btn-default" value="返回" onclick="WF.page.list()"/>
        </div>
    </div>
</spring-form:form>
</body>
</html>