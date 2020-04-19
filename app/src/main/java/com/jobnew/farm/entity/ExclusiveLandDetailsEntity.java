package com.jobnew.farm.entity;


import java.util.List;

/**
 * Created by wufengqiao on 2017/8/15.
 * function：
 * desc：
 */

public class ExclusiveLandDetailsEntity {

    /**
     * id : 36
     * createDate : 1500898094000
     * modifyDate : null
     * name : 神秘1号
     * cname : null
     * pImg : http://192.168.9.200/images/image/c10540ac6c5b4acd97354cde2a11a886.jpg
     * sn : null
     * price : 0.01
     * unitId : null
     * unitName : null
     * intro : 大自然的馈赠
     * stock : 1000
     * isStock : null
     * isSale : null
     * isTop : null
     * isHot : null
     * orderBy : null
     * farmId : 9
     * categoryId : 58
     * categoryName : 种植作物
     * categoryIcon : http://42.51.205.2/images/icon/5.png
     * saleType : null
     * saleDate : 1500898094000
     * maxStock : 1000
     * minRate : null
     * maxRate : null
     * cycleTime : null
     * soldOutDate : null
     * farm : {"id":9,"createDate":null,"modifyDate":null,"userId":null,"name":"有个农场","address":"为名路12号","introduce":null,"detail":null,"auditState":null,"state":null,"auditTime":null,"auditOpinion":null,"saleCount":null,"commentCount":null,"province":"四川省","city":"成都市","area":"郫县","longitude":"103.88462503304833","latitude":"30.839641883011013","grade":null,"phone":"18961389011","businessScope":null,"mainBusiness":null,"landStock":null,"distance":null,"img":"http://192.168.9.200/images/image/12191bc79e2f4dd4bfec0d6cde452ff6.jpg","del":null}
     * productImages : [{"id":null,"imgUrl":"http://192.168.9.200/images/image/c10540ac6c5b4acd97354cde2a11a886.jpg","type":1,"productId":null,"orderBy":null},{"id":null,"imgUrl":"http://192.168.9.200/images/image/be025193bc394e318c7806bb6cabe17e.jpg","type":0,"productId":null,"orderBy":null},{"id":null,"imgUrl":"http://192.168.9.200/images/image/dcf2261d25a84005ba66350f2022bc02.jpg","type":0,"productId":null,"orderBy":null}]
     * distance : 1.132967735826945E7
     * fitValue : null
     * isJoin : false
     * orderId : null
     */

    public int id;
    public String name;
    public String cname;
    public String pImg;
    public String sn;
    public double price;
    public int unitId;
    public String unitName;
    public String intro;
    public int stock;
    public String orderBy;
    public int farmId;
    public int categoryId;
    public long cycleTime;
    public FarmEntity farm;
    public double distance;
    public String fitValue;
    public int orderId;
    public String categoryName;
    public String categoryIcon;

    public List<ProductImagesBean> productImages;

    public static class ProductImagesBean {
        /**
         * id : null
         * imgUrl : http://192.168.9.200/images/image/c10540ac6c5b4acd97354cde2a11a886.jpg
         * type : 1
         * productId : null
         * orderBy : null
         */

        public int id;
        public String imgUrl;
        public int type;
        public int productId;
        public String orderBy;
    }
}
