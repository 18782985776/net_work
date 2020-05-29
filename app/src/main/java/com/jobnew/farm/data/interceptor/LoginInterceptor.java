package com.jobnew.farm.data.interceptor;

import android.text.TextUtils;
import android.util.Log;

import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.utils.SignUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by wufenqiao on 2017/5/26.
 * Function:
 */

public class LoginInterceptor implements Interceptor {
    boolean isLogin;
    public static final String TAG = "llll_";
    private String tag;
    private boolean showResponse = true;
    private boolean isLogger;

    public LoginInterceptor(boolean checkLogin){
        this(checkLogin,false,TAG);
    }
    public LoginInterceptor(boolean checkLogin,Boolean isLogger,String tag){
        this.isLogin = checkLogin;
        this.isLogger = isLogger;
        if (TextUtils.isEmpty(tag)){
            this.tag = TAG;
        }else {
            this.tag = tag;
        }
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder();
        HashMap<String, String> hashMap = new HashMap<>();
        String time = String.valueOf(System.currentTimeMillis());
        //请求体定制：统一添加token参数
        if(original.body() instanceof FormBody){
            FormBody.Builder newFormBody = new FormBody.Builder();
            FormBody oldFormBody = (FormBody) original.body();
            hashMap.put("deviceId",Constant.DEVICE_ID);
            newFormBody.add("deviceId", Constant.DEVICE_ID);
            for (int i = 0;i<oldFormBody.size();i++){
                String key = oldFormBody.name(i);
                String value = oldFormBody.value(i);
                hashMap.put(key,value);
                newFormBody.add(key,value);
            }
            if (isLogin) {
                hashMap.put("token", Constant.token);
                newFormBody.add("token", Constant.token);
            }
            hashMap.put("timestamp",time);
            newFormBody.add("timestamp", time);
            String sign = SignUtils.getSign(hashMap);
            newFormBody.add("sign", sign);
            requestBuilder.method(original.method(), newFormBody.build());
        }else if (original.body() instanceof MultipartBody){ //上传文件
            MultipartBody.Builder newMultBody = new MultipartBody.Builder();
            newMultBody.setType(MultipartBody.FORM);
            MultipartBody oldMultBody = (MultipartBody) original.body();
            for (int i = 0; i < oldMultBody.size(); i++) {
                MultipartBody.Part part = oldMultBody.part(i);
                newMultBody.addPart(part);
            }
            HttpUrl url = original.url();
            List<String> list = new ArrayList<>();
            for (int i = 0; i < url.querySize(); i++) {
                String name = url.queryParameterName(i);
                String value = url.queryParameterValue(i);
                hashMap.put(name,value);
                list.add(name);
                newMultBody.addFormDataPart(name, value);
            }
            HttpUrl.Builder newUrlBuilder = url.newBuilder();
            for (int i = 0; i < list.size(); i++) {
                newUrlBuilder.removeAllQueryParameters(list.get(i));
            }
            if (isLogin) {
                hashMap.put("token", Constant.token);
                newMultBody.addFormDataPart("token", Constant.token);
            }
            hashMap.put("deviceId",Constant.DEVICE_ID);
            newMultBody.addFormDataPart("deviceId",Constant.DEVICE_ID);

            hashMap.put("timestamp",time);
            newMultBody.addFormDataPart("timestamp",time);

            String sign = SignUtils.getSign(hashMap);
            newMultBody.addFormDataPart("sign",sign);

            requestBuilder.url(newUrlBuilder.build());
            requestBuilder.method(original.method(), newMultBody.build());
        }

        Request request = requestBuilder.build();

        //打印日志
        if (isLogger) {
            logForRequest(request);
        }
        Response response = chain.proceed(request);
        return logForResponse(response);
    }


    private Response logForResponse(Response response) {
        try {
            //===>response log
            Log.e(tag, "========response'log=======");
            Response.Builder builder = response.newBuilder();
            Response clone = builder.build();
            Log.e(tag, "code : " + clone.code());
            if (!TextUtils.isEmpty(clone.message()))
                Log.e(tag, "message : " + clone.message());

            if (showResponse) {
                ResponseBody body = clone.body();
                if (body != null) {
                    MediaType mediaType = body.contentType();
                    if (mediaType != null) {
                        Log.e(tag, "responseBody's contentType : " + mediaType.toString());
                        if (isText(mediaType)) {
                            String resp = body.string();
                            Log.e(tag, "responseBody's content : " + resp);

                            body = ResponseBody.create(mediaType, resp);
                            Log.e(tag, "========response'log=======end");
                            return response.newBuilder().body(body).build();
                        } else {
                            Log.e(tag, "responseBody's content : maybe [file part] , too large too print , ignored!");
                        }
                    }
                }
            }

            Log.e(tag, "========response'log=======end");
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return response;
    }

    private void logForRequest(Request request) {
        try {
            String url = request.url().toString();
            Headers headers = request.headers();

            Log.e(tag, "________request'log________");
            Log.e(tag, "method : " + request.method());
            Log.e(tag, "url : " + url);
            if (headers != null && headers.size() > 0) {
                Log.e(tag, "headers : " + headers.toString());
            }
            RequestBody requestBody = request.body();
            if (requestBody != null) {
                MediaType mediaType = requestBody.contentType();
                if (mediaType != null) {
                    Log.e(tag, "requestBody's contentType : " + mediaType.toString());
                    if (isText(mediaType)) {
                        Log.e(tag, "requestBody's content : " + bodyToString(request));
                    } else {
                        Log.e(tag, "requestBody's content : maybe [file part] , too large too print , ignored!");
                    }
                }
            }
            Log.e(tag, "________request'log________end");
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
    private boolean isText(MediaType mediaType) {
        if (mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        if (mediaType.subtype() != null) {
            if (mediaType.subtype().equals("json") ||
                    mediaType.subtype().equals("xml") ||
                    mediaType.subtype().equals("html") ||
                    mediaType.subtype().equals("webviewhtml") ||
                    mediaType.subtype().equals("x-www-form-urlencoded")
                    )
                return true;
        }
        return false;
    }
    private String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "something error when show requestBody.";
        }
    }
}
