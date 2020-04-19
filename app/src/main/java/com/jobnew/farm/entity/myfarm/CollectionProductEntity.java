package com.jobnew.farm.entity.myfarm;

/**
 * Created by wangwenlang on 2017/7/17.
 * title:
 * note:
 */

public class CollectionProductEntity {

    /**
     * id : 212
     * name : 3213
     * pImg : http://192.168.9.200/images/image/35571dcf3d0f4e17bfcec3eb69b4e193.png
     * price : 123.0
     * unitName : null
     * intro : null
     * stock : null
     * saleCount : 0
     * commentCount : 0
     * maxStock : null
     */

    private int id;
    private String name;
    private String pImg;
    private double price;
    private Object unitName;
    private Object intro;
    private Object stock;
    private int saleCount;
    private int commentCount;
    private Object maxStock;

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

    public String getPImg() {
        return pImg;
    }

    public void setPImg(String pImg) {
        this.pImg = pImg;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Object getUnitName() {
        return unitName;
    }

    public void setUnitName(Object unitName) {
        this.unitName = unitName;
    }

    public Object getIntro() {
        return intro;
    }

    public void setIntro(Object intro) {
        this.intro = intro;
    }

    public Object getStock() {
        return stock;
    }

    public void setStock(Object stock) {
        this.stock = stock;
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

    public Object getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(Object maxStock) {
        this.maxStock = maxStock;
    }
}
