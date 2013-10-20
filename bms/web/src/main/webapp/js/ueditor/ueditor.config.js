/**
 *  UEditor 配置
 */

(function () {
    window.UEDITOR_CONFIG = {
        UEDITOR_HOME_URL: '/js/ueditor/',
        imageUrl: "",
        imagePath: "",
        imageFieldName: "",
        toolbars: [
            ["source", "|", "bold", "italic", "underline", "|",
                "fontsize", "forecolor", "backcolor", "|",
                "justifyleft", "justifycenter", "justifyright", "|",
                "lineheight", "insertunorderedlist", "insertorderedlist", "|",
                "inserttable", "link", "insertimage", "insertvideo", "|", "removeformat", "autotypeset" ]
        ],
        lang: "zh-cn",
        initialFrameWidth: 'auto',
        initialContent: ''
    };
})();
