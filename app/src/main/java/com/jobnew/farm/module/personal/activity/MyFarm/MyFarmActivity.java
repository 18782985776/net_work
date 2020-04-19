package com.jobnew.farm.module.personal.activity.MyFarm;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.jobnew.farm.MainActivity;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.module.personal.adapter.MyFarm.MyFarmPageAdapter;
import com.jobnew.farm.module.personal.fragment.MyFarm.MyFarmBreedingFragment;
import com.jobnew.farm.module.personal.fragment.MyFarm.MyFarmExclusiveFragment;
import com.jobnew.farm.module.personal.fragment.MyFarm.MyFarmPlantingFragment;
import com.jobnew.farm.module.personal.fragment.MyFarm.MyFarmReapFragment;
import com.jobnew.farm.utils.SPUtils;
import com.jobnew.farm.utils.ScreenToolsUtils;
import com.jobnew.farm.widget.TitleBarHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wufengqiao on 2017/6/13.
 * function：
 * desc：
 */

public class MyFarmActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_farm;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        setSelectedTabIndicatorMargin();
        MyFarmPlantingFragment plantingFragment = new MyFarmPlantingFragment();
        MyFarmBreedingFragment plantingFragment1 = new MyFarmBreedingFragment();
        MyFarmReapFragment plantingFragment2 = new MyFarmReapFragment();
        MyFarmExclusiveFragment plantingFragment3 = new MyFarmExclusiveFragment();
        List<Fragment> list = new ArrayList();
        list.add(plantingFragment);
        list.add(plantingFragment1);
        list.add(plantingFragment2);
        list.add(plantingFragment3);
        String[] strings = {"种植", "养殖", "已收获", "专属土地"};
        MyFarmPageAdapter myFarmPageAdapter = new MyFarmPageAdapter(getSupportFragmentManager(), list, strings);
        viewpager.setAdapter(myFarmPageAdapter);
        tabLayout.setupWithViewPager(viewpager);
        int payType = SPUtils.get(Constant.PAY_TYPE, 0);
        switch (payType) {
            case 0:
            case 1:
                viewpager.setCurrentItem(payType);
                break;
        }
    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        titleBar.setTitleMainText("我的农场");
        titleBar.setOnLeftTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyFarmActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            startActivity(new Intent(MyFarmActivity.this, MainActivity.class));
            finish();
            return true;
        }
        return false;
    }

    private void setSelectedTabIndicatorMargin() {
        Class<?> clazz = tabLayout.getClass();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                Field tabStrip = clazz.getDeclaredField("mTabStrip");
                tabStrip.setAccessible(true);
                LinearLayout ll_tab = (LinearLayout) tabStrip.get(tabLayout);
                for (int i = 0; i < ll_tab.getChildCount(); i++) {
                    View child = ll_tab.getChildAt(i);
                    child.setPadding(0, 0, 0, 0);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                    params.setMarginStart(ScreenToolsUtils.dp2px(22f));
                    params.setMarginEnd(ScreenToolsUtils.dp2px(22f));
                    child.setLayoutParams(params);
                    child.invalidate();
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
}
