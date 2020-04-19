package com.jobnew.farm.entity;

import java.util.List;

/**
 * Created by wangwenlang on 2017/8/30.
 * title:
 * note:
 */

public class PostFeeEntity {

    /**
     * id : 25
     * createDate : null
     * modifyDate : null
     * name : 你买了吗
     * freeWeight : 3565
     * firstWeight : 0
     * continueWeight : 0
     * defaultFirstPrice : 2.0
     * defaultContinuePrice : 4.0
     * icon : null
     * description : null
     * defaultDeliveryCorpId : 1
     * defaultDeliveryCorp : null
     * paymentMethods : []
     * freightConfigs : []
     * provinceId : null
     * orders : []
     * farmId : 25
     * productId : 508
     */

    private int id;
    private Object createDate;
    private Object modifyDate;
    private String name;
    private int freeWeight;
    private int firstWeight;
    private int continueWeight;
    private double defaultFirstPrice;
    private double defaultContinuePrice;
    private Object icon;
    private Object description;
    private int defaultDeliveryCorpId;
    private Object defaultDeliveryCorp;
    private Object provinceId;
    private int farmId;
    private int productId;
    private List<?> paymentMethods;
    private List<?> freightConfigs;
    private List<?> orders;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFreeWeight() {
        return freeWeight;
    }

    public void setFreeWeight(int freeWeight) {
        this.freeWeight = freeWeight;
    }

    public int getFirstWeight() {
        return firstWeight;
    }

    public void setFirstWeight(int firstWeight) {
        this.firstWeight = firstWeight;
    }

    public int getContinueWeight() {
        return continueWeight;
    }

    public void setContinueWeight(int continueWeight) {
        this.continueWeight = continueWeight;
    }

    public double getDefaultFirstPrice() {
        return defaultFirstPrice;
    }

    public void setDefaultFirstPrice(double defaultFirstPrice) {
        this.defaultFirstPrice = defaultFirstPrice;
    }

    public double getDefaultContinuePrice() {
        return defaultContinuePrice;
    }

    public void setDefaultContinuePrice(double defaultContinuePrice) {
        this.defaultContinuePrice = defaultContinuePrice;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public int getDefaultDeliveryCorpId() {
        return defaultDeliveryCorpId;
    }

    public void setDefaultDeliveryCorpId(int defaultDeliveryCorpId) {
        this.defaultDeliveryCorpId = defaultDeliveryCorpId;
    }

    public Object getDefaultDeliveryCorp() {
        return defaultDeliveryCorp;
    }

    public void setDefaultDeliveryCorp(Object defaultDeliveryCorp) {
        this.defaultDeliveryCorp = defaultDeliveryCorp;
    }

    public Object getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Object provinceId) {
        this.provinceId = provinceId;
    }

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public List<?> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<?> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<?> getFreightConfigs() {
        return freightConfigs;
    }

    public void setFreightConfigs(List<?> freightConfigs) {
        this.freightConfigs = freightConfigs;
    }

    public List<?> getOrders() {
        return orders;
    }

    public void setOrders(List<?> orders) {
        this.orders = orders;
    }
}
