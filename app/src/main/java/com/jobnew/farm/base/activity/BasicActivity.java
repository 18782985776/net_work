package com.jobnew.farm.base.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.util.Log;


import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wfq on 2016/7/18/10:24.
 * All Activity base class
 */
public abstract class BasicActivity extends BaseRxActivity {

    protected String TAG = this.getClass().getSimpleName();

    protected Activity mContext;

    private Unbinder mUnbinder;

    protected boolean mIsFirstShow = true;


    @LayoutRes
    protected abstract int getLayout();



    /**
     * 需要加载数据时重写此方法
     */
    protected void loadData() {
    }

    /**
     * 执行setContentView()方法前调用
     */
    protected void beforeSetView() {

    }

    /**
     * 在初始化控件前进行一些操作
     */

    protected void beforeInitView() {
    }

    protected abstract void initView(Bundle savedInstanceState);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        beforeSetView();
        setContentView(getLayout());
        mUnbinder = ButterKnife.bind(this);
        beforeInitView();
        initView(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        if (mIsFirstShow) {
            mIsFirstShow = false;
            loadData();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
