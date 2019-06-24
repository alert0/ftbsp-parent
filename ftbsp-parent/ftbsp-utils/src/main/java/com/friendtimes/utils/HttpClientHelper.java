package com.friendtimes.utils;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class HttpClientHelper {
    private static final String APPLICATION_X_WWW_FORM_URLENCODED = ContentType.APPLICATION_FORM_URLENCODED.getMimeType();
    private static final String APPLICATION_JSON = ContentType.APPLICATION_JSON.getMimeType();
    private static final String CHARTSET = "UTF-8";
    private static final int CONNTIMEOUT = 60000;
    private static final int READTIMEOUT = 60000;
    private static final int MAX_RETRY = 3;
    private static HttpClientBuilder httpClientBuilder;
    private static CloseableHttpClient httpClient;

    static {
        SSLContext ctx = SSLContexts.createDefault();
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", plainsf).register("https", sslsf).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(1000);
        cm.setDefaultMaxPerRoute(30);
        cm.setValidateAfterInactivity(2000);
        httpClientBuilder = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(getCustomReqConfig(CONNTIMEOUT, READTIMEOUT));
        httpClient = httpClientBuilder.build();
    }

    public static String postFormData(String url, Map<String,String> params) throws IOException {
        return postForm(url, params, null, CONNTIMEOUT, READTIMEOUT);
    }

    public static String postForm(String url, Map<String, String> params, Map<String, String> headers, Integer connTimeout, Integer readTimeout) throws IOException {
        HttpPost post = new HttpPost(url);
        try {
            if (params != null && !params.isEmpty()) {
                ArrayList<NameValuePair> formParams = new ArrayList<>();
                Set<Map.Entry<String, String>> entrySet = params.entrySet();
                for (Map.Entry<String, String> entry : entrySet) {
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
                post.setEntity(entity);
            }
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    post.addHeader(entry.getKey(), entry.getValue());
                }
            }
            RequestConfig customReqConfig = getCustomReqConfig(connTimeout, readTimeout);
            post.setConfig(customReqConfig);
            HttpResponse response = httpClient.execute(post);
            return EntityUtils.toString(response.getEntity(), CHARTSET);
        } finally {
            post.releaseConnection();
        }
    }

    private static RequestConfig getCustomReqConfig(Integer connTimeout, Integer readTimeout) {
        RequestConfig.Builder customReqConf = RequestConfig.custom();
        if (connTimeout != null) {
            customReqConf.setConnectTimeout(connTimeout);
        }
        if (readTimeout != null) {
            customReqConf.setSocketTimeout(readTimeout);
        }
        return customReqConf.build();
    }
}
