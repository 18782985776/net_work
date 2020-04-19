package com.jobnew.farm.data.downLoad;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by wangwenlang on 2017/7/6.
 * title:
 * note:
 */

public interface DownloadApi {
    @GET
    Call<ResponseBody> retrofitDownload(@Url String url);

    @GET
    Call<ResponseBody> retrofitDownloadImg(@Url String url);
}

