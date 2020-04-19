package com.jobnew.farm.data.repository;



import com.jobnew.farm.data.AddressBean;
import com.jobnew.farm.data.KindMyBean;
import com.jobnew.farm.data.helper.MultipartHelper;
import com.jobnew.farm.data.service.TestService;
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

import java.util.List;
import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/5/27.
 */

public class TestRepository extends BaseRepository<TestService> {
    private static TestRepository sInstance;


    @Override
    public Class<TestService> bindService() {
        return TestService.class;
    }

    public static TestRepository getIns() {
        if (sInstance == null) {
            synchronized (TestRepository.class) {
                if (sInstance == null) {
                    sInstance = new TestRepository();
                }
            }
        }
        return sInstance;
    }


    /**
     * 调用接口 获取验证码
     * @param map
     * @return
     */
    public Observable<BaseEntity> getCode(Map<String,String> map){
        return checkNet(getService().getCode(map));
    }

    /**
     * 调用接口 注册
     * @param map
     * @return
     */
    public Observable<BaseEntity<LoginEntity>> registerApp(Map<String,String> map){
        return checkNet(getService().registerApp(map));
    }

    /**
     * 调用接口 手机登录
     * @param map
     * @return
     */
    public Observable<BaseEntity<LoginEntity>> loginApp(Map<String,String> map){
        return checkNet(getService().loginApp(map));
    }
    /**
     * 调用接口 第三方登录
     * @param map
     * @return
     */
    public Observable<BaseEntity<LoginEntity>> ThreeLoginApp(Map<String,String> map){
        return checkNet(getService().ThreeLoginApp(map));
    }
    /**
     * 调用接口 退出登录
     * @param map
     * @return
     */
    public Observable<BaseEntity> OutApp(Map<String,String> map){
        return checkNet(getLoginService().OutApp(map));
    }
    /**
     * 调用接口 忘记密码
     * @param map
     * @return
     */
    public Observable<BaseEntity> ForgetPwd(Map<String,String> map){
        return checkNet(getService().ForgetPwd(map));
    }
    /**
    * 调用接口 修改用户密码
    * @param map
    * @return
    */
    public Observable<BaseEntity> UpdatePwd(Map<String,String> map){
        return checkNet(getLoginService().UpdatePwd(map));
    }
    /**
     * 调用接口 修改登录用户信息
     * @param map
     * @return
     */
    public Observable<BaseEntity> UserUpdate(Map<String,String> map){
        return checkNet(getLoginService().UserUpdate(map));
    }
    /**
     * 调用接口 添加地址
     * @param map
     * @return
     */
    public Observable<BaseEntity> AddAddress(Map<String,String> map){
        return checkNet(getLoginService().AddAddress(map));
    }
    /**
     * 调用接口 删除地址
     * @param map
     * @return
     */
    public Observable<BaseEntity> DeleteAddress(String id,Map<String,String> map){
        return checkNet(getLoginService().DeleteAddress(id,map));
    }
    /**
     * 调用接口 删除地址
     * @param map
     * @return
     */
    public Observable<BaseEntity> IsDefaultAddress(String id,Map<String,String> map){
        return checkNet(getLoginService().IsDefaultAddress(id,map));
    }
    /**
     * 调用接口 查询地址
     * @param map
     * @return
     */
    public Observable<BaseEntity<List<AddressBean>>> QueryAddress(Map<String,String> map){
        return checkNet(getLoginService().QueryAddress(map));
    }

    /**
     * 上传文件
     */
    public Observable<BaseEntity> upload(String folder,File file){
        return checkNet(getUploadService().upload(folder, MultipartHelper.toPart("upload",file)));
    }
    /**
     * 调用接口 修改地址
     * @param map
     * @return
     */
    public Observable<BaseEntity> ChangeAddress(Map<String,String> map){
        return checkNet(getLoginService().ChangeAddress(map));
    }
    /**
     * 调用接口 27.	查询用户相片接口
     * @param map
     * @return
     */
    public Observable<BaseEntity<List<UserPhotoBean>>> QueryUserPhoto(Map<String,String> map){
        return checkNet(getLoginService().QueryUserPhoto(map));
    }

    /**
     * 调用接口 28.	保存用户相片接口
     * @param map
     * @return
     */
    public Observable<BaseEntity<Integer>> SaveUserPhoto(Map<String,String> map){
        return checkNet(getLoginService().SaveUserPhoto(map));
    }

