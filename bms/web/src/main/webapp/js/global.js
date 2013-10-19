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
        }
    }
};
