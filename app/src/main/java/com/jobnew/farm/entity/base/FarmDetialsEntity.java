package com.jobnew.farm.entity.base;

import java.util.List;

/**
 * Created by wangwenlang on 2017/6/7.
 * title:
 * note:
 */

public class FarmDetialsEntity {

    /**
     * farm : {"id":28,"userId":22,"name":"啊","address":"啊","images":[{"id":28,"imgUrl":"http://222.88.94.204:5080/images/image/6d7e884163904481aba68bd3eb8abc1d.jpg","type":1,"farmId":28,"orderBy":0}],"introduce":null,"detail":null,"auditState":"1","state":"1","auditTime":null,"auditOpinion":null,"saleCount":0,"commentCount":0,"province":"四川省","city":"成都市","area":"东城区","longitude":"116.42188470126446","latitude":"39.93857401298612","grade":0,"phone":"123","farmBusinessList":[{"id":24,"farmId":28,"categoryId":1,"categoryName":"种植","type":1,"icon":"http://222.88.94.204:5080/images/icon/4.png","orderBy":0},{"id":25,"farmId":28,"categoryId":3,"categoryName":"集市","type":0,"icon":"icon1.png","orderBy":1}],"manager":"啊","img":null,"icon":null,"landStock":0,"distance":null,"del":false}
     */

    private FarmBean farm;

    public FarmBean getFarm() {
        return farm;
    }

    public void setFarm(FarmBean farm) {
        this.farm = farm;
    }

    public static class FarmBean {
        /**
         * id : 28
         * userId : 22
         * name : 啊
         * address : 啊
         * images : [{"id":28,"imgUrl":"http://222.88.94.204:5080/images/image/6d7e884163904481aba68bd3eb8abc1d.jpg","type":1,"farmId":28,"orderBy":0}]
         * introduce : null
         * detail : null
         * auditState : 1
         * state : 1
         * auditTime : null
         * auditOpinion : null
         * saleCount : 0
         * commentCount : 0
         * province : 四川省
         * city : 成都市
         * area : 东城区
         * longitude : 116.42188470126446
         * latitude : 39.93857401298612
         * grade : 0.0
         * phone : 123
         * farmBusinessList : [{"id":24,"farmId":28,"categoryId":1,"categoryName":"种植","type":1,"icon":"http://222.88.94.204:5080/images/icon/4.png","orderBy":0},{"id":25,"farmId":28,"categoryId":3,"categoryName":"集市","type":0,"icon":"icon1.png","orderBy":1}]
         * manager : 啊
         * img : null
         * icon : null
         * landStock : 0
         * distance : null
         * del : false
         */

        private int id;
        private int userId;
        private String name;
        private String address;
        private Object introduce;
        private Object detail;
        private String auditState;
        private String state;
        private Object auditTime;
        private Object auditOpinion;
        private int saleCount;
        private int commentCount;
        private String province;
        private String city;
        private String area;
        private String longitude;
        private String latitude;
        private double grade;
        private String phone;
        private String manager;
        private Object img;
        private Object icon;
        private int landStock;
        private double distance;
        private boolean del;
        private List<ImagesBean> images;
        private List<FarmBusinessListBean> farmBusinessList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getIntroduce() {
            return introduce;
        }

        public void setIntroduce(Object introduce) {
            this.introduce = introduce;
        }

        public Object getDetail() {
            return detail;
        }

        public void setDetail(Object detail) {
            this.detail = detail;
        }

        public String getAuditState() {
            return auditState;
        }

        public void setAuditState(String auditState) {
            this.auditState = auditState;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Object getAuditTime() {
            return auditTime;
        }

        public void setAuditTime(Object auditTime) {
            this.auditTime = auditTime;
        }

        public Object getAuditOpinion() {
            return auditOpinion;
        }

        public void setAuditOpinion(Object auditOpinion) {
            this.auditOpinion = auditOpinion;
        }

        public int getSaleCount() {
            return saleCount;
        }

        public void setSaleCount(int saleCount) {
            this.saleCount = saleCount;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
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

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public double getGrade() {
            return grade;
        }

        public void setGrade(double grade) {
            this.grade = grade;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getManager() {
            return manager;
        }

        public void setManager(String manager) {
            this.manager = manager;
        }

        public Object getImg() {
            return img;
        }

        public void setImg(Object img) {
            this.img = img;
        }

        public Object getIcon() {
            return icon;
        }

        public void setIcon(Object icon) {
            this.icon = icon;
        }

        public int getLandStock() {
            return landStock;
        }

        public void setLandStock(int landStock) {
            this.landStock = landStock;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public boolean isDel() {
            return del;
        }

        public void setDel(boolean del) {
            this.del = del;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public List<FarmBusinessListBean> getFarmBusinessList() {
            return farmBusinessList;
        }

        public void setFarmBusinessList(List<FarmBusinessListBean> farmBusinessList) {
            this.farmBusinessList = farmBusinessList;
        }

        public static class ImagesBean {
            /**
             * id : 28
             * imgUrl : http://222.88.94.204:5080/images/image/6d7e884163904481aba68bd3eb8abc1d.jpg
             * type : 1
             * farmId : 28
             * orderBy : 0
             */

            private int id;
            private String imgUrl;
            private int type;
            private int farmId;
            private int orderBy;

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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getFarmId() {
                return farmId;
            }

            public void setFarmId(int farmId) {
                this.farmId = farmId;
            }

            public int getOrderBy() {
                return orderBy;
            }

            public void setOrderBy(int orderBy) {
                this.orderBy = orderBy;
            }
        }

        public static class FarmBusinessListBean {
            /**
             * id : 24
             * farmId : 28
             * categoryId : 1
             * categoryName : 种植
             * type : 1
             * icon : http://222.88.94.204:5080/images/icon/4.png
             * orderBy : 0
             * key : repast_agritmnt
             */

            private int id;
            private int farmId;
            private int categoryId;
            private String categoryName;
            private int type;
            private String icon;
            private int orderBy;
            private int showType;
            private String key;
            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }


            public int getShowType(){
                return  showType;
            }
            public void setShowType(int showType){
                this.showType=showType;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getFarmId() {
                return farmId;
            }

            public void setFarmId(int farmId) {
                this.farmId = farmId;
            }

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getOrderBy() {
                return orderBy;
            }

            public void setOrderBy(int orderBy) {
                this.orderBy = orderBy;
            }
        }
    }
}
