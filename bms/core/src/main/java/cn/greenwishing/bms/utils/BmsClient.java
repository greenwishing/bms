package cn.greenwishing.bms.utils;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Wufan
 * @date 2017/10/22
 */
public class BmsClient {

    public static String doGet(String url) {
        return doRequest(new HttpGet(url));
    }

    public static <T extends HttpRequestBase> String doRequest(T request) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        try {
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            return IOUtils.toString(content, "utf-8");
        } catch (IOException e) {
            throw new RuntimeException("request to " + request.toString() + " error", e);
        }
    }
}
