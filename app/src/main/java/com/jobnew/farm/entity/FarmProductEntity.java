package com.jobnew.farm.entity;

import java.util.List;

/**
 * Created by wangwenlang on 2017/6/22.
 * title:
 * note:
 */

public class FarmProductEntity {

    /**
     * id : 1
     * name : 500平优质土地
     * cname : 优质优质的土地
     * pImg : http://222.88.94.204:5080/images/avatar/1b76787060d1499d8636a593c2d1f94e.jpg
     * imgs : null
     * sn : null
     * price : 1101.0
     * unitId : 3
     * unitName : ㎡
     * intro : 没有介绍
     * stock : 500
     * isStock : true
     * isSale : null
     * isTop : null
     * isHot : null
     * orderBy : null
     * farmId : null
     * categoryId : null
     * saleType : NORMAL
     * saleDate : 1498011561000
     * farm : null
     * productExtraValues : [{"id":1,"attributeId":null,"value":"普通出租","productId":null,"attribute":{"id":1,"categoryId":null,"name":"出租方式","key":"renttype","defaultValue":null,"unitId":null,"unitName":null}},{"id":2,"attributeId":null,"value":"养殖","productId":null,"attribute":{"id":2,"categoryId":null,"name":"土地适宜","key":"landfit","defaultValue":null,"unitId":null,"unitName":null}},{"id":3,"attributeId":null,"value":"黑土","productId":null,"attribute":{"id":3,"categoryId":null,"name":"土壤类型","key":"landtype","defaultValue":null,"unitId":null,"unitName":null}},{"id":4,"attributeId":null,"value":"","productId":null,"attribute":{"id":4,"categoryId":null,"name":"上传土壤检测报告","key":"checkimage","defaultValue":null,"unitId":null,"unitName":null}},{"id":5,"attributeId":null,"value":"闲置中","productId":null,"attribute":{"id":5,"categoryId":null,"name":"土地状态","key":"landstatus","defaultValue":null,"unitId":null,"unitName":null}},{"id":6,"attributeId":null,"value":"0","productId":null,"attribute":null}]
     */

    private int id;
    private String name;
    private String cname;
    private String pImg;
    private Object imgs;
    private Object sn;
    private double price;
    private int unitId;
    private String unitName;
    private String intro;
    private int stock;
    private int maxStock;
    private boolean isStock;
    private Object isSale;
    private Object isTop;
    private Object isHot;
    private Object orderBy;
    private int farmId;
    private int categoryId;
    private String saleType;
    private long saleDate;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private  String key;

    public String getpImg() {
        return pImg;
    }

    public void setpImg(String pImg) {
        this.pImg = pImg;
    }

    public boolean isStock() {
        return isStock;
    }

    public void setStock(boolean stock) {
        isStock = stock;
    }

    public long getSoldOutDate() {
        return soldOutDate;
    }

    public void setSoldOutDate(long soldOutDate) {
        this.soldOutDate = soldOutDate;
    }

    private long soldOutDate;
    private Object farm;
    private List<ProductExtraValuesBean> productExtraValues;//这个字段目前在养殖里面用不到（夏伟文说的)，仅在土地里面用到
    private int cycleTime;
    public int getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(int maxStock) {
        this.maxStock = maxStock;
    }
    public int getCycleTime(){
        return cycleTime;
    }
    public void setCycleTime(int cycleTime){
        this.cycleTime=cycleTime;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Object getImgs() {
        return imgs;
    }

    public void setImgs(Object imgs) {
        this.imgs = imgs;
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

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
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

    public boolean isIsStock() {
        return isStock;
    }

    public void setIsStock(boolean isStock) {
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public long getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(long saleDate) {
        this.saleDate = saleDate;
    }

    public Object getFarm() {
        return farm;
    }

    public void setFarm(Object farm) {
        this.farm = farm;
    }

    public List<ProductExtraValuesBean> getProductExtraValues() {
        return productExtraValues;
    }

    public void setProductExtraValues(List<ProductExtraValuesBean> productExtraValues) {
        this.productExtraValues = productExtraValues;
    }

    public static class ProductExtraValuesBean {
        /**
         * id : 1
         * attributeId : null
         * value : 普通出租
         * productId : null
         * attribute : {"id":1,"categoryId":null,"name":"出租方式","key":"renttype","defaultValue":null,"unitId":null,"unitName":null}
         */

        private int id;
        private Object attributeId;
        private String value;
        private Object productId;
        private AttributeBean attribute;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getAttributeId() {
            return attributeId;
        }

        public void setAttributeId(Object attributeId) {
            this.attributeId = attributeId;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
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
             * id : 1
             * categoryId : null
             * name : 出租方式
             * key : renttype
             * defaultValue : null
             * unitId : null
             * unitName : null
             */

            private int id;
            private Object categoryId;
            private String name;
            private String key;
            private Object defaultValue;
            private Object unitId;
            private Object unitName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(Object categoryId) {
                this.categoryId = categoryId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
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
}
