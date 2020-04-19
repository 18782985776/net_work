package com.jobnew.farm.entity.plan;


import java.io.Serializable;
import java.util.List;

/**
 * Created by wufengqiao on 2017/6/2.
 * function：
 * desc：
 */

public class CropEntity implements Serializable {
    /**
     * id : 3
     * name : 胡萝卜种子
     * pImg : ""
     * imgs : ""
     * sn : ""
     * price : 1
     * unitId : 2
     * unitName : 颗
     * intro : ""
     * isStock : false
     * isSale : false
     * productExtraValues : [{"id":7,"value":"120","attribute":{"id":6,"name":"成长周期","key":"grow","defaultValue":null}}]
     */
    public int id;
    public String name;
    public String cname;
    public String pImg;
    public String imgs;
    public String sn;
    public float price;
    public int unitId;
    public String unitName;
    public String intro;
    public int cycleTime;
    public boolean isStock;
    public boolean isSale;
    public boolean isItemSelect;

}
