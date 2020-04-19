package com.jobnew.farm.module.home.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.base.activity.BaseRefreshAndLoadActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.FarmProductEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.base.FarmDetialsEntity;
import com.jobnew.farm.module.farm.activity.ScanPictureActivity;
import com.jobnew.farm.module.farm.activity.farmActivity.ShareUtilsActivity;
import com.jobnew.farm.module.home.adapter.FarmHappyDetatilsAdapter;
import com.jobnew.farm.module.personal.adapter.NetworkImageHolderView;
import com.jobnew.farm.utils.StarLinearLayout;
import com.jobnew.farm.widget.TitleBarHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;
import pub.devrel.easypermissions.EasyPermissions;

public class FarmHappyDetatilsActivity extends BaseActivity {
    Unbinder unbinder;
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
    @BindView(R.id.farm_happy_banner)
    ConvenientBanner farmHappyBanner;
    @BindView(R.id.farm_happy_star)
    StarLinearLayout farmHappyStar;
    @BindView(R.id.tv_showlocation)
    TextView tvShowlocation;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.img_call)
    ImageView imgCall;
    @BindView(R.id.tv_cating_filter)
    TextView tvCatingFilter;
    @BindView(R.id.tv_plant_main_tag)
    TextView tvPlantMainTag;
    @BindView(R.id.indicator_plant)
    View indicatorPlant;

    @BindView(R.id.plant_rl)
    RelativeLayout plantRl;
    @BindView(R.id.tv_chess_and_card_filter)
    TextView tvChessAndCardFilter;
    @BindView(R.id.tv_raise_main_tag)
    TextView tvRaiseMainTag;
    @BindView(R.id.indicator_rasie)
    View indicatorRasie;
    @BindView(R.id.rasie_rl)
    RelativeLayout rasieRl;
    @BindView(R.id.tv_finish_filter)
    TextView tvFinishFilter;
    @BindView(R.id.tv_main_product_tag)
    TextView tvMainProductTag;
    @BindView(R.id.indicator_product)
    View indicatorProduct;
    @BindView(R.id.product_rl)
    RelativeLayout productRl;
    @BindView(R.id.tv_activity_filter)
    TextView tvActivityFilter;
    @BindView(R.id.tv_main_activity_tag)
    TextView tvMainActivityTag;
    @BindView(R.id.indicator_activity)
    View indicatorActivity;
    @BindView(R.id.activity_rl)
    RelativeLayout activityRl;
    @BindView(R.id.tv_five_filter)
    TextView tvFiveFilter;
    @BindView(R.id.tv_main_catering_tag)
    TextView tvMainCateringTag;
    @BindView(R.id.indicator_catering)
    View indicatorCatering;
    @BindView(R.id.cateing_rl)
    RelativeLayout cateingRl;
    @BindView(R.id.tv_six_filter)
    TextView tvSixFilter;
    @BindView(R.id.tv_main_hotel_tag)
    TextView tvMainHotelTag;
    @BindView(R.id.indicator_stay)
    View indicatorStay;
    @BindView(R.id.hotel_rl)
    RelativeLayout hotelRl;
    @BindView(R.id.farm_happy_recycle)
    RecyclerView farmHappyRecycle;
    FarmHappyDetatilsAdapter adapter;
    ArrayList <FarmProductEntity> arrayList;//adapter数据源
    String phone;
    Intent dialIntent;
    boolean collectonTog=false;//收藏标志
    String farmName;
    int farmId;
    int pageNo=1;
    int pageSize=20;
    boolean isDateOver=false;
    int categoryId;
    RelativeLayout currentRl;//当前选中的relativeLayout;
    List<FarmDetialsEntity.FarmBean.FarmBusinessListBean> farmBusinessList;//主营业务实体类集合
    ArrayList<RelativeLayout> relativeLayoutArrays;
    ArrayList<View> indicators;
    /**收藏成功弹窗**/
    PopupWindow myPop;
    TitleBarHelper mtitleBar;
    LinearLayoutManager mLayoutManager;//为了获取滑动底部的位置；
    int lastVisibleItemPosition;//最后的滑动位置
    boolean isLoading;//是否正在加载；
    @Override
    protected int getLayout() {
        return R.layout.activity_farm_happy_detatils;
    }

    /**点击添加和取消收藏**/
    private void handleCollection(TitleBarHelper titleBar) {
        HashMap<String,String> map=new HashMap<>();
        map.put("cid",farmId+"");
        map.put("ctype","agritmnt");
        MyFarmRepository.getIns().addCollection(map).subscribe(new DefaultSubscriber<BaseEntity>(this,false) {
            @Override
            public void _onNext(BaseEntity entity) {//提交成功了
                if(collectonTog){//状态反转
                    collectonTog=false;
                    titleBar.getRightImageView().setImageResource(R.mipmap.farm_icon_collection);//收藏
                    //TODO提交状态到后台
                }else{
                    collectonTog=true;
                    titleBar.getRightImageView().setImageResource(R.mipmap.farm_icon_collection2);//收藏
                    //TODO提交状态到后台
                    showCollectionSucceed();
                }
            }
        });
    }
    /***获取是否收藏**/
    private void whetherCollection(TitleBarHelper titleBar) {
        HashMap<String,String> map=new HashMap<>();
        map.put("cid",farmId+"");
        map.put("ctype","agritmnt");
        MyFarmRepository.getIns().getCollectionInfo(map).subscribe(new DefaultSubscriber<BaseEntity>(this,false) {
            @Override
            public void _onNext(BaseEntity entity) {
                if(entity.data.toString().equals("-1.0")){
                    collectonTog=false;
                    titleBar.getRightImageView().setImageResource(R.mipmap.farm_icon_collection);//收藏
                }else {
                    collectonTog=true;
                    titleBar.getRightImageView().setImageResource(R.mipmap.farm_icon_collection2);//收藏
                }
                titleBar.getRightImageView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {//点击收藏按钮
                        handleCollection(titleBar);
                    }
                });
            }
        });
    }


    /***弹出收藏成功弹窗**/
    private void showCollectionSucceed() {
        View view= LayoutInflater.from(this).inflate(R.layout.layout_collection_succeed,null);
        myPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        myPop.setTouchable(true);
        myPop.setOutsideTouchable(true);
        myPop.showAtLocation(tvTitleLeft, Gravity.CENTER,0,0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    handler.sendEmptyMessage(1);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what==1){
                if(myPop.isShowing()){
                    myPop.dismiss();
                }
            }
            return true;
        }
    });

    @Override
    protected void initView(Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        farmName = intent.getStringExtra("farmName");
        farmId = intent.getIntExtra("farmId", -1);
        initRelativeLayoutArrays();
        setTitle(farmName,true);
        initBanner();
        initData();
        whetherCollection(mtitleBar);
        mLayoutManager = new LinearLayoutManager(this);
        farmHappyRecycle.setLayoutManager(mLayoutManager);
        adapter=new FarmHappyDetatilsAdapter(this,arrayList);
        farmHappyRecycle.setAdapter(adapter);
        farmHappyRecycle.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItemPosition==adapter.getItemCount()-1&&!isDateOver&&!isLoading){
                    //TODO 加载数据
                    loadmoreData();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }
