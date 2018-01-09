
// const BASE_URL = 'https://greenwishing.cn';

const BASE_URL = 'http://localhost:8086';

function formatTime(date) {
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  var day = date.getDate()

  var hour = date.getHours()
  var minute = date.getMinutes()
  var second = date.getSeconds()


  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

function formatNumber(n) {
  n = n.toString()
  return n[1] ? n : '0' + n
}

function isEmpty(value) {
  return value === undefined || value === null || value === '' || value.length === 0
}

function showTopTips(page, options) {
  if (typeof options === 'string') {
    options = {text: options};
  }
  page.setData({ topTipsText: options.text || '', showTopTips: true });
  var instance = setTimeout(function () {
    page.setData({ topTipsText: '', showTopTips: false });
    clearTimeout(instance);
  }, options.duration || 1500);
}

function ajax(options, context) {
  let loading = false;
  if (options.loading) {
    loading = true;
    wx.showLoading({
      title: options.loadingText || '请稍后',
    });
  }
  var originUrl = options.url || '';
  options.url = originUrl.indexOf('/') === 0 ? BASE_URL + options.url : originUrl;
  var originSuccess = options.success;
  var success = function (res) {
    if (loading) {
      wx.hideLoading();
    }
    if (res.statusCode === 200) {
      let result = res.data;
      if (result.success) {
        if (typeof originSuccess === 'function') {
          originSuccess.apply(context || this, [result]);
        }
      } else {
        alert(result.message);
      }
    } else {
      alert(res.statusCode);
    }
  };
  options.success = success;
  options.fail = function (res) {
    if (loading) {
      wx.hideLoading();
    }
    alert(res.statusCode);
  };
  options.complete = function (res) {
    if (loading) {
      wx.hideLoading();
    }
  };
  wx.request(options);
}

function alert(text) {
  var content = typeof text === 'string' ? text : '' + text;
  wx.showModal({
    title: '',
    content: content,
    showCancel: false
  });
}

function confirm(text, ok) {
  var content = typeof text === 'string' ? text : '' + text;
  wx.showModal({
    title: '',
    content: content,
    success: function(res) {
      if (res.confirm) {
        if (typeof ok === 'function') {
          ok.apply(null, []);
        }
      }
    }
  });
}

module.exports = {
  formatTime: formatTime,
  ajax: ajax,
  isEmpty: isEmpty,
  showTopTips: showTopTips,
  alert: alert,
  confirm, confirm
}
