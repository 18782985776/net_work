package com.jobnew.farm.module.home.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.FarmHappy.ProductEntity;
import com.jobnew.farm.entity.FarmHappyOrderEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.plan.FarmHappyOrderItemEntity;
import com.jobnew.farm.entity.plan.OrderEntity;
import com.jobnew.farm.entity.plan.OrderItemEntity;
import com.jobnew.farm.module.farm.activity.ConfirmOrderActivity;
import com.jobnew.farm.module.farm.activity.ScanPictureActivity;
import com.jobnew.farm.module.personal.adapter.NetworkImageHolderView;
import com.jobnew.farm.utils.SPUtils;
import com.jobnew.farm.utils.TimePickerViewUtils;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.TitleBarHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pub.devrel.easypermissions.EasyPermissions;

public class CateringDetailsActivity extends BaseActivity {
    Unbinder unbinder;
    @BindView(R.id.banner)
    ConvenientBanner banner;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.price_tv)
    TextView priceTv;
    @BindView(R.id.store_tv)
    TextView storeTv;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.distance_tv)
    TextView distanceTv;
    @BindView(R.id.img_call)
    ImageView imgCall;
    @BindView(R.id.reduce_img)
    ImageView reduceImg;
    @BindView(R.id.content_tv)
    TextView contentTv;
    @BindView(R.id.increase_img)
    ImageView increaseImg;
    @BindView(R.id.unit_tv)
    TextView unitTv;
    @BindView(R.id.date_tv)
    TextView dateTv;
    @BindView(R.id.introduce_tv)
    TextView introduceTv;
    @BindView(R.id.details_tv)
    TextView detailsTv;
    @BindView(R.id.total_price_tv)
    TextView totalPriceTv;
    @BindView(R.id.go_buy_bt)
    Button goBuyBt;
    @BindView(R.id.tv_update_date_name)
    TextView updateDateTv;
    ArrayList<String> bannerArray;
    int buyNum = 1;

    int productId;//商品id
    int mfarmId;//农场id;
    double price;//单价

    /**
     * 农家乐活动id
     */
    int farmId;
    /***农场名字**/
    String name;
    /**农场名字**/
    String farmImg;
    String key;/**订单类型**/
    /**
     * 农家乐活动名称
     **/
    String farmName;
    String phone;
    Intent dialIntent;

    String formatdate;
    java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");

    @Override
    protected int getLayout() {
        return R.layout.activity_catering_details;
    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        titleBar.setTitleMainText("餐饮详情");
        titleBar.getLeftTextView().setVisibility(View.VISIBLE);
        titleBar.getLeftTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /***请求页面数据***/
    public void getData() {
        HashMap<String, String> map = new HashMap<>();
        MyFarmRepository.getIns().getProductDetails(farmId + "", map).subscribe(new DefaultSubscriber<BaseEntity<ProductEntity>>(this, false) {
            @Override
            public void _onNext(BaseEntity<ProductEntity> entity) {
                ProductEntity productEntity = entity.data;
                productId = productEntity.getId();
                mfarmId = productEntity.getFarmId();
                price = productEntity.getPrice();
                name = productEntity.getFarm().getName();

                farmImg = productEntity.getFarm().getImg();
                if (productEntity.getUnitName() != null) {
                    priceTv.setText(productEntity.getPrice() + "/" + productEntity.getUnitName());
                } else {
                    priceTv.setText(productEntity.getPrice() + "");
                }
                totalPriceTv.setText(df.format(buyNum*price));
                storeTv.setText(productEntity.getStock() + "");
                ProductEntity.FarmBean farm = productEntity.getFarm();
                addressTv.setText(farm.getProvince() + farm.getCity() + farm.getArea() + farm.getAddress());
                long modifyDate = productEntity.getModifyDate();
                SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd");
                updateDateTv.setText(myFmt1.format(productEntity.getModifyDate()));
                if (productEntity.getIntro() != null) {
                    detailsTv.setText(productEntity.getIntro());
                }
                initBanner(productEntity);
                if (productEntity.getFarm().getDistance() != null) {
                    distanceTv.setText(productEntity.getFarm().getDistance() + "");
                }
                phone = productEntity.getFarm().getPhone();
            }
        });
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this);
        contentTv.setText(buyNum + "");
        Intent lastIntent = getIntent();
        farmId = lastIntent.getIntExtra("farmId", 0);
        farmName = lastIntent.getStringExtra("farmName");
        key=lastIntent.getStringExtra("key");//repast_agritmnt
        Log.d("我日", "onClick: "+"farmId:"+farmId);
        Log.d("我日", "onClick: "+"farmName:"+farmName);
        Log.d("我日", "onClick: "+"key:"+key);
        setTitle(farmName, true);
        nameTv.setText(farmName);
        SimpleDateFormat msformat=new SimpleDateFormat("yyyy-MM-dd");
        formatdate = msformat.format(new Date());
        dateTv.setText(formatdate);
        getData();
    }

    private void initBanner(ProductEntity productEntity) {
        List<ProductEntity.ProductImagesBean> productImages = productEntity.getProductImages();
        bannerArray = new ArrayList<>();
        if (productImages.size() > 0) {
            for (int i = 0; i < productImages.size(); i++) {
                bannerArray.add(productImages.get(i).getImgUrl());
            }
        } else {
            bannerArray.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495706920225&di=9484e62b44d7d79cfa6fdde9d76f6784&imgtype=0&src=http%3A%2F%2Fpic6.wed114.cn%2F20150728%2F2015072814481569663791.jpg");
        }

        banner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, bannerArray);
        banner.startTurning(2000);    //设置自动切换（同时设置了切换时间间隔）
        banner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //  showMsg("调查看大图！");
                if (bannerArray != null && bannerArray.size() > 0) {
                    Intent scanIntent = new Intent(CateringDetailsActivity.this, ScanPictureActivity.class);
                    scanIntent.putStringArrayListExtra("pictures", bannerArray);
                    startActivity(scanIntent);
                }
            }
        });

    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        super.onPermissionsGranted(requestCode, perms);
        startActivity(dialIntent);
    }

    @OnClick({R.id.img_call, R.id.reduce_img, R.id.increase_img, R.id.date_tv, R.id.go_buy_bt})
    public void dealClick(View view) {
        switch (view.getId()) {
            case R.id.img_call:
                if (phone != null) {
                    //跳转到拨号界面，同时传递电话号码
                    dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                    String[] mPermissionList = {Manifest.permission.CALL_PHONE,};
                    if (EasyPermissions.hasPermissions(this, mPermissionList)) {
                        startActivity(dialIntent);
                    } else {
                        EasyPermissions.requestPermissions(this, "需要开启必要的权限", 888, mPermissionList);
                    }
                }

                break;
            case R.id.reduce_img:
                if (buyNum > 1) {
                    buyNum--;
                }
                contentTv.setText(buyNum + "");
                totalPriceTv.setText(df.format(buyNum*price));
                break;
            case R.id.increase_img:
                buyNum++;
                contentTv.setText(buyNum + "");
                totalPriceTv.setText(df.format(buyNum*price));
                break;
            case R.id.date_tv:
                showTimePop();
                break;
            case R.id.go_buy_bt:
                showMsg("点击订购");
                makeOder();
                break;
        }
    }
    /***生成订单，成功后跳转支付界面***/
    public void makeOder()  {
        HashMap<String,String> map=new HashMap<>();
        FarmHappyOrderEntity  farmHappyOrder=new FarmHappyOrderEntity();
        farmHappyOrder.setType(key);//订单类型
        farmHappyOrder.setFarmId(mfarmId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(TextUtils.isEmpty(key)){
            Log.d("我日", "makeOder: "+"空");
        }else {
            Log.d("我日", "makeOder: "+key);
        }

        Date date = null;
        try {
            date = simpleDateFormat.parse(formatdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        farmHappyOrder.setValidDate(ts);//消费日期
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
                OrderEntity orderEntity = entity.data;
                //跳转确认订单
                Intent intent=new Intent();
                intent.putExtra(Constant.NAME,name);
                intent.putExtra(Constant.FARM_IMG,farmImg);
                intent.putExtra(Constant.ORDER,orderEntity);
                OrderItemEntity orderItemEntity = orderEntity.orderItems.get(0);
                intent.putExtra(Constant.ORDER_BODY,"网农公社—农家乐");
                intent.putExtra(Constant.TYPE,"NORMAL");
                SPUtils.put(Constant.PAY_TYPE, 3);
                AppManager.jump(ConfirmOrderActivity.class, intent);
            }
        });
    }

    private void showTimePop() {
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(new Date());
        Calendar endDate = Calendar.getInstance();
        endDate.set(2030, 12, 12);
        TimePickerViewUtils.getPvTime(mContext, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                formatdate= format.format(date);
                dateTv.setText(formatdate);
            }
        }).setRangDate(startDate, endDate).build().show();
    }



}
