package com.jobnew.farm.entity.myfarm;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by wufengqiao on 2017/7/10.
 * function：
 * desc：
 */

public class PlantingOrderEntity implements MultiItemEntity {
    /**
     * farmName : 正大农场
     * farmId : 23
     * farmImg : http://192.168.9.200/images/image/9de670eefcb64ea7b8245deb7fc517e4.jpg
     * majorProductName : 正大2号
     * majorProductQuantity : 1
     * majorProductPrice : 0.01
     * majorProductImg : http://192.168.9.200/images/image/ca4860ed4d544c58b926c060442b93eb.jpg
     * startDate : null
     * endDate : null
     * orderId : 64
     * minorProductName : 枇杷
     * orderStatus : pendingShipment
     */
    public String orderSn;
    public String farmName;
    public double orderAmount;
    public int farmId;
    public String farmImg;
    public String majorProductName;
    public int majorProductQuantity;
    public double majorProductPrice;
    public String majorProductImg;
    public long startDate;
    public long endDate;
    public int orderId;
    public String minorProductName;
    public String orderStatus;

    @Override
    public int getItemType() {
        switch (orderStatus){
            case "growing":         //种植,养殖中
                return 1;
            default:
                return 0;
        }
    }
}
