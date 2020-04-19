package com.jobnew.farm.entity.myfarm;

/**
 * Created by wangwenlang on 2017/7/17.
 * title:
 * note:
 */

public class CollectionLandEntity {

    /**
     * id : 218
     * name : 枇杷
     * pImg : http://192.168.9.200/images/image/d0eee29456c74f98b2a5cad0c6657e09.jpg
     * price : 0.3
     * unitName : ㎡
     * intro : null
     * stock : 0
     * saleCount : 0
     * commentCount : 0
     * type : 普通出租
     * maxStock : 0
     */

    private int id;
    private String name;
    private String pImg;
    private double price;
    private String unitName;
    private Object intro;
    private int stock;
    private int saleCount;
    private int commentCount;
    private String type;
    private int maxStock;

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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Object getIntro() {
        return intro;
    }

    public void setIntro(Object intro) {
        this.intro = intro;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(int maxStock) {
        this.maxStock = maxStock;
    }
}
