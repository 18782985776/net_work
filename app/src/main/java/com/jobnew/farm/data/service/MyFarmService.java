package com.jobnew.farm.data.service;

import com.jobnew.farm.data.AddressBean;
import com.jobnew.farm.entity.AllBusniessEntity;
import com.jobnew.farm.entity.FarmHappy.FarmHappyOrderSpendEntity;
import com.jobnew.farm.entity.FarmHappy.ProductEntity;
import com.jobnew.farm.entity.FarmProductEntity;
import com.jobnew.farm.entity.GuidEntity;
import com.jobnew.farm.entity.HomeEntertainment.HomeEntertainmentDetailsEntity;
import com.jobnew.farm.entity.HomeEntertainment.HomeEntertainmentEntity;
import com.jobnew.farm.entity.LoginEntity;
import com.jobnew.farm.entity.NoteEntity;
import com.jobnew.farm.entity.PostFeeEntity;
import com.jobnew.farm.entity.SourceEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.base.FarmDetialsEntity;
import com.jobnew.farm.entity.base.FarmEntity;
import com.jobnew.farm.entity.base.HelpEntity;
import com.jobnew.farm.entity.base.LoveDonationEntity;
import com.jobnew.farm.entity.matchFeature.MatchDetailsEntity;
import com.jobnew.farm.entity.matchFeature.MatchEntity;
import com.jobnew.farm.entity.myfarm.CollectionLandEntity;
import com.jobnew.farm.entity.myfarm.CollectionProductEntity;
import com.jobnew.farm.entity.plan.OrderEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by wangwenlang on 2017/6/7.
 * title:
 * note:
 */

public interface MyFarmService {
    @FormUrlEncoded
    @POST("user/sms")
    Observable<BaseEntity> getCode(@FieldMap Map<String,String> map);//获取验证码

