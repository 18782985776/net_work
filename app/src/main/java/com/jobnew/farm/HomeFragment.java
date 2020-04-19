package com.jobnew.farm;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jobnew.farm.adapter.MyCoverFlowAdapter;
import com.jobnew.farm.base.fragment.BaseFragment;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.coverFlowView.CoverFlowView;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.data.rxbus.MsgEvent;
import com.jobnew.farm.data.rxbus.RxBus;
import com.jobnew.farm.entity.HomeBannerBean;
import com.jobnew.farm.entity.HomeEntertainment.HomeEntertainmentEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.base.FarmEntity;
import com.jobnew.farm.entity.bazaar.HomeBazzar;
import com.jobnew.farm.module.exclusive.ExclusiveLandActivity;
import com.jobnew.farm.module.farm.activity.farmActivity.FarmDetailsActivity;
import com.jobnew.farm.module.home.activity.EntertainmentDetailsActivity;
import com.jobnew.farm.module.home.activity.FarmHappyActivity;
import com.jobnew.farm.module.home.activity.FarmHappyDetatilsActivity;
import com.jobnew.farm.module.home.activity.HomeEntertainmentActivity;
import com.jobnew.farm.module.home.activity.MatchFeatureActivity;
import com.jobnew.farm.module.home.activity.ScanActivity;
import com.jobnew.farm.module.home.activity.SearchActivityForHome;
import com.jobnew.farm.module.loginAndRegister.activity.LoginActivity;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.utils.SPUtils;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.wxapi.ChangeListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by wangcheng on 2017/5/22.
 * title：首页
 * note：
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.ll_TopTitle)
    LinearLayout llTopTitle;
    @BindView(R.id.coverflow_view)
    CoverFlowView coverFlowView;
    @BindView(R.id.txt_banner_name)
    TextView txtBannerName;
    @BindView(R.id.ll_search)
    LinearLayout layoutSearch;
    @BindView(R.id.txt_location)
    TextView textViewLocation;
    @BindView(R.id.ll_activity_ll)
    LinearLayout llActivityLl;
    Unbinder unbinder1;
    @BindView(R.id.ll_generated_ll)
    LinearLayout llGeneratedLl;
    @BindView(R.id.ll_bazaar_ll)
    LinearLayout llBazaarLl;
    private AMapLocationClient aMapLocationClient = null;
    private AMapLocationListener mAMapLocationListener = null;
    AMapLocation mlocationMapLocation;


    private List<HomeBannerBean> channelBeanList;

    public static final String[] channelNames = {"图片1", "图片2", "图片3", "图片4", "图片5"};

    private ChangeListener changeListener = null;

    /***设置回调***/
    public void setChangeListener(ChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    private void initMap() {
        aMapLocationClient = new AMapLocationClient(getActivity());
        AMapLocationClientOption mAMapLocationClientOption = new AMapLocationClientOption();
        mAMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        /***设置只定位一次**/
        mAMapLocationClientOption.setOnceLocation(true);
        aMapLocationClient.setLocationOption(mAMapLocationClientOption);
        mAMapLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation.getErrorCode() == 0) {
                    Log.d("主定位", "onLocationChanged: " + aMapLocation.getAddress());
                    Log.d("主定位", "onLocationChanged: " + "------------------------------------------------8118");
                    //  String mAddress = new Gson().toJson(aMapLocation);

                    Log.d("主定位", "onLocationChanged: " + aMapLocation.getLatitude());
                    Log.d("主定位", "onLocationChanged: " + aMapLocation.getLongitude() + "经度");
                    Constant.LATITUDE = aMapLocation.getLatitude();
                    Constant.LONGITUDE = aMapLocation.getLongitude();
                    Constant.PROVINECE = aMapLocation.getProvince();
                    Constant.CITY = aMapLocation.getCity();
                    Constant.ADDRESS = aMapLocation.getAddress();
                    if (textViewLocation == null) {
                        Log.d("主定位", "onLocationChanged: " + "没有设置定位");
                    } else {
                        Log.d("主定位", "onLocationChanged: " + "设置了定位" + Constant.PROVINECE);
                        textViewLocation.setText(aMapLocation.getCity());
                    }
                } else {
                    showMsg("定位失败");
                    Log.d("定位", "onLocationChanged: " + aMapLocation.getErrorCode());
                    Log.d("定位", "onLocationChanged: " + aMapLocation.getErrorInfo());
                }
                //设置接口回调
            }
        };
        aMapLocationClient.setLocationListener(mAMapLocationListener);
        aMapLocationClient.startLocation();
    }


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }


    Timer timer = null;

    @Override
    protected void initView(Bundle bundle, View view) {
        setTitleBarPadding(llTopTitle);
        initBannerData();
        initActivity();
        initGenerated();
        initBazaar();
        registerRxBus();
        statTime();
        initMap();
    }

    List<HomeBazzar> bazaarData;

    /**
     * 初始化集市数据
     */
    private void initBazaar() {
        TestRepository.getIns().getBazaar(new HashMap<String, String>()).subscribe(new DefaultSubscriber<BaseEntity<List<HomeBazzar>>>(this, false) {
            @Override
            public void _onNext(BaseEntity<List<HomeBazzar>> entity) {
                List<HomeBazzar> data = entity.data;
                if (data == null) {
                    return;
                }
                if (data.size() < 4) {
                    return;
                }
                //存放banner图
                SPUtils.put("bazaarData", new Gson().toJson(entity.data));
                bazaarData = entity.data;
                initBazaarData();
            }
        });
    }


    List<FarmEntity> generatedData;

    /**
     * 初始化农家乐
     */
    private void initGenerated() {
        HashMap<String, String> map = new HashMap<>();
        map.put("province", Constant.PROVINECE);
        map.put("city", Constant.CITY);
        map.put("latitude", Constant.LATITUDE + "");
        map.put("longitude", Constant.LONGITUDE + "");
        map.put("pageNo", "1");
        map.put("pageSize", "4");
        map.put("sort", "grade");
        map.put("orderBy", "asc");
        map.put("type", "agrimnt");
        MyFarmRepository.getIns().getFarmMsg(map).subscribe(new DefaultSubscriber<BaseEntity<List<FarmEntity>>>(this, false) {
            @Override
            public void _onNext(BaseEntity<List<FarmEntity>> entity) {
                List<FarmEntity> data = entity.data;
                if (data == null) {
                    return;
                }
                if (data.size() < 4) {
                    return;
                }
                //存放banner图
                SPUtils.put("generatedData", new Gson().toJson(entity.data));
                generatedData = entity.data;
                initGeneratedData();
            }
        });
    }


    /**
     * 初始化活动数据
     */
    private void initActivity() {
        HashMap<String, String> map = new HashMap<>();
        map.put("pageSize", "4");
        map.put("pageNo", "1");
        map.put("orderBy", "desc");
        map.put("orderType", "popularity");
        map.put("latitude", Constant.LATITUDE + "");
        map.put("longitude", Constant.LONGITUDE + "");
        MyFarmRepository.getIns().queryActivityList(map).subscribe(new DefaultSubscriber<BaseEntity<List<HomeEntertainmentEntity>>>(this, false) {
            @Override
            public void _onNext(BaseEntity<List<HomeEntertainmentEntity>> entity) {
                List<HomeEntertainmentEntity> data = entity.data;
                if (data == null) {
                    return;
                }
                if (data.size() < 3) {
                    return;
                }
                //存放banner图
                SPUtils.put("activityData", new Gson().toJson(entity.data));
                dataActivity = entity.data;
                initActivityData();
            }
        });
    }

    /**
     * 初始化集市接口
     */
    private void initBazaarData() {
        for (int i = 0; i < llBazaarLl.getChildCount(); i++) {
            if (llBazaarLl.getChildAt(i) instanceof LinearLayout) {
                LinearLayout childAt = (LinearLayout) llBazaarLl.getChildAt(i);
                for (int j = 0; j < childAt.getChildCount(); j++) {
                    if (childAt.getChildAt(j) instanceof ImageView) {
                        GlideManager.loadImg(bazaarData.get(i).getPImg(), (ImageView) childAt.getChildAt(j));
                    } else if (childAt.getChildAt(j) instanceof TextView) {
                        ((TextView) childAt.getChildAt(j)).setText(bazaarData.get(i).getName());
                    } else if (childAt.getChildAt(j) instanceof LinearLayout) {
                        ((TextView) ((LinearLayout) childAt.getChildAt(j)).getChildAt(0)).setText("￥" + bazaarData.get(i).getPrice());
                        ((TextView) ((LinearLayout) childAt.getChildAt(j)).getChildAt(1)).setText("/" + bazaarData.get(i).getUnitName());
                    }
                }
                int finalI = i;
                childAt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (MyApplication.isLogin()) {
                            Intent intent = new Intent();
                            intent.putExtra("farmId", bazaarData.get(finalI).getId() + "");
                            intent.putExtra("farmName", bazaarData.get(finalI).getName());
                            AppManager.jump(FarmDetailsActivity.class, intent);
                        } else {
                            AppManager.jump(LoginActivity.class);
                        }
                    }
                });
            }
        }
    }

    /**
     * 加载农家乐数据
     */
    private void initGeneratedData() {
        for (int i = 0; i < llGeneratedLl.getChildCount(); i++) {
            if (llGeneratedLl.getChildAt(i) instanceof LinearLayout) {
                LinearLayout childAt = (LinearLayout) llGeneratedLl.getChildAt(i);
                for (int j = 0; j < childAt.getChildCount(); j++) {
                    if (childAt.getChildAt(j) instanceof ImageView) {
                        GlideManager.loadImg(generatedData.get(i).getImg(), (ImageView) childAt.getChildAt(j));
                    } else if (childAt.getChildAt(j) instanceof TextView) {
                        ((TextView) childAt.getChildAt(j)).setText(generatedData.get(i).getName());
                    }
                }
                int finalI = i;
                childAt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int farmId = generatedData.get(finalI).getId();
                        String farmName = generatedData.get(finalI).getName();
                        Intent intent = new Intent();
                        intent.putExtra("farmName", farmName);
                        intent.putExtra("farmId", farmId);
                        AppManager.jump(FarmHappyDetatilsActivity.class, intent);
                    }
                });
            }
        }
    }

    private List<HomeEntertainmentEntity> dataActivity;

    /**
     * 初始化活动数据
     *
     * @param
     */
    private void initActivityData() {
        for (int i = 0; i < llActivityLl.getChildCount(); i++) {
            if (llActivityLl.getChildAt(i) instanceof LinearLayout) {
                LinearLayout childAt = (LinearLayout) llActivityLl.getChildAt(i);
                for (int j = 0; j < childAt.getChildCount(); j++) {
                    if (childAt.getChildAt(j) instanceof ImageView) {
                        GlideManager.loadImg(dataActivity.get(i).getPImg(), (ImageView) childAt.getChildAt(j));
                    } else if (childAt.getChildAt(j) instanceof TextView) {
                        ((TextView) childAt.getChildAt(j)).setText(dataActivity.get(i).getName());
                    }
                }
                int finalI = i;
                childAt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent jumpIntent = new Intent();
                        jumpIntent.putExtra("id", dataActivity.get(finalI).getId());
                        AppManager.jump(EntertainmentDetailsActivity.class, jumpIntent);
                    }
                });
            }
        }
    }

    Disposable subscribe;

    /**
     * 注册rxbus
     */
    private void registerRxBus() {
        Flowable<MsgEvent> f = RxBus.getInstance().register(MsgEvent.class);
        subscribe = f.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MsgEvent>() {
                    @Override
                    public void accept(@NonNull MsgEvent msgEvent) throws Exception {
                        if (msgEvent.getRequest() == 124) {
                            if (msgEvent.getType() == 1) {
                                if (coverFlowView != null) {
//                                    coverFlowView.gotoForward();
                                    coverFlowView.setSelection(2, false);
                                }
                                statTime();
                            } else {
                                stopTime();
                                if (coverFlowView != null) {
                                    coverFlowView.gotoPrevious();
                                }
                            }
                        }
                    }
                });
    }

    private void stopTime() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        statTime();
    }

    @Override
    public void onStop() {
        super.onStop();
        stopTime();
    }

    private void statTime() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    Message message = new Message();
                    message.what = 0;
                    mHandler.sendMessage(message);
                }
            }, 5000, 5000);
        }
    }

    private MyCoverFlowAdapter adapter;

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopTime();
        if (subscribe != null && !subscribe.isDisposed()) {
            subscribe.dispose();
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            // TODO Auto-generated method stub
            if (msg.what == 0) {
                if (coverFlowView != null) {
                    coverFlowView.gotoForward();
                }
            }
        }
    };
    List<FarmEntity> datas;

    /**
     * 初始化banner数据图
     */
    private void initBannerData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("longitude", Constant.LONGITUDE + "");
        map.put("latitude", Constant.LATITUDE + "");
        map.put("sort", "normal");
        map.put("pageNo", "1");
        map.put("pageSize", "5");
        map.put("orderBy", "ase");
        map.put("province", Constant.PROVINECE);
        map.put("city", Constant.CITY);
        MyFarmRepository.getIns().getFarmMsg(map).subscribe(new DefaultSubscriber<BaseEntity<List<FarmEntity>>>(this, false) {
            @Override
            public void _onNext(BaseEntity<List<FarmEntity>> entity) {
                if (entity.data == null) {
                    return;
                }
                datas = entity.data;
                if (datas.size() <= 4) {
                    return;
                }
                channelBeanList = new ArrayList<>();
                //存放banner图
                SPUtils.put("bannerData", new Gson().toJson(entity.data));
                for (int i = 0; i < channelNames.length; i++) {
                    HomeBannerBean channelBean = new HomeBannerBean();
                    channelBean.setImg(datas.get(i).getImg());
                    channelNames[i] = datas.get(i).getName();
                    channelBean.setName(channelNames[i]);
                    channelBeanList.add(channelBean);
                }
                initBanner();
            }
        });
    }

    ;


    @Override
    public void noNet() {
        String bannerData = SPUtils.get("bannerData", "1");
        if (!bannerData.equals("1")) {
            datas = new Gson().fromJson(bannerData, new TypeToken<List<FarmEntity>>() {
            }.getType());
            channelBeanList = new ArrayList<>();
            for (int i = 0; i < channelNames.length; i++) {
                HomeBannerBean channelBean = new HomeBannerBean();
                channelBean.setImg(datas.get(i).getImg());
                channelNames[i] = datas.get(i).getName();
                channelBean.setName(channelNames[i]);
                channelBeanList.add(channelBean);
            }
            initBanner();
        }
        String ActivityData = SPUtils.get("activityData", "1");
        if (!ActivityData.equals("1")) {
            dataActivity = new Gson().fromJson(ActivityData, new TypeToken<List<HomeEntertainmentEntity>>() {
            }.getType());
            initActivityData();
        }
        String generatedString = SPUtils.get("activityData", "1");
        if (!generatedString.equals("1")) {
            generatedData = new Gson().fromJson(generatedString, new TypeToken<List<FarmEntity>>() {
            }.getType());
            initGeneratedData();
        }
        String bazzarString = SPUtils.get("activityData", "1");
        if (!bazzarString.equals("1")) {
            bazaarData = new Gson().fromJson(bazzarString, new TypeToken<List<HomeBazzar>>() {
            }.getType());
            initBazaarData();
        }
    }

    /**
     * 初始化banner图
     */
    private void initBanner() {
        adapter = new MyCoverFlowAdapter(mContext, channelBeanList);
        coverFlowView.setAdapter(adapter);
        coverFlowView.setOnTopViewClickListener(new CoverFlowView.OnTopViewClickListener() {
            @Override
            public void onClick(int position, View itemView) {
                if (MyApplication.isLogin()) {
                    Intent intent = new Intent();
                    intent.putExtra("farmId", datas.get(position).getId() + "");
                    intent.putExtra("farmName", datas.get(position).getName());
                    AppManager.jump(FarmDetailsActivity.class, intent);
                } else {
                    AppManager.jump(LoginActivity.class);
                }
            }
        });
        coverFlowView.setOnViewOnTopListener(new CoverFlowView.OnViewOnTopListener() {
            @Override
            public void viewOnTop(int position, View itemView) {
                txtBannerName.setText(datas.get(position).getName());
            }

        });
    }

    String[] mPermissionList = new String[]{
            Manifest.permission.CAMERA,
    };

    @OnClick({R.id.img_scan, R.id.txt_msg, R.id.rl_farm, R.id.ll_activity,
            R.id.ll_activity_more, R.id.ll_live, R.id.ll_generated, R.id.ll_generated_more,
            R.id.ll_kitchen, R.id.ll_exclusive_farm, R.id.ll_search, R.id.ll_bazaar_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_scan:
                if (EasyPermissions.hasPermissions(getActivity(), mPermissionList)) {
                    AppManager.jump(ScanActivity.class);
                } else {
                    EasyPermissions.requestPermissions(this, "需要开启必要的权限", 123, mPermissionList);
                }
                break;
            case R.id.txt_msg:
                showMsg("消息");
                break;
            case R.id.rl_farm:
                RxBus.getInstance().post(new MsgEvent(125, 1, "farm"));
                break;
            case R.id.ll_bazaar_more:
                RxBus.getInstance().post(new MsgEvent(125, 2, "bazaar"));
                break;
            case R.id.ll_activity:
            case R.id.ll_activity_more:
                AppManager.jump(HomeEntertainmentActivity.class);
                break;
            case R.id.ll_live:
                //   showMsg("直播");
                //AppManager.jump(GuideActivity.class);
                // AppManager.jump(WelcomeActivity.class);
                break;
            case R.id.ll_generated:
            case R.id.ll_generated_more:
                AppManager.jump(FarmHappyActivity.class);
                break;
            case R.id.ll_kitchen://比赛
//                showMsg("阳光厨房");
                AppManager.jump(MatchFeatureActivity.class);
                break;
            case R.id.ll_exclusive_farm://租地
                AppManager.jump(ExclusiveLandActivity.class);
//                showMsg("专属农场");
//                //临时挂靠在这里  wwl
//                AppManager.jump(ProductDetailsActivity.class);
                break;
            case R.id.ll_search:
                AppManager.jump(SearchActivityForHome.class);
                break;
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        super.onPermissionsGranted(requestCode, perms);
        AppManager.jump(ScanActivity.class);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
