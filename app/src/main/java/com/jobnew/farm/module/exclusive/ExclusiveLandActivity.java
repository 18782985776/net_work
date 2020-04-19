package com.jobnew.farm.module.exclusive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseRefreshAndLoadActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.ExclusiveLandRepository;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.AllBusniessEntity;
import com.jobnew.farm.entity.exclusive.ExclusiveLandEntity;
import com.jobnew.farm.module.personal.activity.HelpActivity;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.TitleBarHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wufengqiao on 2017/7/31.
 * function： 租地  --- 专属土地页面
 * desc：
 */

public class ExclusiveLandActivity extends BaseRefreshAndLoadActivity {

    boolean isAscent = true;
    @BindView(R.id.tv_ranking_filter)
    TextView tvRankingFilter;

    @BindView(R.id.tv_price_filter)
    CheckedTextView tvPriceFilter;

    @BindView(R.id.tv_area_filter)
    CheckedTextView tvAreaFilter;

    @BindView(R.id.tv_distance_filter)
    CheckedTextView tvDistanceFilter;

    @BindView(R.id.tv_super_filter)
    TextView tvSuperFilter;
    @BindView(R.id.rv_title)
    RecyclerView rvTitle;
    private ArrayList<ExclusiveLandEntity> mList;
    private ExclusiveLandAdapter mAdapter;
    private List<AllBusniessEntity.BusinessBean> titleList;
    private ExclusiveLandTitleAdapter mTitleAdapter;
    private boolean isNoFit = true;
    private int pageNo = 1;
    private boolean isRefresh;
    private String orderType = "normal";
    private int fit;
    private AllBusniessEntity.BusinessBean titleEntity;

