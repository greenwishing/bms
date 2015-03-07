/**
 * @author Wu Fan
 */

var WF = {
    resources: {
        emptyOption: '<option value="">请选择</option>'
    },
    paging: {
        GO: function(form, page){
            var thisForm = $(form);
            var url = thisForm.attr('action');
            var searchUrl = url + ((url.indexOf("?") != -1) ? "&" : "?") + "currentPage=" + page;
            WF.page.forward(searchUrl + '&' + thisForm.serialize());
        }
    },
    page: {
        forward: function(url) {
            location.href = url;
        },
        list: function(params) {
            WF.page.forward('list' + (params ? params : ''));
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
                if (firstFn && typeof firstFn == 'function'){
                    firstFn();
                }
            }
            $(form)[0].submit();
        }
    },
    util: {
        dateFromToPicker: function(from, to) {
            var options = {language:  'zh-CN', format: 'yyyy-mm-dd', weekStart: 1, todayBtn: 1, autoclose: 1, todayHighlight: 1, startView: 2, minView: 2, forceParse: 0};
            $("#" + from).datetimepicker(options).on('changeDate', function(ev){
                $("#" + to).datetimepicker("setStartDate", ev.date);
            });
            $("#" + to).datetimepicker(options).on('changeDate', function(ev){
                $("#" + from).datetimepicker("setEndDate", ev.date);
            });
        }
    },
    ajax: {
        req: function(opts) {
            var params = $.extend({}, WF.ajax.defaults, opts || {});
            $.ajax(params);
        },
        defaults: {
            error:  function(x, s, e){
                var errorMsg = '';
                if (s == 'timeout') {
                    errorMsg = '连接超时';
                } else {
                    var errorCode = x.status;
                    if (errorCode == '401') {
                        errorMsg = '401 - 访问被拒绝';
                    } else if (errorCode == '403') {
                        errorMsg = '403 - 禁止访问';
                    } else if (errorCode == '404') {
                        errorMsg = '404 - 未找到';
                    } else if (errorCode == '500') {
                        errorMsg = '500 - 内部服务器错误';
                    } else if (errorCode == '12029') {
                        errorMsg = '无法建立HTTP连接';
                    } else {
                        errorMsg = e;
                    }
                }
                if (!WF.validation.isEmpty(errorMsg)) alert(errorMsg);
            }
        }
    },
    validation: {
        isEmpty: function(val) {
            return null == val || "" == val;
        }
    },
    billing: {
        categories: function (_this) {
            var select = $(_this);
            var target = $('#' + select.attr('targetId'));
            var defaultValue = target.attr('default-value');
            target.html(WF.resources.emptyOption);

            var billingType = select.val();
            if (WF.validation.isEmpty(billingType)) return;

            WF.ajax.req({
                type: 'post',
                url: '/system/billing/categories?type=' + billingType,
                success: function (result) {
                    var categories = result.categories;
                    for (var i in categories) {
                        var category = categories[i];
                        target.append('<option value="' + category.guid + '">' + category.name + '</option>');
                    }
                    if (!WF.validation.isEmpty(defaultValue)) {
                        target.val(defaultValue);
                        WF.billing.subcategories(target);
                    }
                }
            });
        },
        subcategories: function (_this) {
            var select = $(_this);
            var target = $('#' + select.attr('targetId'));
            var defaultValue = target.attr('default-value');
            target.html(WF.resources.emptyOption);

            var categoryGuid = select.val();
            if (WF.validation.isEmpty(categoryGuid)) return;

            WF.ajax.req({
                type: 'post',
                url: '/system/billing/subcategories?categoryGuid=' + categoryGuid,
                success: function (result) {
                    var subcategories = result.subcategories;
                    for (var i in subcategories) {
                        var subcategory = subcategories[i];
                        target.append('<option value="' + subcategory.guid + '">' + subcategory.name + '</option>');
                    }
                    target.val(defaultValue);
                }
            });
        },
        templates: function(box, callback) {
            var container = $(box);
            container.addClass('btn-group');
            WF.ajax.req({
                type: 'post',
                url: '/system/billing/templates',
                success: function(result) {
                    var templates = result.templates;
                    for (var i in templates) {
                        var template = templates[i];
                        var label = template.name + ' ' + template.categoryName + ' ' + template.subcategoryName + ' ' + template.amount;
                        var item = $('<button class="btn btn-success"></button>').html(label).attr(template);
                        container.append(item);
                    }
                    if (callback && typeof callback == 'function') {
                        container.find('button').bind('click', function(){
                            callback(this);
                        });
                    }
                }
            });
        }
    }
};
