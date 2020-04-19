package com.jobnew.farm.entity.plan;

import java.io.Serializable;

/**
 * Created by wufengqiao on 2017/5/27.
 */

public class SignboardEntity implements Serializable{


    /**
     * id : 4
     * name : 测试标志牌
     * cname : 测试标识牌
     * pImg : http://222.88.94.204:5080/images/avatar/1b76787060d1499d8636a593c2d1f94e.jpg
     * imgs : null
     * sn : null
     * price : 1.0E7
     * unitId : 8
     * unitName : 个
     * intro : 没有介绍
     * stock : 10000
     * isStock : true
     * isSale : null
     * isTop : null
     * isHot : null
     * orderBy : null
     * farmId : null
     * categoryId : null
     * saleType : NORMAL
     * saleDate : 1497869114000
     * farm : null
     * productExtraValues : []
     */

    public int id;
    public String name;
    public String pImg;
    public String imgs;
    public String sn;
    public double price;
    public String unitName;
    public String intro;
    public int stock;
    public boolean isStock;
}
