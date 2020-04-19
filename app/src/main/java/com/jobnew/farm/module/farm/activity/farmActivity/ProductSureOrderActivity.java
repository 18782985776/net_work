package com.jobnew.farm.module.farm.activity.farmActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.AddressBean;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.FarmHappyOrderEntity;
import com.jobnew.farm.entity.HomeEntertainment.HomeEntertainmentDetailsEntity;
import com.jobnew.farm.entity.PostFeeEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.plan.FarmHappyOrderItemEntity;
import com.jobnew.farm.entity.plan.OrderEntity;
import com.jobnew.farm.module.farm.activity.ConfirmOrderActivity;
import com.jobnew.farm.module.home.activity.CateringDetailsActivity;
import com.jobnew.farm.module.personal.activity.AddressManage;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.utils.SPUtils;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.RundImageView;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductSureOrderActivity extends BaseActivity {

    @BindView(R.id.tv_title_left)
    TextView tvTitleLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.title_bar)
    LinearLayout titleBar;
    @BindView(R.id.tt)
    TextView tt;
    @BindView(R.id.tv_receiver)
    TextView tvReceiver;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.tv_farm_name)
    TextView tvFarmName;
    @BindView(R.id.img_product)
    ImageView imgProduct;
    @BindView(R.id.tv_product)
    TextView tvProduct;
    @BindView(R.id.tv_unitName)
    TextView tvUnitName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_intro)
    TextView tvIntro;
    @BindView(R.id.tv_product_num)
    TextView tvProductNum;
    @BindView(R.id.dd)
    TextView dd;
    @BindView(R.id.reduce_img)
    ImageView reduceImg;
    @BindView(R.id.num_tv)
    TextView numTv;
    @BindView(R.id.increase_img)
    ImageView increaseImg;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_post_cost)
    TextView tvPostCost;
    @BindView(R.id.tv_total_price2)
    TextView tvTotalPrice2;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.ll_farm)
    LinearLayout llFarm;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.img_farm)
    RundImageView imgFarm;
    int num=1;
    /***返回选择的收货地址***/
    AddressBean myAddressBean;
    FarmHappyOrderEntity farmHappyOrder;//
    Integer userAddressId;
    double price;//产品价格
    int quantity;//产品个数
    String unitName;//单位
    int productId;//产品id
    String intro;//产品介绍
    int farmId;//农场id
    String productName;//产品名称
    String productImg;//产品图片
    String farmName;//农场名字
    String farmImg;//农场图片
    String key="general";
    PostFeeEntity postFeeEntity=null;//运费实体类
   HashMap<String,String> postMap=new HashMap();
    java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");

    @Override
    protected int getLayout() {
        return R.layout.activity_product_sure_order;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("确认订单",true);
        Intent orderIntent = getIntent();
        price=orderIntent.getDoubleExtra("price",1.0);
        quantity=orderIntent.getIntExtra("quantity",1);
        num=quantity;
        unitName=orderIntent.getStringExtra("unitName");
        productId=orderIntent.getIntExtra("productId",3);
        intro=orderIntent.getStringExtra("intro");
        farmId=orderIntent.getIntExtra("farmId",1);
        productName=orderIntent.getStringExtra("productName");
        productImg=orderIntent.getStringExtra("priductImg");
        farmName=orderIntent.getStringExtra("farmName");
        farmImg=orderIntent.getStringExtra("farmImg");
        numTv.setText(quantity+"");
        netAddress();
        initOrderView();
    }
/***初始化订单界面视图***/
    private void initOrderView() {
        GlideManager.loadImg(farmImg,imgFarm);
        tvFarmName.setText(farmName);
        GlideManager.loadImg(productImg,imgProduct);
        tvProduct.setText(productName);
        tvPrice.setText(price+"");
        if(unitName!=null){
            tvUnitName.setText("/"+unitName);
        }
        tvIntro.setText(intro);
        //tvProductNum
        tvProductNum.setText(quantity+"");
        tvTotalPrice.setText(quantity*price+"");
        tvTotalPrice2.setText(quantity*price+"");
    }

    /***发起请求默认地址***/
    private void netAddress() {
        MyFarmRepository.getIns().queryDefalutAddress(new HashMap<String,String>()).
                subscribe(new DefaultSubscriber<BaseEntity<AddressBean>>(this,false) {
            @Override
            public void _onNext(BaseEntity<AddressBean> entity) {
                if(entity.data==null){//没有默认地址则返回
                    Intent intent=new Intent(ProductSureOrderActivity.this,AddressManage.class);
                    intent.putExtra(Constant.ADDRESS_MANAGE,104);
                    //  AppManager.jump(AddressManage.class,intent);
                    startActivityForResult(intent,663);
                    return;
                }
                myAddressBean=entity.data;
                initAddressView(myAddressBean);
                postMap.put(productId+"",myAddressBean.getId()+"");
                netForPostFee();
            }
        });
    }
