package com.jobnew.farm.module.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;

import com.jobnew.farm.MainActivity;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.module.home.fragment.FarmHappyNotSpendingOrderFragment;
import com.jobnew.farm.module.home.fragment.FarmHappySendedOrederFragment;
import com.jobnew.farm.module.personal.activity.MyFarm.MyFarmActivity;
import com.jobnew.farm.module.personal.adapter.FarmPagerAdapter;
import com.jobnew.farm.widget.TitleBarHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFarmHappyOrderActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    String[] mStrings = {"未消费", "已消费"};
    private FarmPagerAdapter farmPagerAdapter;
    ArrayList<Fragment> fragmentList;


    @Override
    protected int getLayout() {
        return R.layout.activity_my_farm_happy_order;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        fragmentList=new ArrayList<>();
       fragmentList.add(new FarmHappyNotSpendingOrderFragment());
        fragmentList.add(new FarmHappySendedOrederFragment());
        farmPagerAdapter=new FarmPagerAdapter(getSupportFragmentManager(),fragmentList,mStrings);
        viewPager.setAdapter(farmPagerAdapter);
        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabMode(TabLayout.MODE_FIXED);
    }
    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        titleBar.setTitleMainText("农家乐—我的订单");
        titleBar.setOnLeftTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyFarmHappyOrderActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            startActivity(new Intent(MyFarmHappyOrderActivity.this, MainActivity.class));
            finish();
            return true;
        }
        return false;
    }
}
