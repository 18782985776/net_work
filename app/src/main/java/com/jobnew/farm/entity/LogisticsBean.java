package com.jobnew.farm.entity;

import java.util.List;

/**
 * Created by wc on 2017/8/17.
 * Function：
 * desc：
 */

public class LogisticsBean {

    /**
     * orderSn : 20170725091310701004173
     * createDate : 1500974004000
     * expressName : 圆通速递
     * expressNo : 885867431066660982
     * expressItems : [{"time":"2017-07-29 16:52:47","context":"客户 签收人: 积木到家 已签收  感谢使用圆通速递，期待再次为您服务"},{"time":"2017-07-29 09:25:07","context":"四川省成都市双流县中和镇公司(点击查询电话)胡** 派件中 派件员电话18190944980"},{"time":"2017-07-29 06:13:19","context":"四川省成都市双流县天府中和公司 已收入"},{"time":"2017-07-29 04:39:48","context":"成都转运中心 已发出,下一站 四川省成都市双流县天府中和"},{"time":"2017-07-29 04:09:47","context":"成都转运中心 已收入"},{"time":"2017-07-27 23:39:50","context":"佛山转运中心 已发出,下一站 成都转运中心"},{"time":"2017-07-27 23:37:13","context":"佛山转运中心 已收入"},{"time":"2017-07-27 22:39:13","context":"广东省广州市番禺区城区公司 已发出,下一站 佛山转运中心"},{"time":"2017-07-27 20:37:02","context":"广东省广州市番禺区城区公司 已打包"},{"time":"2017-07-27 20:06:24","context":"广东省广州市番禺区城区公司(点击查询电话) 已揽收"},{"time":"2017-07-27 18:49:57","context":"广东省广州市番禺区城区公司 取件人: 蔡金泉 已收件"}]
     */

    private String orderSn;
    private long createDate;
    private String expressName;
    private String expressNo;
    /**
     * time : 2017-07-29 16:52:47
     * context : 客户 签收人: 积木到家 已签收  感谢使用圆通速递，期待再次为您服务
     */

    private List<ExpressItemsEntity> expressItems;

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public void setExpressItems(List<ExpressItemsEntity> expressItems) {
        this.expressItems = expressItems;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public long getCreateDate() {
        return createDate;
    }

    public String getExpressName() {
        return expressName;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public List<ExpressItemsEntity> getExpressItems() {
        return expressItems;
    }

    public static class ExpressItemsEntity {
        private String time;
        private String context;

        public void setTime(String time) {
            this.time = time;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getTime() {
            return time;
        }

        public String getContext() {
            return context;
        }
    }
}