    /**
     * 调用接口 28.	删除用户相片接口
     * @param map
     * @return
     */
    public Observable<BaseEntity> DeleteUserPhoto(String  id,Map<String,String> map){
        return checkNet(getLoginService().DeleteUserPhoto(id,map));
    }
    /**
     * 调用接口 绑定手机
     * @param map
     * @return
     */
    public Observable<BaseEntity> BindPhone(Map<String,String> map){
        return checkNet(getLoginService().BindPhone(map));
    }
    /**
     * 调用接口 微信支付
     * @param map
     * @return
     */
    public Observable<BaseEntity<PayWeiXinInfo>> PayWinXin(Map<String,String> map){
        return checkNet(getLoginService().PayWeiXin(map));
    }
    /**
     * 调用接口 支付宝
     * @param map
     * @return
     */
    public Observable<BaseEntity> PayAli(Map<String,String> map){
        return checkNet(getLoginService().PayAli(map));
    }
    /**
     * 调用接口 支付宝
     * @param map
     * @return
     */
    public Observable<BaseEntity> Invest(Map<String,String> map){
        return checkNet(getLoginService().Invest(map));
    }

    /**
     * 集市一级分类子分类
     * @param map
     * @return
     */
    public Observable<BaseEntity<List<BazaarBig>>> getBazaarBig(Map<String,String> map){
        return checkNet(getService().getBazaarBig(map));
    }
    /**
     * 集市子分类里面的分类查询
     * @param map
     * @return
     */
    public Observable<BaseEntity<List<BazaarSmall>>> queryBazaarSmall(Map<String,String> map){
        return checkNet(getService().queryBazaarSmall(map));
    }

    public Observable<BaseEntity> getTVLogin(Map<String,String> map){
        return checkNet(getLoginService().getTVLogin(map));
    }

    public Observable<BaseEntity<List<KindMyBean>>> getKindMy(Map<String,String> map){
        return checkNet(getService().getKindMy(map));
    }

    public Observable<BaseEntity<List<OrderBazaarBean>>> getOrder(Map<String,String> map){
        return checkNet(getLoginService().getOder(map));
    }

    /**
     * 删除订单
     * @param map
     * @return
     */
    public Observable<BaseEntity> DeleteOrder(String id,Map<String,String> map){
        return checkNet(getLoginService().DeleteOrder(id,map));
    }
    /**
     * 查看订单编号
     * @param map
     * @return
     */
    public Observable<BaseEntity<LogisticsBean>> getLogistics(Map<String,String> map){
        return checkNet(getLoginService().getLogistics(map));
    }
    /**
     * 确认收货
     * @param map
     * @return
     */
    public Observable<BaseEntity> goGood(String id,Map<String,String> map){
        return checkNet(getLoginService().goGood(id,map));
    }

    /**
     * 申请售后
     * @param map
     * @return
     */
    public Observable<BaseEntity> getRefund(Map<String,String> map){
        return checkNet(getLoginService().getRefund(map));
    }

    /**
     * 添加评论
     * @param map
     * @return
     */
    public Observable<BaseEntity> addEvaluate(Map<String,String> map){
        return checkNet(getLoginService().addEvaluate(map));
    }

    /**
     * 查询我的售后列表
     * @param map
     * @return
     */
    public Observable<BaseEntity<List<AfterSalesBean>>> getAfterOrder(Map<String,String> map){
        return checkNet(getLoginService().getAfterOrder(map));
    }

    /**
     * 撤销申请
     * @param map
     * @return
     */
    public Observable<BaseEntity> canCelApplication(String id,Map<String,String> map){
        return checkNet(getLoginService().canCelApplication(id,map));
    }

    /**
     * 申请平台介入
     * @param map
     * @return
     */
    public Observable<BaseEntity> palatBind(Map<String,String> map){
        return checkNet(getLoginService().palatBind(map));
    }

    /**
     * 售后申请
     * @param map
     * @return
     */
    public Observable<BaseEntity<AfterDetailsBean>> getAfterDetail(String id, Map<String,String> map){
        return checkNet(getLoginService().getAfterDetail(id,map));
    }

    /**
     * 首页集市
     * @param map
     * @return
     */
    public Observable<BaseEntity<List<HomeBazzar>>> getBazaar(Map<String,String> map){
        return checkNet(getService().getBazaar(map));
    }

    /**
     * 微信充值
     * @param map
     * @return
     */
    public Observable<BaseEntity<TopUpBean>> getTopUp(Map<String,String> map){
        return checkNet(getLoginService().getTopUp(map));
    }

    /**
     * 明细接口
     * @param map
     * @return
     */
    public Observable<BaseEntity<List<TopUpDetial>>> getDetailPay(Map<String,String> map){
        return checkNet(getLoginService().getDetailPay(map));
    }

    /**
     * 支付宝充值
     * @param map
     * @return
     */
    public Observable<BaseEntity> PayAliChone(Map<String,String> map){
        return checkNet(getLoginService().PayAliChone(map));
    }

    /**
     * 获取个人账户信息
     * @param map
     * @return
     */
    public Observable<BaseEntity<WalletBean>> getBalance(Map<String,String> map){
        return checkNet(getLoginService().getBalance(map));
    }
}
