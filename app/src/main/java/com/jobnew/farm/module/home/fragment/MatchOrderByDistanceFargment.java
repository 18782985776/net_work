package com.jobnew.farm.module.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.fragment.BaseRefreshLoadFragment;
import com.jobnew.farm.module.home.adapter.TakeMatchAdapter;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wangwenlang on 2017/7/25.
 * title:
 * note:距离最近并列
 */

public class MatchOrderByDistanceFargment extends BaseRefreshLoadFragment {
    private TakeMatchAdapter adapter;
    @Override
    protected void initView(Bundle bundle, View view) {

    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public BaseQuickAdapter getAdapter() {
        ArrayList arrayList=new ArrayList();
        arrayList.add("1");
        adapter=new TakeMatchAdapter(R.layout.match_item,arrayList);
        return adapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_take_match;
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {

    }
}
