package cn.greenwishing.bms.api.weixin.weapp;

import cn.greenwishing.bms.api.BaseBmsRequest;
import cn.greenwishing.bms.api.BaseBmsResponse;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wufan
 * @date 2018/6/9
 */
public class TemplateMessageSendRequest extends BaseBmsRequest<BaseBmsResponse> {

    /**
     * 接收者（用户）的 openid
     */
    @SerializedName("touser")
    private String openId;
    /**
     * 所需下发的模板消息的id
     */
    @SerializedName("template_id")
    private String templateId;
    /**
     * 点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
     */
    @SerializedName("page")
    /**
     * 表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id
     */
    private String page;
    /**
     * 模板内容，不填则下发空模板
     */
    @SerializedName("form_id")
    private String formId;
    /**
     * 模板内容，不填则下发空模板
     */
    @SerializedName("data")
    private Map<String, String> data = new HashMap<>();
    /**
     * 模板内容字体的颜色，不填默认黑色 【废弃】
     */
    @SerializedName("color")
    private String color;
    /**
     * 模板需要放大的关键词，不填则默认无放大
     */
    @SerializedName("emphasis_keyword")
    private String emphasisKeyword;

    public TemplateMessageSendRequest(String openId, Template template, Map<String, String> fields, String formId) {
        this.openId = openId;
        this.templateId = template.getTemplateId();
        this.page = template.getPage();
        this.formId = formId;
        this.data.putAll(fields);
    }

    public TemplateMessageSendRequest setColor(String color) {
        this.color = color;
        return this;
    }

    public TemplateMessageSendRequest setEmphasisKeyword(String emphasisKeyword) {
        this.emphasisKeyword = emphasisKeyword;
        return this;
    }

    @Override
    protected String getRequestUrl() {
        String accessToken = AccessTokenManager.get();
        return "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + accessToken;
    }

    @Override
    protected Class<BaseBmsResponse> getResponseClass() {
        return BaseBmsResponse.class;
    }
}
