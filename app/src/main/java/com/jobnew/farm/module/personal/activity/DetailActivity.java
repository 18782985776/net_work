package com.jobnew.farm.module.personal.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.base.activity.BaseRefreshAndLoadActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.TopUpDetial;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.bazaar.OrderBazaarBean;
import com.jobnew.farm.module.personal.adapter.CollectionActivityAdapter;
import com.jobnew.farm.module.personal.adapter.DetailAdapter;
import com.jobnew.farm.module.personal.adapter.MyOrderAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wc on 2017/6/19.
 * Function：明细
 * desc：
 */

public class DetailActivity extends BaseRefreshAndLoadActivity {
    DetailAdapter adapter;
    private int page = 1;//页数
    private int pageSize=20;//加载条数
    @Override
    protected int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("明细",true);
        initData(false,1);

    }
    @Override
    public void errorChickData() {
        initData(false,1);

    }
    private List<TopUpDetial> dataAll;
    /**
     * 加载数据
     */
    private void initData(boolean isLoad,int a) {
        if (a==1){
            loading();
        }
        if (!isLoad) {
            page = 1;
        } else {
            page++;
        }
        Map<String, String> map=new HashMap<>();
        map.put("pageNo",page+"");
        map.put("pageSize",pageSize+"");
        TestRepository.getIns().getDetailPay(map).subscribe(new DefaultSubscriber<BaseEntity<List<TopUpDetial>>>(this,"获取中...") {
            @Override
            public void _onNext(BaseEntity<List<TopUpDetial>> entity) {
                List<TopUpDetial> dataSmall = entity.data;
                if (page == 1) {
                    if (dataSmall.size() == 0) {
                        empty();
                    } else {
                        content();
                    }
                }
                if (!isLoad) {
                    dataAll.clear();
                }
                dataAll.addAll(dataSmall);
                adapter.loadMoreComplete();
                ptrLayout.refreshComplete();
                if (dataSmall.size() < pageSize || dataSmall.size() == 0) {
                    adapter.loadMoreEnd(false);
                } else {
                    adapter.setEnableLoadMore(true);
                }
                adapter.notifyDataSetChanged();
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
        dataAll = new ArrayList<>();
        adapter = new DetailAdapter(R.layout.activity_detail_item, dataAll);
        return adapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    public void onLoadMoreRequested() {
        initData(true,2);
    }
    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        initData(false,2);
    }
}
