package cn.greenwishing.bms.api;

/**
 * @author Wufan
 * @date 2017/12/29
 */
public interface BmsRequest<Response extends BmsResponse> {

    /**
     * 执行请求
     * @return 响应结果
     */
    Response execute();
}
