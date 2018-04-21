/**
 * @author Frank wu
 */
$(function(){
    $('[async-load="true"]').asyncLoader();
});

String.prototype.format = function (args) {
    var result = this;
    if (arguments.length > 0) {
        if (arguments.length === 1 && typeof args === "object") {
            for (var key in args) {
                if (args[key] !== undefined) {
                    result = result.replace(new RegExp("({" + key + "})", "g"), args[key]);
                }
            }
        }
        else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] !== undefined) {
                    result = result.replace(new RegExp("({)" + i + "(})", "g"), arguments[i]);
                }
            }
        }
    }
    return result;
};
String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "")
};
String.prototype.replaceAll = function (c, b, a) {
    if (!RegExp.prototype.isPrototypeOf(c)) {
        return this.replace(new RegExp(c, (a ? "gi" : "g")), b);
    } else {
        return this.replace(c, b);
    }
};
String.prototype.startsWith = function (a) {
    return (!WF.validation.isEmpty(a) || this.length || a.length <= this.length) && this.substring(0, a.length) === a;
};
String.prototype.endsWith = function (a) {
    return (!WF.validation.isEmpty(a) || this.length || a.length <= this.length) && this.substring(this.length - a.length) === a;
};
Date.prototype.add = function (a) {
    if ("days" in a) {
        return new Date(this.setDate(this.getDate() + a.days));
    } else {
        if ("month" in a) {
            return new Date(this.setMonth(this.getMonth() + a.month));
        } else {
            console.error("Date add/subtract support days|month ONLY.");
            return null;
        }
    }
};

