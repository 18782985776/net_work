package com.jobnew.farm.data.downLoad;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by wangwenlang on 2017/7/5.
 * title:
 * note:
 */

public interface NongDownApi {
    @GET("/mobilesafe/shouji360/360safesis/360MobileSafe_6.2.3.1060.apk")
    Call<ResponseBody> retrofitDownload();
}
