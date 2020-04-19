package com.jobnew.farm.module.home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseRefreshAndLoadActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.matchFeature.MatchEntity;
import com.jobnew.farm.module.home.adapter.MyMatchAdapter;
import com.jobnew.farm.widget.AppManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

public class MyMatchActivity extends BaseRefreshAndLoadActivity {
   private MyMatchAdapter adapter;
    ArrayList<MatchEntity> arrayList;//数据源
    int pageNo=1;
    int pageSize=20;
    @Override
    protected int getLayout() {
        return R.layout.activity_my_match;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("我的比赛",true);
        initData();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MatchEntity matchEntity = arrayList.get(position);
                Intent infoIntent=new Intent();
                infoIntent.putExtra("matchId",matchEntity.getId());
                AppManager.jump(MatchInfoActivity.class,infoIntent);
            }
        });
    }
/***获取数据***/
    private void initData() {
        HashMap<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize",pageSize+"");
        loading();
        MyFarmRepository.getIns().queryMyMatch(map).subscribe(new DefaultSubscriber<BaseEntity<List<MatchEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<MatchEntity>> entity) {
                List<MatchEntity> data = entity.data;
                if(data.isEmpty()){
                    empty();
                    return;
                }
                arrayList.clear();
                arrayList.addAll(data);
                adapter.notifyDataSetChanged();
                if(data.size()<20){
                    adapter.loadMoreEnd(false);
                }else {
                    pageNo++;
                }
                content();
            }
            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
            }
        });
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        arrayList = new ArrayList<>();
        adapter=new MyMatchAdapter(R.layout.match_item,arrayList);
        return adapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public void onLoadMoreRequested() {
        HashMap<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize",pageSize+"");
        MyFarmRepository.getIns().queryMyMatch(map).subscribe(new DefaultSubscriber<BaseEntity<List<MatchEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<MatchEntity>> entity) {
                List<MatchEntity> data = entity.data;
                if(data.isEmpty()){
                    empty();
                    return;
                }
                arrayList.addAll(data);
                adapter.notifyDataSetChanged();
                if(data.size()<20){
                    adapter.loadMoreEnd(false);
                }else {
                    pageNo++;
                }
                content();
            }
            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
            }
        });
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        pageNo=1;
        HashMap<String,String> map=new HashMap<>();
        map.put("pageNo",pageNo+"");
        map.put("pageSize",pageSize+"");
        MyFarmRepository.getIns().queryMyMatch(map).subscribe(new DefaultSubscriber<BaseEntity<List<MatchEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<MatchEntity>> entity) {
                List<MatchEntity> data = entity.data;
                if(data.isEmpty()){
                    empty();
                    return;
                }
                arrayList.clear();
                arrayList.addAll(data);
                adapter.notifyDataSetChanged();
                if(data.size()<20){
                    adapter.loadMoreEnd(false);
                }else {
                    pageNo++;
                }
                content();
                frame.refreshComplete();
            }
            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
            }
        });
    }
}