var WF = {
    resources: {
        emptyOption: '<option value="">请选择</option>'
    },
    paging: {
        GO: function(form, page){
            var thisForm = $(form);
            var url = thisForm.attr('action');
            var searchUrl = url + ((url.indexOf("?") !== -1) ? "&" : "?") + "currentPage=" + page;
            WF.page.forward(searchUrl + '&' + thisForm.serialize());
        }
    },
    page: {
        menu: function($menu, requestURI) {
            $menu.find('li').each(function(){
                var $li = $(this);
                var src = $li.find('a').attr('href');
                if (src.startsWith(requestURI)) {
                    $li.addClass('active');
                    return false;
                }
            });
        },
        forward: function(url) {
            location.href = url;
        },
        list: function(params) {
            WF.page.forward('list' + (params ? params : ''));
        },
        reload: function(forceget) {
            location.reload(forceget || false);
        }
    },
    editor: {
        init: function(id) {
            var editor = new UE.ui.Editor();
            editor.render(id);
        },
        getContent: function(id) {
            return UE.getEditor(id).getContent();
        }
    },
    form: {
        submit: function(form, params) {
            if (params) {
                var firstFn = params.first;
                if (firstFn && typeof firstFn === 'function'){
                    firstFn();
                }
            }
            $(form)[0].submit();
        },
        ajaxSubmit: function(form, callback) {
            var loading = weui.loading('提交数据...');
            var $form = $(form);
            WF.ajax.req({
                url: $form.attr('action'),
                type: $form.attr('method') || 'POST',
                data: $form.serialize(),
                success: function(result) {
                    loading.hide();
                    if (result.success) {
                        if (result.tips) {
                            weui.topTips(result.tips, function(){
                                WF.ajax.successHandler($form, result, callback);
                            });
                        } else {
                            WF.ajax.successHandler($form, result, callback);
                        }
                    } else {
                        weui.topTips(result.message);
                    }
                },
                error: function () {
                    loading.hide();
                    WF.ajax.defaults.error.apply(this, arguments);
                }
            });
        }
    },
    util: {
        dateRangePicker: function($el, callback) {
            // required mobiscroll, momentjs
            var startSel = $el.data('start-input'), endSel = $el.data('end-input'),
                startInput = $(startSel), endInput = $(endSel),
                startValue = startInput.val(), endValue = endInput.val(),
                startDate = moment(startValue).toDate(), endDate = moment(endValue).toDate(),
                render = function(event, inst){
                    var range = inst.getVal();
                    if (!range || !range[0] || !range[1]) {
                        if (event && typeof callback === 'function') {
                            callback.apply(this, []);
                        }
                        $el.html($el.data('empty-text') || '日期');
                    } else {
                        var start = moment(range[0]), end = moment(range[1]), now = moment(), format;
                        startInput.val(start.format('YYYY-MM-DD'));
                        endInput.val(end.format('YYYY-MM-DD'));
                        if (event && typeof callback === 'function') {
                            callback.apply(this, [start, end, event, inst]);
                        }
                        var thisYear = start.year() === now.year() && end.year() === now.year();
                        var thisMonth = thisYear && start.month() === now.month() && end.month() === now.month();
                        if (thisYear) {
                            format = {start: 'M月D日'};
                            if (thisMonth) {
                                format.end = 'D日';
                            }
                        } else {
                            format = {start: 'YY年M月D日'}
                        }
                        if (!format.end) {
                            format.end = format.start;
                        }
                        $el.html(start.format(format.start) + '至' + end.format(format.end));
                    }
                };
            render(null, {
                getVal: function(){
                    return [startValue, endValue]
                }
            });
            $el.mobiscroll().range({
                lang: 'zh',
                startInput: startInput,
                endInput: endInput,
                defaultValue: [startDate, endDate],
                maxDate: new Date(),
                onSet: function() {
                    render.apply(this, arguments);
                }
            });
        },
        checkAll: function(field) {
            var all = $(field);
            var checkbox = $('input:checkbox[name="' + all.attr('data-name') + '"]');
            all.bind('click', function(){
                if (all.is(':checked')) {
                    checkbox.attr({'checked': 'checked'});
                } else {
                    checkbox.removeAttr('checked');
                }
            });
        },
        dropdown: function(callback){
            var $dropdown = $('.dropdown-toggle');
            var $parent = $dropdown.parent();
            var $menu = $parent.find('.dropdown-menu');
            $dropdown.bind('click', function(){
                toggleOpen();
            });
            $menu.delegate('li:not(.divider):visible', 'click', function(){
                toggleOpen();
                (typeof callback === 'function') && callback($(this));
            });
            $parent.delegate('.dropdown-backdrop', 'click', function(){
                toggleOpen();
            });

            function toggleOpen() {
                var isActive = $parent.hasClass('open');
                $parent.toggleClass('open', !isActive);
                if (isActive) {
                    $('.dropdown-backdrop').remove();
                } else {
                    $('<div class="dropdown-backdrop"/>').insertAfter($menu);
                }
            }
        },
        topTip: function(message, opts){
            weui.toast(message, opts);
        },
        closeDialog: function(el) {
            var $dialog = $(el).closest('.async-load-dialog');
            if ($dialog.length) {
                $dialog.data('asyncLoader').close();
            } else {
                location.reload();
            }
        }
    },
    ajax: {
        req: function(opts) {
            var params = $.extend({}, WF.ajax.defaults, opts || {});
            $.ajax(params);
        },
        doAjax: function(opts, ctx) {
            var loading = weui.loading('提交数据...');
            var customSuccess = opts.success;
            opts = $.extend(opts, {success: function(result){
                loading.hide();
                if (result.success) {
                    if (typeof customSuccess === 'function') {
                        customSuccess.apply(ctx || this, arguments);
                    } else if (result.redirectUrl) {
                        WF.page.forward(result.redirectUrl);
                    } else if (result.back) {
                        history.back();
                    } else {
                        location.reload();
                    }
                } else {
                    weui.alert(result.message);
                }
            }, error: function () {
                loading.hide();
                WF.ajax.defaults.error.apply(ctx || this, arguments);
            }});
            WF.ajax.req(opts);
        },
        defaults: {
            error:  function(x, s, e){
                weui.alert(e || s || x.status || '未知错误');
            }
        },
        successHandler: function($form, result, callback) {
            if (callback && typeof callback === 'function') {
                callback(result);
            } else if (result.redirectUrl) {
                WF.page.forward(result.redirectUrl);
            } else if (result.back) {
                history.back();
            } else {
                var $dialog = $form.closest('.async-load-dialog');
                if ($dialog.length) {
                    $dialog.data('asyncLoader').close();
                } else {
                    location.reload();
                }
            }
        }
    },
    validation: {
        isEmpty: function(val) {
            return null === val || "" === val;
        },
        isDate: function(val) {
            return /^\d{4}-\d{2}-\d{2}$/.test(val);
        }
    },
    billing: {
        categories: function (_this) {
            var billingType = (typeof _this === 'object') ? $(_this).val() : _this;
            var $target = $('#categoryGuid');
            var defaultValue = $target.attr('default-value');
            $target.html(WF.resources.emptyOption);

            if (WF.validation.isEmpty(billingType)) return;

            WF.ajax.req({
                type: 'post',
                url: 'categories',
                data: {type: billingType, dataType: 'json'},
                success: function (result) {
                    var categories = result.categories;
                    for (var i in categories) {
                        var category = categories[i];
                        var $option = $('<option>' + category.name + '</option>').attr({value: category.guid, 'data-id': category.id}).html(category.name);
                        $target.append($option);
                    }
                    if (!WF.validation.isEmpty(defaultValue)) {
                        WF.billing.defaultValue($target, defaultValue);
                        WF.billing.subcategories($target);
                    }
                }
            });
        },
        subcategories: function (_this) {
            var categoryGuid = (typeof _this === 'object') ? $(_this).val() : _this;
            var $target = $('#subcategoryGuid');
            var defaultValue = $target.attr('default-value');
            $target.html(WF.resources.emptyOption);

            if (WF.validation.isEmpty(categoryGuid)) return;

            WF.ajax.req({
                type: 'post',
                url: 'subcategories',
                data: {categoryGuid: categoryGuid, dataType: 'json'},
                success: function (result) {
                    var subcategories = result.subcategories;
                    for (var i in subcategories) {
                        var subcategory = subcategories[i];
                        var $option = $('<option>' + subcategory.name + '</option>').attr({value: subcategory.guid, 'data-id': subcategory.id}).html(subcategory.name);
                        $target.append($option);
                    }
                    WF.billing.defaultValue($target, defaultValue);
                }
            });
        },
        changeStatus: function(guid, status) {
            weui.confirm('确定要执行该操作？', function(){
                WF.ajax.req({
                    type: 'post',
                    url: 'status',
                    data: {guid: guid, status: status},
                    success: function(result) {
                        WF.page.reload();
                    }
                });
            });
        },
        defaultValue: function($select, defaultValue) {
            var $option = $select.find('option[data-id=' + defaultValue + ']');
            if ($option.length) {
                $select.val($option.val());
            } else {
                $select.val(defaultValue);
            }
        }
    },
    article: {},
    user: {}
};

