package com.jobnew.farm.module.farm.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.rxbus.RxBus;
import com.jobnew.farm.module.farm.fragment.CustomSchemeFragment;
import com.jobnew.farm.module.farm.fragment.RecommendSchemeFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wufengqiao on 2017/5/25.
 */

public class PlantingSchemeActivity extends BaseActivity {


    @BindView(R.id.tv_recom_scheme)
    TextView tvRecomScheme;
    @BindView(R.id.tv_custom_scheme)
    TextView tvCustomScheme;
    private Fragment mCurrentFragment;
    private RecommendSchemeFragment recomFragment;
    private CustomSchemeFragment customSchemeFragment;
    private int mSeedId = -1;
    private int number;
    private int duration;
    private boolean isDefultScheme;
    private ArrayList<Parcelable> schemeList;

    @Override
    protected int getLayout() {
        return R.layout.activity_planting_scheme;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("种植方案", true);
        mSeedId = getIntent().getIntExtra(Constant.SEED_ID, -1);
        number = getIntent().getIntExtra(Constant.NUMBER, 0);
        duration = getIntent().getIntExtra(Constant.DURATION, -1);
        isDefultScheme = getIntent().getBooleanExtra(Constant.SCHEME_TYPE, true);
        schemeList = getIntent().getParcelableArrayListExtra(Constant.SCHEME);
        if (isDefultScheme) {
            selectRecom();
        } else {
            selectCustom();
        }


    }

    @OnClick({R.id.tv_recom_scheme, R.id.tv_custom_scheme})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_recom_scheme:
                selectRecom();
                break;
            case R.id.tv_custom_scheme:
                selectCustom();
                break;
        }
    }

    /**
     * 选择自定义Fragment
     */
    private void selectCustom() {
        tvRecomScheme.setSelected(false);
        tvCustomScheme.setSelected(true);
        if (customSchemeFragment == null) {
            customSchemeFragment = CustomSchemeFragment.newInstance(mSeedId, duration, number,isDefultScheme,schemeList);
        }
        switchFragment(customSchemeFragment);
    }

    /**
     * 选择默认Fragment
     */
    private void selectRecom() {
        tvRecomScheme.setSelected(true);
        tvCustomScheme.setSelected(false);
        if (recomFragment == null) {
            recomFragment = RecommendSchemeFragment.newInstance(mSeedId, duration, number,!isDefultScheme,schemeList);
        }
        switchFragment(recomFragment);
    }

    /**
     * 改变选中Fragment
     *
     * @param fragment
     */
    public void switchFragment(Fragment fragment) {

        // 如果
        if (mCurrentFragment == fragment) {
            return;
        }
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        if (mCurrentFragment == null) {
            transaction.add(R.id.fl_fragment, fragment)
                    .commit();
        } else {
            if (fragment.isAdded()) {
                transaction.hide(mCurrentFragment)
                        .show(fragment)
                        .commitAllowingStateLoss();
            } else {
                transaction
                        .hide(mCurrentFragment)
                        .add(R.id.fl_fragment, fragment)
                        .commit();
            }
        }
        mCurrentFragment = fragment;
    }

}
