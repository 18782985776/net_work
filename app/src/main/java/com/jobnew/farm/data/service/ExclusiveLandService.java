package com.jobnew.farm.data.service;

import com.jobnew.farm.entity.ExclusiveLandDetailsEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.exclusive.ExclusiveLandEntity;
import com.jobnew.farm.entity.myfarm.PlantingOrderEntity;
import com.jobnew.farm.entity.plan.OrderEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by wufengqiao on 2017/8/14.
 * function：
 * desc：
 */

public interface ExclusiveLandService {
    /**
     * 查询专属土地列表
     */
    @FormUrlEncoded
    @POST("activity/lease/query")
    Observable<BaseEntity<List<ExclusiveLandEntity>>> getExclusiveLand(@FieldMap Map<String, String> map);

    /**
     * 查询专属土地详情
     */
    @FormUrlEncoded
    @POST("activity/lease/query/{id}")
    Observable<BaseEntity<ExclusiveLandDetailsEntity>> getExclusiveLandDetails(@Path("id") String id, @FieldMap Map<String, String> map);

    /**
     * 添加租地订单
     */
    @FormUrlEncoded
    @POST("activity/order/lease/add")
    Observable<BaseEntity<OrderEntity>> addLeaseOrder(@FieldMap Map<String, String> map);

}
