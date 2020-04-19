package com.jobnew.farm.entity.FarmHappy;

import java.util.List;

/**
 * Created by wangwenlang on 2017/8/3.
 * title:
 * note:
 */

public class ProductEntity {

    /**
     * id : 362
     * createDate : 1501673171000
     * modifyDate : 1501673171000
     * name : 星巴克01
     * cname : 高级的星巴克
     * pImg : http://192.168.9.200/images/image/d79002be197f46a6a701ca6eec3e240e.png
     * sn : null
     * price : 0.01
     * unitId : null
     * unitName : null
     * intro : 这是一个神奇的星巴克
     * stock : null
     * isStock : false
     * isSale : true
     * isTop : true
     * isHot : null
     * orderBy : null
     * farmId : 13
     * categoryId : 12
     * saleType : null
     * saleDate : null
     * maxStock : null
     * minRate : null
     * maxRate : null
     * cycleTime : null
     * farm : {"id":13,"createDate":null,"modifyDate":null,"userId":null,"name":"王二娃柴火鸡","address":"天府二街","introduce":null,"detail":null,"auditState":null,"state":null,"auditTime":null,"auditOpinion":null,"saleCount":null,"commentCount":null,"province":"四川省","city":"成都市","area":"青羊区","longitude":"104.06137695451396","latitude":"30.735622100763015","grade":null,"phone":"18384183223","businessScope":null,"mainBusiness":null,"landStock":null,"distance":null,"img":"http://192.168.9.200/images/image/0c0cdfd5792848ffb56e8547772f3065.jpg","del":null}
     * productExtraValues : [{"id":null,"createDate":null,"modifyDate":null,"attributeId":null,"value":null,"productId":null,"attribute":{"id":null,"createDate":null,"modifyDate":null,"categoryId":12,"name":null,"key":null,"defaultValue":null,"unitId":null,"unitName":null}},{"id":null,"createDate":null,"modifyDate":null,"attributeId":null,"value":null,"productId":null,"attribute":{"id":null,"createDate":null,"modifyDate":null,"categoryId":12,"name":null,"key":null,"defaultValue":null,"unitId":null,"unitName":null}},{"id":null,"createDate":null,"modifyDate":null,"attributeId":null,"value":null,"productId":null,"attribute":{"id":null,"createDate":null,"modifyDate":null,"categoryId":12,"name":null,"key":null,"defaultValue":null,"unitId":null,"unitName":null}}]
     * productImages : [{"id":null,"imgUrl":"http://192.168.9.200/images/image/d79002be197f46a6a701ca6eec3e240e.png","type":1,"productId":null,"orderBy":null},{"id":null,"imgUrl":"http://192.168.9.200/images/image/d79002be197f46a6a701ca6eec3e240e.png","type":0,"productId":null,"orderBy":null}]
     * shippingMethodId : null
     * shippingMethod : null
     * type : null
     */

    private int id;
    private long createDate;
    private long modifyDate;
    private String name;
    private String cname;
    private String pImg;
    private Object sn;
    private double price;
    private Object unitId;
    private Object unitName;
    private String intro;
    private Object stock;
    private boolean isStock;
    private boolean isSale;
    private boolean isTop;
    private Object isHot;
    private Object orderBy;
    private int farmId;
    private int categoryId;
    private Object saleType;
    private Object saleDate;
    private Object maxStock;
    private Object minRate;
    private Object maxRate;
    private Object cycleTime;
    private FarmBean farm;
    private Object shippingMethodId;
    private Object shippingMethod;
    private Object type;
    private List<ProductExtraValuesBean> productExtraValues;
    private List<ProductImagesBean> productImages;

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