    @FormUrlEncoded
    @POST("user/register")
    Observable<BaseEntity<LoginEntity>> registerApp(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseEntity<LoginEntity>> loginApp(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/third/login")
    Observable<BaseEntity<LoginEntity>> ThreeLoginApp(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/logout")
    Observable<BaseEntity> OutApp(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/update/forget_pwd")
    Observable<BaseEntity> ForgetPwd(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/update/pwd")
    Observable<BaseEntity> UpdatePwd(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("merchant/farm/page")
    Observable<BaseEntity<List<FarmEntity>>> getFarmMsg(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST ("merchant/farm/query/{id}")//"id"
    Observable<BaseEntity<FarmDetialsEntity>> getFarmDetailsMsg(@Path("id") String id, @FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST ("user/product/page")
    Observable<BaseEntity<List<FarmProductEntity>>> getFarmProductMsgByPage(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST (" user/user_address/loves/query")
    Observable<BaseEntity<List<LoveDonationEntity>>> queryLoveAddress(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST ("product/category/root/query")
    Observable<BaseEntity> queryFarmProductCategory(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST ("user/feedback/add")
    Observable<BaseEntity> feedBackSubmit(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST ("merchant/farm/business")
    Observable<BaseEntity<AllBusniessEntity>> getAllBusinessType(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("config/init/android")
    Observable<BaseEntity<GuidEntity>> getGuidInfo(@FieldMap Map<String ,String> map);

    @FormUrlEncoded
    @POST("article/query")
    Observable <BaseEntity<List<HelpEntity>>> getHelpInfo(@FieldMap Map<String ,String> map);

    @FormUrlEncoded
    @POST("collect/hascollected")    //是否收藏
    Observable <BaseEntity> getCollectionInfo(@FieldMap Map<String ,String> map);

    @FormUrlEncoded
    @POST("collect/addcollect")    //取消和添加收藏,cid 是指农场id,或者农家乐id.....
    Observable <BaseEntity> addCollection(@FieldMap Map<String ,String> map);

    @FormUrlEncoded
    @POST("collect/collectlist")    //根据不同的String值返回不同的数据
    Observable <BaseEntity> getCollectionDataByTag(@FieldMap Map<String ,String> map);

    @FormUrlEncoded
    @POST("collect/collectlist")    //收藏农场数据
    Observable <BaseEntity<List<FarmEntity>>> getCollectionDataByFarm(@FieldMap Map<String ,String> map);

    @FormUrlEncoded
    @POST("collect/collectlist")    //收藏农家乐数据
    Observable <BaseEntity<List<FarmEntity>>> getCollectionDataByFarmHappy(@FieldMap Map<String ,String> map);

    @FormUrlEncoded
    @POST("collect/collectlist")    //收藏活动数据
    Observable <BaseEntity> getCollectionDataByEntertainment(@FieldMap Map<String ,String> map);


    @FormUrlEncoded
    @POST("collect/collectlist")    //收藏比赛数据
    Observable <BaseEntity> getCollectionDataByMatch(@FieldMap Map<String ,String> map);

    @FormUrlEncoded
    @POST("collect/collectlist")    //收藏专属土地数据
    Observable <BaseEntity<List<CollectionLandEntity>>> getCollectionDataByLand(@FieldMap Map<String ,String> map);

    @FormUrlEncoded
    @POST("collect/collectlist")    //收藏专属土地数据
    Observable <BaseEntity<List<CollectionProductEntity>>> getCollectionDataByProduct(@FieldMap Map<String ,String> map);

    //192.168.9.200/user/product/query/{id}
    @FormUrlEncoded
    @POST("user/product/query/{id}")    //农家乐里面什么餐饮详情，棋牌详情
    Observable <BaseEntity<ProductEntity>> getProductDetails(@Path("id") String id, @FieldMap Map<String ,String> map);

    @FormUrlEncoded
    @POST("order/order/market/add")    //提交农家乐订单
    Observable <BaseEntity<OrderEntity>> makeOrder(@FieldMap Map<String ,String> map);

    //查询农家乐订单
    @FormUrlEncoded
    @POST("order/market/query")//
    Observable<BaseEntity<List<FarmHappyOrderSpendEntity>>> queryOrder(@FieldMap Map<String,String> map);

    //查询活动列表
    @FormUrlEncoded
    @POST("activity/query")
    Observable<BaseEntity<List<HomeEntertainmentEntity>>> queryActivityList(@FieldMap Map<String,String> map);


    //查询活动列表
    @FormUrlEncoded
    @POST("activity/query/{id}")
    Observable<BaseEntity<HomeEntertainmentDetailsEntity>> queryActivityDetials(@Path("id") String id, @FieldMap Map<String,String> map);

    //查询活动留言
    @FormUrlEncoded
    @POST("activity/review/query")
    Observable<BaseEntity<NoteEntity>> queryActivityMessage(@FieldMap Map<String,String> map);


    //删除订单
    @FormUrlEncoded
    @POST("order/order/delete/{id}")
    Observable<BaseEntity> deleteOrder(@Path("id") String id, @FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/user_address/default/query")//查询默认收货地址
    Observable<BaseEntity<AddressBean>> queryDefalutAddress(@FieldMap Map<String,String> map);


    @FormUrlEncoded
    @POST("s/shop/product/querySourceInfo")//查询产品溯源
    Observable<BaseEntity<SourceEntity>> querySourceInfo(@FieldMap Map<String,String> map);


    @FormUrlEncoded
    @POST("activity/review/add")//添加留言
    Observable<BaseEntity> addMessage(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("match/query")//查询比赛列表
    Observable<BaseEntity<List<MatchEntity>>> queryMatchList(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("order/cart/item/add")//添加购物车
    Observable<BaseEntity> addShoppingCar(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("match/mine/query")//查询我的比赛
    Observable<BaseEntity<List<MatchEntity>>> queryMyMatch(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("match/query/{id}")//查询比赛详情
    Observable<BaseEntity<MatchDetailsEntity>> queryMatchDetails(@Path("id")String id, @FieldMap Map<String,String> map);

   //参加比赛
    @POST("match/signup/{id}")
    Observable<BaseEntity> signupMatch(@Path("id") String id);

    //查询运费计算方式
    @FormUrlEncoded
    @POST("order/order/shippingmethod/query")
    Observable<BaseEntity<List<PostFeeEntity>>> queryPostfee(@FieldMap Map<String,String> map);
}
