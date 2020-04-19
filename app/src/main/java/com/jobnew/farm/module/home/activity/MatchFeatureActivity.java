package com.jobnew.farm.module.home.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.listeners.TextDistanceLisenter;
import com.jobnew.farm.listeners.TextDistanceMatchOverLisenter;
import com.jobnew.farm.listeners.TextDistanceMatchingLisenter;
import com.jobnew.farm.module.home.fragment.MatchOrderByDistanceFargment;
import com.jobnew.farm.module.home.fragment.MatchOverFargment;
import com.jobnew.farm.module.home.fragment.MatchingFragment;
import com.jobnew.farm.module.home.fragment.TakeMatchFragment;
import com.jobnew.farm.module.personal.adapter.FarmPagerAdapter;
import com.jobnew.farm.widget.AppManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MatchFeatureActivity extends BaseActivity {
    @BindView(R.id.tab_match)
    TabLayout tabMatch;
   // String[] mStrings = {"报名中", "比赛中", "已结束", "距离最近"};
    String[] mStrings = {"报名中", "比赛中", "已结束"};
    @BindView(R.id.m_pager)
    ViewPager mPager;
    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.show_addresstv)
    TextView showAddresstv;
    @BindView(R.id.my_match_tv)
    TextView myMatchTv;
    @BindView(R.id.tv_orderby_address)
    TextView tvOrderByAddress;
    private FarmPagerAdapter farmPagerAdapter;
    ArrayList<Fragment> fragmentList;
    public static boolean isPos=false;
    int clickNum=1;
    private TextDistanceLisenter distanceLisenter=null;
    private TextDistanceMatchOverLisenter matchOverLisenter=null;
    private TextDistanceMatchingLisenter matchingLisenter=null;
    public void setMatchingLisenter(TextDistanceMatchingLisenter matchingLisenter){
        this.matchingLisenter=matchingLisenter;
    }
    public void setMatchOverLisenter(TextDistanceMatchOverLisenter matchOverLisenter){
        this.matchOverLisenter=matchOverLisenter;
    }
    public void setTextDistanceLisenter(TextDistanceLisenter distanceLisenter){
        this.distanceLisenter=distanceLisenter;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_match_feature;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new TakeMatchFragment());
        fragmentList.add(new MatchingFragment());
        fragmentList.add(new MatchOverFargment());
      //  fragmentList.add(new MatchOrderByDistanceFargment());
        farmPagerAdapter = new FarmPagerAdapter(getSupportFragmentManager(), fragmentList, mStrings);
        mPager.setAdapter(farmPagerAdapter);
        tabMatch.setupWithViewPager(mPager);
        tabMatch.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    @OnClick({R.id.back_img,R.id.my_match_tv,R.id.show_addresstv,R.id.tv_orderby_address})
    public  void dealViewClick(View view){
        switch (view.getId()){
            case R.id.back_img:
                onBackPressed();
                break;
            case R.id.my_match_tv:
                AppManager.jump(MyMatchActivity.class);
                break;
            case  R.id.show_addresstv:

                break;
            case R.id.tv_orderby_address :
                clickNum++;
                if(clickNum%2==0){//选中距离排序
                    tvOrderByAddress.setTextColor(Color.parseColor("#90b659"));
                    isPos=true;
                    if(distanceLisenter!=null) distanceLisenter.reLoadData();
                    if(matchingLisenter!=null)    matchingLisenter.reLoadData();
                    if(matchOverLisenter!=null)   matchOverLisenter.reLoadData();
                }else {//没选中
                    isPos=false;
                    tvOrderByAddress.setTextColor(Color.parseColor("#686868"));
                    if(distanceLisenter!=null) distanceLisenter.reLoadData();
                    if(matchingLisenter!=null)    matchingLisenter.reLoadData();
                    if(matchOverLisenter!=null)   matchOverLisenter.reLoadData();
                }
                break;
        }
    }
}
