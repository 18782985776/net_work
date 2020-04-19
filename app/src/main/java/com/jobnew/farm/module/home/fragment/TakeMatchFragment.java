package com.jobnew.farm.module.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.fragment.BaseRefreshLoadFragment;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.matchFeature.MatchEntity;
import com.jobnew.farm.listeners.TextDistanceLisenter;
import com.jobnew.farm.module.home.activity.MatchFeatureActivity;
import com.jobnew.farm.module.home.activity.MatchInfoActivity;
import com.jobnew.farm.module.home.adapter.TakeMatchAdapter;
import com.jobnew.farm.widget.AppManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wangwenlang on 2017/7/25.
 * title:
 * note:报名中
 */

public class TakeMatchFragment extends BaseRefreshLoadFragment implements TextDistanceLisenter {
    private TakeMatchAdapter adapter;
    private int pageSize=20;
    private int pageNo=1;
    private int status=1;
    //    boolean isPos=false;
    public TakeMatchFragment(){
        super();
    }
    MatchFeatureActivity myAty;
    ArrayList<MatchEntity> arrayList;//数据源
    boolean isDataOver=false;

    @Override
    protected void initView(Bundle bundle, View view) {
        myAty= (MatchFeatureActivity) getActivity();
        myAty.setTextDistanceLisenter(this);
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
/***初次加载和距离选中发生变化时调用***/
    private void initData() {
        HashMap<String,String> map=new HashMap<>();
        map.put("longitude", Constant.LONGITUDE+"");
        map.put("latitude", Constant.LATITUDE+"");
        map.put("isPos", MatchFeatureActivity.isPos+"");
        map.put("status",status+"");
        map.put("pageNo",pageNo+"");
        map.put("pageSize",pageSize+"");
        loading();
        MyFarmRepository.getIns().queryMatchList(map).subscribe(new DefaultSubscriber<BaseEntity<List<MatchEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<MatchEntity>> entity) {
                List<MatchEntity> data = entity.data;
                if (data.isEmpty()){
                    empty();
                    return;
                }
                if(data.size()==0){
                    empty();
                    return;
                }
                arrayList.clear();
                arrayList.addAll(data);
                adapter.notifyDataSetChanged();
                if(data.size()<20){
                    isDataOver=true;
                    adapter.loadMoreEnd(false);
                }else {
                    pageNo++;
                }
                content();
                showMsg(data.size()+"");
            }
            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public BaseQuickAdapter getAdapter() {

        arrayList = new ArrayList();
        // arrayList.add("1");
        adapter=new TakeMatchAdapter(R.layout.match_item,arrayList);
        adapter.setLodaTag(1);
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
        pageNo=1;
        HashMap<String,String> map=new HashMap<>();
        map.put("longitude", Constant.LONGITUDE+"");
        map.put("latitude", Constant.LATITUDE+"");
        map.put("isPos", MatchFeatureActivity.isPos+"");
        map.put("status",status+"");
        map.put("pageNo",pageNo+"");
        map.put("pageSize",pageSize+"");
        MyFarmRepository.getIns().queryMatchList(map).subscribe(new DefaultSubscriber<BaseEntity<List<MatchEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<MatchEntity>> entity) {
                List<MatchEntity> data = entity.data;
                if (data.isEmpty()){
                    empty();
                    return;
                }
                arrayList.clear();
                arrayList.addAll(data);
                adapter.notifyDataSetChanged();
                if(data.size()<20){
                    isDataOver=true;
                    adapter.loadMoreEnd(false);
                }else {
                    pageNo++;
                }
                content();
                showMsg(data.size()+"");
                frame.refreshComplete();
            }
            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
            }
        });
    }

    @Override
    public void errorChickData() {
        super.errorChickData();
        pageNo=1;
        initData();
    }

    @Override
    public void reLoadData() {
        showMsg("重新加载");
       // if(getUserVisibleHint()){
            pageNo=1;
            initData();
      //  }
    }
}
