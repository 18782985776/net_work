package com.jobnew.farm.entity;

/**
 * Created by wc on 2017/6/6.
 * Function：
 * desc：
 */

public class UserPhotoBean {
    public UserPhotoBean(int id, String url, long createTime) {
        this.id = id;
        this.url = url;
        this.createTime = createTime;
    }

    /**
     * id : 3
     * url : photo/d98e8f4f12724bf188abfbf0b9867647.jpg
     * createTime : 1496732423000
     */

    private int id;
    private String url;
    private long createTime;

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public long getCreateTime() {
        return createTime;
    }
}
