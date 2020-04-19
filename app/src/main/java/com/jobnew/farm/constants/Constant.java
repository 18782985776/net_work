package com.jobnew.farm.constants;

import java.util.UUID;

import retrofit2.http.PUT;

/**
 * Created by wufengqiao on 2017/5/26.
 */

public class Constant {
    public static final String EXCLUSIVE_LAND = "exclusive_land";
    //    public static String DEVICE_ID = UUID.randomUUID().toString();
    public static String DEVICE_ID = "";
    public static String token = "";//用户token
    public static final String NAME = "name";
    public static final String FARM_NAME = "farmName";
    public final static String FARM_ID = "farmId";
    public static final String FARM_IMG = "farm_img";
    public final static String FARM_PRODUCT = "farm_product";
    public final static String PRODUCT_ID = "product_id";
    public final static String CROP_SEED = "CROP_SEED";
    public static final String SEED_ID = "seed_id";
    public static final String DURATION = "duration";
    public static final String STOCK ="stock";
    public final static String NUMBER = "number";
    public final static String PRODUCT_PROCESS = "product_process";
    public final static String STEWARD = "steward";
    public final static String SIGNBOARD = "signboard";
    public final static String ADDRESS_MANAGE = "address_mange";
    public final static String ADDRESS_ID = "address_id";
    public static final String SCHEME = "scheme";
    public static final String SCHEME_TYPE = "scheme_type";
    public final static String DATE = "date";
    public final static String ORDER = "order";
    public final static String ORDER_ID = "order_id";
    public static final String TOTAL_PRICE = "total_price";
    public static final String ORDER_SN = "order_cn";
    public static final String ORDER_BODY = "order_body";
    public static final String TYPE = "type";
    public static final String PAY_TYPE = "pay_type";//存滴012问吴   3 代表我的农家乐  4 代表我的订单 集市 5 代表网农币充值
    public static final String CATEGORY_SN = "categorySn";
    /*定位*/
    public static String PROVINECE="四川省";
    public static String CITY="成都市";
    public static String ADDRESS="四川省成都市武侯区泰然环球时代中心";
    public static double LATITUDE=30.554515;//纬度
    public static double LONGITUDE=104.064615;//经度 longitude

}
