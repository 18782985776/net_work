package com.jobnew.farm.data.downLoad;


/**
 * Created by wangwenlang on 2017/7/5.
 * title:
 * note:
 */


public class ProgressBean {
    // onProgress(progressBean.getBytesRead(),progressBean.getContentLength(),progressBean.isDone());
    boolean isDone;
    long bytesRead;
    long contentLength;


    public ProgressBean() {

    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public long getBytesRead() {
        return bytesRead;
    }

    public void setBytesRead(long bytesRead) {
        this.bytesRead = bytesRead;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLentth) {
        this.contentLength = contentLentth;
    }
}