    public long getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(long modifyDate) {
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

    public Object getUnitName() {
        return unitName;
    }

    public void setUnitName(Object unitName) {
        this.unitName = unitName;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Object getStock() {
        return stock;
    }

    public void setStock(Object stock) {
        this.stock = stock;
    }

    public boolean isIsStock() {
        return isStock;
    }

    public void setIsStock(boolean isStock) {
        this.isStock = isStock;
    }

    public boolean isIsSale() {
        return isSale;
    }

    public void setIsSale(boolean isSale) {
        this.isSale = isSale;
    }

    public boolean isIsTop() {
        return isTop;
    }

    public void setIsTop(boolean isTop) {
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Object getSaleType() {
        return saleType;
    }

    public void setSaleType(Object saleType) {
        this.saleType = saleType;
    }

    public Object getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Object saleDate) {
        this.saleDate = saleDate;
    }

    public Object getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(Object maxStock) {
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

    public FarmBean getFarm() {
        return farm;
    }

    public void setFarm(FarmBean farm) {
        this.farm = farm;
    }

    public Object getShippingMethodId() {
        return shippingMethodId;
    }

    public void setShippingMethodId(Object shippingMethodId) {
        this.shippingMethodId = shippingMethodId;
    }

    public Object getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(Object shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public List<ProductExtraValuesBean> getProductExtraValues() {
        return productExtraValues;
    }

    public void setProductExtraValues(List<ProductExtraValuesBean> productExtraValues) {
        this.productExtraValues = productExtraValues;
    }

    public List<ProductImagesBean> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImagesBean> productImages) {
        this.productImages = productImages;
    }

    public static class FarmBean {
        /**
         * id : 13
         * createDate : null
         * modifyDate : null
         * userId : null
         * name : 王二娃柴火鸡
         * address : 天府二街
         * introduce : null
         * detail : null
         * auditState : null
         * state : null
         * auditTime : null
         * auditOpinion : null
         * saleCount : null
         * commentCount : null
         * province : 四川省
         * city : 成都市
         * area : 青羊区
         * longitude : 104.06137695451396
         * latitude : 30.735622100763015
         * grade : null
         * phone : 18384183223
         * businessScope : null
         * mainBusiness : null
         * landStock : null
         * distance : null
         * img : http://192.168.9.200/images/image/0c0cdfd5792848ffb56e8547772f3065.jpg
         * del : null
         */

        private int id;
        private Object createDate;
        private Object modifyDate;
        private Object userId;
        private String name;
        private String address;
        private Object introduce;
        private Object detail;
        private Object auditState;
        private Object state;
        private Object auditTime;
        private Object auditOpinion;
        private Object saleCount;
        private Object commentCount;
        private String province;
        private String city;
        private String area;
        private String longitude;
        private String latitude;
        private Object grade;
        private String phone;
        private Object businessScope;
        private Object mainBusiness;
        private Object landStock;
        private Object distance;
        private String img;
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

        public Object getGrade() {
            return grade;
        }

        public void setGrade(Object grade) {
            this.grade = grade;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public Object getDel() {
            return del;
        }

        public void setDel(Object del) {
            this.del = del;
        }
    }

    public static class ProductExtraValuesBean {
        /**
         * id : null
         * createDate : null
         * modifyDate : null
         * attributeId : null
         * value : null
         * productId : null
         * attribute : {"id":null,"createDate":null,"modifyDate":null,"categoryId":12,"name":null,"key":null,"defaultValue":null,"unitId":null,"unitName":null}
         */

        private Object id;
        private Object createDate;
        private Object modifyDate;
        private Object attributeId;
        private Object value;
        private Object productId;
        private AttributeBean attribute;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
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

        public Object getAttributeId() {
            return attributeId;
        }

        public void setAttributeId(Object attributeId) {
            this.attributeId = attributeId;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Object getProductId() {
            return productId;
        }

        public void setProductId(Object productId) {
            this.productId = productId;
        }

        public AttributeBean getAttribute() {
            return attribute;
        }

        public void setAttribute(AttributeBean attribute) {
            this.attribute = attribute;
        }

        public static class AttributeBean {
            /**
             * id : null
             * createDate : null
             * modifyDate : null
             * categoryId : 12
             * name : null
             * key : null
             * defaultValue : null
             * unitId : null
             * unitName : null
             */

            private Object id;
            private Object createDate;
            private Object modifyDate;
            private int categoryId;
            private Object name;
            private Object key;
            private Object defaultValue;
            private Object unitId;
            private Object unitName;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
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

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public Object getKey() {
                return key;
            }

            public void setKey(Object key) {
                this.key = key;
            }

            public Object getDefaultValue() {
                return defaultValue;
            }

            public void setDefaultValue(Object defaultValue) {
                this.defaultValue = defaultValue;
            }

            public Object getUnitId() {
                return unitId;
            }

            public void setUnitId(Object unitId) {
                this.unitId = unitId;
            }

            public Object getUnitName() {
                return unitName;
            }

            public void setUnitName(Object unitName) {
                this.unitName = unitName;
            }
        }
    }

    public static class ProductImagesBean {
        /**
         * id : null
         * imgUrl : http://192.168.9.200/images/image/d79002be197f46a6a701ca6eec3e240e.png
         * type : 1
         * productId : null
         * orderBy : null
         */

        private Object id;
        private String imgUrl;
        private int type;
        private Object productId;
        private Object orderBy;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
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

        public Object getProductId() {
            return productId;
        }

        public void setProductId(Object productId) {
            this.productId = productId;
        }

        public Object getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(Object orderBy) {
            this.orderBy = orderBy;
        }
    }
}
