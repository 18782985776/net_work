package com.jobnew.farm.entity;

import java.math.BigDecimal;

/**
 * Created by wc on 2017/8/28.
 * Function：
 * desc：
 */

public class TopUpDetial {

    /**
     * id : 2
     * createDate : 1503939783000
     * modifyDate : 1503939783000
     * userId : 50
     * title : 网农币充值2000个
     * amount : 2000
     * balance : 2000
     * type : recharge_coin
     * statu : in_process
     * outTradeNo : null
     * tradeNo : RC_20170828090301691835128
     */

    private int id;
    private long createDate;
    private long modifyDate;
    private int userId;
    private String title;
    private BigDecimal amount;
    private BigDecimal balance;
    private String type;
    private String statu;
    private Object outTradeNo;
    private String tradeNo;

    public void setId(int id) {
        this.id = id;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public void setModifyDate(long modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public void setOutTradeNo(Object outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public int getId() {
        return id;
    }

    public long getCreateDate() {
        return createDate;
    }

    public long getModifyDate() {
        return modifyDate;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getType() {
        return type;
    }

    public String getStatu() {
        return statu;
    }

    public Object getOutTradeNo() {
        return outTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }
}
