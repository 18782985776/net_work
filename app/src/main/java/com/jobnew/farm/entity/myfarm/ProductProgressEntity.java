package com.jobnew.farm.entity.myfarm;

import java.util.List;

/**
 * Created by wufengqiao on 2017/7/20.
 * function：
 * desc：
 */

public class ProductProgressEntity {

    /**
     * artProductName : 播种
     * managerName : 王二麻子
     * createDate : 1498719456000
     * images : [{"imgUrl":"http://www.baidu.com/img/logo.png","orderBy":1}]
     * source : {"farmName":"开心农场","farmManagerId":2,"farmImg":"http://192.168.9.200/images/image/d1c3e371a7574c11a38c73b5e187df1d.jpg"}
     */

    public String artProductName;
    public String managerName;
    public long createDate;
    public SourceBean source;
    public List<ImagesBean> images;

    public static class SourceBean {
        /**
         * farmName : 开心农场
         * farmManagerId : 2
         * farmImg : http://192.168.9.200/images/image/d1c3e371a7574c11a38c73b5e187df1d.jpg
         */

        public String farmName;
        public int farmManagerId;
        public String farmImg;
    }

    public static class ImagesBean {
        /**
         * imgUrl : http://www.baidu.com/img/logo.png
         * orderBy : 1
         */

        public String imgUrl;
        public int orderBy;
    }
}
