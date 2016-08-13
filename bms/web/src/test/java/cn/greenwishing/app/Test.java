package cn.greenwishing.app;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.InputStream;

/**
 * @author Wufan
 * @date 2016/8/13
 */
public class Test {

    public static final String TOKEN_URL = "http://localhost:8080/oauth/token?client_id=%s&client_secret=%s&grant_type=client_credentials";

    public static void main(String[] args) {
        String appId = "test888888";
        String appSecret = "d25f622a6f0c4d34a27ac2864f9bb091";

        String requestUrl = String.format(TOKEN_URL, appId, appSecret);

        System.out.println("请求地址：" + requestUrl);
        HttpPost request = new HttpPost(requestUrl);
        CloseableHttpClient client = HttpClientBuilder.create().build();
        try {
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            String responseString = IOUtils.toString(is);
            System.out.println("返回结果：" + responseString);
        } catch (Exception e) {
            System.out.println("出错了：" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
