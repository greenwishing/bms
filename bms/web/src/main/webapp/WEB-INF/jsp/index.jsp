<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<div class="weui-article">
  <h1>首页</h1>
</div>
<div class="weui-grids">
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
      <img src="${pageContext.request.contextPath}/images/icons/icon_list.png">
    </div>
    <p class="weui-grid__label">账单</p>
  </a>
  <a href="${pageContext.request.contextPath}/system/billing/accounts" class="weui-grid">
    <div class="weui-grid__icon">
      <img src="${pageContext.request.contextPath}/images/icons/icon_template.png">
    </div>
    <p class="weui-grid__label">账户</p>
  </a>
  <a href="${pageContext.request.contextPath}/system/billing/categories" class="weui-grid">
    <div class="weui-grid__icon">
      <img src="${pageContext.request.contextPath}/images/icons/icon_category.png">
    </div>
    <p class="weui-grid__label">分类</p>
  </a>
  <a href="${pageContext.request.contextPath}/system/article/list" class="weui-grid">
    <div class="weui-grid__icon">
      <img src="${pageContext.request.contextPath}/images/icons/icon_article.png">
    </div>
    <p class="weui-grid__label">文章</p>
  </a>
  <a href="${pageContext.request.contextPath}/system/metro/list" class="weui-grid">
    <div class="weui-grid__icon">
      <img src="${pageContext.request.contextPath}/images/icons/icon_metro.png">
    </div>
    <p class="weui-grid__label">地铁</p>
  </a>
  <a href="${pageContext.request.contextPath}/system/user/list" class="weui-grid">
    <div class="weui-grid__icon">
      <img src="${pageContext.request.contextPath}/images/icons/icon_user.png">
    </div>
    <p class="weui-grid__label">用户</p>
  </a>
  <a href="${pageContext.request.contextPath}/system/app/list" class="weui-grid">
    <div class="weui-grid__icon">
      <img src="${pageContext.request.contextPath}/images/icons/icon_app.png">
    </div>
    <p class="weui-grid__label">应用</p>
  </a>
  <a href="${pageContext.request.contextPath}/logout" class="weui-grid">
    <div class="weui-grid__icon">
      <img src="${pageContext.request.contextPath}/images/icons/icon_back.png">
    </div>
    <p class="weui-grid__label">退出</p>
  </a>
</div>
</body>
</html>
