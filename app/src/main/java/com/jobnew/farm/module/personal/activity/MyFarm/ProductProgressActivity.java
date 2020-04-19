package com.jobnew.farm.module.personal.activity.MyFarm;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseRefreshAndLoadActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.PersonMyFarmRepository;
import com.jobnew.farm.entity.myfarm.ProductProgressEntity;
import com.jobnew.farm.module.farm.adapter.ProductProcessAdapter;
import com.jobnew.farm.module.farm.adapter.farmAdapter.GoToSourceAdapter;
import com.jobnew.farm.module.personal.adapter.MyFarm.ProductProgressAdapter;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.widget.TitleBarHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrFrameLayout;

public class ProductProgressActivity extends BaseRefreshAndLoadActivity {
    ProductProgressAdapter adapter;
    List<ProductProgressEntity> mList;
    private int orderId;
    private boolean isFresh = false;
    private int page = 1;

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        setTitle("成长进度", true);
        titleBar.setRightText("联系管家", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FarmToastUtils.show("联系管家");
            }
        });
        titleBar.getRightTextView().setCompoundDrawablesWithIntrinsicBounds(R.mipmap.farm_icon_consultation, 0, 0, 0);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_select_refresh;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        orderId = getIntent().getIntExtra(Constant.ORDER_ID, -1);
        if (orderId <= 0) {
            error("");
            return;
        }
        loading();
        requestData();
    }

    private void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderId + "");
        map.put("pageNo", page + "");
        map.put("pageSize", "20");
        PersonMyFarmRepository.getIns().getProductProgress(map)
                .subscribe(new DefaultSubscriber<List<ProductProgressEntity>>(this, false) {
                    @Override
                    public void _onNext(List<ProductProgressEntity> list) {
                        if (list == null) {
                            error("");
                        }
                        if (isFresh) {
                            ptrLayout.refreshComplete();
                            mList.clear();
                        }

                        mList.addAll(list);
                        if (page != 1) {
                            adapter.loadMoreComplete();
                        }
                        if (list.size() < 30) {
                            adapter.loadMoreEnd(true);
                        }
                        if (mList.size() == 0) {
                            empty();
                        } else {
                            adapter.notifyDataSetChanged();
                            content();
                        }
                    }
                });

    }


    @Override
    public BaseQuickAdapter getAdapter() {
        mList = new ArrayList<>();
        return adapter = new ProductProgressAdapter(R.layout.item_product_source, mList, this);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        requestData();
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        page = 1;
        isFresh = true;
        requestData();
    }
}
