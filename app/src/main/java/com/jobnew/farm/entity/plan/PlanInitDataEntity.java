package com.jobnew.farm.entity.plan;

import java.util.ArrayList;

/**
 * Created by wufengqiao on 2017/6/29.
 * function：
 * desc：
 */

public class PlanInitDataEntity {


    /**
     * defaultSignboardId : 1
     * defaultSignboardName : 测试标志牌
     * defaultSignboardPrice : 100
     * defaultUserAddressId : 46
     * defaultUserAddressTitle : 四川省成都市青羊区草堂北路22号
     * freight : 10
     * artOrderItemResults : [{"artProductId":1061,"isCount":false,"count":0,"countTitle":"0","isDuration":false,"duration":0,"durationTitle":null,"isInterval":false,"interval":0,"intervalTitle":null,"farmArtId":1,"farmArtName":"播种","unitName":"颗","price":0.01,"isDefault":true,"ftype":"RADIOBOX","ctype":2,"intro":"产品播种","categoryId":18,"categoryName":"播种","unionTask":true,"unionTitle":"播种时间"}]
     */

    public int defaultSignboardId;
    public String defaultSignboardName;
    public double defaultSignboardPrice;
    public int defaultUserAddressId;
    public String defaultUserAddressTitle;
    public double freight;
    public ArrayList<SchemeEntity> artOrderItemResults;
}
