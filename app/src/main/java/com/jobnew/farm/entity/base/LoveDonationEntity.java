package com.jobnew.farm.entity.base;

/**
 * Created by wangwenlang on 2017/6/9.
 * title:
 * note:
 */

public class LoveDonationEntity {

    /**
     * id : 51
     * createDate : null
     * modifyDate : null
     * userId : 0
     * contactName : 女士后三
     * areaId : null
     * provinceId : 150000
     * address : 楠楠是是你计算机
     * phone : 15802849373
     * isDefault : true
     * zipCode : null
     * description : null
     * area : {"id":150102,"name":"新城区","parentId":null,"levelType":null,"zipCode":null,"mergerName":"内蒙古自治区,呼和浩特市,新城区"}
     */

    private int id;
    private Object createDate;
    private Object modifyDate;
    private int userId;
    private String contactName;
    private Object areaId;
    private int provinceId;
    private String address;
    private String phone;
    private boolean isDefault;
    private Object zipCode;
    private String description;
    private AreaBean area;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Object getAreaId() {
        return areaId;
    }

    public void setAreaId(Object areaId) {
        this.areaId = areaId;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Object getZipCode() {
        return zipCode;
    }

    public void setZipCode(Object zipCode) {
        this.zipCode = zipCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AreaBean getArea() {
        return area;
    }

    public void setArea(AreaBean area) {
        this.area = area;
    }

    public static class AreaBean {
        /**
         * id : 150102
         * name : 新城区
         * parentId : null
         * levelType : null
         * zipCode : null
         * mergerName : 内蒙古自治区,呼和浩特市,新城区
         */

        private int id;
        private String name;
        private Object parentId;
        private Object levelType;
        private Object zipCode;
        private String mergerName;

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

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public Object getLevelType() {
            return levelType;
        }

        public void setLevelType(Object levelType) {
            this.levelType = levelType;
        }

        public Object getZipCode() {
            return zipCode;
        }

        public void setZipCode(Object zipCode) {
            this.zipCode = zipCode;
        }

        public String getMergerName() {
            return mergerName;
        }

        public void setMergerName(String mergerName) {
            this.mergerName = mergerName;
        }
    }
}
