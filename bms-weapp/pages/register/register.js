// pages/register/register.js
var app = getApp();
var _ = app.util;
Page({
  data: {
    showTopTips: false,
    topTipsText: ''
  },
  onLoad: function (options) {

  },
  /**
   * 注册新用户
   */
  register: function (e) {
    var detail = e.detail;
    var value = detail.value;
    if (_.isEmpty(value.account)) {
      _.showTopTips(this, '请设置登录帐号');
      return;
    }
    if (_.isEmpty(value.password)) {
      _.showTopTips(this, '请设置密码');
      return;
    }
    if (value.password !== value.retypePassword) {
      _.showTopTips(this, '请保证两次密码输入一致');
      return;
    }
    var data = value;
    data.openid = app.cfg.openid;
    _.ajax({
      url: 'register',
      data: data,
      success: function (result) {
        app.cfg.userGuid = result.userGuid;
        wx.navigateTo({
          url: '/pages/index/index',
        })
      }
    });
  }
})