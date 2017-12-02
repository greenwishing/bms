/**
 * @author Frank wu
 */

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
            WF.ajax.req({
                url: $(form).attr('action'),
                type: $(form).attr('method') || 'POST',
                data: $(form).serialize(),
                success: function(result) {
                    loading.hide();
                    if (result.success) {
                        if (result.tips) {
                            weui.topTips(result.tips, function(){
                                WF.ajax.successHandler(result, callback);
                            });
                        } else {
                            WF.ajax.successHandler(result, callback);
                        }
                    } else {
                        weui.alert(result.message);
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
        successHandler: function(result, callback) {
            if (callback && typeof callback === 'function') {
                callback(result);
            } else if (result.redirectUrl) {
                WF.page.forward(result.redirectUrl);
            } else if (result.back) {
                history.back();
            } else {
                location.reload();
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
