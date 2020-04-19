package com.jobnew.farm.data.repository;

import android.util.Log;

import com.google.gson.Gson;
import com.jobnew.farm.data.service.FarmService;
import com.jobnew.farm.entity.CommentEntity;
import com.jobnew.farm.entity.plan.CommitOrderEntity;
import com.jobnew.farm.entity.plan.CropEntity;
import com.jobnew.farm.entity.plan.OrderEntity;
import com.jobnew.farm.entity.plan.PlanInitDataEntity;
import com.jobnew.farm.entity.ProductDetailsEntity;
import com.jobnew.farm.entity.plan.ProductProcessEntity;
import com.jobnew.farm.entity.plan.RecommendSchemeEntity;
import com.jobnew.farm.entity.plan.SchemeEntity;
import com.jobnew.farm.entity.plan.SignboardEntity;
import com.jobnew.farm.entity.plan.StewardEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wufengqiao on 2017/6/5.
 * function：
 * desc：
 */

public class FarmRepository extends BaseRepository<FarmService> {



    private static FarmRepository sInstance;

    @Override
    public Class bindService() {
        return FarmService.class;
    }

    public static FarmRepository getIns() {
        if (sInstance == null) {
            synchronized (FarmRepository.class) {
                if (sInstance == null) {
                    sInstance = new FarmRepository();
                }
            }
        }
        return sInstance;
    }

    /**
     * 调用接口 土地、养殖、农产品等农场产品详情
     * @return
     */
    public Observable<ProductDetailsEntity> getProductDetails(String path, Map<String,String> map){
        return sepaCheckNet(getService().getProductDetails(path,map));
    }

    /**
     * 作物种子列表接口
     * @param map
     * @return
     */
    public Observable<List<CropEntity>> getCropSeed(Map<String,String> map){
        return sepaCheckNet(getService().getCropSeed(map));
    }

    /**
     *农场管家列表接口
     */
    public Observable<List<StewardEntity>> getSteward(Map<String,String> map){
        return sepaCheckNet(getLoginService().getSteward(map));
    }

    /**
     * 获取农场标识牌列表接口
     */
    public Observable<List<SignboardEntity>> getSignboard(Map<String,String> map){
        return sepaCheckNet(getLoginService().getSignboard(map));
    }

    /**
     * 用户评论接口
     */
    @FormUrlEncoded
    @POST("comment/page")
    public Observable<List<CommentEntity>> getComment(Map<String,String> map){
        return sepaCheckNet(getLoginService().getComment(map));
    }

    /**
     * 默认方案接口
     */
    public Observable<RecommendSchemeEntity> getDefaultScheme(int productId){
        Map<String, String> map = new HashMap<>();
        map.put("productId", productId + "");
        return sepaCheckNet(getLoginService().getDefaultScheme(map));
    }

    /**
     * 自定义方案接口
     */
    public Observable<List<SchemeEntity>> getCustomScheme(Map<String,String> map){
        return sepaCheckNet(getLoginService().getCustomScheme(map));
    }

    /**
     * 获取产品加工接口
     * @param map
     * @return
     */
    public Observable<List<ProductProcessEntity>> getProductProcess(Map<String, String> map){
        return sepaCheckNet(getLoginService().getProductProcess(map));
    }

    /**
     * 提交生成订单
     */
    public Observable<OrderEntity> commitOrder(CommitOrderEntity entity){
        Map<String,String> map= new HashMap<>();
        String json = new Gson().toJson(entity);
        map.put("data", json);
        Log.e("aaaaaaaa",json);
        return sepaCheckNet(getLoginService().commitOrder(map));
    }

    /**
     * 查询产品初始化数据，包括 默认方案价格，默认标识牌
     */
    public Observable<PlanInitDataEntity> getPlanData(int productId){
        Map<String,String> map= new HashMap<>();
        map.put("productId", productId + "");
        return sepaCheckNet(getLoginService().getPlanData(map));
    }


}
