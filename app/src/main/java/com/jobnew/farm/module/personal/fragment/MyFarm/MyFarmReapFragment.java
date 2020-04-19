package com.jobnew.farm.module.personal.fragment.MyFarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.fragment.BaseRefreshLoadFragment;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.PersonMyFarmRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.myfarm.PlantingOrderEntity;
import com.jobnew.farm.entity.myfarm.ReapOrderEntity;
import com.jobnew.farm.module.farm.activity.farmActivity.ProductGoToSource;
import com.jobnew.farm.module.personal.activity.MyFarm.LandInformationActivity;
import com.jobnew.farm.module.personal.activity.MyFarm.PlantingPlanDetailsActivity;
import com.jobnew.farm.module.personal.activity.OrderEvaluateActivity;
import com.jobnew.farm.module.personal.adapter.MyFarm.MyFarmPlantingAdapter;
import com.jobnew.farm.module.personal.adapter.MyFarm.MyFarmReapAdapter;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.widget.AppManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wufengqiao on 2017/6/13.
 * function：
 * desc：
 */

public class MyFarmReapFragment extends BaseRefreshLoadFragment {

    private List<ReapOrderEntity> mList;
    private MyFarmReapAdapter mAdapter;
    private int page = 1;
    private boolean isRefresh = false;

    @Override
    protected int getLayout() {
        return R.layout.fragment_refresh_load;
    }

    @Override
    protected void initView(Bundle bundle, View view) {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                if (mList.get(position).orderType.equals("plant")) {

                    intent.putExtra(LandInformationActivity.TYPE, 1);
                } else if (mList.get(position).orderType.equals("grow")) {
                    intent.putExtra(LandInformationActivity.TYPE, 2);
                }
                intent.putExtra(Constant.ORDER_ID, mList.get(position).orderId);
                AppManager.jump(LandInformationActivity.class, intent);
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ReapOrderEntity entity = mList.get(position);
                switch (view.getId()) {
                    case R.id.tv_progress:
                        AppManager.jump(ProductGoToSource.class);
                        break;
                    case R.id.iv_delete:
                        deleteOrder(entity.orderId,position);
                        break;
                    case R.id.tv_comment:
                        AppManager.jump(OrderEvaluateActivity.class);
                        break;
                }
            }
        });
        loading();
        requestData();
    }

    /**
     * 删除订单
     */
    private void deleteOrder(int id,int postion) {
        PersonMyFarmRepository.getIns().deleteOrder(id + "")
                .subscribe(new DefaultSubscriber<BaseEntity>(this, "正在删除订单") {
                    @Override
                    public void _onNext(BaseEntity entity) {
                        if (entity.code == 200) {
                            FarmToastUtils.show("删除订单成功");
                            mList.remove(postion);
                            mAdapter.notifyDataSetChanged();
                            if (mList.size() == 0){
                                empty();
                            }
                        }
                    }
                });
    }

    /**
     * 请求数据
     */
    private void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("pageNo", page + "");
        map.put("pageSize", "10");
        PersonMyFarmRepository.getIns().getMyFarmCompletedOrder(map)
                .subscribe(new DefaultSubscriber<List<ReapOrderEntity>>(this, false) {
                    @Override
                    public void _onNext(List<ReapOrderEntity> list) {
                        content();
                        if (isRefresh) {
                            mList.clear();
                            ptrLayout.refreshComplete();
                            mAdapter.setEnableLoadMore(true);
                        }
                        mAdapter.addData(list);
                        mAdapter.loadMoreComplete();
                        if (list.size() < 10) {
                            mAdapter.loadMoreEnd(true);
                        }
                        if (mList.size() == 0) {
                            empty();
                        } else {
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }


    @Override
    public BaseQuickAdapter getAdapter() {
        mList = new ArrayList<>();
        mAdapter = new MyFarmReapAdapter(R.layout.item_my_farm_reap, mList, getActivity());
        return mAdapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public void onLoadMoreRequested() {
        isRefresh = false;
        page++;
        requestData();
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        isRefresh = true;
        page = 1;
        requestData();
    }
}
