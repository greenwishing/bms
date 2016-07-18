<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<html>
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
            var content = WF.editor.getContent('article_content');
            $('#content').val(content);
            WF.form.ajaxSubmit($('#data-form'))
        }
    </script>
</head>
<body>
<div class="weui_tab">
    <div class="weui_tab_bd">
        <spring-form:form id="data-form" cssClass="form-horizontal" commandName="articleDTO" method="post" onsubmit="return false;">
            <div class="weui_cells_title">基本信息</div>
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell">
                    <div class="weui_cell_hd"><label class="weui_label">标题</label></div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <spring-form:input cssClass="weui_input" path="title" id="title" placeholder="标题"/>
                    </div>
                </div>
                <div class="weui_cell weui_cell_select weui_select_after">
                    <div class="weui_cell_hd">
                        <label class="weui_label">分类</label>
                    </div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <spring-form:select cssClass="weui_select" id="categoryGuid" path="categoryGuid" items="${categoryDTOs}" itemValue="guid" itemLabel="name"/>
                    </div>
                </div>
            </div>
            <div class="weui_cells_title">内容</div>
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell">
                    <div class="weui_cell_bd weui_cell_primary">
                        <script type="text/plain" id="article_content">${articleDTO.content}</script>
                        <spring-form:textarea cssClass="weui_textarea" id="content" path="content" cssStyle="display: none;"/>
                    </div>
                </div>
            </div>
        </spring-form:form>
    </div>
    <div class="weui_tabbar" style="z-index: 1000;">
        <a class="weui_tabbar_item" href="javascript:void(0)" onclick="articleFormSubmit()">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/images/icons/icon_save.png" alt="">
            </div>
            <p class="weui_tabbar_label">保存</p>
        </a>
        <a class="weui_tabbar_item" href="javascript:void(0)" onclick="history.back();">
            <div class="weui_tabbar_icon">
                <img src="${pageContext.request.contextPath}/images/icons/icon_back.png" alt="">
            </div>
            <p class="weui_tabbar_label">返回</p>
        </a>
    </div>
</div>
</body>
</html>