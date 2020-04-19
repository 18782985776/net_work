package com.jobnew.farm.module.personal.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.fragment.BaseRefreshLoadFragment;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.personal.adapter.CollectionActivityAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wangwenlang on 2017/6/15.
 * title:
 * note:
 */

public class CollectionActivityFragment extends BaseRefreshLoadFragment {
    ArrayList<String> arrayList;
    CollectionActivityAdapter adapter;
    int pageNo=1;
    boolean isDataOver=false;
    @Override
    protected void initView(Bundle bundle, View view) {
        initData();
        adapter.setNewData(arrayList);
    }
/****进来初次请求数据**/
    private void initData() {
        HashMap<String,String> map=new HashMap<>();
        map.put("type","activity");
        map.put("pageNo",pageNo+"");
        map.put("pageSize",20+"");
        MyFarmRepository.getIns().getCollectionDataByEntertainment(map).subscribe(new DefaultSubscriber<BaseEntity>(this,false) {
            @Override
            public void _onNext(BaseEntity entity) {
                pageNo++;
            }
        });

    }

    @Override
    public void onLoadMoreRequested() {
        if(arrayList.size()<10){
            arrayList.add("1");
            adapter.notifyDataSetChanged();
            adapter.loadMoreComplete();
        }else {
            adapter.loadMoreEnd(false);
        }
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        arrayList=new ArrayList<>();
        arrayList.add("1");
        if(adapter==null){
            adapter=new CollectionActivityAdapter(R.layout.item_collection_activity,arrayList);
        }
        return adapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
       return new LinearLayoutManager(getActivity());
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_collection_activity;
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        frame.refreshComplete();
    }
}
