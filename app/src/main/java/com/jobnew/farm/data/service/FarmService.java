package com.jobnew.farm.data.service;


import com.jobnew.farm.entity.CommentEntity;
import com.jobnew.farm.entity.plan.CropEntity;
import com.jobnew.farm.entity.plan.OrderEntity;
import com.jobnew.farm.entity.plan.PlanInitDataEntity;
import com.jobnew.farm.entity.ProductDetailsEntity;
import com.jobnew.farm.entity.plan.ProductProcessEntity;
import com.jobnew.farm.entity.plan.RecommendSchemeEntity;
import com.jobnew.farm.entity.plan.SchemeEntity;
import com.jobnew.farm.entity.plan.SignboardEntity;
import com.jobnew.farm.entity.plan.StewardEntity;
import com.jobnew.farm.entity.base.BaseEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by wufengqiao on 2017/6/5.
 * function：
 * desc：
 */

public interface FarmService {

    /**
     * 调用接口 土地、养殖、农产品等农场产品详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("user/product/query/{id}")
    Observable<BaseEntity<ProductDetailsEntity>> getProductDetails(@Path("id") String path, @FieldMap Map<String, String> map);

    /**
     * 获取作物种子列表接口
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("user/product/seed/query")
    Observable<BaseEntity<List<CropEntity>>> getCropSeed(@FieldMap Map<String, String> map);

    /**
     * 获取农场管家列表接口
     */
    @FormUrlEncoded
    @POST("user/farm/manager/query")
    Observable<BaseEntity<List<StewardEntity>>> getSteward(@FieldMap Map<String, String> map);

    /**
     * 获取农场标识牌列表接口
     */
    @FormUrlEncoded
    @POST("user/product/signboard/query")
    Observable<BaseEntity<List<SignboardEntity>>> getSignboard(@FieldMap Map<String, String> map);

    /**
     * 获取用户评论接口
     */
    @FormUrlEncoded
    @POST("user/comment/page")
    Observable<BaseEntity<List<CommentEntity>>> getComment(@FieldMap Map<String, String> map);

    /**
     * 获取默认方案接口
     */
    @FormUrlEncoded
    @POST("user/product/template/query")
    Observable<BaseEntity<RecommendSchemeEntity>> getDefaultScheme(@FieldMap Map<String, String> map);

    /**
     * 获取默认方案接口
     */
    @FormUrlEncoded
    @POST("user/product/art/query")
    Observable<BaseEntity<List<SchemeEntity>>> getCustomScheme(@FieldMap Map<String, String> map);

    /**
     * 获取产品加工接口
     */
    @FormUrlEncoded
    @POST("user/product/process/query")
    Observable<BaseEntity<List<ProductProcessEntity>>> getProductProcess(@FieldMap Map<String, String> map);

    /**
     * 提交生成订单
     */
    @FormUrlEncoded
    @POST("order/order/plant/add")
    Observable<BaseEntity<OrderEntity>> commitOrder(@FieldMap Map<String, String> map);

    /**
     * 查询产品初始化数据，包括 默认方案价格，默认标识牌
     */
    @FormUrlEncoded
    @POST("user/product/init")
    Observable<BaseEntity<PlanInitDataEntity>> getPlanData(@FieldMap Map<String, String> map);


}
