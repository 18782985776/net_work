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
import com.jobnew.farm.entity.base.FarmEntity;
import com.jobnew.farm.module.personal.adapter.CollectionHappyFarmAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wangwenlang on 2017/6/15.
 * title:
 * note:
 */

public class CollectionHappyFarmhouseFragment extends BaseRefreshLoadFragment {
    ArrayList<FarmEntity> arrayList;
    CollectionHappyFarmAdapter adapter;
    int pageNo=1;
    boolean isDataOver=false;
    @Override
    protected void initView(Bundle bundle, View view) {
        initData();
        adapter.setNewData(arrayList);
    }
/**初始化数据**/
    private void initData() {
        loading();
        HashMap<String,String> map=new HashMap<>();
        map.put("type","agritmnt");
        map.put("pageSize",20+"");
        map.put("pageNo",pageNo+"");
        MyFarmRepository.getIns().getCollectionDataByFarmHappy(map).subscribe(new DefaultSubscriber<BaseEntity<List<FarmEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<FarmEntity>> entity) {
                List<FarmEntity> list = entity.data;
                if(entity.data==null){
                    empty();
                    return;
                }
                arrayList.clear();
                arrayList.addAll(list);
                adapter.setNewData(arrayList);
                adapter.notifyDataSetChanged();
                if(list.size()<20){
                    isDataOver=true;
                    adapter.loadMoreEnd(false);
                }
                content();
                pageNo++;
            }

            @Override
            public void _onError(Throwable e, String msg) {
                error(msg);
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        HashMap<String,String> map=new HashMap<>();
        map.put("type","agritmnt");
        map.put("pageSize",20+"");
        map.put("pageNo",pageNo+"");
        MyFarmRepository.getIns().getCollectionDataByFarmHappy(map).subscribe(new DefaultSubscriber<BaseEntity<List<FarmEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<FarmEntity>> entity) {
                List<FarmEntity> list = entity.data;
                if(entity.data==null){
                    empty();
                    return;
                }
                arrayList.addAll(list);
                adapter.addData(arrayList);
                adapter.notifyDataSetChanged();
                if(list.size()<20){
                    isDataOver=true;
                    adapter.loadMoreEnd(false);
                }
                pageNo++;
                adapter.loadMoreComplete();
                content();
            }

            @Override
            public void _onError(Throwable e, String msg) {
                error(msg);
            }
        });
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        arrayList=new ArrayList<>();
        if(adapter==null){
            adapter=new CollectionHappyFarmAdapter(R.layout.item_collection_farmhouse,arrayList);
        }
        return adapter;
    }

    @Override
    public void errorChickData() {
        pageNo=1;
        initData();
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_collection_happy_farmhouse;
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        pageNo=1;
        initData();
    }
}
