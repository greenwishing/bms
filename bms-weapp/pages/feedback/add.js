// pages/feedback/add.js
var app = getApp();
var _ = app.util;
Page({
  data: {
    type: {
      data: null,
      list: [
        { value: 'ACCOUNT', label: '帐号' },
        { value: 'FUNCTION', label: '功能' },
        { value: 'UI', label: '界面' },
        { value: 'UE', label: '体验' },
        { value: 'OTHER', label: '其它' }
      ],
      index: 0
    },
  },
  onReady: function () {
    this.onTypeChange();
  },
  onTypeChange: function(e) {
    var type = this.data.type;
    type.index = e ? e.detail.value : 0;
    type.data = type.list[type.index];
    this.setData({
      type: type
    });
  },
  submitFeedback: function (e) {
    var data = this.data, form = e.detail.value, formId = e.detail.formId;
    var params = {
      userGuid: app.userGuid,
      content: form.content,
      formId: formId
    };
    var type = data.type.data;
    params.type = type.value;
    _.ajax({
      url: 'addfeedback',
      data: params,
      success: function (result) {
        wx.showToast({
          title: '您的反馈我们已收到，感谢您的支持！',
          success: function () {
            wx.redirectTo({ url: '/pages/feedback/list' });
          }
        });
      },
      loadingText: '保存中...'
    }, this);
  }
})