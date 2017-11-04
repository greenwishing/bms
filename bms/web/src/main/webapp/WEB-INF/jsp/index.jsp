<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<html>
<head>
  <title>首页</title>
</head>
<body>
<div class="weui-article">
  <h1>首页</h1>
</div>
<div class="weui-grids">
<security:authorize access="hasRole('ROLE_BILLING')">
  <a href="${pageContext.request.contextPath}/system/billing/overview" class="weui-grid">
    <div class="weui-grid__icon">
      <img src="${pageContext.request.contextPath}/images/icons/icon_line.png">
    </div>
    <p class="weui-grid__label">概览</p>
  </a>
  <a href="${pageContext.request.contextPath}/system/billing/main" class="weui-grid">
    <div class="weui-grid__icon">
      <img src="${pageContext.request.contextPath}/images/icons/icon_add.png">
    </div>
    <p class="weui-grid__label">记账</p>
  </a>
  <a href="${pageContext.request.contextPath}/system/billing/list" class="weui-grid">
    <div class="weui-grid__icon">
      <img src="${pageContext.request.contextPath}/images/icons/icon_billing.png">
    </div>
    <p class="weui-grid__label">账单</p>
  </a>
  <a href="${pageContext.request.contextPath}/system/billing/accounts" class="weui-grid">
    <div class="weui-grid__icon">
      <img src="${pageContext.request.contextPath}/images/icons/icon_account.png">
    </div>
    <p class="weui-grid__label">账户</p>
  </a>
  <a href="${pageContext.request.contextPath}/system/billing/categories" class="weui-grid">
    <div class="weui-grid__icon">
      <img src="${pageContext.request.contextPath}/images/icons/icon_category.png">
    </div>
    <p class="weui-grid__label">账单分类</p>
  </a>
</security:authorize>
<security:authorize access="hasRole('ROLE_MOMENT')">
  <a href="${pageContext.request.contextPath}/system/moment/list" class="weui-grid">
    <div class="weui-grid__icon">
      <img src="${pageContext.request.contextPath}/images/icons/icon_clock.png">
    </div>
    <p class="weui-grid__label">时间管理</p>
  </a>
</security:authorize>
<security:authorize access="hasRole('ROLE_TODO')">
  <a href="${pageContext.request.contextPath}/system/todo/list" class="weui-grid">
    <div class="weui-grid__icon">
      <img src="${pageContext.request.contextPath}/images/icons/icon_todo.png">
    </div>
    <p class="weui-grid__label">待办事项</p>
  </a>
</security:authorize>
<security:authorize access="hasRole('ROLE_ARTICLE')">
  <a href="${pageContext.request.contextPath}/system/article/list" class="weui-grid">
    <div class="weui-grid__icon">
      <img src="${pageContext.request.contextPath}/images/icons/icon_article.png">
    </div>
    <p class="weui-grid__label">文章</p>
  </a>
</security:authorize>
<security:authorize access="hasRole('ROLE_METRO')">
  <a href="${pageContext.request.contextPath}/system/metro/list" class="weui-grid">
    <div class="weui-grid__icon">
      <img src="${pageContext.request.contextPath}/images/icons/icon_metro.png">
    </div>
    <p class="weui-grid__label">地铁</p>
  </a>
</security:authorize>
<security:authorize access="hasRole('ROLE_USER')">
  <a href="${pageContext.request.contextPath}/system/user/list" class="weui-grid">
    <div class="weui-grid__icon">
      <img src="${pageContext.request.contextPath}/images/icons/icon_user.png">
    </div>
    <p class="weui-grid__label">用户</p>
  </a>
</security:authorize>
<security:authorize access="hasRole('ROLE_APP')">
  <a href="${pageContext.request.contextPath}/system/app/list" class="weui-grid">
    <div class="weui-grid__icon">
      <img src="${pageContext.request.contextPath}/images/icons/icon_app.png">
    </div>
    <p class="weui-grid__label">应用</p>
  </a>
</security:authorize>
<security:authorize access="hasRole('ROLE_CONFIGURATION')">
  <a href="${pageContext.request.contextPath}/system/configuration/list" class="weui-grid">
    <div class="weui-grid__icon">
      <img src="${pageContext.request.contextPath}/images/icons/icon_config.png">
    </div>
    <p class="weui-grid__label">系统配置</p>
  </a>
</security:authorize>
  <a href="${pageContext.request.contextPath}/system/logout" class="weui-grid">
    <div class="weui-grid__icon">
      <img src="${pageContext.request.contextPath}/images/icons/icon_back.png">
    </div>
    <p class="weui-grid__label">退出</p>
  </a>
</div>
</body>
</html>
