package cn.greenwishing.bms.api;

import cn.greenwishing.bms.utils.BmsClient;
import cn.greenwishing.bms.utils.GsonUtil;
import org.apache.log4j.Logger;

/**
 * @author Wufan
 * @date 2017/12/29
 */
public abstract class BaseBmsRequest<Response extends BaseBmsResponse> implements BmsRequest<Response> {
    protected static final Logger logger = Logger.getLogger(BaseBmsRequest.class);

    @Override
    public Response execute() {
        String requestUrl = getRequestUrl();
        logger.info("request: " + requestUrl);
        String responseText = BmsClient.doGet(requestUrl);
        logger.info("response: " + responseText);
        Class<Response> responseClass = getResponseClass();
        Response response = GsonUtil.fromJson(responseText, responseClass);
        if (response != null) {
            response.setBody(responseText);
        }
        return response;
    }

    /**
     * 请求地址
     * @return 请求地址
     */
    protected abstract String getRequestUrl();

    /**
     * 响应对象类
     * @return 响应对象类
     */
    protected abstract Class<Response> getResponseClass();
}
