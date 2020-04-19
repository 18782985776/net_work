package com.jobnew.farm.base.activity;

import com.jobnew.farm.base.BasePresenter;

/**
 * Created by wufengqiao on 2017/5/17.
 * Function:MVPActivity基类
 * Desc:
 */
public abstract class BaseMVPActivity<V, T extends BasePresenter<V>> extends BaseActivity {

    protected T mPresenter;

    @Override
    protected void beforeInitView() {
        //创建代理
        mPresenter = createPresenter();
        //创建关联
        mPresenter.attachView((V) this);
        super.beforeInitView();
    }

    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detacheView();
        }
    }
}
