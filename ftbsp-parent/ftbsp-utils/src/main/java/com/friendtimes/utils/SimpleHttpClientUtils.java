package com.friendtimes.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


public class SimpleHttpClientUtils {
    public static String sendRequest(String url, Map<String, String> map) throws IOException, ParseException {
        String body = "";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        ArrayList<NameValuePair> formParams = new ArrayList<>();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        post.setEntity(new UrlEncodedFormEntity(formParams, "utf-8"));

        System.out.println(url);
        System.out.println(formParams.toString());

        post.setHeader("Content-type", "application/x-www-form-urlencoded");

        CloseableHttpResponse res = client.execute(post);
        HttpEntity entity = res.getEntity();
        if (entity != null) {
            body = EntityUtils.toString(entity, "utf-8");
        }
        EntityUtils.consume(entity);
        res.close();
        return body;
    }
}
