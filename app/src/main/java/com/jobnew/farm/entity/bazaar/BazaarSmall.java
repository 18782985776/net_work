package com.jobnew.farm.entity.bazaar;

import java.math.BigDecimal;

/**
 * Created by wc on 2017/8/1.
 * Function：
 * desc：
 */

public class BazaarSmall {

    /**
     * id : 85
     * name : 测试集市产品
     * pImg : http://192.168.9.200/images/image/bff4979ac4b94684a53792b2cf51674b.jpg
     * price : 100
     * unitName : null
     * intro : 测试集市产品
     * stock : 101
     * farmId : 8
     * saleType : NORMAL
     * saleDate : null
     * distance : 8774677.410267381
     */

    private int id;
    private String name;
    private String pImg;
    private BigDecimal price;
    private Object unitName;
    private String intro;
    private int stock;
    private int farmId;
    private String saleType;
    private Object saleDate;
    private double distance;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPImg(String pImg) {
        this.pImg = pImg;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setUnitName(Object unitName) {
        this.unitName = unitName;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public void setSaleDate(Object saleDate) {
        this.saleDate = saleDate;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPImg() {
        return pImg;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Object getUnitName() {
        return unitName;
    }

    public String getIntro() {
        return intro;
    }

    public int getStock() {
        return stock;
    }

    public int getFarmId() {
        return farmId;
    }

    public String getSaleType() {
        return saleType;
    }

    public Object getSaleDate() {
        return saleDate;
    }

    public double getDistance() {
        return distance;
    }
}
