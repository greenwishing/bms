//index.js
var app = getApp();
Page({
  data: {
    showPage: false
  },
  onLoad: function () {
    var self = this;
    app.getUserInfo(function (userInfo) {
      if (!app.cfg.userGuid) {
        wx.navigateTo({
          url: '/pages/login/login',
        })
      } else {
        self.setData({showPage: true});
      }
    }, this);
  }
})
