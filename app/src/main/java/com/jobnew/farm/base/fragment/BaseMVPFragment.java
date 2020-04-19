package com.jobnew.farm.base.fragment;

import android.os.Bundle;
import android.view.View;

import com.jobnew.farm.base.BasePresenter;

/**
 * Created by wufengqiao on 2017/5/17.
 * Function: MVPFragment基类
 * Desc:
 */
public abstract class BaseMVPFragment<V, T extends BasePresenter<V>> extends BaseFragment {

    protected T mPresenter;

    @Override
    protected void initView(View view, Bundle bundle) {
        //创建代理
        mPresenter = createPresenter();
        //创建关联
        mPresenter.attachView((V) this);
        super.initView(view, bundle);
    }

    protected abstract T createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detacheView();
        }
    }
}
