package com.jobnew.farm.base.fragment;

import android.os.Bundle;
import android.view.View;

import com.jobnew.farm.base.BasePresenter;

/**
 * Created by wufengqiao on 2017/5/17
 * Function: 可刷新的MVPFragment基类
 * Desc:
 */
public abstract class BaseMVPRefreshLoadFragment<V, T extends BasePresenter<V>> extends BaseRefreshLoadFragment {

    protected T mPresenter;

    @Override
    protected void initView( View view,Bundle bundle) {
        super.initView(view,bundle);
        //创建代理
        mPresenter = createPresenter();
        //创建关联
        mPresenter.attachView((V) this);
    }

    protected abstract T createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detacheView();
        }
    }
}
