// pages/login/login.js
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
   * 老用户登录
   */
  login: function(e) {
    var detail = e.detail;
    var value = detail.value;
    if (_.isEmpty(value.account)) {
      _.showTopTips(this, '请输入帐号');
      return;
    }
    var data = value;
    data.openid = app.openid;
    _.ajax({
      url: '/api/weixin/weapp/login',
      data: data,
      success: function(result) {
        app.userGuid = result.userGuid;
        wx.navigateTo({
          url: '/pages/index/index',
        })
      }
    });
  },
  /**
   * 新用户，使用小程序获取的信息创建用户
   */
  fastRegister: function () {
    var confirm = function () {
      var data = app.userInfo;
      data.openid = app.openid;
      _.ajax({
        url: '/api/weixin/weapp/fastRegister',
        data: data,
        success: function (result) {
          app.userGuid = result.userGuid;
          wx.navigateTo({
            url: '/pages/index/index',
          })
        }
      });
    };
    wx.showModal({
      title: '请问',
      content: '您是否在此平台有帐号？',
      confirmText: '没有',
      cancelText: '有',
      success: function (res) {
        if (res.confirm) {
          confirm();
        }
      }
    });
  }
})