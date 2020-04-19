package com.jobnew.farm.entity;

/**
 * Created by wangwenlang on 2017/6/8.
 * title:
 * note:
 */

public class FarmLandEntity {

    /**
     * id : 2
     * name : 一号土地
     * introduce : 土地说明
     * mainImage : 图三
     * allStock : 50
     * stock : 49
     * price : 1.0
     * commentCount : 0
     * saleCount : 0
     */

    private int id;
    private String name;
    private String introduce;
    private String mainImage;
    private int allStock;
    private int stock;
    private double price;
    private int commentCount;
    private int saleCount;
    private double distance;
    public double getDistance(){
        return distance;
    }
    public void setDistance(double distance){
        this.distance=distance;
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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public int getAllStock() {
        return allStock;
    }

    public void setAllStock(int allStock) {
        this.allStock = allStock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    @Override
    public String toString() {
        return "FarmLandEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", allStock=" + allStock +
                ", stock=" + stock +
                ", price=" + price +
                ", commentCount=" + commentCount +
                ", saleCount=" + saleCount +
                ", distance=" + distance +
                '}';
    }
}
