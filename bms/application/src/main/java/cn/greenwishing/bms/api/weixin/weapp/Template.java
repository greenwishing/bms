package cn.greenwishing.bms.api.weixin.weapp;

/**
 * @author Wufan
 * @date 2018/6/9
 */
public enum Template {
    FEEDBACK("反馈成功通知", "GzwxObS_48DdHR9X8BB0lIl9wQp2uYyErvGiM6XIgYc", "/pages/feedback/list"),
    FEEDBACK_REPLY("反馈处理结果通知", "QxIzfiUxNWpTx6FkIQjeJJlFTfkCy20hmMAuYoeKxrs", "/pages/feedback/list"),
    ;

    private String label;
    private String templateId;
    private String page;

    Template(String label, String templateId, String page) {
        this.label = label;
        this.templateId = templateId;
        this.page = page;
    }

    public String getLabel() {
        return label;
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getPage() {
        return page;
    }
}
