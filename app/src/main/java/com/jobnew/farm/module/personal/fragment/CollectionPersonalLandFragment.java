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
import com.jobnew.farm.entity.myfarm.CollectionLandEntity;
import com.jobnew.farm.module.personal.adapter.CollectionPersonalLandAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wangwenlang on 2017/6/15.
 * title:
 * note:
 */

public class CollectionPersonalLandFragment extends BaseRefreshLoadFragment{
    ArrayList<CollectionLandEntity> arrayList;
    CollectionPersonalLandAdapter adapter;
    int pageNo=1;
    int pageSize=20;
    boolean isDataOver=false;
    @Override
    protected void initView(Bundle bundle, View view) {
        initData();
    }
/**初始化土地数据*****/
    private void initData() {
        HashMap<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize",pageSize+"");
        map.put("type","land");
        MyFarmRepository.getIns().getCollectionDataByLand(map).subscribe(new DefaultSubscriber<BaseEntity<List<CollectionLandEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<CollectionLandEntity>> entity) {
                List<CollectionLandEntity> data = entity.data;
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
        if (adapter==null){
            adapter=new CollectionPersonalLandAdapter(R.layout.item_collection_personal_land,arrayList);
        }
        return adapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_collection_personal_land;
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        frame.refreshComplete();
    }
}
