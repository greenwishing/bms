// pages/feedback/list.js
var app = getApp();
var _ = app.util;
Page({
  data: {
    types: {
      'ACCOUNT': '帐号',
      'FUNCTION': '功能',
      'UI': '界面',
      'UE': '体验',
      'OTHER': '其它'
    },
    paging: null
  },
  onReady: function () {
    this.queryFeedback();
  },
  queryFeedback: function (data) {
    if (!data) data = { currentPage: 1 };
    data.userGuid = app.userGuid;
    _.ajax({
      url: 'feedbackdata',
      data: data,
      success: function (result) {
        this.renderPaging(result.paging);
      },
      loadingText: '加载中'
    }, this);
  },
  renderPaging: function (paging) {
    this.setData({
      paging: paging
    });
  },
  previousPage: function () {
    var paging = this.data.paging;
    if (paging.hasPreviousPage) {
      var data = { currentPage: paging.currentPage - 1 };
      var key = this.data.searchText;
      if (key && key.length) {
        data.key = key;
      }
      this.queryFeedback(data);
    }
  },
  nextPage: function () {
    var paging = this.data.paging;
    if (paging.hasNextPage) {
      var data = { currentPage: paging.currentPage + 1 };
      var key = this.data.searchText;
      if (key && key.length) {
        data.key = key;
      }
      this.queryFeedback(data);
    }
  }
})