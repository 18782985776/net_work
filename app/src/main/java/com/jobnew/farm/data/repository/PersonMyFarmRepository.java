package com.jobnew.farm.data.repository;

import android.util.Log;

import com.google.gson.Gson;
import com.jobnew.farm.data.service.PersonMyFarmService;
import com.jobnew.farm.entity.ShoppingCar.AddShoppingTrolleyOrderSuccessBaen;
import com.jobnew.farm.entity.ShoppingCar.ShippingMethodEntity;
import com.jobnew.farm.entity.ShoppingCar.ShoppingCarProductBean;
import com.jobnew.farm.entity.ShoppingCar.ShoppingProductEntity;
import com.jobnew.farm.entity.ShoppingCar.AddShoppingTrolleyOrderBean;
import com.jobnew.farm.entity.exclusive.ExclusiveLandEntity;
import com.jobnew.farm.entity.myfarm.CommitNewTaskEntity;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wufengqiao on 2017/7/10.
 * function：
 * desc：
 */

public class PersonMyFarmRepository extends BaseRepository<PersonMyFarmService> {

    private static PersonMyFarmRepository sInstance;

    @Override
    public Class<PersonMyFarmService> bindService() {
        return PersonMyFarmService.class;
    }

    public static PersonMyFarmRepository getIns() {
        if (sInstance == null) {
            synchronized (FarmRepository.class) {
                if (sInstance == null) {
                    sInstance = new PersonMyFarmRepository();
                }
            }
        }
        return sInstance;
    }

    /**
     * 查询我的农场种植订单
     *
     * @return
     */
    public Observable<List<PlantingOrderEntity>> getMyFarmPlantOrder(Map<String, String> map) {
        return sepaCheckNet(getLoginService().getMyFarmPlantOrder(map));
    }

    /**
     * 查询我的农场养殖订单
     *
     * @return
     */
    public Observable<List<PlantingOrderEntity>> getMyFarmBreedOrder(Map<String, String> map) {
        return sepaCheckNet(getLoginService().getMyFarmBreedOrder(map));
    }

    /**
     * 查询我的农场已完成订单
     *
     * @return
     */
    public Observable<List<ReapOrderEntity>> getMyFarmCompletedOrder(Map<String, String> map) {
        return sepaCheckNet(getLoginService().getMyFarmCompletedOrder(map));
    }

    /**
     * 删除订单
     */
    public Observable<BaseEntity> deleteOrder(String id) {
        return checkNet(getLoginService().deleteOrder(id, new HashMap<>()));
    }

    /**
     * 查询土地/养殖资料
     */
    public Observable<LandOrBreedInfoEntity> getOrderInfo(int id) {
        return sepaCheckNet(getLoginService().getOrderInfo(id, new HashMap<>()));
    }

    /**
     * 查询种植/养殖计划
     */
    public Observable<PlanDetailsEntity> getPlan(int id) {
        return sepaCheckNet(getLoginService().getPlan(id, new HashMap<>()));
    }

    /**
     * 查询新任务记录
     */
    public Observable<List<TaskRecordEntity>> getNewTaskInfo(Map<String, String> map) {
        return sepaCheckNet(getLoginService().getNewTaskInfo(map));
    }

    /**
     * 查询添加新任务产品服务
     */
    public Observable<List<SchemeEntity>> getAddNewTaskProduct(String productId) {
        Map<String, String> map = new HashMap<>();
        map.put("productId", productId);
        return sepaCheckNet(getLoginService().getAddNewTaskProduct(map));
    }

    /**
     * 添加新任务
     */
    @FormUrlEncoded
    @POST("order/task/newtask/append")
    public Observable<OrderEntity> addNewTaskProduct(CommitNewTaskEntity entity) {
        Map<String, String> map = new HashMap<>();
        String json = new Gson().toJson(entity);
        map.put("data", json);
        Log.e("aaaaaaaa", json);
        return sepaCheckNet(getLoginService().addNewTaskProduct(map));
    }

    /**
     * 查询我的农场进度
     */
    public Observable<List<ProductProgressEntity>> getProductProgress(Map<String, String> map) {
        return sepaCheckNet(getLoginService().getProductProgress(map));
    }

    /**
     * 查询购物车
     */
    public Observable<List<ShoppingProductEntity>> getShoppingTrolley() {
        return sepaCheckNet(getLoginService().getShoppingTrolley(new HashMap<>()));
    }

    /**
     * 删除购物车
     */
    public Observable<BaseEntity> deleteShoppingTrolley(int productId) {
        Map<String, String> map = new HashMap<>();
        map.put("productId", productId + "");
        return checkNet(getLoginService().deleteShoppingTrolley(map));
    }

    /**
     * 修改购物车数量
     */
    public Observable<BaseEntity> updateShoppingTrolley(List<ShoppingCarProductBean> list) {
        String data = new Gson().toJson(list);
        Log.e("aaaaaa", data);
        Map<String, String> map = new HashMap<>();
        map.put("data", data);
        return checkNet(getLoginService().updateShoppingTrolley(map));
    }

    /**
     * 查询产品库存
     */
    public Observable<BaseEntity> getproductQuantity(int productId) {
        Map<String, String> map = new HashMap<>();
        map.put("productId", productId + "");
        return checkNet(getLoginService().getproductQuantity(map));
    }

    /**
     * 购物车下单
     */
    public Observable<AddShoppingTrolleyOrderSuccessBaen> addShoppingTrolleyOrder(AddShoppingTrolleyOrderBean entity) {
        String data = new Gson().toJson(entity);
        Map<String, String> map = new HashMap<>();
        Log.e("aaaaaa", data);
        map.put("data", data);
        return sepaCheckNet(getLoginService().addShoppingTrolleyOrder(map));
    }
    /**
     * 查询产品运费计算方式
     */
    public Observable<List<ShippingMethodEntity>> getShippingMethod(Map<String, String> map) {
        return sepaCheckNet(getLoginService().getShippingMethod(map));
    }

    /**
     * 我的农场 - 查询专属土地
     */
    public Observable<List<MyExclusiveLandEntity>> getMyExclusiveLand(Map<String, String> map) {
        return sepaCheckNet(getLoginService().getMyExclusiveLand(map));
    }

}
