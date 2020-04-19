package com.jobnew.farm.module.farm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseRefreshAndLoadActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.FarmRepository;
import com.jobnew.farm.entity.plan.StewardEntity;
import com.jobnew.farm.module.farm.adapter.StewardAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wufengqiao on 2017/5/26.
 * Function : 选择管家
 */

public class StewardActivity extends BaseRefreshAndLoadActivity {

    private List<StewardEntity> mList;
    private StewardAdapter mAdapter;
    private int farmId;


    @Override
    public BaseQuickAdapter getAdapter() {
        mList = new ArrayList<>();
        mAdapter = new StewardAdapter(R.layout.item_steward, mList);
        return mAdapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_select_refresh;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("选择管家",true);
        farmId = getIntent().getIntExtra(Constant.FARM_ID, -1);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent().putExtra(Constant.STEWARD, mList.get(position));
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    /**
     * 需要加载数据时重写此方法
     */
    protected void loadData() {
        loading();
        requestData(false);
    }

    private void requestData(boolean isRefresh) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("farmId",farmId+"");
        FarmRepository.getIns().getSteward(map).subscribe(new DefaultSubscriber<List<StewardEntity>>(this,false) {
            @Override
            public void _onNext(List<StewardEntity> list) {
                //模拟数据
                mList.clear();
                mList.addAll(list);
                if (isRefresh){
                    ptrLayout.refreshComplete();
                }else {
                    content();
                }
                if (list.size() == 0){
                    empty();
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        mAdapter.loadMoreComplete();
        mAdapter.loadMoreEnd(true);
    }
    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        requestData(true);
    }
}
