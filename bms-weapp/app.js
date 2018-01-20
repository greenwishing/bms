// app.js
var _ = require('utils/util.js');
App({
  version: '1.0.0',
  util: _,
  openid: null,
  userInfo: null,
  userGuid: null,
  billingTypes: [],
  billingAccounts: [],
  articleCategories: [],

  onLaunch: function () {
    console.log('app launch')
  },
  checkUserLogin: function (callback, context) {
    this.getUserInfo(function (userInfo) {
      if (!this.userGuid) {
        wx.navigateTo({
          url: '/pages/login/login',
        });
        return;
      }
      this.checkUpdate(function(result){
        if (result.update) {
          this.syncAppData(callback, context);
        } else {
          callback.apply(context || this, arguments);
        }
      }, this);
    }.bind(this));
  },
  getUserInfo: function (callback, context) {
    if (this.userInfo){
      if (typeof callback === "function") {
        callback.apply(context || this, [this.userInfo]);
      }
    } else {
      var self = this;
      wx.login({
        success: function (res) {
          let code = res.code;
          wx.getUserInfo({
            success: function (res) {
              var userInfo = res.userInfo;
              self.userInfo = userInfo;
              userInfo.code = code;
              self.updateUserInfo(userInfo, callback, context);
            }
          });
        }
      })
    }
  },
  updateUserInfo: function (data, callback, context) {
    _.ajax({
      url: 'updateuser',
      data: data,
      success: function (result) {
        this.openid = result.openid;
        this.userGuid = result.userGuid;
        if (typeof callback === "function") {
          callback.apply(context || this, [data]);
        }
      }
    }, this);
  },
  checkUpdate: function(callback, context) {
    _.ajax({
      url: 'checkupdate',
      data: { version: '1.0.0' },
      success: function (result) {
        if (typeof callback === 'function') {
          callback.apply(context || this, arguments);
        }
      }
    }, this);
  },
  syncAppData: function (callback, context) {
    _.ajax({
      url: 'data',
      data: { userGuid: this.userGuid },
      success: function (result) {
        this.billingTypes = result.billingTypes;
        this.billingAccounts = result.billingAccounts;
        this.articleCategories = result.articleCategories;
        callback.apply(context || this, arguments);
      },
      loadingText: '正在同步数据'
    }, this);
  }
})