'use strict';
(function($){
    function AsyncLoader($el, options) {
        this.$el = $el;
        this.options = $.extend($.fn.asyncLoader.defaults, options || {});
        this.$dialog = null;
        this.$content = null;
        this.create();
    }
    $.extend(AsyncLoader.prototype, {
        create: function() {
            var self = this;
            this.$el.bind('click', function(e) {
                e.preventDefault();
                e.stopPropagation();
                self.show();
            });
        },
        show: function(){
            var dialog = weui.dialog({
                title: '&nbsp;',
                content: '',
                className: 'async-load-dialog',
                buttons: []
            });
            this.$dialog = $(dialog);
            this.$content = this.$dialog.find('.weui-dialog__bd');
            this.$dialog.data('asyncLoader', this);
            this.bindClose();
            this.load();
        },
        load: function() {
            this.$content.html('<div class="weui-loading"></div>');
            var url = this.$el.attr(this.options.urlAttr);
            var self = this;
            WF.ajax.req({
                url: url,
                success: function(result){
                    self.render(result);
                }
            })
        },
        render: function(content) {
            this.$content.html(content);
            var $title = this.$content.find('title');
            if ($title.length) {
                this.$dialog.find('.weui-dialog__title').html($title.html());
            }
        },
        bindClose: function() {
            var self = this, $dialog = this.$dialog;
            var $close = $('<a href="javascript:void(0)" class="weui-dialog__close">&times</a>');
            $close.bind('click', function(){
                self.close();
            });
            this.$dialog.find('.weui-dialog__hd').append($close);
        },
        close: function(callback) {
            this.$dialog.hide(function () {
                if (typeof callback === 'function') {
                    callback();
                } else {
                    location.reload();
                }
            });
        }
    });
    var old = $.fn.asyncLoader;
    $.fn.asyncLoader = function (options) {
        return this.each(function () {
            var $el = $(this), data = $el.data('asyncLoader');
            if (!data) $el.data('asyncLoader', (data = new AsyncLoader($el, options)))
        });
    };
    $.fn.asyncLoader.Constructor = AsyncLoader;
    $.fn.asyncLoader.noConflict = function () {$.fn.asyncLoader = old;return this;};
    $.fn.asyncLoader.defaults = {
        urlAttr: 'href'
    };
})(jQuery);