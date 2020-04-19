package com.jobnew.farm.entity.bazaar;

/**
 * Created by wc on 2017/8/24.
 * Function：
 * desc：
 */

public class AfterDetailsBean {

    /**
     * id : 1
     * createDate : 32323232332
     * returnReason : 假货
     * refundAmount : 100.0
     * returnInfo : 反正就是假货
     * returnImg : ["pppp.png","pppp.png"]
     * returnType : refund
     * returnStatus : pending
     * orderStatus : 1
     */

    private int id;
    private long createDate;
    private String returnReason;
    private double refundAmount;
    private String returnInfo;
    private String returnImg;
    private String returnType;
    private String returnStatus;
    private int orderStatus;

    public void setId(int id) {
        this.id = id;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public void setReturnInfo(String returnInfo) {
        this.returnInfo = returnInfo;
    }

    public void setReturnImg(String returnImg) {
        this.returnImg = returnImg;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getId() {
        return id;
    }

    public long getCreateDate() {
        return createDate;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public double getRefundAmount() {
        return refundAmount;
    }

    public String getReturnInfo() {
        return returnInfo;
    }

    public String getReturnImg() {
        return returnImg;
    }

    public String getReturnType() {
        return returnType;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public int getOrderStatus() {
        return orderStatus;
    }
}