/******根据网络请求或者选择地址选项返回addressBean初始化地址选项******/
    private void initAddressView(AddressBean myAddressBean) {
        tvReceiver.setText(myAddressBean.getContactName());
        tvPhone.setText(myAddressBean.getPhone());
        AddressBean.AreaEntity area = myAddressBean.getArea();
        String s=area.getMergerName()+area.getName()+myAddressBean.getAddress();
        addressTv.setText(s.replace(",",""));
        userAddressId=myAddressBean.getId();
    }

    @OnClick({R.id.ll_address,R.id.ll_farm,R.id.reduce_img,R.id.increase_img,R.id.tv_commit})
    public void dealClick(View view){
        switch (view.getId()) {
            case R.id.ll_address:
                Intent intent=new Intent(ProductSureOrderActivity.this,AddressManage.class);
                intent.putExtra(Constant.ADDRESS_MANAGE,104);
              //  AppManager.jump(AddressManage.class,intent);
                startActivityForResult(intent,663);
                break;
            case R.id.ll_farm:

                break;
            case R.id.reduce_img:
                if(num==1){
                    showMsg("达到最低");
                    return;
                }
                num--;
                numTv.setText(num+"");
                tvProductNum.setText(num+"");
                setPostFeeView();
                break;
            case R.id.increase_img:
                num++;
                numTv.setText(num+"");
                tvProductNum.setText(num+"");
                setPostFeeView();

                break;
            case R.id.tv_commit:
                makeOder();
                break;
        }
    }

    private void setPostFeeView() {
        if(postFeeEntity==null){
            tvTotalPrice.setText(df.format(num*price));
            tvTotalPrice2.setText(df.format(num*price));
            return;
        }
            if(num<postFeeEntity.getFreeWeight()|num==postFeeEntity.getFreeWeight()){
                tvPostCost.setText("（含运费：￥0.00)");
                tvTotalPrice.setText(df.format(num*price));
                tvTotalPrice2.setText(df.format(num*price));
            }else  if(num>postFeeEntity.getFreeWeight()&&num<postFeeEntity.getFirstWeight()){
                tvPostCost.setText("(含运费：￥"+postFeeEntity.getDefaultFirstPrice()+")");
                tvTotalPrice.setText(df.format(num*price+postFeeEntity.getDefaultFirstPrice()));
                tvTotalPrice2.setText(df.format(num*price+postFeeEntity.getDefaultFirstPrice()));
            }else {
                tvPostCost.setText("(含运费：￥"+(postFeeEntity.getDefaultFirstPrice()+
                        (num-postFeeEntity.getFirstWeight())*postFeeEntity.getDefaultContinuePrice())+")");
                tvTotalPrice.setText(df.format(num*price+(postFeeEntity.getDefaultFirstPrice()+
                        (num-postFeeEntity.getFirstWeight())*postFeeEntity.getDefaultContinuePrice())));
                tvTotalPrice2.setText(df.format(num*price+(postFeeEntity.getDefaultFirstPrice()+
                        (num-postFeeEntity.getFirstWeight())*postFeeEntity.getDefaultContinuePrice())));
            }


    }

    /***生成订单，成功后跳转支付界面***/
    public void makeOder()  {
        HashMap<String,String> map=new HashMap<>();
        FarmHappyOrderEntity  farmHappyOrder=new FarmHappyOrderEntity();
        farmHappyOrder.setType(key);//订单类型
        farmHappyOrder.setFarmId(farmId);
        if(userAddressId==null){
            showMsg("请选择地址");
            return;
        }else {
            farmHappyOrder.setUserAddressId(userAddressId+"");
        }
        List<FarmHappyOrderItemEntity> itemModels=new ArrayList<>();;
        FarmHappyOrderItemEntity  itemEntity=new FarmHappyOrderItemEntity();
        itemEntity.setProductId(productId);
        itemEntity.setQuantity(num);
        itemModels.add(itemEntity);
        farmHappyOrder.setItemModels(itemModels);
        String json = new Gson().toJson(farmHappyOrder);
        map.put("data", json);
        MyFarmRepository.getIns().makeOrder(map).subscribe(new DefaultSubscriber<BaseEntity<OrderEntity>>(this,false) {
            @Override
            public void _onNext(BaseEntity<OrderEntity> entity) {
                OrderEntity orderEntity = entity.data;
                //跳转确认订单
                gotoPay(orderEntity);
            }
        });
    }
/***跳转王成支付界面***/
    private void gotoPay( OrderEntity mOrderEntity) {
        Intent intent = new Intent();
        intent.putExtra(Constant.ORDER_SN, mOrderEntity.sn);
        intent.putExtra(Constant.TOTAL_PRICE, mOrderEntity.amount);
        intent.putExtra(Constant.ORDER_BODY, "网农公社—集市");
        intent.putExtra(Constant.TYPE, "NORMAL");
        SPUtils.put(Constant.PAY_TYPE, 4);
        AppManager.jump(PayOrderActivity.class,intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==663){
            if(resultCode==RESULT_OK){
               myAddressBean= (AddressBean) data.getSerializableExtra(Constant.ADDRESS_MANAGE);
                initAddressView(myAddressBean);
                postMap.put(productId+"",myAddressBean.getId()+"");
                netForPostFee();
            }
        }
    }
/*****请求邮费***/
    private void netForPostFee() {
        HashMap<String,String> map=new HashMap();
        String s = new Gson().toJson(postMap);
        map.put("data",s);
       MyFarmRepository.getIns().queryPostfee(map).subscribe(new DefaultSubscriber<BaseEntity<List<PostFeeEntity>>>(this,false) {
           @Override
           public void _onNext(BaseEntity<List<PostFeeEntity>> entity) {

               List<PostFeeEntity> data = entity.data;
               if(data!=null){
                   postFeeEntity = data.get(0);
               }
           }
       });

    }
}
