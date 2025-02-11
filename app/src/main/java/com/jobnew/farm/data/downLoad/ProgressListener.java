package com.jobnew.farm.data.downLoad;

/**
 * Created by wangwenlang on 2017/7/5.
 * title:
 * note:
 */

public interface ProgressListener {
    /**
     * @param progress     已经下载或上传字节数
     * @param total        总字节数
     * @param done         是否完成
     */
    void onProgress(long progress, long total, boolean done);
}
