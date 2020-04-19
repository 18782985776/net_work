package com.jobnew.farm.entity;

/**
 * Created by wc on 2017/8/28.
 * Function：
 * desc：
 */

public class TopUpBean {


    /**
     * appid : wxbf8cec66f691fe6e
     * noncestr : 00472095368
     * packageValue : Sign=WXPay
     * partnerid : 1439257002
     * prepayid : wx201708281712479014e4f8710036445847
     * timestamp : 1503911568
     * sign : 2142CBCBDC53499093200FD8C3911631
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