    @Override
    public BaseQuickAdapter getAdapter() {
        mList = new ArrayList<>();
        mAdapter = new ExclusiveLandAdapter(R.layout.item_exclusive_land, mList);
        return mAdapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_exclusive_land;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        tvRankingFilter.setSelected(true);

        loading();
        setTitleRecycleView();  //土地适宜view设置
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {   //专属农场列表item监听
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //调整到土地详情
                Intent intent = new Intent();
                intent.putExtra(Constant.FARM_ID, mList.get(position).id);
                AppManager.jump(ExclusiveLandDetailsActivity.class,intent);
            }
        });

        initClassTitle();
        requestData();
    }

    /**
     * 请求网络，初始化土地适宜
     */
    private void initClassTitle() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "enterprise");
        MyFarmRepository.getIns().getAllBusinessType(map)
                .subscribe(new DefaultSubscriber<AllBusniessEntity>(this, false) {
                    @Override
                    public void _onNext(AllBusniessEntity entity) {
                        if (entity.getBusiness() == null || entity.getBusiness().size() == 0) {
                            empty();
                            isNoFit = true;
                            return;
                        }
                        isNoFit = false;
                        titleList.addAll(entity.getBusiness());
                        mTitleAdapter.notifyDataSetChanged();
                    }
                });
    }


    /**
     * 请求专属土地列表信息
     */
    private void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("longitude", Constant.LONGITUDE + "");
        map.put("latitude", Constant.LATITUDE + "");
        map.put("orderType", orderType);
        map.put("orderBy", isAscent ? "asc" : "desc");
        if (fit > 0) {   //fit土地适宜   不传代表所有
            map.put("fit", fit + "");
        }
        map.put("pageNo", pageNo + "");
        map.put("pageSize", 15 + "");
        ExclusiveLandRepository.getIns().getExclusiveLand(map)
                .subscribe(new DefaultSubscriber<List<ExclusiveLandEntity>>(this, false) {
                    @Override
                    public void _onNext(List<ExclusiveLandEntity> list) {
                        if (pageNo == 1) {
                            mList.clear();
                        }
                        mList.addAll(list);
                        if (isRefresh) {
                            ptrLayout.refreshComplete();
                            mAdapter.setNewData(mList);
                        } else {
                            if (pageNo != 1) {
                                mAdapter.loadMoreComplete();
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                        if (list.size() < 15) {
                            mAdapter.loadMoreEnd();
                        }
                        content();
                    }
                });
    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        titleBar.setTitleMainText("专属土地");
        titleBar.setLeftText("成都", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        titleBar.setLeftTextDrawable(R.mipmap.ic_select);
        titleBar.setRightText("帮助说明", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.jump(HelpActivity.class,Constant.CATEGORY_SN,"ELHELP");
            }
        });

    }

    /**
     * 土地适宜设置
     */
    private void setTitleRecycleView() {
        rvTitle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        titleList = new ArrayList<>();
        mTitleAdapter = new ExclusiveLandTitleAdapter(R.layout.item_tab_exclusive_land, titleList);
        rvTitle.setAdapter(mTitleAdapter);
        mTitleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < titleList.size(); i++) {
                    AllBusniessEntity.BusinessBean entity = titleList.get(i);
                    if (i != position) {
                        entity.isSelect = false;
                    } else {
                        titleEntity = entity;
                    }
                }
                if (!titleEntity.isSelect) {
                    titleEntity.isSelect = true;
                    mTitleAdapter.notifyDataSetChanged();
                }
                fit = titleEntity.getId();
                requestData();
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        pageNo++;
        requestData();
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        isRefresh = true;
        requestData();
    }

    @OnClick({R.id.tv_ranking_filter, R.id.rl_price_filter, R.id.rl_distance_filter, R.id.rl_area_filter, R.id.tv_super_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_ranking_filter: //综合排序
                setViewStatus(tvRankingFilter);
                orderType = "normal";
                requestData();
                break;
            case R.id.rl_price_filter: //价格
                setViewStatus(tvPriceFilter);
                orderType = "price";
                requestData();
                break;
            case R.id.rl_distance_filter:  //距离
                setViewStatus(tvDistanceFilter);
                orderType = "position";
                requestData();
                break;
            case R.id.rl_area_filter:   //面积
                setViewStatus(tvAreaFilter);
                orderType = "area";
                requestData();
                break;
            case R.id.tv_super_filter:  //土地适宜
                if (isNoFit) {
                    FarmToastUtils.show("暂时没有选项，请稍后再试");
                } else {
                    tvSuperFilter.setSelected(!tvSuperFilter.isSelected());
                    if (tvSuperFilter.isSelected()) {
                        rvTitle.setVisibility(View.VISIBLE);
                        for (int i = 0; i < titleList.size(); i++) {
                            if (titleList.get(i).isSelect) {
                                fit = titleList.get(i).getId();
                                requestData();
                            }
                        }
                    } else {
                        rvTitle.setVisibility(View.GONE);
                        fit = 0;
                        requestData();
                    }
                    break;
                }
        }
    }

    /**
     * 设置选择状态
     */

    private void setViewStatus(TextView view) {
        tvRankingFilter.setSelected(false);
        tvDistanceFilter.setSelected(false);
        tvPriceFilter.setSelected(false);
        tvAreaFilter.setSelected(false);
        view.setSelected(true);
        if (view == tvDistanceFilter) {
            tvDistanceFilter.setChecked(!tvDistanceFilter.isChecked());
        } else {
            tvDistanceFilter.setChecked(false);
        }
        if (view == tvPriceFilter) {
            tvPriceFilter.setChecked(!tvPriceFilter.isChecked());
        } else {
            tvPriceFilter.setChecked(false);
        }
        if (view == tvAreaFilter) {
            tvAreaFilter.setChecked(!tvAreaFilter.isChecked());
        } else {
            tvAreaFilter.setChecked(false);
        }
        if (view instanceof CheckedTextView) {
            isAscent = ((CheckedTextView) view).isChecked();
        } else {
            isAscent = false;
        }
    }
}
