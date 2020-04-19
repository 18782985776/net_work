package com.jobnew.farm.module.personal.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jobnew.farm.R;
import com.jobnew.farm.base.fragment.BaseRefreshLoadFragment;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.base.FarmEntity;
import com.jobnew.farm.module.farm.activity.farmActivity.FarmDetailsActivity;
import com.jobnew.farm.module.personal.adapter.FarmCollectionAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

import static android.R.id.list;

/**
 * Created by wangwenlang on 2017/6/15.
 * title:
 * note:
 */

public class CollectionFarmFragment extends BaseRefreshLoadFragment{
    ArrayList<FarmEntity> arrayList;
    FarmCollectionAdapter adapter;
    int pageNo=1;
    boolean isDataOver=false;
    @Override
    protected void initView(Bundle bundle, View view) {
        initData();
        adapter.setNewData(arrayList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FarmEntity farmEntity = arrayList.get(position);
                Intent intent=  new Intent(getActivity(), FarmDetailsActivity.class);
                intent.putExtra("farmId",farmEntity.getId()+"");
                intent.putExtra("farmName",farmEntity.getName());
                startActivity(intent);
            }
        });
    }
/***初始化农场数据*/
    private void initData() {
        loading();
        HashMap<String,String> map=new HashMap<>();
        map.put("type","farm");
        map.put("pageSize",20+"");
        map.put("pageNo",pageNo+"");
        MyFarmRepository.getIns().getCollectionDataByFarm(map).subscribe(new DefaultSubscriber<BaseEntity<List<FarmEntity>>>(this,false) {
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
    public void errorChickData() {
       // super.errorChickData();
        pageNo=1;
        initData();

    }

    @Override
    public void onLoadMoreRequested() {
        HashMap<String,String> map=new HashMap<>();
        map.put("type","farm");
        map.put("pageSize",20+"");
        map.put("pageNo",pageNo+"");
        MyFarmRepository.getIns().getCollectionDataByFarm(map).subscribe(new DefaultSubscriber<BaseEntity<List<FarmEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<FarmEntity>> entity) {
                List<FarmEntity> list = entity.data;
                if(entity.data==null){
                    empty();
                    return;
                }
                adapter.addData(list);
                adapter.notifyDataSetChanged();
                adapter.loadMoreComplete();
                if(list.size()<20){//没有数据了
                    isDataOver=true;
                    adapter.loadMoreEnd(false);
                }
                pageNo++;
                content();
            }

            @Override
            public void _onError(Throwable e, String msg) {
                //super._onError(e, msg);
                   error(msg);
            }
        });
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        arrayList=new ArrayList<>();
        if(adapter==null){
            adapter=new FarmCollectionAdapter(R.layout.listview_farm_item,arrayList);
        }
        return adapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_farm_collection;
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {//下拉刷新
        pageNo=1;
        refreshData(frame);
    }
/**下拉刷新**/
    private void refreshData(PtrFrameLayout frame) {
        HashMap<String,String> map=new HashMap<>();
        map.put("type","farm");
        map.put("pageSize",20+"");
        map.put("pageNo",pageNo+"");
        MyFarmRepository.getIns().getCollectionDataByFarm(map).subscribe(new DefaultSubscriber<BaseEntity<List<FarmEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<FarmEntity>> entity) {
                List<FarmEntity> list=entity.data;
                arrayList.clear();
                arrayList.addAll(list);
                adapter.setNewData(arrayList);
                adapter.notifyDataSetChanged();
                frame.refreshComplete();
                if(list.size()<20){//没有数据了
                    isDataOver=true;
                    adapter.loadMoreEnd(false);
                }
                pageNo++;
            }
        });
    }
}
