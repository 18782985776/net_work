package com.jobnew.farm.base.activity;

import com.jobnew.farm.base.BasePresenter;

/**
 * Created by wufengqiao on 2017/5/17
 * Function: 可刷新的MVPActivity基类
 * Desc:
 */
public abstract class BaseMVPRefreshAndLoadActivity<V, T extends BasePresenter<V>> extends BaseRefreshAndLoadActivity {

    protected T mPresenter;

    @Override
    protected void beforeInitView() {
        super.beforeInitView();
        //创建代理
        mPresenter = createPresenter();
        //创建关联
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }

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
