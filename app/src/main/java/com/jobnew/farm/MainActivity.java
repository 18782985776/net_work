package com.jobnew.farm;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.google.gson.Gson;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.base.fragment.BasicFragment;
import com.jobnew.farm.data.rxbus.MsgEvent;
import com.jobnew.farm.data.rxbus.RxBus;
import com.jobnew.farm.utils.SPUtils;
import com.jobnew.farm.wxapi.ChangeListener;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import pub.devrel.easypermissions.EasyPermissions;


public class MainActivity extends BaseActivity {

    RadioGroup mainGroup;
    @BindView(R.id.tab_farm)
    RadioButton tabFarm;
    @BindView(R.id.tab_bazaar)
    RadioButton tabBazaar;
    private List<BasicFragment> mFrags;
    private Fragment mFrom;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mainGroup = (RadioGroup) findViewById(R.id.main_group);

        init();
        initRxBus();
    }




    Disposable subscribe;

    /**
     * 初始化rxbus
     */
    private void initRxBus() {
        Flowable<MsgEvent> f = RxBus.getInstance().register(MsgEvent.class);
        subscribe = f.observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<MsgEvent>() {
            @Override
            public void accept(@NonNull MsgEvent msgEvent) throws Exception {
                if (msgEvent.getRequest() == 125) {
                    if (msgEvent.getType() == 1) {
                        tabFarm.setChecked(true);
                    } else if (msgEvent.getType() == 2) {
                        tabBazaar.setChecked(true);
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscribe != null && !subscribe.isDisposed()) {
            subscribe.dispose();
        }
    }

    private void init() {
        mFrags = new ArrayList<>();
        mFrags.add(HomeFragment.newInstance());
        mFrags.add(FarmFragment.newInstance());
        mFrags.add(BazaarFragment.newInstance());
        mFrags.add(CommunityFragment.newInstance());
        mFrags.add(MineFragment.newInstance());
        mainGroup.setOnCheckedChangeListener(tabListener);
        switchPage(0, false);
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                showMsg(getString(R.string.outapp));
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
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

    /**
     * 切换页面
     */
    private void switchPage(int position, boolean isRx) {
        Fragment to = mFrags.get(position);
        if (to == mFrom) return;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (to.isAdded()) {
            transaction.show(to);
        } else {
            transaction.add(R.id.flContent, to);
        }
        if (mFrom != null) {
            transaction.hide(mFrom);
            if (isRx && mFrom == mFrags.get(0)) {
                RxBus.getInstance().post(new MsgEvent(124, 2, "stop"));
            } else if (isRx && to == mFrags.get(0)) {
                RxBus.getInstance().post(new MsgEvent(124, 1, "start"));
            }
        }
        transaction.commitAllowingStateLoss();
        mFrom = to;
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        RxBus.getInstance().post(new MsgEvent(124, 2, "stop"));
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        RxBus.getInstance().post(new MsgEvent(124, 1, "start"));
//    }

    private RadioGroup.OnCheckedChangeListener tabListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (group.getCheckedRadioButtonId()) {
                case R.id.tab_home:
                    switchPage(0, true);
                    break;
                case R.id.tab_farm:
                    switchPage(1, true);
                    break;
                case R.id.tab_bazaar:
                    switchPage(2, true);
                    break;
                case R.id.tab_community:
                    switchPage(3, true);
                    break;
                case R.id.tab_mine:
                    switchPage(4, true);
                    break;
            }
        }
    };
}
