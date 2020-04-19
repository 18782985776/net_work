package com.jobnew.farm.data.repository;

import com.jobnew.farm.data.AddressBean;
import com.jobnew.farm.data.service.MyFarmService;
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

/**
 * Created by wangwenlang on 2017/6/7.
 * title:
 * note:
 */

public class MyFarmRepository extends BaseRepository<MyFarmService>{
    private static MyFarmRepository sInstance;


    @Override
    public Class<MyFarmService> bindService() {
        return MyFarmService.class;
    }

    public static MyFarmRepository getIns() {
        if (sInstance == null) {
            synchronized (MyFarmRepository.class) {
                if (sInstance == null) {
                    sInstance = new MyFarmRepository();
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
        return checkNet(getService().UpdatePwd(map));
    }
    /**
     * 调用接口 查询农场信息
     * @param map
     * @return
     */
    public  Observable<BaseEntity<List<FarmEntity>>> getFarmMsg(Map<String,String> map){
        return checkNet(getService().getFarmMsg(map));
    }
    /**
     * 根据农场id请数据  查询农场详情接口
     * @param map
     * @return
     */
    public  Observable<FarmDetialsEntity> getFarmDetailsMsgById(String id, Map<String,String> map){
        return sepaCheckNet(getService().getFarmDetailsMsg(id,map));
    }
    /**
     * 根据农场id请求农场产品分页
     * @param map
     * @return
     */
    public  Observable<BaseEntity<List<FarmProductEntity>>> getFarmProductMsgByPage(Map<String,String> map){
       // return checkNet(getService().getFarmLanMsg(map));
        return checkNet(getService().getFarmProductMsgByPage(map));
    }
    /**
     * 查询爱心收货地址
     * @param map
     * @return
     */
    public  Observable<BaseEntity<List<LoveDonationEntity>>> queryLoveAddress(Map<String,String> map){
        // return checkNet(getService().getFarmLanMsg(map));
        return checkNet(getLoginService().queryLoveAddress(map));
    }
    /***
     * 根据农场id查询农场主要产品分类*/
    public Observable<BaseEntity> queryFarmProductCategory(Map<String,String> map){
        return checkNet(getLoginService().queryFarmProductCategory(map));
    }


    /**提交意见反馈**/
    public Observable<BaseEntity> feedBackSubmit(Map<String,String> map){
        return checkNet(getLoginService().feedBackSubmit(map));
    }
    /**查询所有业务类型***/
    public Observable<AllBusniessEntity> getAllBusinessType(Map<String,String> map){
        return sepaCheckNet(getService().getAllBusinessType(map));
    }

    public Observable<GuidEntity> getGuidInfo(Map<String,String> map){
        return sepaCheckNet(getService().getGuidInfo(map));
    }
/**帮助说明，通过字段值：Aftersale和introduce来区别不同的入口***/
    public Observable <BaseEntity<List<HelpEntity>>> getHelpInfo(Map<String, String> map){
        return checkNet(getLoginService().getHelpInfo(map));
    }
    /**是否收藏**/
    public Observable<BaseEntity> getCollectionInfo(Map<String,String> map){
        return checkNet(getLoginService().getCollectionInfo(map));
    }
    /**添加收藏**/
    public Observable<BaseEntity> addCollection(Map<String,String> map){
        return checkNet(getLoginService().addCollection(map));
    }
    /***我的收藏数据接口，废弃***/
    public Observable<BaseEntity> getCollectionDataByTag(Map<String,String> map){
        return checkNet(getLoginService().getCollectionDataByTag(map));
    }

/***得到我的收藏农场数据**/
    public Observable<BaseEntity<List<FarmEntity>>> getCollectionDataByFarm(Map<String,String> map){
        return checkNet(getLoginService().getCollectionDataByFarm(map));
    }

    /***得到我的收藏农家乐数据**/
    public Observable<BaseEntity<List<FarmEntity>>> getCollectionDataByFarmHappy(Map<String,String> map){
        return checkNet(getLoginService().getCollectionDataByFarmHappy(map));
    }

    /***得到我的收藏活动数据**/
    public Observable<BaseEntity> getCollectionDataByEntertainment(Map<String,String> map){
        return checkNet(getLoginService().getCollectionDataByEntertainment(map));
    }

    /***得到我的收藏比赛数据**/
    public Observable<BaseEntity> getCollectionDataByMatch(Map<String,String> map){
        return checkNet(getLoginService().getCollectionDataByMatch(map));
    }

    /***得到我的收藏土地数据**/
    public Observable<BaseEntity<List<CollectionLandEntity>>> getCollectionDataByLand(Map<String,String> map){
        return checkNet(getLoginService().getCollectionDataByLand(map));
    }

    /***得到我的收藏农产品数据**/
    public Observable<BaseEntity<List<CollectionProductEntity>>> getCollectionDataByProduct(Map<String,String> map){
        return checkNet(getLoginService().getCollectionDataByProduct(map));
    }

    /*****农家乐商品详情接口****/
  public Observable<BaseEntity<ProductEntity>> getProductDetails(String id, Map<String,String> map){
      return checkNet(getLoginService().getProductDetails(id,map));
  }

  /***提交农家乐订单***/
     public Observable<BaseEntity<OrderEntity>>  makeOrder(Map<String,String> map){

         return checkNet(getLoginService().makeOrder(map));
     }

    /***查询农家乐订单****/
    public Observable<BaseEntity<List<FarmHappyOrderSpendEntity>>> queryOrder(Map<String,String> map){
        return checkNet(getLoginService().queryOrder(map));
    }

    //
    /***查询活动列表****/
    public Observable<BaseEntity<List<HomeEntertainmentEntity>>> queryActivityList(Map<String,String> map){
        return checkNet(getService().queryActivityList(map));
    }
    /***查询我的活动列表****/
    public Observable<BaseEntity<List<HomeEntertainmentEntity>>> queryMyActivityList(Map<String,String> map){
        return checkNet(getLoginService().queryActivityList(map));
    }

    /***查询活动详情*这和集市详情是同一个接口*曾雪强说的**/
    public Observable<BaseEntity<HomeEntertainmentDetailsEntity>> queryActivityDetials(String id, Map<String,String> map){
        return checkNet(getLoginService().queryActivityDetials(id,map));
    }


    /***查询活动留言****/
    public Observable<BaseEntity<NoteEntity>> queryActivityMessage(Map<String,String> map){
        return checkNet(getService().queryActivityMessage(map));
    }



    /***删除订单****/
    public Observable<BaseEntity> deleteOrder(String id, Map<String,String> map){
       return checkNet(getLoginService().deleteOrder(id,map));
    }


    /***查询默认收货地址****/
    public Observable<BaseEntity<AddressBean>> queryDefalutAddress(Map<String,String> map){
        return checkNet(getLoginService().queryDefalutAddress(map));
    }

    /***查询产品溯源****/
    public Observable<BaseEntity<SourceEntity>> querySourceInfo(Map<String,String> map){
        return checkNet(getLoginService().querySourceInfo(map));
    }

    /***添加留言****/
    public Observable<BaseEntity> addMessage(Map<String,String> map){

        return checkNet(getLoginService().addMessage(map));
    }

    /***查询比赛列表****/
    public Observable<BaseEntity<List<MatchEntity>>> queryMatchList(Map<String,String> map){
        return checkNet(getService().queryMatchList(map));
    }

    /***添加购物车****/
    public Observable<BaseEntity> addShoppingCar(Map<String,String> map){
        return checkNet(getLoginService().addShoppingCar(map));
    }

    /***查询我的比赛****/
    public Observable<BaseEntity<List<MatchEntity>>> queryMyMatch(Map<String,String> map){
        return checkNet(getLoginService().queryMyMatch(map));
    }

    /****查询比赛详情***/
    public Observable<BaseEntity<MatchDetailsEntity>> queryMatchDetails(String id, Map<String,String> map){
        return checkNet(getService().queryMatchDetails(id,map));
    }
    /***参加比赛**/
    public Observable<BaseEntity> signupMatch(String id){
        return checkNet(getLoginService().signupMatch(id));
    }
   //
    /***参加比赛**/
    public Observable<BaseEntity<List<PostFeeEntity>>> queryPostfee(Map<String,String> map){
        return checkNet(getLoginService().queryPostfee(map));
    }
}
