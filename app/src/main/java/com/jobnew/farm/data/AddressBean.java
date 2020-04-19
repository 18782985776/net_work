package com.jobnew.farm.data;

import java.io.Serializable;

/**
 * Created by liyi on 2017/4/21.
 * tilte:  地址的Bean目录
 */
public class AddressBean implements Serializable{


    /**
     * id : 1
     * contactName : 澎湃
     * phone : 18628228147
     * zipCode : 610000
     * address : 中和镇
     * areaId : 100001
     * provinceId : 100000
     * isDefault : true
     * area : {"id":"100001","name":"高新区","mergerName":"四川省,成都市"}
     */

    private int id;
    private String contactName;
    private String phone;
    private String zipCode;
    private String address;
    private String areaId;
    private int provinceId;
    private boolean isDefault;
    /**
     * id : 100001
     * name : 高新区
     * mergerName : 四川省,成都市
     */

    private AreaEntity area;

    public void setId(int id) {
        this.id = id;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public void setArea(AreaEntity area) {
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public String getContactName() {
        return contactName;
    }

    public String getPhone() {
        return phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getAddress() {
        return address;
    }

    public String getAreaId() {
        return areaId;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public boolean isIsDefault() {
        return isDefault;
    }

    public AreaEntity getArea() {
        return area;
    }

    public static class AreaEntity implements Serializable {
        private String id;
        private String name;
        private String mergerName;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setMergerName(String mergerName) {
            this.mergerName = mergerName;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getMergerName() {
            return mergerName;
        }
    }
}
