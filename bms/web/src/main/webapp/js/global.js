/**
 * @author Wu Fan
 */

var WF = {
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
        list: function() {
            WF.page.forward('list');
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
    }
};
