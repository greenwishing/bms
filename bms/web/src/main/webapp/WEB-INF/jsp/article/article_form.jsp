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
            WF.form.submit($('#article_form'), {first: function(){
                var content = WF.editor.getContent('article_content');
                $('#content').val(content);
            }});
        }
    </script>
</head>
<body>
    <div class="form">
        <spring-form:form id="article_form" commandName="articleDTO" method="post" onsubmit="return false;">
            <div class="items">
                <div class="item">
                    <label>标题</label>
                    <spring-form:input path="title" cssClass="text"/>
                    <spring-form:errors path="title"/>
                </div>
                <div class="item editor">
                    <label>内容</label>
                    <div class="content">
                        <spring-form:textarea id="content" path="content" cssClass="textarea hidden"/>
                        <script type="text/plain" id="article_content">${articleDTO.content}</script>
                        <spring-form:errors path="content"/>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="item">
                    <label>分类</label>
                    <spring-form:select path="categoryGuid" items="${categoryDTOs}" itemValue="guid" itemLabel="name" cssClass="select"/>
                </div>
                <div class="item">
                    <label>&nbsp;</label>
                    <input type="button" value="保存" onclick="articleFormSubmit();"/>
                    <input type="button" value="返回" onclick="WF.page.list()"/>
                </div>
            </div>
        </spring-form:form>
    </div>
</body>
</html>