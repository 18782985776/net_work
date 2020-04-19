package com.jobnew.farm.entity;

import java.util.List;

/**
 * Created by wangwenlang on 2017/8/17.
 * title:
 * note:
 */

public class SourceEntity {

    /**
     * token : null
     * sign : null
     * timestamp : 0
     * deviceId : null
     * id : 20
     * sn : null
     * productName : 测试农产品
     * farmName : 有个农场
     * address : 为名路12号
     * farmManager : 某徐
     * province : 四川省
     * city : 成都市
     * area : 郫县
     * farmImgUrl : http://192.168.9.200/images/image/12191bc79e2f4dd4bfec0d6cde452ff6.jpg
     * sourceInfos : [{"id":1,"artProductId":null,"artProductName":"播种","managerId":null,"managerName":"老吴","createDate":1502988397000,"images":[{"id":null,"sourceInfoId":null,"imgUrl":"http://192.168.9.200/images/image/571bc676a5ac47469b90fc6feb8ce11f.jpg","orderBy":null}],"sourceId":null,"source":null}]
     */

    private Object token;
    private Object sign;
    private int timestamp;
    private Object deviceId;
    private int id;
    private String sn;
    private String productName;
    private String farmName;
    private String address;
    private String farmManager;
    private String province;
    private String city;
    private String area;
    private String farmImgUrl;
    private List<SourceInfosBean> sourceInfos;

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public Object getSign() {
        return sign;
    }

    public void setSign(Object sign) {
        this.sign = sign;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public Object getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Object deviceId) {
        this.deviceId = deviceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFarmManager() {
        return farmManager;
    }

    public void setFarmManager(String farmManager) {
        this.farmManager = farmManager;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFarmImgUrl() {
        return farmImgUrl;
    }

    public void setFarmImgUrl(String farmImgUrl) {
        this.farmImgUrl = farmImgUrl;
    }

    public List<SourceInfosBean> getSourceInfos() {
        return sourceInfos;
    }

    public void setSourceInfos(List<SourceInfosBean> sourceInfos) {
        this.sourceInfos = sourceInfos;
    }

    public static class SourceInfosBean {
        /**
         * id : 1
         * artProductId : null
         * artProductName : 播种
         * managerId : null
         * managerName : 老吴
         * createDate : 1502988397000
         * images : [{"id":null,"sourceInfoId":null,"imgUrl":"http://192.168.9.200/images/image/571bc676a5ac47469b90fc6feb8ce11f.jpg","orderBy":null}]
         * sourceId : null
         * source : null
         */

        private int id;
        private Object artProductId;
        private String artProductName;
        private Object managerId;
        private String managerName;
        private long createDate;
        private Object sourceId;
        private Object source;
        private List<ImagesBean> images;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getArtProductId() {
            return artProductId;
        }

        public void setArtProductId(Object artProductId) {
            this.artProductId = artProductId;
        }

        public String getArtProductName() {
            return artProductName;
        }

        public void setArtProductName(String artProductName) {
            this.artProductName = artProductName;
        }

        public Object getManagerId() {
            return managerId;
        }

        public void setManagerId(Object managerId) {
            this.managerId = managerId;
        }

        public String getManagerName() {
            return managerName;
        }

        public void setManagerName(String managerName) {
            this.managerName = managerName;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public Object getSourceId() {
            return sourceId;
        }

        public void setSourceId(Object sourceId) {
            this.sourceId = sourceId;
        }

        public Object getSource() {
            return source;
        }

        public void setSource(Object source) {
            this.source = source;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public static class ImagesBean {
            /**
             * id : null
             * sourceInfoId : null
             * imgUrl : http://192.168.9.200/images/image/571bc676a5ac47469b90fc6feb8ce11f.jpg
             * orderBy : null
             */

            private Object id;
            private Object sourceInfoId;
            private String imgUrl;
            private Object orderBy;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public Object getSourceInfoId() {
                return sourceInfoId;
            }

            public void setSourceInfoId(Object sourceInfoId) {
                this.sourceInfoId = sourceInfoId;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public Object getOrderBy() {
                return orderBy;
            }

            public void setOrderBy(Object orderBy) {
                this.orderBy = orderBy;
            }
        }
    }
}
