package com.jobnew.farm.entity;

/**
 * Created by wc on 2017/7/4.
 * Function：
 * desc：
 */

public class PayWeiXinInfo {


    /**
     * appid : wxbf8cec66f691fe6e
     * noncestr : 00754218592
     * packageValue : Sign=WXPay
     * partnerid : 1439257002
     * prepayid : wx20170704173028d9c71a46710430818339
     * timestamp : 1499160628
     * sign : D65A969546ECDBEFDE522C8A5AFB1084
     */

    private String appid;
    private String noncestr;
    private String packageValue;
    private String partnerid;
    private String prepayid;
    private String timestamp;
    private String sign;

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAppid() {
        return appid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getSign() {
        return sign;
    }
}
