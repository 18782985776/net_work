package com.jobnew.farm.data.service;

import com.jobnew.farm.entity.ShoppingCar.AddShoppingTrolleyOrderSuccessBaen;
import com.jobnew.farm.entity.ShoppingCar.ShippingMethodEntity;
import com.jobnew.farm.entity.ShoppingCar.ShoppingProductEntity;
import com.jobnew.farm.entity.ShoppingCar.AddShoppingTrolleyOrderBean;
import com.jobnew.farm.entity.exclusive.ExclusiveLandEntity;
import com.jobnew.farm.entity.myfarm.MyExclusiveLandEntity;
import com.jobnew.farm.entity.myfarm.ProductProgressEntity;
import com.jobnew.farm.entity.plan.OrderEntity;
import com.jobnew.farm.entity.plan.SchemeEntity;
import com.jobnew.farm.entity.myfarm.TaskRecordEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.myfarm.LandOrBreedInfoEntity;
import com.jobnew.farm.entity.myfarm.PlanDetailsEntity;
import com.jobnew.farm.entity.myfarm.PlantingOrderEntity;
import com.jobnew.farm.entity.myfarm.ReapOrderEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by wufengqiao on 2017/7/10.
 * function：
 * desc：
 */

public interface PersonMyFarmService {

    /**
     * 查询我的农场种植订单
     */
    @FormUrlEncoded
    @POST("order/myfarm/plant/query")
    Observable<BaseEntity<List<PlantingOrderEntity>>> getMyFarmPlantOrder(@FieldMap Map<String, String> map);

    /**
     * 查询我的农场养植订单
     */
    @FormUrlEncoded
    @POST("order/myfarm/grow/query")
    Observable<BaseEntity<List<PlantingOrderEntity>>> getMyFarmBreedOrder(@FieldMap Map<String, String> map);

    /**
     * 查询我的农场已完成订单
     */
    @FormUrlEncoded
    @POST("order/myfarm/completed/query")
    Observable<BaseEntity<List<ReapOrderEntity>>> getMyFarmCompletedOrder(@FieldMap Map<String, String> map);

    /**
     * 删除订单
     */
    @FormUrlEncoded
    @POST("order/order/delete/{id}")
    Observable<BaseEntity> deleteOrder(@Path("id") String id, @FieldMap Map<String, String> map);

    /**
     * 查询土地/养殖资料
     */
    @FormUrlEncoded
    @POST("order/myfarm/plant/query/{orderId}")
    Observable<BaseEntity<LandOrBreedInfoEntity>> getOrderInfo(@Path("orderId") int id, @FieldMap Map<String, String> map);

    /**
     * 查询种植/养殖计划
     */
    @FormUrlEncoded
    @POST("order/myfarm/plant/plan/query/{orderId}")
    Observable<BaseEntity<PlanDetailsEntity>> getPlan(@Path("orderId") int id, @FieldMap Map<String, String> map);

    /**
     * 查询新任务记录
     */
    @FormUrlEncoded
    @POST("order/task/newtask/query")
    Observable<BaseEntity<List<TaskRecordEntity>>> getNewTaskInfo(@FieldMap Map<String, String> map);


    /**
     * 查询添加新任务产品服务
     */
    @FormUrlEncoded
    @POST("user/product/newtask/art/query")
    Observable<BaseEntity<List<SchemeEntity>>> getAddNewTaskProduct(@FieldMap Map<String, String> map);

    /**
     * 添加新任务
     */
    @FormUrlEncoded
    @POST("order/task/newtask/append")
    Observable<BaseEntity<OrderEntity>> addNewTaskProduct(@FieldMap Map<String, String> map);

    /**
     * 查询我的农场进度
     */
    @FormUrlEncoded
    @POST("s/query")
    Observable<BaseEntity<List<ProductProgressEntity>>> getProductProgress(@FieldMap Map<String, String> map);

    /**
     * 查询购物车
     */
    @FormUrlEncoded
    @POST("order/cart/item/query")
    Observable<BaseEntity<List<ShoppingProductEntity>>> getShoppingTrolley(@FieldMap Map<String, String> map);

    /**
     * 删除购物车
     */
    @FormUrlEncoded
    @POST("order/cart/item/delete")
    Observable<BaseEntity> deleteShoppingTrolley(@FieldMap Map<String, String> map);

    /**
     * 修改购物车数量
     */
    @FormUrlEncoded
    @POST("order/cart/item/quantity/update")
    Observable<BaseEntity> updateShoppingTrolley(@FieldMap Map<String, String> map);

    /**
     * 查询产品库存
     */
    @FormUrlEncoded
    @POST("order/product/stock/query")
    Observable<BaseEntity> getproductQuantity(@FieldMap Map<String, String> map);

    /**
     * 购物车下单
     */
    @FormUrlEncoded
    @POST("order/order/cart/add")
    Observable<BaseEntity<AddShoppingTrolleyOrderSuccessBaen>> addShoppingTrolleyOrder(@FieldMap Map<String, String> map);

    /**
     * 查询产品运费计算方式
     */
    @FormUrlEncoded
    @POST("order/order/cart/add")
    Observable<BaseEntity<List<ShippingMethodEntity>>> getShippingMethod(@FieldMap Map<String, String> map);

    /**
     * 我的农场 - 查询专属土地
     */
    @FormUrlEncoded
    @POST("order/myfarm/lease/query")
    Observable<BaseEntity<List<MyExclusiveLandEntity>>> getMyExclusiveLand(@FieldMap Map<String, String> map);


}
