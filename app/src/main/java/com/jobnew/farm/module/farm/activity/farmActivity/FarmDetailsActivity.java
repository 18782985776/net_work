package com.jobnew.farm.module.farm.activity.farmActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.FarmProductEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.base.FarmDetialsEntity;
import com.jobnew.farm.module.farm.activity.ScanPictureActivity;
import com.jobnew.farm.module.farm.adapter.farmAdapter.FarmDetailsRecycleAdapter;
import com.jobnew.farm.module.home.activity.LocationActivity;
import com.jobnew.farm.module.personal.adapter.NetworkImageHolderView;
import com.jobnew.farm.utils.StarLinearLayout;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.TitleBarHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pub.devrel.easypermissions.EasyPermissions;
import uk.co.senab.photoview.log.LoggerDefault;

/**
 * Created by wangwenlang on 2017/5/24.
 * title:
 * note:
 */

public class FarmDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_title_left)
    TextView tvTitleLeft;
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.tv_showlocation)
    TextView tvShowlocation;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.img_call)
    ImageView imgCall;
    @BindView(R.id.tv_plant_filter)
    TextView tvPlantFilter;
    @BindView(R.id.tv_raise_filter)
    TextView tvRaiseFilter;
    @BindView(R.id.tv_farmProducts_filter)
    TextView tvFarmProductsFilter;
    @BindView(R.id.tv_activity_filter)
    TextView tvActivityFilter;
    @BindView(R.id.tv_catering_filter)
    TextView tvCateringFilter;
    @BindView(R.id.tv_stay_filter)
    TextView tvStayFilter;
    @BindView(R.id.recycleView_farm_details)
    RecyclerView recycleViewFarmDetails;
    @BindView(R.id.indicator_plant)
    View indicatorPlant;
    @BindView(R.id.indicator_rasie)
    View indicatorRasie;
    @BindView(R.id.indicator_product)
    View indicatorProduct;
    @BindView(R.id.indicator_activity)
    View indicatorActivity;
    @BindView(R.id.indicator_catering)
    View indicatorCatering;
    @BindView(R.id.indicator_stay)
    View indicatorStay;
    @BindView(R.id.tv_plant_main_tag)
    TextView tvPlantMainTag;
    @BindView(R.id.tv_raise_main_tag)
    TextView tvRaiseMainTag;
    @BindView(R.id.tv_main_product_tag)
    TextView tvProductMainTag;
    @BindView(R.id.tv_main_catering_tag)
    TextView tvCateringMainTag;
    @BindView(R.id.tv_main_activity_tag)
    TextView tvActivityMainTag;
    @BindView(R.id.tv_main_hotel_tag)
    TextView tvHotelMainTag;
    @BindView(R.id.other_filter)
    TextView otherFilter;//额外分类
    @BindView(R.id.tv_other_main_tag)
    TextView tvOtherMainTag;//额外主
    @BindView(R.id.farm_star)
    StarLinearLayout farmStar;
    @BindView(R.id.plant_rl)
    RelativeLayout plantRl;//为动态设置营业范围，控制指示器显示隐藏的
    @BindView(R.id.rasie_rl)
    RelativeLayout rasieRl;
    @BindView(R.id.product_rl)
    RelativeLayout productRl;
    @BindView(R.id.activity_rl)
    RelativeLayout activityRl;
    @BindView(R.id.cateing_rl)
    RelativeLayout cateingRl;
    @BindView(R.id.hotel_rl)
    RelativeLayout hotelRl;
    @BindView(R.id.other_rl)
    RelativeLayout motherRl;//额外Rl
    @BindView(R.id.other_stay_line)
    View otherStayLine;
    Unbinder unbinder;
    FarmDetailsRecycleAdapter farmDetailsRecycleAdapter;
    /**
     * 适配器数据源
     **/
    ArrayList<Object> arrayList;
    ArrayList<RelativeLayout> tabfilters;
    RelativeLayout currentRl;
    ArrayList<View> indicators;
    public String farmId;
    public String phone;
    Intent dialIntent;
    //public  String mainBusiness;
    public int pageNo = 1;
    public int pageSize = 20;
    public int categoryId;
    /**
     * 根据点击的选项，切换type,决定怎么处理数据加载*type==1表示主营，其他表示非主营
     */
    public String type = "1";
    public boolean isLoadingMore = false;
    public boolean isDateOver = false;//服务器没数据了
    //String businessScope;//营业范围
    List<FarmDetialsEntity.FarmBean.FarmBusinessListBean> farmBusinessList;//替代营业范围的实体集合
    boolean collectonTog = false;
    String farmName;
    /**
     * 收藏成功弹窗
     **/
    PopupWindow myPop;
    int lastVisibleItem;//最后可见项

    //    String stringContent;
