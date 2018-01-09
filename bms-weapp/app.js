// app.js
var util = require('utils/util.js');
App({
  onLaunch: function () {
    // app init
  },
  getUserInfo: function (callback, context) {
    if (this.cfg.userInfo){
      if (typeof callback === "function") {
        callback.apply(context || this, [this.cfg.userInfo]);
      }
    } else {
      // 调用登录接口
      var that = this;
      wx.login({
        success: function (res) {
          let code = res.code;
          wx.getUserInfo({
            success: function (res) {
              var userInfo = res.userInfo;
              that.cfg.userInfo = userInfo;
              userInfo.code = code;
              that.updateUserInfo(userInfo, callback, context);
            }
          });
        }
      })
    }
  },
  updateUserInfo: function (data, callback, context) {
    util.ajax({
      url: '/api/weixin/weapp/user',
      data: data,
      success: function (result) {
        this.cfg.openid = result.openid;
        this.cfg.userGuid = result.userGuid;
        if (typeof callback === "function") {
          callback.apply(context || this, [data]);
        }
      }
    }, this);
  },
  util: util,
  cfg:{
    openid: null,
    userInfo: null,
    userGuid: null
  }
})