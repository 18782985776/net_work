package com.jobnew.farm.base.fragment;

import android.view.View;


import com.jobnew.farm.base.interfaces.IUpdateDataView;
import com.jobnew.farm.widget.UpdateDataDelegate;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;

/**
 * Create by wfq on 2016/10/26/22:38.
 * Function：Fragment继承该类可以实现下拉刷新和上拉加载功能
 * Desc：
 */
public abstract class RapidRefreshLoadFragment extends BasicFragment implements IUpdateDataView {

    private UpdateDataDelegate mDelegate;

    @Override
    protected void beforeInitView() {
        mDelegate = new UpdateDataDelegate(mContentView);
        mDelegate.initPTR(this, getRefreshHeader());
        mDelegate.initLoad(this,getAdapter(), getLayoutManager());
    }

    // 检测是否可以进行下拉刷新
    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
    }

    //获取下拉刷新头样式，如果子类不重写该方法，则使用默认的样式
    @Override
    public PtrUIHandler getRefreshHeader() {
        return new PtrClassicDefaultHeader(mContext);
    }
}
