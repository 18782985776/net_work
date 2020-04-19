package com.jobnew.farm.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wufengqiao on 2017/6/5.
 * function：
 * desc：
 */

public class ProductDetailsEntity implements Serializable{


    /**
     * id : 1
     * name : 500平优质土地
     * cname : null
     * pImg : null  预览图
     * imgs : null   图片Json
     * sn : 10000001
     * price : 100
     * unitId : 3
     * unitName : ㎡
     * intro : 没有介绍
     * stock : 500
     * isStock : true
     * isSale : true
     * isTop : true
     * isHot : true
     * orderBy : 1
     * farmId : 1
     * categoryId : 1
     * saleType : NORMAL
     * saleDate : null
     * farm : {"id":1,"name":"开心农场","address":"中和镇红树湾","province":"四川省","city":"成都市","area":"高新区","longitude":"104.079162","latitude":"30.522879","phone":"18628228047"}
     * productExtraValues : [{"id":1,"value":"普通出租","attribute":{"id":3,"categoryId":1,"name":"土壤类型","key":"landtype","defaultValue":null}}]
     */

    public int id;
    public String name;
    public String cname;
    public String pImg;
    public String imgs;
    public String sn;
    public double price;
    public int unitId;
    public String unitName;
    public String intro;
    public int stock;
    public long modifyDate;
    public int maxStock;
    public int maxRate;
    public int minRate;
    public int cycleTime;
    public boolean isStock;
    public boolean isSale;
    public boolean isTop;
    public boolean isHot;
    public int orderBy;
    public int farmId;
    public int categoryId;
    public String saleType;
    public long saleDate;
    public FarmEntity farm;
    public List<ProductImages> productImages;


    public static class ProductImages implements Serializable{
        public String imgUrl;
        public int type;
    }
}
