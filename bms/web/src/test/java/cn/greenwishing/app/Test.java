package cn.greenwishing.app;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
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
    public static final String DATA_URL = "http://localhost:8080/oauth/api/nearest?access_token=%s&size=7";

    public static void main(String[] args) {
        String appId = "test888888";
        String appSecret = "d25f622a6f0c4d34a27ac2864f9bb091";
        try {
            String requestUrl = String.format(TOKEN_URL, appId, appSecret);
            String tokenResponse = 发送请求(requestUrl);
            if (JSONUtils.mayBeJSON(tokenResponse)) {
                JSONObject tokenJson = JSONObject.fromObject(tokenResponse);
                if (tokenJson.has("access_token")) {
                    String accessToken = tokenJson.getString("access_token");
                    System.out.println("获取到Token：" + accessToken);
                    requestUrl = String.format(DATA_URL, accessToken);
                    发送请求(requestUrl);
                } else {
                    System.out.println("获取Token失败[" + tokenJson.get("error") + "]：" + tokenJson.get("error_description"));
                }
            }
        } catch (Exception e) {
            System.out.println("出错了：" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public static String 发送请求(String requestUrl) {
        HttpPost request = new HttpPost(requestUrl);
        CloseableHttpClient client = HttpClientBuilder.create().build();
        try {
            System.out.println("请求地址：" + requestUrl);
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            String responseString = IOUtils.toString(is);
            System.out.println("返回结果：" + responseString);
            return responseString;
        } catch (Exception e) {
            System.out.println("出错了：" + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return null;
    }
}
