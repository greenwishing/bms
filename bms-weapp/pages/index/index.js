//index.js
var app = getApp();
Page({
  data: {
    pageReady: false
  },
  onLoad: function () {
    wx.showLoading({
      title: '请稍后',
    });
    app.checkUserLogin(function () {
      wx.hideLoading();
      this.setData({
        pageReady: true
      })
    }, this);
  },
  onReady: function () {
  }
})
