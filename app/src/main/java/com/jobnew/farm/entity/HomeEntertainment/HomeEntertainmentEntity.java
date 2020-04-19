package com.jobnew.farm.entity.HomeEntertainment;

import java.util.List;

/**
 * Created by wangwenlang on 2017/8/11.
 * title:
 * note:
 */

public class HomeEntertainmentEntity {

    /**
     * id : 450
     * createDate : 1504094096000
     * modifyDate : null
     * name : 开心农场活动1
     * cname : null
     * pImg : http://192.168.9.200/images/image/c7fbee2489b44755b707a15ba16bfcf0.jpg
     * sn : null
     * price : 100.0
     * unitId : null
     * unitName : null
     * intro : 我后悔6S店
     * stock : 10
     * isStock : null
     * isSale : null
     * isTop : null
     * isHot : null
     * orderBy : null
     * farmId : 47
     * categoryId : null
     * saleType : null
     * saleDate : 1502769297000
     * maxStock : 10
     * minRate : null
     * maxRate : null
     * cycleTime : null
     * soldOutDate : null
     * farm : {"id":47,"createDate":null,"modifyDate":null,"userId":null,"name":null,"address":null,"introduce":null,"detail":null,"auditState":null,"state":null,"auditTime":null,"auditOpinion":null,"saleCount":null,"commentCount":null,"province":null,"city":null,"area":null,"longitude":"104.0811477043776","latitude":"30.562050577390545","grade":null,"phone":null,"businessScope":null,"mainBusiness":null,"landStock":null,"distance":null,"img":null,"del":null}
     * productImages : []
     * distance : 1789.362170512958
     * fitValue : null
     */

    private int id;
    private long createDate;
    private Object modifyDate;
    private String name;
    private String cname;
    private String pImg;
    private Object sn;
    private double price;
    private Object unitId;
    private String unitName;
    private String intro;
    private int stock;
    private Object isStock;
    private Object isSale;
    private Object isTop;
    private Object isHot;
    private Object orderBy;
    private int farmId;
    private Object categoryId;
    private Object saleType;
    private long saleDate;
    private int maxStock;
    private Object minRate;
    private Object maxRate;
    private Object cycleTime;
    private long soldOutDate;
    private FarmBean farm;
    private double distance;
    private Object fitValue;
    private List<?> productImages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public Object getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Object modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPImg() {
        return pImg;
    }

    public void setPImg(String pImg) {
        this.pImg = pImg;
    }

    public Object getSn() {
        return sn;
    }

    public void setSn(Object sn) {
        this.sn = sn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Object getUnitId() {
        return unitId;
    }

    public void setUnitId(Object unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Object getIsStock() {
        return isStock;
    }

    public void setIsStock(Object isStock) {
        this.isStock = isStock;
    }

    public Object getIsSale() {
        return isSale;
    }

    public void setIsSale(Object isSale) {
        this.isSale = isSale;
    }

    public Object getIsTop() {
        return isTop;
    }

    public void setIsTop(Object isTop) {
        this.isTop = isTop;
    }

    public Object getIsHot() {
        return isHot;
    }

    public void setIsHot(Object isHot) {
        this.isHot = isHot;
    }

    public Object getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Object orderBy) {
        this.orderBy = orderBy;
    }

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public Object getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Object categoryId) {
        this.categoryId = categoryId;
    }

    public Object getSaleType() {
        return saleType;
    }

    public void setSaleType(Object saleType) {
        this.saleType = saleType;
    }

    public long getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(long saleDate) {
        this.saleDate = saleDate;
    }

    public int getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(int maxStock) {
        this.maxStock = maxStock;
    }

    public Object getMinRate() {
        return minRate;
    }

    public void setMinRate(Object minRate) {
        this.minRate = minRate;
    }

    public Object getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(Object maxRate) {
        this.maxRate = maxRate;
    }

    public Object getCycleTime() {
        return cycleTime;
    }

    public void setCycleTime(Object cycleTime) {
        this.cycleTime = cycleTime;
    }

    public long getSoldOutDate() {
        return soldOutDate;
    }

    public void setSoldOutDate(long soldOutDate) {
        this.soldOutDate = soldOutDate;
    }

    public FarmBean getFarm() {
        return farm;
    }

    public void setFarm(FarmBean farm) {
        this.farm = farm;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Object getFitValue() {
        return fitValue;
    }

    public void setFitValue(Object fitValue) {
        this.fitValue = fitValue;
    }

    public List<?> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<?> productImages) {
        this.productImages = productImages;
    }

    public static class FarmBean {
        /**
         * id : 47
         * createDate : null
         * modifyDate : null
         * userId : null
         * name : null
         * address : null
         * introduce : null
         * detail : null
         * auditState : null
         * state : null
         * auditTime : null
         * auditOpinion : null
         * saleCount : null
         * commentCount : null
         * province : null
         * city : null
         * area : null
         * longitude : 104.0811477043776
         * latitude : 30.562050577390545
         * grade : null
         * phone : null
         * businessScope : null
         * mainBusiness : null
         * landStock : null
         * distance : null
         * img : null
         * del : null
         */

        private int id;
        private Object createDate;
        private Object modifyDate;
        private Object userId;
        private Object name;
        private Object address;
        private Object introduce;
        private Object detail;
        private Object auditState;
        private Object state;
        private Object auditTime;
        private Object auditOpinion;
        private Object saleCount;
        private Object commentCount;
        private Object province;
        private Object city;
        private Object area;
        private String longitude;
        private String latitude;
        private Object grade;
        private Object phone;
        private Object businessScope;
        private Object mainBusiness;
        private Object landStock;
        private Object distance;
        private Object img;
        private Object del;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Object createDate) {
            this.createDate = createDate;
        }

        public Object getModifyDate() {
            return modifyDate;
        }

        public void setModifyDate(Object modifyDate) {
            this.modifyDate = modifyDate;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
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

        public Object getAuditState() {
            return auditState;
        }

        public void setAuditState(Object auditState) {
            this.auditState = auditState;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
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

        public Object getSaleCount() {
            return saleCount;
        }

        public void setSaleCount(Object saleCount) {
            this.saleCount = saleCount;
        }

        public Object getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(Object commentCount) {
            this.commentCount = commentCount;
        }

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
            this.province = province;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getArea() {
            return area;
        }

        public void setArea(Object area) {
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

        public Object getGrade() {
            return grade;
        }

        public void setGrade(Object grade) {
            this.grade = grade;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getBusinessScope() {
            return businessScope;
        }

        public void setBusinessScope(Object businessScope) {
            this.businessScope = businessScope;
        }

        public Object getMainBusiness() {
            return mainBusiness;
        }

        public void setMainBusiness(Object mainBusiness) {
            this.mainBusiness = mainBusiness;
        }

        public Object getLandStock() {
            return landStock;
        }

        public void setLandStock(Object landStock) {
            this.landStock = landStock;
        }

        public Object getDistance() {
            return distance;
        }

        public void setDistance(Object distance) {
            this.distance = distance;
        }

        public Object getImg() {
            return img;
        }

        public void setImg(Object img) {
            this.img = img;
        }

        public Object getDel() {
            return del;
        }

        public void setDel(Object del) {
            this.del = del;
        }
    }
}
