package com.web.config.util;

import com.bitkrx.core.util.HttpComLib;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class HttpUtil {


    CloseableHttpClient httpClient;
    PoolingHttpClientConnectionManager connManager;

    public String sendApi(String target, Object param) {
        Map map = null;
        String resultStr = "";
        HttpResponse response = null;
        if (httpClient == null) {
            connManager = new PoolingHttpClientConnectionManager();
            connManager.setDefaultMaxPerRoute(100);
            connManager.setMaxTotal(2000);
            httpClient = HttpClients.custom().setConnectionManager(connManager).build();
        }

        int timeout = 5 * 1000;
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).build();
        // .setSocketTimeout(timeout)
        HttpPost httpPost = new HttpPost(target);
        httpPost.setConfig(requestConfig);
        try {
            if (param != null) {
                List<NameValuePair> nvps = new ArrayList();
                if (param != null) {
                    if (param instanceof Map) {
                        map = (Map) param;
                    } else {
                        map = HttpComLib.objectToMap(param);
                    }

                    Iterator iter = map.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry<String, Object> paramMap = (Map.Entry) iter.next();
                        String key = (String) paramMap.getKey();
                        String value = (String) map.get(key);
                        nvps.add(new BasicNameValuePair(key, value));
                    }
                }

                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            }
            response = httpClient.execute(httpPost);
            resultStr = EntityUtils.toString(response.getEntity());
            HttpEntity entity = response.getEntity();
            EntityUtils.consumeQuietly(entity);
        } catch (Exception e) {
            e.printStackTrace();

            HashMap<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("resultStrCode", "-1662");
            errorMap.put("resultMsg", "접속량이 많아 서버통신을 하지 못했습니다. 잠시 후 다시 시도해주시기 바랍니다.");

        } finally {
            try {
                // httpClient.close();
                httpPost.releaseConnection();
                if (response != null) {
                    ((CloseableHttpResponse) response).close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultStr;
    }
}
