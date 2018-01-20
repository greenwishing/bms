
const BASE_URL = 'https://greenwishing.cn';
// const BASE_URL = 'http://localhost:8086';
const URI_PREFIX = '/api/weixin/weapp/';

function formatDate(date) {
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  var day = date.getDate()


  return [year, month, day].map(formatNumber).join('-');
}

function formatTime(date) {
  var hour = date.getHours()
  var minute = date.getMinutes()
  return [hour, minute].map(formatNumber).join(':')
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
  if (options.loadingText) {
    loading = true;
    wx.showLoading({
      title: options.loadingText || '请稍后',
    });
  }
  var originUrl = options.url || '';
  options.url = originUrl.indexOf('http') === 0 ? originUrl : (BASE_URL + (originUrl.indexOf('/') === 0 ? '' : URI_PREFIX) + options.url);
  var originSuccess = options.success;
  var success = function (res) {
    if (loading) {
      wx.hideLoading();
    }
    var msg = res.errMsg;
    if (msg && 'request:ok' === msg.trim()) {
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
    } else {
      alert(msg);
    }
  };
  options.success = success;
  options.fail = function (res) {
    if (loading) {
      wx.hideLoading();
    }
    var msg = res.errMsg;
    if (msg) {
      alert('request:fail' === msg.trim() ? '请求失败，请检查您的网络' : msg);
    } else {
      alert(res.statusCode);
    }
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

function min(value1, value2) {
  return value1 < value2 ? value1: value2;
}

function max(value1, value2) {
  return value1 > value2 ? value1 : value2;
}

module.exports = {
  formatDate: formatDate,
  formatTime: formatTime,
  ajax: ajax,
  isEmpty: isEmpty,
  showTopTips: showTopTips,
  alert: alert,
  confirm, confirm,
  min: min,
  max: max
}
