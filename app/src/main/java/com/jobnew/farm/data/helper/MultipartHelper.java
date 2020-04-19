package com.jobnew.farm.data.helper;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by wufengqiao on 2017/6/5.
 * function：
 * desc：
 */

public class MultipartHelper {
    /**
     * 将文件封装到MultiPart
     *
     * @param key
     * @return
     */
    public static MultipartBody.Part toPart(String key, File file) {
        //构建要上传的文件
        RequestBody requestFile = RequestBody
                .create(MediaType.parse("application/otcet-stream"), file);
        return MultipartBody.Part
                .createFormData(key, file.getName(), requestFile);
    }
}
