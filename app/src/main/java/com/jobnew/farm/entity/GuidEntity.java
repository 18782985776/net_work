package com.jobnew.farm.entity;

import java.util.List;

/**
 * Created by wangwenlang on 2017/7/4.
 * title:
 * note:
 */

public class GuidEntity {


    /**
     * id : 2
     * guideVersion : 1
     * majorVersion : 2
     * majorUrl : http://192.168.9.200/apk/app-debug.apk
     * osType : null
     * versionName : 高级半
     * forceUpdate : true
     * updateInfo : 修改了土地价格等
     * imgs : [{"id":5,"imgUrl":"http://192.168.9.200/images/guide/android/1.png","orderBy":0,"configId":null},{"id":6,"imgUrl":"http://192.168.9.200/images/guide/android/2.png","orderBy":0,"configId":null},{"id":7,"imgUrl":"http://192.168.9.200/images/guide/android/3.png","orderBy":0,"configId":null},{"id":8,"imgUrl":"http://192.168.9.200/images/guide/android/4.png","orderBy":0,"configId":null}]
     */

    private int id;
    private int guideVersion;
    private int majorVersion;
    private String majorUrl;
    private Object osType;
    private String versionName;
    private boolean forceUpdate;
    private String updateInfo;
    private List<ImgsBean> imgs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGuideVersion() {
        return guideVersion;
    }

    public void setGuideVersion(int guideVersion) {
        this.guideVersion = guideVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public String getMajorUrl() {
        return majorUrl;
    }

    public void setMajorUrl(String majorUrl) {
        this.majorUrl = majorUrl;
    }

    public Object getOsType() {
        return osType;
    }

    public void setOsType(Object osType) {
        this.osType = osType;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public boolean getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(String updateInfo) {
        this.updateInfo = updateInfo;
    }

    public List<ImgsBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImgsBean> imgs) {
        this.imgs = imgs;
    }

    public static class ImgsBean {
        /**
         * id : 5
         * imgUrl : http://192.168.9.200/images/guide/android/1.png
         * orderBy : 0
         * configId : null
         */

        private int id;
        private String imgUrl;
        private int orderBy;
        private Object configId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(int orderBy) {
            this.orderBy = orderBy;
        }

        public Object getConfigId() {
            return configId;
        }

        public void setConfigId(Object configId) {
            this.configId = configId;
        }
    }
}
