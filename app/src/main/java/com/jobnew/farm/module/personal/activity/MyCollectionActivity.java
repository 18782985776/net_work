package com.jobnew.farm.module.personal.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.module.personal.adapter.CollectionAdapter;
import com.jobnew.farm.module.personal.adapter.CollectionPagerAdapter;
import com.jobnew.farm.module.personal.fragment.CollectionActivityFragment;
import com.jobnew.farm.module.personal.fragment.CollectionCompetitionFragment;
import com.jobnew.farm.module.personal.fragment.CollectionFarmFragment;
import com.jobnew.farm.module.personal.fragment.CollectionHappyFarmhouseFragment;
import com.jobnew.farm.module.personal.fragment.CollectionPersonalLandFragment;
import com.jobnew.farm.module.personal.fragment.CollectionProductionFragment;
import com.jobnew.farm.widget.TitleBarHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyCollectionActivity extends BaseActivity {
    @BindView(R.id.tv_title_left)
    TextView tvTitleLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.title_bar)
    LinearLayout titleBar;
    @BindView(R.id.viewPager_collection)
    ViewPager viewPagerCollection;
    @BindView(R.id.tab_cpllection)
    TabLayout tabLayout;
    Unbinder unbinder;
    CollectionPagerAdapter pagerAdapter;
    String[] titles={"农场","农家乐","活动","比赛","土地","农产品"};
    ArrayList<Fragment> fragments;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_collection;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        unbinder=ButterKnife.bind(this);
        initData();
        pagerAdapter=new CollectionPagerAdapter(getSupportFragmentManager(),fragments,titles);
        viewPagerCollection.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPagerCollection);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
    /**
     * 取数据
     **/
    private void initData() {
        fragments=new ArrayList<>();
        fragments.add(new CollectionFarmFragment());
        fragments.add(new CollectionHappyFarmhouseFragment());
        fragments.add(new CollectionActivityFragment());//暂无数据
        fragments.add(new CollectionCompetitionFragment());//暂无数据
        fragments.add(new CollectionPersonalLandFragment());
        fragments.add(new CollectionProductionFragment());
    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        titleBar.getLeftTextView().setVisibility(View.VISIBLE);
        titleBar.setTitleMainText("我的收藏");
        titleBar.getLeftTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
