package com.jobnew.farm.data.helper;

import com.jobnew.farm.data.interceptor.LoggerInterceptor;
import com.jobnew.farm.data.interceptor.LoginInterceptor;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wufengqiao on 2017/5/21.
 * Function:
 * Desc:
 */
public class FarmOkHttpHelper {

    private static final int TIME_OUT = 15;
    private static final String TAG = "FARM";
//    private static final String HTTP_CONNECTION = "close";
//    private static final String HTTP_ACCEPT = "text/html;charset=UTF-8,application/json";
//    private static final String HTTP_CONTENT_TYPE = "application/x-www-form-urlencoded;charset=UTF-8";

    /**
     * 拼接免证书访问--降低安全性
     *
     * @return
     */
    public static OkHttpClient.Builder creator() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        try {
            addSSLContext(builder);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
       /* builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                String url = request.url().toString();
//                Log
                return chain.proceed(request);
            }
        });*/
        return builder;
    }


    /**
     * 添加SSL
     *
     * @param builder
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private static void addSSLContext(OkHttpClient.Builder builder) throws NoSuchAlgorithmException,
            KeyManagementException {
        final TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };

        final SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        // Create an ssl socket factory with our all-trusting manager
        final javax.net.ssl.SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

        builder.sslSocketFactory(sslSocketFactory);
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
    }

    /**
     * 创建OkHttp
     *
     * @return
     */
    public static OkHttpClient createLogin(boolean checkLogin) {
        return creator()
                .addInterceptor(new LoginInterceptor(checkLogin,true,"nongyinong——"))
                .build();
    }

    public static OkHttpClient createUpload() {
        return creator()
                .addInterceptor(new LoggerInterceptor(TAG,true))
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }
}