/***初始化relativeLayout数组**/
    private void initRelativeLayoutArrays() {
        relativeLayoutArrays=new ArrayList<>();
        //plantRl   rasie_rl  productRl  activityRl  cateingRl  hotelRl
        relativeLayoutArrays.add(plantRl);
        relativeLayoutArrays.add(rasieRl);
        relativeLayoutArrays.add(productRl);
        relativeLayoutArrays.add(activityRl);
        relativeLayoutArrays.add(cateingRl);
        relativeLayoutArrays.add(hotelRl);
        indicators=new ArrayList<>();
        indicators.add(indicatorPlant);
        indicators.add(indicatorRasie);
        indicators.add(indicatorProduct);
        indicators.add(indicatorActivity);
        indicators.add(indicatorCatering);
        indicators.add(indicatorStay);
    }

    /****初始化头部视图****/
    private void initBanner() {
        HashMap map=new HashMap();
        map.put("lng", Constant.LONGITUDE+"");
        map.put("lat",Constant.LATITUDE+"");
        MyFarmRepository.getIns().getFarmDetailsMsgById(farmId+"",map).subscribe(new DefaultSubscriber<FarmDetialsEntity>(this,false) {
            @Override
            public void _onNext(FarmDetialsEntity entity) {
                List<FarmDetialsEntity.FarmBean.ImagesBean> images = entity.getFarm().getImages();//图片实体类
                phone = entity.getFarm().getPhone();
                if(entity.getFarm().getDistance()!=0){//距离
                    tvDistance.setText("距离 "+(int)(entity.getFarm().getDistance()/1000)+"km");
                }
                //定位
                tvShowlocation.setText(entity.getFarm().getProvince()+entity.getFarm().getCity()+entity.getFarm().getArea()+entity.getFarm().getAddress());
                farmHappyStar.setScore((int) (entity.getFarm().getGrade()));
                farmBusinessList = entity.getFarm().getFarmBusinessList();
                adapter.setMainBuess(farmBusinessList.get(0).getKey());//这样写为了避免第一次加载数据时，没有点击第一个控件导致key==null
                setGray();
                ArrayList<String> list=new ArrayList();
                if(images!=null&&images.size()>0){
                    for (int i=0;i<images.size();i++){
                        list.add(i,images.get(i).getImgUrl());
                    }
                }else {
                    list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495706920225&di=9484e62b44d7d79cfa6fdde9d76f6784&imgtype=0&src=http%3A%2F%2Fpic6.wed114.cn%2F20150728%2F2015072814481569663791.jpg");
                }

                farmHappyBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, list);
                farmHappyBanner.startTurning(2000);    //设置自动切换（同时设置了切换时间间隔）
                farmHappyBanner.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent scanIntent=new Intent(FarmHappyDetatilsActivity.this, ScanPictureActivity.class);
                        scanIntent.putStringArrayListExtra("pictures",list);
                        startActivity(scanIntent);
                    }
                });
            }
        });
    }