//    String url;
    @Override
    protected int getLayout() {
        return R.layout.activity_farm_details;
    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        //  titleBar.setLeftText("成都");
        farmId = getIntent().getStringExtra("farmId");
        farmName = getIntent().getStringExtra("farmName");
        titleBar.setLeftTextDrawable(R.drawable.action_sheet_bottom, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        titleBar.setLeftTextDrawable(R.mipmap.ic_back_arrow);
        titleBar.getLeftTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        titleBar.getRightImageView().setImageResource(R.mipmap.farm_icon_collection);//收藏
        titleBar.getRightImageView().setVisibility(View.VISIBLE);
        whetherCollection(titleBar);

        titleBar.setRightTextDrawable(R.mipmap.farm_icon_share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    Toast.makeText(getApplication(),"分享",Toast.LENGTH_SHORT).show();
                // SharedUtil.setShareAction(FarmDetailsActivity.this, "分享测试", "快来种菜吧", "http://www.agriplan.cn/");
                //  PopUtils.getInstance().getPopShow(FarmDetailsActivity.this,R.id.farm_detail_super_layout,"https://www.baidu.com/");
                Intent intent = new Intent(FarmDetailsActivity.this, ShareUtilsActivity.class);
                intent.putExtra("stringContent", "我要分享");
                intent.putExtra("url", "https://www.baidu.com/");
                startActivityForResult(intent, 8);
                overridePendingTransition(R.anim.share_activity_in, R.anim.share_activity_out);
            }
        });//分享
    }

    /**
     * 点击添加和取消收藏
     **/
    private void handleCollection(TitleBarHelper titleBar) {
        HashMap<String, String> map = new HashMap<>();
        map.put("cid", farmId);
        map.put("ctype", "farm");
        MyFarmRepository.getIns().addCollection(map).subscribe(new DefaultSubscriber<BaseEntity>(this, false) {
            @Override
            public void _onNext(BaseEntity entity) {//提交成功了
                if (collectonTog) {//状态反转
                    collectonTog = false;
                    titleBar.getRightImageView().setImageResource(R.mipmap.farm_icon_collection);//收藏
                    //TODO提交状态到后台
                } else {
                    collectonTog = true;
                    titleBar.getRightImageView().setImageResource(R.mipmap.farm_icon_collection2);//收藏
                    //TODO提交状态到后台
                    showCollectionSucceed();
                }
            }
        });
    }

    /***点击刷新***/
    @Override
    public void errorChickData() {
        super.errorChickData();
        initFristPageData();
    }

    /***弹出收藏成功弹窗**/
    private void showCollectionSucceed() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_collection_succeed, null);
        myPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        myPop.setTouchable(true);
        myPop.setOutsideTouchable(true);
        myPop.showAtLocation(tvTitleLeft, Gravity.CENTER, 0, 0);
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

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1) {
                if (myPop.isShowing()) {
                    myPop.dismiss();
                }
            }
            return true;
        }
    });

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        if (requestCode == 8) {//表示是对话框返回的结果
            switch (resultCode) {
                case 2://失败
                    showMsg(data.getStringExtra("share_media") + "失败");
                    break;
                case 1://成功
                    showMsg(data.getStringExtra("share_media") + "成功");
                    break;
                case 3://取消
                    showMsg(data.getStringExtra("share_media") + "取消");
                    break;
            }
        }
    }

    @OnClick({R.id.plant_rl, R.id.rasie_rl, R.id.product_rl, R.id.activity_rl, R.id.cateing_rl, R.id.hotel_rl, R.id.img_call, R.id.tv_showlocation})
    public void CilckReport(View view) {
        try {
            switch (view.getId()) {
                case R.id.plant_rl://点击的第一个控件
                    if (currentRl == view) {
                        return;
                    }
                    categoryId = farmBusinessList.get(0).getCategoryId();
                    farmDetailsRecycleAdapter.setShowType(farmBusinessList.get(0).getShowType());//设置显示类型
                    showMsg(farmBusinessList.get(0).getShowType() + "");
                    justC(view);
                    switchTab((RelativeLayout) view);
                    initFristPageData();
                    isDateOver = false;
                    //TODO数据加载
                    // showLoading("正在加载");
                    break;
                case R.id.rasie_rl:
                    if (currentRl == view) {
                        return;
                    }
                    categoryId = farmBusinessList.get(1).getCategoryId();
                    farmDetailsRecycleAdapter.setShowType(farmBusinessList.get(1).getShowType());//设置显示类型
                    showMsg(farmBusinessList.get(1).getShowType() + "");
                    justC(view);
                    switchTab((RelativeLayout) view);
                    initFristPageData();
                    isDateOver = false;
                    //TODO数据加载
                    // showLoading("正在加载");
                    break;
                case R.id.product_rl:
                    if (currentRl == view) {
                        return;
                    }
                    categoryId = farmBusinessList.get(2).getCategoryId();
                    farmDetailsRecycleAdapter.setShowType(farmBusinessList.get(2).getShowType());//设置显示类型
                    showMsg(farmBusinessList.get(2).getShowType() + "");
                    justC(view);
                    switchTab((RelativeLayout) view);
                    initFristPageData();
                    isDateOver = false;
                    //TODO数据加载
                    // showLoading("正在加载");
                    break;
                case R.id.activity_rl:
                    if (currentRl == view) {
                        return;
                    }
                    categoryId = farmBusinessList.get(3).getCategoryId();
                    farmDetailsRecycleAdapter.setShowType(farmBusinessList.get(3).getShowType());//设置显示类型
                    showMsg(farmBusinessList.get(3).getShowType() + "");
                    justC(view);
                    isDateOver = false;
                    switchTab((RelativeLayout) view);
                    initFristPageData();
                    //TODO数据加载
                    // showLoading("正在加载");
                    break;
                case R.id.cateing_rl:
                    if (currentRl == view) {
                        return;
                    }
                    categoryId = farmBusinessList.get(4).getCategoryId();
                    farmDetailsRecycleAdapter.setShowType(farmBusinessList.get(4).getShowType());//设置显示类型
                    showMsg(farmBusinessList.get(4).getShowType() + "");
                    justC(view);
                    isDateOver = false;
                    switchTab((RelativeLayout) view);
                    initFristPageData();
                    // showLoading("正在加载");
                    //TODO数据加载
                    break;
                case R.id.hotel_rl:
                    if (currentRl == view) {
                        return;
                    }
                    categoryId = farmBusinessList.get(5).getCategoryId();
                    farmDetailsRecycleAdapter.setShowType(farmBusinessList.get(5).getShowType());//设置显示类型
                    showMsg(farmBusinessList.get(5).getShowType() + "");
                    justC(view);
                    isDateOver = false;
                    switchTab((RelativeLayout) view);
                    initFristPageData();
                    //showLoading("正在加载");
                    //TODO数据加载
                    break;
                case R.id.other_rl://额外添加防止后台多返回数据导致崩溃
                    if (currentRl == view) {
                        return;
                    }
                    categoryId = farmBusinessList.get(6).getCategoryId();
                    farmDetailsRecycleAdapter.setShowType(farmBusinessList.get(6).getShowType());//设置显示类型
                    showMsg(farmBusinessList.get(6).getShowType() + "");
                    justC(view);
                    isDateOver = false;
                    switchTab((RelativeLayout) view);
                    initFristPageData();
                    break;
                case R.id.img_call:
                    //Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                    //   showMsg("打电话跳转");;//phone
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
                case R.id.tv_showlocation:
                    //  showMsg("定位跳转");
                    Intent locationIntent = new Intent(FarmDetailsActivity.this, LocationActivity.class);
                    locationIntent.putExtra("farmLongitude", 105.006123);
                    locationIntent.putExtra("farmLatitude", 31.663215);
                    locationIntent.putExtra("farmName", farmName);
                    AppManager.jump(LocationActivity.class, locationIntent);
                    break;
            }
        } catch (NullPointerException e) {
            //  showMsg("空指针");
        }

    }

    /**
     * 看点击效果
     **/
    private void justC(View view) {
        for (int i = 0; i < tabfilters.size(); i++) {
            if (tabfilters.get(i) == view) {
                //  showMsg("点击位置时候"+i);
            }
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        super.onPermissionsGranted(requestCode, perms);
        startActivity(dialIntent);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        unbinder = ButterKnife.bind(this);
        setTitle(farmName, false);
        arrayList = new ArrayList();
        initTabfilters();
        initIndicator();
        farmDetailsRecycleAdapter = new FarmDetailsRecycleAdapter(arrayList, this, farmId);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycleViewFarmDetails.setLayoutManager(mLayoutManager);
        recycleViewFarmDetails.setAdapter(farmDetailsRecycleAdapter);
        switchTab(plantRl);
        initBanner();
        recycleViewFarmDetails.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (lastVisibleItem == farmDetailsRecycleAdapter.getItemCount() - 1 && !isDateOver && !isLoadingMore && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    loadMorePage();//这里多线程也要手动控制isLoadingMore
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    /***获取是否收藏**/
    private void whetherCollection(TitleBarHelper titleBar) {
        HashMap<String, String> map = new HashMap<>();
        map.put("cid", farmId);
        map.put("ctype", "farm");
        MyFarmRepository.getIns().getCollectionInfo(map).subscribe(new DefaultSubscriber<BaseEntity>(this, false) {
            @Override
            public void _onNext(BaseEntity entity) {
                if (entity.data.toString().equals("-1.0")) {
                    collectonTog = false;
                    titleBar.getRightImageView().setImageResource(R.mipmap.farm_icon_collection);//收藏
                } else {
                    collectonTog = true;
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

    /**
     * 加载更多数据
     **/
    private void loadMorePage() {
        isLoadingMore = true;
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNo", pageNo + "");
        map.put("categoryId", categoryId + "");
        map.put("farmId", farmId);
        map.put("pageSize", pageSize + "");
        isLoadingMore = true;
        MyFarmRepository.getIns().getFarmProductMsgByPage(map).subscribe(new DefaultSubscriber<BaseEntity<List<FarmProductEntity>>>(this, false) {
            @Override
            public void _onNext(BaseEntity<List<FarmProductEntity>> entity) {
                if (entity.data == null) {
                    isDateOver = true;
                    isLoadingMore = false;
                    return;
                }
                arrayList.addAll(entity.data);
                farmDetailsRecycleAdapter.notifyDataSetChanged();
                pageNo++;
                if (entity.data.size() < pageSize) {//说明数据加载完了
                    isDateOver = true;
                    //footerView.setVisibility(View.VISIBLE);
                    showMsg("没有更多数据");
                }
                isLoadingMore = false;
            }

            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
                isLoadingMore = false;
            }
        });
    }


    /**
     * 初始化下标指示器
     **/
    private void initIndicator() {
        indicators = new ArrayList<View>();
        indicators.add(indicatorPlant);
        indicators.add(indicatorRasie);
        indicators.add(indicatorProduct);
        indicators.add(indicatorActivity);
        indicators.add(indicatorCatering);
        indicators.add(indicatorStay);
        indicators.add(otherStayLine);
    }

    /**
     * 根据点击的选择器切换数据,如果点击的是当前的rl则不做任何改变
     * 如果不是当前Rl则设置pageNO=1，并加载数据
     **/
    private void switchTab(RelativeLayout checkRl) {
        checkRl.setVisibility(View.VISIBLE);
        pageNo = 1;
        if (checkRl == currentRl) {
            return;
        }
        switch (checkRl.getId()) {
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
            case R.id.other_rl://额外的，防止后台多穿数据导致崩溃
                changeCheckedTvColor(checkRl);
                showIndicator(6);
                break;
        }
    }

    /**
     * 控制下标指示器显示隐藏
     **/
    private void showIndicator(int i) {
        for (int j = 0; j < indicators.size(); j++) {
            indicators.get(j).setVisibility(View.GONE);
        }
        indicators.get(i).setVisibility(View.VISIBLE);
    }

    /**
     * 改变CheckedTextView颜色
     **/
    private void changeCheckedTvColor(RelativeLayout checkRl) {
        for (RelativeLayout colorRl : tabfilters) {
            if (checkRl == colorRl) {
                TextView cT = (TextView) colorRl.getChildAt(0);
                cT.setTextColor(Color.parseColor("#90b659"));
                if (currentRl != null) {
                    TextView fT = (TextView) currentRl.getChildAt(0);
                    fT.setTextColor(Color.parseColor("#383938"));
                }
            }
        }
        currentRl = checkRl;
    }

    private void initTabfilters() {
        tabfilters = new ArrayList<RelativeLayout>();
        tabfilters.add(plantRl);
        tabfilters.add(rasieRl);
        tabfilters.add(productRl);
        tabfilters.add(activityRl);
        tabfilters.add(cateingRl);
        tabfilters.add(hotelRl);
        tabfilters.add(motherRl);
    }

    /**
     * 初始化banner图
     */
    private void initBanner() {
        HashMap<String, String> map = new HashMap<>();
        map.put("lng", Constant.LONGITUDE+"");
        map.put("lat",Constant.LATITUDE+"");
        MyFarmRepository.getIns().getFarmDetailsMsgById(farmId, map).subscribe(new DefaultSubscriber<FarmDetialsEntity>(this, false) {
            @Override
            public void _onNext(FarmDetialsEntity entity) {
                ArrayList<String> list = new ArrayList<>();
                List<FarmDetialsEntity.FarmBean.ImagesBean> images = entity.getFarm().getImages();
                phone = entity.getFarm().getPhone();
                farmBusinessList = entity.getFarm().getFarmBusinessList();
                categoryId = farmBusinessList.get(0).getCategoryId();
                setGray();
              //  entity.getFarm().getDistance()
                if (entity.getFarm().getDistance() != 0) {
                    tvDistance.setText("距离 " + (int)entity.getFarm().getDistance()/1000+"km");
                    farmDetailsRecycleAdapter.setMyDistance(entity.getFarm().getDistance());
                }
                tvShowlocation.setText(entity.getFarm().getProvince() + entity.getFarm().getCity() + entity.getFarm().getArea() + entity.getFarm().getAddress());
                if (images != null && images.size() > 0) {
                    for (int i = 0; i < images.size(); i++) {
                        list.add(images.get(i).getImgUrl());
                    }
                } else {
                    list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495706920225&di=9484e62b44d7d79cfa6fdde9d76f6784&imgtype=0&src=http%3A%2F%2Fpic6.wed114.cn%2F20150728%2F2015072814481569663791.jpg");
                }
                convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, list);
                convenientBanner.startTurning(5000);    //设置自动切换（同时设置了切换时间间隔）
                convenientBanner.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //  showMsg(position + "");
                        Intent scanIntent = new Intent();
                        scanIntent.putStringArrayListExtra("pictures", list);
                        //startActivity(scanIntent);
                        AppManager.jump(ScanPictureActivity.class, scanIntent);
                    }
                });
                farmStar.setScore((int) (entity.getFarm().getGrade()));
            }
        });
    }

    /**
     * 选择器置灰
     **/
    private void setGray() {
        for (int i = 0; i < farmBusinessList.size(); i++) {//tabfilters前面依次显示出来
            TextView ct = (TextView) tabfilters.get(i).getChildAt(0);
            tabfilters.get(i).setVisibility(View.VISIBLE);
            tabfilters.get(i).setClickable(true);
            ct.setText(farmBusinessList.get(i).getCategoryName());//设置文字
            Log.d(TAG, "setGray: 设置文字" + farmBusinessList.get(i).getCategoryName());
            if (farmBusinessList.get(i).getType() == 1) {//主营业务
                tabfilters.get(i).getChildAt(1).setVisibility(View.VISIBLE);
            } else {//非主营
                tabfilters.get(i).getChildAt(1).setVisibility(View.GONE);
            }
            // int finalI = i;
//            tabfilters.get(i).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    categoryId=farmBusinessList.get(finalI).getCategoryId();
//                    if(currentRl==tabfilters.get(finalI)){//如果是当前tv和点击到的TV是一致的则返回，不做处理；
//                        return;
//                    }else{//每次切换过后，pageNo都会重置
//                        pageNo=1;
//                        currentRl=tabfilters.get(finalI);

//                    }
//                }
//            });
        }
        for (int i = farmBusinessList.size(); i < tabfilters.size(); i++) {//让CheckedTextView的父容器消失
            tabfilters.get(i).setVisibility(View.GONE);
        }
        farmDetailsRecycleAdapter.setShowType(farmBusinessList.get(0).getShowType());
        loading();
        initFristPageData();//上部初始化成功过后再初始化下半部分
        currentRl = tabfilters.get(0);
    }

    /**
     * 初始化第一页适配器数据源头*,里面是多线程执行的
     */
    private void initFristPageData() {
        isLoadingMore = true;
        HashMap<String, String> map = new HashMap<>();
        map.put("categoryId", categoryId + "");
        map.put("farmId", farmId);
        map.put("pageNo", pageNo + "");
        map.put("pageSize", pageSize + "");
        loading();
        MyFarmRepository.getIns().getFarmProductMsgByPage(map).subscribe(new DefaultSubscriber<BaseEntity<List<FarmProductEntity>>>(this, false) {
            @Override
            public void _onNext(BaseEntity<List<FarmProductEntity>> entity) {
                if (entity.data == null | entity.data.size() == 0) {
                    empty();
                    isLoadingMore = false;
                    return;
                }
                arrayList.clear();
                arrayList.addAll(entity.data);
                farmDetailsRecycleAdapter.notifyDataSetChanged();
                pageNo++;
                if (entity.data.size() < pageSize) {
                    isDateOver = true;
                }
                content();
                isLoadingMore = false;
            }

            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
