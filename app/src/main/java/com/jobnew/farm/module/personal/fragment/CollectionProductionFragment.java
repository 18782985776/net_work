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
import com.jobnew.farm.entity.myfarm.CollectionProductEntity;
import com.jobnew.farm.module.personal.adapter.CollectionProductionAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wangwenlang on 2017/7/11.
 * title:
 * note:
 */


public class CollectionProductionFragment extends BaseRefreshLoadFragment {
    CollectionProductionAdapter adapter;
    ArrayList<CollectionProductEntity> arrayList;
    int pageNo=1;
    boolean isDataOver=false;
    public CollectionProductionFragment(){
        super();
    }
    @Override
    protected void initView(Bundle bundle, View view) {
        initData();
        adapter.setNewData(arrayList);
        adapter.notifyDataSetChanged();
    }

    private void initData() {
        HashMap<String,String> map=new HashMap<>();
        map.put("type","market");
        map.put("pageNo",pageNo+"");
        map.put("pageSize",20+"");
        MyFarmRepository.getIns().getCollectionDataByProduct(map).subscribe(new DefaultSubscriber<BaseEntity<List<CollectionProductEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<CollectionProductEntity>> entity) {
                List<CollectionProductEntity> data = entity.data;
                adapter.addData(data);
                adapter.notifyDataSetChanged();
                if(data.size()<20){
                    isDataOver=true;
                    adapter.loadMoreEnd(false);
                }
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public BaseQuickAdapter getAdapter() {
        arrayList=new ArrayList<>();
        adapter=new CollectionProductionAdapter(R.layout.item_collection_production,arrayList);
        return adapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_collection_pruduct_fragment;
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {

    }
}