/***初始化第一个营业***/
    private void setGray() {
        for(int i=0;i<farmBusinessList.size();i++){//tabfilters前面依次显示出来
            TextView ct = (TextView) relativeLayoutArrays.get(i).getChildAt(0);
            relativeLayoutArrays.get(i).setVisibility(View.VISIBLE);
            relativeLayoutArrays.get(i).setClickable(true);
            ct.setText(farmBusinessList.get(i).getCategoryName());//设置文字
            Log.d(TAG, "setGray: 设置文字"+farmBusinessList.get(i).getCategoryName());
            if(farmBusinessList.get(i).getType()==1){//主营业务
                relativeLayoutArrays.get(i).getChildAt(1).setVisibility(View.VISIBLE);
            }else {//非主营
                relativeLayoutArrays.get(i).getChildAt(1).setVisibility(View.GONE);
            }
        }
        for (int i=farmBusinessList.size();i<relativeLayoutArrays.size();i++){//让CheckedTextView的父容器消失
            relativeLayoutArrays.get(i).setVisibility(View.GONE);
        }
        loading();
        categoryId=farmBusinessList.get(0).getCategoryId();
        initFristPageData();//上部初始化成功过后再初始化下半部分
        currentRl = relativeLayoutArrays.get(0);
    }

    /**初始化第一页适配器数据源头*,里面是多线程执行的*/
    private void initFristPageData() {
        HashMap<String,String> map=new HashMap<>();
        map.put("categoryId",categoryId+"");
        map.put("farmId",farmId+"");
        map.put("pageNo",pageNo+"");
        map.put("pageSize",pageSize+"");
        isLoading=true;
        MyFarmRepository.getIns().getFarmProductMsgByPage(map).subscribe(new DefaultSubscriber<BaseEntity<List<FarmProductEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<FarmProductEntity>> entity) {
                if(entity.data==null|entity.data.size()==0){
                    empty();
                    return;
                }
                arrayList.clear();
                arrayList.addAll(entity.data);
                adapter.notifyDataSetChanged();
                pageNo++;
                if(entity.data.size()<pageSize){
                    isDateOver=true;
                }
                content();
                isLoading=false;
            }

            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
                isLoading=false;
            }
        });
    }

    /***加载更多***/
    private void loadmoreData(){
        HashMap<String,String> map=new HashMap<>();
        map.put("categoryId",categoryId+"");
        map.put("farmId",farmId+"");
        map.put("pageNo",pageNo+"");
        map.put("pageSize",pageSize+"");
        isLoading=true;
        MyFarmRepository.getIns().getFarmProductMsgByPage(map).subscribe(new DefaultSubscriber<BaseEntity<List<FarmProductEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<FarmProductEntity>> entity) {
                if(entity.data==null|entity.data.size()==0){
                    isLoading=false;
                    isDateOver=true;
                    return;
                }
                arrayList.addAll(entity.data);
                adapter.notifyDataSetChanged();
                pageNo++;
                if(entity.data.size()<pageSize){
                    isDateOver=true;
                }
                content();
                isLoading=false;
            }

            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
                isLoading=false;
            }
        });
    }

    private void initData() {
        if(arrayList==null){
            arrayList=new ArrayList();
        }
//        for(int i=0;i<30;i++){
//            arrayList.add(new FarmProductEntity());
//        }
    }
    //plantRl   rasie_rl  productRl  activityRl  cateingRl  hotelRl
    @OnClick({R.id.img_call,R.id.plant_rl,R.id.rasie_rl,R.id.product_rl,R.id.activity_rl,R.id.cateing_rl,R.id.hotel_rl})
    public void dealClick(View view){
        switch (view.getId()){
            case R.id.img_call:
                if (phone == null) {
                    phone="18980421376";
                    //跳转到拨号界面，同时传递电话号码
                    dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                    String[] mPermissionList={Manifest.permission.CALL_PHONE,};
                    if (EasyPermissions.hasPermissions(this, mPermissionList)) {
                        startActivity(dialIntent);
                    } else {
                        EasyPermissions.requestPermissions(this, "需要开启必要的权限", 888, mPermissionList);
                    }

                }else {
                    dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                    String[] mPermissionList={Manifest.permission.CALL_PHONE,};
                    if (EasyPermissions.hasPermissions(this, mPermissionList)) {
                        startActivity(dialIntent);
                    } else {
                        EasyPermissions.requestPermissions(this, "需要开启必要的权限", 888, mPermissionList);
                    }
                }
                //plantRl   rasie_rl  productRl  activityRl  cateingRl  hotelRl
                break;
            case R.id.plant_rl:
                if(currentRl==view){
                    return;
                }
                categoryId= farmBusinessList.get(0).getCategoryId();
                adapter.setMainBuess(farmBusinessList.get(0).getKey());

                switchTab((RelativeLayout) view);
                initFristPageData();
                isDateOver=false;
                break;
            case R.id.rasie_rl:
                if(currentRl==view){
                    return;
                }
                categoryId= farmBusinessList.get(1).getCategoryId();
                adapter.setMainBuess(farmBusinessList.get(1).getKey());
                switchTab((RelativeLayout) view);
                initFristPageData();
                isDateOver=false;
                break;
            case R.id.product_rl:
                if(currentRl==view){
                    return;
                }
                categoryId= farmBusinessList.get(2).getCategoryId();
                adapter.setMainBuess(farmBusinessList.get(2).getKey());
                switchTab((RelativeLayout) view);
                initFristPageData();
                isDateOver=false;
                break;
            case R.id.activity_rl:
                if(currentRl==view){
                    return;
                }
                categoryId= farmBusinessList.get(3).getCategoryId();
                adapter.setMainBuess(farmBusinessList.get(3).getKey());
                switchTab((RelativeLayout) view);
                initFristPageData();
                isDateOver=false;
                break;
            case R.id.cateing_rl:
                if(currentRl==view){
                    return;
                }
                categoryId= farmBusinessList.get(4).getCategoryId();
                adapter.setMainBuess(farmBusinessList.get(4).getKey());
                switchTab((RelativeLayout) view);
                initFristPageData();
                isDateOver=false;
                break;
            case R.id.hotel_rl:
                if(currentRl==view){
                    return;
                }
                categoryId= farmBusinessList.get(5).getCategoryId();
                adapter.setMainBuess(farmBusinessList.get(5).getKey());
                switchTab((RelativeLayout) view);
                initFristPageData();
                isDateOver=false;
                break;
        }

    }


    /**改变CheckedTextView颜色**/
    private void changeCheckedTvColor(RelativeLayout checkRl) {
        for (RelativeLayout colorRl:relativeLayoutArrays){
            if(checkRl==colorRl){
                TextView cT= (TextView) colorRl.getChildAt(0);
                cT.setTextColor(Color.parseColor("#90b659"));
                if(currentRl!=null){
                    TextView fT = (TextView) currentRl.getChildAt(0);
                    fT.setTextColor(Color.parseColor("#383938"));
                }

            }
        }
        currentRl=checkRl;
    }
    /**控制下标指示器显示隐藏**/
    private void showIndicator(int i) {
        for(int j=0;j<indicators.size();j++){
            indicators.get(j).setVisibility(View.GONE);
        }
        indicators.get(i).setVisibility(View.VISIBLE);
    }


    /**根据点击的选择器切换数据,如果点击的是当前的rl则不做任何改变
     * 如果不是当前Rl则设置pageNO=1，并加载数据**/
    private void switchTab(RelativeLayout checkRl) {
        checkRl.setVisibility(View.VISIBLE);
        if(checkRl==currentRl){
            return;
        }
        pageNo=1;
        switch (checkRl.getId()){
            case R.id.plant_rl:
                changeCheckedTvColor(checkRl);
                showIndicator(0);
                //  ToDo分页查询农场土地接口调用
                break;
            case R.id.rasie_rl:
                changeCheckedTvColor(checkRl);
                showIndicator(1);
                // ToDo分页查询农场养殖接口调用
                break;
            case R.id.product_rl:
                changeCheckedTvColor(checkRl);
                showIndicator(2);
                // ToDo分页查询农场农产品接口调用
                break;
            case R.id.activity_rl:
                changeCheckedTvColor(checkRl);
                showIndicator(3);
                // ToDo分页查询农场活动接口调用
                break;
            case R.id.cateing_rl:
                changeCheckedTvColor(checkRl);
                showIndicator(4);
                // ToDo分页查询农场餐饮接口调用
                break;
            case R.id.hotel_rl:
                changeCheckedTvColor(checkRl);
                showIndicator(5);
                // ToDo分页查询农场住宿接口调用
                break;
        }
    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        if(farmName!=null){
            int farmId;
        }
        mtitleBar=titleBar;
        titleBar.getLeftTextView().setVisibility(View.VISIBLE);
        titleBar.getLeftTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        titleBar.getRightImageView().setImageResource(R.mipmap.farm_icon_collection);//收藏
        titleBar.getRightImageView().setVisibility(View.VISIBLE);

        titleBar.setRightTextDrawable(R.mipmap.farm_icon_share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FarmHappyDetatilsActivity.this,ShareUtilsActivity.class);
                intent.putExtra("stringContent","我要分享");
                intent.putExtra("url","https://www.baidu.com/");
                startActivityForResult(intent,8);
            }
        });//分享
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        super.onPermissionsGranted(requestCode, perms);
        startActivity(dialIntent);
    }
}
