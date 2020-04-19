package com.jobnew.farm.module.personal.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;

import com.jobnew.farm.BazaarFragment;
import com.jobnew.farm.CommunityFragment;
import com.jobnew.farm.FarmFragment;
import com.jobnew.farm.HomeFragment;
import com.jobnew.farm.MainActivity;
import com.jobnew.farm.MineFragment;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.module.personal.activity.MyFarm.MyFarmActivity;
import com.jobnew.farm.module.personal.adapter.FarmPagerAdapter;
import com.jobnew.farm.module.personal.fragment.DeliveryOrderFragment;
import com.jobnew.farm.module.personal.fragment.EvaluationOrderFragment;
import com.jobnew.farm.module.personal.fragment.GoodsOrderFragment;
import com.jobnew.farm.module.personal.fragment.MyOrderFragment;
import com.jobnew.farm.module.personal.fragment.PaymentOrderFragment;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.TitleBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wc on 2017/6/8.
 * Function：全部订单
 * desc：
 */

public class MyOrderActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    List<Fragment> mFragment;
    FarmPagerAdapter mAdapter;
    String[]mStrings = {"全部","待付款","待发货","待收货","待评价"};

    @Override
    protected int getLayout() {
        return R.layout.activity_my_order;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        int index = getIntent().getIntExtra("key",0);
        mFragment = new ArrayList<Fragment>();
        mFragment.add(new MyOrderFragment());
        mFragment.add(new PaymentOrderFragment());
        mFragment.add(new DeliveryOrderFragment());
        mFragment.add(new GoodsOrderFragment());
        mFragment.add(new EvaluationOrderFragment());
        mAdapter = new FarmPagerAdapter(getSupportFragmentManager(),mFragment,mStrings);
        viewpager.setAdapter(mAdapter);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewpager);
        viewpager.setCurrentItem(index);
    }


    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        titleBar.setTitleMainText("我的订单");
        titleBar.setOnRightTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.jump(AfterSalesActivity.class);
            }
        });
        titleBar.setOnLeftTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyOrderActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            startActivity(new Intent(MyOrderActivity.this, MainActivity.class));
            finish();
            return true;
        }
        return false;
    }
}
