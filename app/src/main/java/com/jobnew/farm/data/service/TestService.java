package com.jobnew.farm.data.service;

import com.jobnew.farm.data.AddressBean;
import com.jobnew.farm.data.KindMyBean;
import com.jobnew.farm.entity.LoginEntity;
import com.jobnew.farm.entity.LogisticsBean;
import com.jobnew.farm.entity.PayWeiXinInfo;
import com.jobnew.farm.entity.TopUpBean;
import com.jobnew.farm.entity.TopUpDetial;
import com.jobnew.farm.entity.UserPhotoBean;
import com.jobnew.farm.entity.WalletBean;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.bazaar.AfterDetailsBean;
import com.jobnew.farm.entity.bazaar.AfterSalesBean;
import com.jobnew.farm.entity.bazaar.BazaarBig;
import com.jobnew.farm.entity.bazaar.BazaarSmall;
import com.jobnew.farm.entity.bazaar.HomeBazzar;
import com.jobnew.farm.entity.bazaar.OrderBazaarBean;
import com.jobnew.farm.module.personal.activity.order.AfterDetailsActivity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by wufenqiao on 2017/5/26.
 */

public interface TestService{
    @FormUrlEncoded
    @POST("user/user/sms")
    Observable<BaseEntity> getCode(@FieldMap Map<String,String> map);//获取验证码

    @FormUrlEncoded
    @POST("user/user/register")
    Observable<BaseEntity<LoginEntity>> registerApp(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/user/login")
    Observable<BaseEntity<LoginEntity>> loginApp(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/user/third/login")
    Observable<BaseEntity<LoginEntity>> ThreeLoginApp(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/user/logout")
    Observable<BaseEntity> OutApp(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/user/update/forget_pwd")
    Observable<BaseEntity> ForgetPwd(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/user/update/pwd")
    Observable<BaseEntity> UpdatePwd(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/user/update")
    Observable<BaseEntity> UserUpdate(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/user_address/add")
    Observable<BaseEntity> AddAddress(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/user_address/delete/{id}")
    Observable<BaseEntity> DeleteAddress(@Path("id") String id,@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/user_address/default/{id}")
    Observable<BaseEntity> IsDefaultAddress(@Path("id") String id,@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/user_address/query")
    Observable<BaseEntity<List<AddressBean>>> QueryAddress(@FieldMap Map<String,String> map);

    @Multipart
    @POST("upload/file/upload")
    Observable<BaseEntity> upload(@Query("folder") String folder, @Part MultipartBody.Part part);

    @FormUrlEncoded
    @POST("user/user_address/update")
    Observable<BaseEntity> ChangeAddress(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/user/photo/add")
    Observable<BaseEntity<Integer>> SaveUserPhoto(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/user/photo/query")
    Observable<BaseEntity<List<UserPhotoBean>>> QueryUserPhoto(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/user/photo/delete/{id}")
    Observable<BaseEntity> DeleteUserPhoto(@Path("id") String id,@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/user/bindphone")
    Observable<BaseEntity> BindPhone(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("order/pay/wxunified_order")
    Observable<BaseEntity<PayWeiXinInfo>> PayWeiXin(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("order/pay/alipay/payinfo")
    Observable<BaseEntity> PayAli(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/invest")
    Observable<BaseEntity> Invest(@FieldMap Map<String,String> map);
    
    @FormUrlEncoded
    @POST("market/category/market/query")
    Observable<BaseEntity<List<BazaarBig>>> getBazaarBig(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("market/product/query")
    Observable<BaseEntity<List<BazaarSmall>>> queryBazaarSmall(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("sso/user/qrcode/login")
    Observable<BaseEntity> getTVLogin(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("market/product/seed/search")
    Observable<BaseEntity<List<KindMyBean>>> getKindMy(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("order/market/query")
    Observable<BaseEntity<List<OrderBazaarBean>>> getOder(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("order/order/delete/{id}")
    Observable<BaseEntity> DeleteOrder(@Path("id") String id,@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("order/express/query")
    Observable<BaseEntity<LogisticsBean>> getLogistics(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("order/receive/{id}")
    Observable<BaseEntity> goGood(@Path("id") String id,@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("order/refund/apply")
    Observable<BaseEntity> getRefund(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("order/review/add")
    Observable<BaseEntity> addEvaluate(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("order/refund/query")
    Observable<BaseEntity<List<AfterSalesBean>>> getAfterOrder(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("order/refund/query/{id}")
    Observable<BaseEntity> canCelApplication(@Path("id") String id,@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("order/refund/contact/bind")
    Observable<BaseEntity> palatBind(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("order/refund/query/{id}")
    Observable<BaseEntity<AfterDetailsBean>> getAfterDetail(@Path("id") String id, @FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("market/product/home")
    Observable<BaseEntity<List<HomeBazzar>>> getBazaar( @FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("order/pay/wx_recharge")
    Observable<BaseEntity<TopUpBean>> getTopUp(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("order/pay/rechargelog")
    Observable<BaseEntity<List<TopUpDetial>>> getDetailPay(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("order/pay/ali_recharge")
    Observable<BaseEntity> PayAliChone(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("order/pay/getbalance")
    Observable<BaseEntity<WalletBean>> getBalance(@FieldMap Map<String,String> map);
}
