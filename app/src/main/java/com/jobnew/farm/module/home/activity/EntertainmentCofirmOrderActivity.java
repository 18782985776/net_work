package com.jobnew.farm.module.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.FarmHappyOrderEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.plan.FarmHappyOrderItemEntity;
import com.jobnew.farm.entity.plan.OrderEntity;
import com.jobnew.farm.module.farm.activity.farmActivity.PayOrderActivity;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.widget.AppManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EntertainmentCofirmOrderActivity extends BaseActivity {

    String key = "activity_farm";//活动订单
    int mfarmId;//农场id
    int productId;//活动id
    int buyNum;//人数
    String farmImg;//农场图片
    String farmName;//农场名字
    double produtcPrice;//产品价格
    String productImg;//产品图片
    String productName;//产品名称
    String pruductIntro;//产品介绍
    long soldOutDate;//活动结束时间
    @BindView(R.id.iv_farm)
    ImageView ivFarm;
    @BindView(R.id.iv_product)
    ImageView ivPruduct;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.go)
    RelativeLayout go;
    @BindView(R.id.tv_intro)
    TextView tvIntro;
    @BindView(R.id.num_tv)
    TextView numTv;
    @BindView(R.id.iv_reduce)
    ImageView ivReduce;
    @BindView(R.id.tv_buy)
    TextView tvBuy;
    @BindView(R.id.iv_increase)
    ImageView ivIncrease;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    @BindView(R.id.tv_farm_name)
    TextView tvFarmName;
    java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");

    @Override
    protected int getLayout() {
        return R.layout.activity_entertainment_cofirm_order;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("确认订单", true);
        Intent orderIntent = getIntent();
        farmImg = orderIntent.getStringExtra("farmImg");
        key = orderIntent.getStringExtra("key");
        farmName = orderIntent.getStringExtra("farmName");
        productImg = orderIntent.getStringExtra("productImg");
        productName = orderIntent.getStringExtra("productName");
        pruductIntro = orderIntent.getStringExtra("pruductIntro");
        mfarmId = orderIntent.getIntExtra("mfarmId", 0);
        productId = orderIntent.getIntExtra("productId", 0);
        buyNum = orderIntent.getIntExtra("buyNum", 0);
        produtcPrice = orderIntent.getDoubleExtra("produtcPrice", 0.01);
        soldOutDate = orderIntent.getLongExtra("soldOutDate", 10000000);
        setViewByIntent();
    }

    private void setViewByIntent() {
        GlideManager.loadRoundImg(farmImg,ivFarm);
        GlideManager.loadImg(productImg,ivPruduct);
        tvIntro.setText(pruductIntro);
        tvPrice.setText(produtcPrice+"");
        tvProductName.setText(productName);
        tvBuy.setText(buyNum+"");//+  -号中间的数字
        tvTotalPrice.setText(df.format(buyNum*produtcPrice));
        tvFarmName.setText(farmName);
        numTv.setText(1+"");

    }
    @OnClick({R.id.iv_increase,R.id.iv_reduce,R.id.tv_commit,R.id.iv_product})
    public void dealChlick(View v){
        switch (v.getId()){
            case R.id.iv_increase:
                buyNum++;
                updateView();
                break;
            case R.id.iv_reduce:
                if(buyNum==1){
                    showMsg("最低数量");
                    return;
                }
                 buyNum--;
                updateView();
                break;
            case R.id.tv_commit:
                makeOder();
                break;
            case R.id.iv_product:
                Intent jumpIntent=new Intent();
                jumpIntent.putExtra("id",productId);
                AppManager.jump(EntertainmentDetailsActivity.class,jumpIntent);
                finish();
                break;
        }
    }
    /***生成订单，成功后跳转支付界面***/
    public void makeOder()  {
        HashMap<String,String> map=new HashMap<>();
        FarmHappyOrderEntity farmHappyOrder=new FarmHappyOrderEntity();
        farmHappyOrder.setType(key);//订单类型
        farmHappyOrder.setFarmId(mfarmId);
        farmHappyOrder.setValidDate(soldOutDate);//消费日期
        List<FarmHappyOrderItemEntity> itemModels=new ArrayList<>();;
        FarmHappyOrderItemEntity  itemEntity=new FarmHappyOrderItemEntity();
        itemEntity.setProductId(productId);
        itemEntity.setQuantity(buyNum);
        itemModels.add(itemEntity);
        farmHappyOrder.setItemModels(itemModels);
        String json = new Gson().toJson(farmHappyOrder);
        map.put("data", json);
        MyFarmRepository.getIns().makeOrder(map).subscribe(new DefaultSubscriber<BaseEntity<OrderEntity>>(this,false) {
            @Override
            public void _onNext(BaseEntity<OrderEntity> entity) {
             //   OrderEntity orderEntity = entity.data;
//                //跳转确认订单
//                Intent intent=new Intent(EntertainmentCofirmOrderActivity.this, PayOrderActivity.class);
//                intent.putExtra(Constant.NAME,farmName);
//                intent.putExtra(Constant.FARM_IMG,farmImg);
//                intent.putExtra(Constant.ORDER,orderEntity);
//                OrderItemEntity orderItemEntity = orderEntity.orderItems.get(0);
//                intent.putExtra(Constant.ORDER_BODY,"网农公社—农家乐");
//                intent.putExtra(Constant.TYPE,"NORMAL");
//                AppManager.jump(ConfirmOrderActivity.class, intent);
                gotoPay(entity.data);
            }
        });
    }

    /***跳转王成支付界面***/
    private void gotoPay( OrderEntity mOrderEntity) {
        Intent intent = new Intent();
        intent.putExtra(Constant.ORDER_SN, mOrderEntity.sn);
        intent.putExtra(Constant.TOTAL_PRICE, mOrderEntity.amount);
        intent.putExtra(Constant.ORDER_BODY, "网农公社—活动");
        intent.putExtra(Constant.TYPE, "NORMAL");
        AppManager.jump(PayOrderActivity.class,intent);
    }


    /****更新数据**/
    private void updateView() {
        tvBuy.setText(buyNum+"");
        tvTotalPrice.setText(df.format(buyNum*produtcPrice));
        numTv.setText(buyNum+"");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
