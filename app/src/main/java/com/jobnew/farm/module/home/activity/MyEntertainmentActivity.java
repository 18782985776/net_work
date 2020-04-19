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
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.FarmHappy.FarmHappyOrderSpendEntity;
import com.jobnew.farm.entity.HomeEntertainment.HomeEntertainmentEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.home.adapter.MyEntertainMentAdapter;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.TitleBarHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

public class MyEntertainmentActivity extends BaseRefreshAndLoadActivity {
    MyEntertainMentAdapter adapter;
    ArrayList<HomeEntertainmentEntity> arrayList;

    int pageNo=1;
    int pageSize=20;
    String type="activity_farm";
    boolean isDataOver=false;
    @Override
    protected int getLayout() {
        return R.layout.activity_my_entertainment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        arrayList=new ArrayList<>();
        adapter.setNewData(arrayList);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent jumpIntent=new Intent();
                jumpIntent.putExtra("id",arrayList.get(position).getId());
                AppManager.jump(EntertainmentDetailsActivity.class,jumpIntent);
                finish();
            }
        });
        initData();
    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        titleBar.setTitleMainText("我的活动");
//        titleBar.setRightText("发起");
//        titleBar.getRightTextView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showMsg("发起");
//               // AppManager.jump();
//                AppManager.jump(LaunchedActivity.class);
//            }
//        });
        titleBar.getLeftTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        titleBar.getLeftTextView().setVisibility(View.VISIBLE);
    }
/****活动订单数据****/
    private void initData() {
//        HashMap<String,String> map=new HashMap<>();
//        map.put("pageNo",pageNo+"");
//        map.put("pageSize",pageSize+"");
//        map.put("type",type);
//        loading();
//        MyFarmRepository.getIns().queryOrder(map).subscribe(new DefaultSubscriber<BaseEntity<List<FarmHappyOrderSpendEntity>>>(this,false) {
//            @Override
//            public void _onNext(BaseEntity<List<FarmHappyOrderSpendEntity>> entity) {
//                List<FarmHappyOrderSpendEntity> data = entity.data;
//                if(pageNo==1){
//                    arrayList.clear();
//                    arrayList.addAll(data);
//                }else {
//                    arrayList.addAll(data);
//                }
//                if(data.size()<20){
//                    isDataOver=true;
//                }else {
//                    pageNo++;
//                }
//                content();
//                adapter.notifyDataSetChanged();
//            }
//        });
        HashMap<String,String> map=new HashMap<>();
        map.put("longitude",Constant.LONGITUDE+"");
        map.put("latitude",Constant.LATITUDE+"");
        map.put("orderType","time");
        map.put("orderBy","desc");
        map.put("pageNo",pageNo+"");
        map.put("pageSize",pageSize+"");
        map.put("onlySelf",true+"");
        if(pageNo==1){
            loading();
        }
        MyFarmRepository.getIns().queryMyActivityList(map).subscribe(new DefaultSubscriber<BaseEntity<List<HomeEntertainmentEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<HomeEntertainmentEntity>> entity) {
                List<HomeEntertainmentEntity> data = entity.data;
                if(pageNo==1){
                    arrayList.clear();
                    arrayList.addAll(data);
                }else {
                    arrayList.addAll(data);
                }
                if(data.size()<20){
                    isDataOver=true;
                    adapter.loadMoreEnd(false);
                }else {
                    pageNo++;
                }
                content();
                adapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    public BaseQuickAdapter getAdapter() {
        adapter=new MyEntertainMentAdapter(R.layout.item_my_entertainment, arrayList);
        return adapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public void onLoadMoreRequested() {
        initData();
        adapter.loadMoreEnd(false);
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        pageNo=1;
        initData();
        frame.refreshComplete();
    }
}
