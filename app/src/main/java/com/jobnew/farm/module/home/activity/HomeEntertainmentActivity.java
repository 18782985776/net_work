package com.jobnew.farm.module.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseRefreshAndLoadActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.HomeEntertainment.HomeEntertainmentEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.home.adapter.EntertainmentAdapter;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.TitleBarHelper;
import com.marno.easystatelibrary.EasyStatusView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class HomeEntertainmentActivity extends BaseRefreshAndLoadActivity {
    EntertainmentAdapter adapter;
    ArrayList<HomeEntertainmentEntity> arrayList;
    @BindView(R.id.grade_tv)
    TextView gradeTv;
    @BindView(R.id.grade_img)
    ImageView gradeImg;
    @BindView(R.id.newwest_tv)
    TextView newwestTv;
    @BindView(R.id.newwest_img)
    ImageView newwestImg;
    @BindView(R.id.distance_filter_tv)
    TextView distanceFilterTv;
    @BindView(R.id.distance_filter_img)
    ImageView distanceFilterImg;
    ArrayList<LinearLayout> linears;
    @BindView(R.id.linear1)
    LinearLayout linear1;
    @BindView(R.id.linear2)
    LinearLayout linear2;
    @BindView(R.id.linear3)
    LinearLayout linear3;
    ImageView currentImg;
    ArrayList<ImageView> imgs;
    int TagGrade=0;
    int TagNewwest=1;
    int TagDistacne=0;
    String orderType="popularity";
    String orderBy="desc";
    String longitude;
    String latitude;
    int pageNo=1;
    int pageSize=20;
    boolean isDataOver;
    @Override
    protected int getLayout() {
        return R.layout.activity_home_entertainment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        latitude= Constant.LATITUDE+"";
        longitude=Constant.LONGITUDE+"";
        initData();
        initFilter();
        switchChecked(linears.get(0),TagGrade);
        adapter.setNewData(arrayList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent jumpIntent=new Intent();
                jumpIntent.putExtra("id",arrayList.get(position).getId());
               AppManager.jump(EntertainmentDetailsActivity.class,jumpIntent);
            }
        });
    }

    @Override
    public void errorChickData() {
        super.errorChickData();
        initData();
    }

    private void initFilter() {
        imgs=new ArrayList<>();
        linears=new ArrayList<>();
        linears.add(linear1);
        linears.add(linear2);
        linears.add(linear3);
        imgs.add(gradeImg);
        imgs.add(newwestImg);
        imgs.add(distanceFilterImg);
    }

    private void initData() {
        HashMap<String,String> map=new HashMap<>();
        map.put("pageSize",pageSize+"");
        map.put("pageNo",pageNo+"");
        map.put("orderBy",orderBy);
        map.put("orderType",orderType);
        map.put("latitude",latitude);
        map.put("longitude",longitude);
        loading();
        MyFarmRepository.getIns().queryActivityList(map).subscribe(new DefaultSubscriber<BaseEntity<List<HomeEntertainmentEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<HomeEntertainmentEntity>> entity) {
                List<HomeEntertainmentEntity> data = entity.data;
                if(data==null){
                    empty();
                    return;
                }
                arrayList.clear();
                arrayList.addAll(data);
                adapter.notifyDataSetChanged();
                if(data.size()<20){
                    isDataOver = true;
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
    @OnClick({R.id.linear1,R.id.linear2,R.id.linear3})
    public void dealClick(View view){
        pageNo=1;
        switch (view.getId()){
            case R.id.linear1:
                orderType="popularity";
                TagGrade++;
                if(TagDistacne%2==0){
                    orderBy="desc";
            }else {
                   orderBy="asc";
                }
                switchChecked((LinearLayout) view,TagGrade);
                break;
            case R.id.linear2:
                orderType="time";
                TagNewwest++;
                if(TagNewwest%2==0){
                    orderBy="desc";
                }else {
                    orderBy="asc";
                }
                switchChecked((LinearLayout) view,TagNewwest);
                break;
            case R.id.linear3:
                orderType="position";
                TagDistacne++;
                if(TagNewwest%2==0){
                    orderBy="desc";
                }else {
                    orderBy="asc";
                }
                switchChecked((LinearLayout) view,TagDistacne);

                break;

        }
    }

    private void switchChecked(LinearLayout linear,int Tag) {
        for (LinearLayout linearL: linears){
            if(linearL==linear){//点中的LinearLayout
                TextView tv= (TextView) linearL.getChildAt(0);
                tv.setTextColor(Color.parseColor("#90b659"));
                currentImg= (ImageView) linearL.getChildAt(1);
              if(Tag%2==0){
                  currentImg.setImageResource(R.drawable.farm_icon_screen3);
              }else {
                  currentImg.setImageResource(R.drawable.farm_icon_screen4);
              }

            }else {
               TextView tv= (TextView) linearL.getChildAt(0);
                tv.setTextColor(Color.parseColor("#7b7b7b"));
                ImageView childAt = (ImageView) linearL.getChildAt(1);
                childAt.setImageResource(R.drawable.farm_icon_screen1);
            }
        }
        initData();
    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        titleBar.setTitleMainText("活动");
        titleBar.getLeftTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        titleBar.getLeftTextView().setVisibility(View.VISIBLE);
        titleBar.setRightText("我的");
        titleBar.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMsg("我的");
                AppManager.jump(MyEntertainmentActivity.class);
            }
        });
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        arrayList=new ArrayList<>();
        adapter = new EntertainmentAdapter(R.layout.entertainment_item, arrayList);
        return adapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public void onLoadMoreRequested() {
        HashMap<String,String> map=new HashMap<>();
        map.put("pageSize",pageSize+"");
        map.put("pageNo",pageNo+"");
        map.put("orderBy",orderBy);
        map.put("orderType",orderType);
        map.put("latitude",latitude);
        map.put("longitude",longitude);
        MyFarmRepository.getIns().queryActivityList(map).subscribe(new DefaultSubscriber<BaseEntity<List<HomeEntertainmentEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<HomeEntertainmentEntity>> entity) {
                List<HomeEntertainmentEntity> data = entity.data;
                arrayList.addAll(data);
                adapter.notifyDataSetChanged();
                if(data.size()<20){
                    isDataOver = true;
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
        refreshBegin(frame);
    }

    private void refreshBegin(PtrFrameLayout frame) {
        HashMap<String,String> map=new HashMap<>();
        map.put("pageSize",pageSize+"");
        map.put("pageNo",pageNo+"");
        map.put("orderBy",orderBy);
        map.put("orderType",orderType);
        map.put("latitude",latitude);
        map.put("longitude",longitude);
        MyFarmRepository.getIns().queryActivityList(map).subscribe(new DefaultSubscriber<BaseEntity<List<HomeEntertainmentEntity>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<HomeEntertainmentEntity>> entity) {
                List<HomeEntertainmentEntity> data = entity.data;
                if(data==null){
                    empty();
                    return;
                }
                arrayList.clear();
                arrayList.addAll(data);
                adapter.notifyDataSetChanged();
                if(data.size()<20){
                    isDataOver = true;
                    adapter.loadMoreEnd(false);
                }else {
                    pageNo++;
                }
                frame.refreshComplete();
                content();
            }

            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
                frame.refreshComplete();
            }
        });
    }
}
