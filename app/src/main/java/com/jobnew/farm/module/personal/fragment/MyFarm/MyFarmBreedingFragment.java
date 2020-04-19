package com.jobnew.farm.module.personal.fragment.MyFarm;

import android.content.DialogInterface;
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
import com.jobnew.farm.data.repository.PersonMyFarmRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.myfarm.PlantingOrderEntity;
import com.jobnew.farm.module.farm.activity.farmActivity.FarmDetailsActivity;
import com.jobnew.farm.module.farm.activity.farmActivity.PayOrderActivity;
import com.jobnew.farm.module.farm.activity.farmActivity.ProductGoToSource;
import com.jobnew.farm.module.personal.activity.MyFarm.LandInformationActivity;
import com.jobnew.farm.module.personal.activity.MyFarm.PlantingPlanDetailsActivity;
import com.jobnew.farm.module.personal.activity.MyFarm.ProductProgressActivity;
import com.jobnew.farm.module.personal.adapter.MyFarm.MyFarmPlantingAdapter;
import com.jobnew.farm.utils.AlertUtil;
import com.jobnew.farm.utils.Arith;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.widget.AppManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wufengqiao on 2017/6/14.
 * function：
 * desc：
 */

public class MyFarmBreedingFragment extends BaseRefreshLoadFragment {

    private List<PlantingOrderEntity> mList;
    private MyFarmPlantingAdapter mAdapter;
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
                intent.putExtra(LandInformationActivity.TYPE, 2);
                intent.putExtra(Constant.ORDER_ID, mList.get(position).orderId);
                AppManager.jump(LandInformationActivity.class, intent);
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                PlantingOrderEntity entity = mList.get(position);
                Intent intent = null;
                switch (view.getId()) {
                    case R.id.tv_contact:
                        FarmToastUtils.show("联系管家");
                        break;
                    case R.id.tv_progress:
                        if ("pendingPayment".equals(entity.orderStatus)) {
                            intent = new Intent();
                            intent.putExtra(Constant.ORDER_SN, entity.orderSn);
                            intent.putExtra(Constant.TOTAL_PRICE, entity.orderAmount);
                            intent.putExtra(Constant.ORDER_BODY, "养殖订单");
                            intent.putExtra(Constant.TYPE, "NORMAL");
                            AppManager.jump(PayOrderActivity.class, intent);
                        } else {
                            AppManager.jump(ProductProgressActivity.class, Constant.ORDER_ID, entity.orderId);
                        }
                        break;
                    case R.id.tv_plant_plan:
                        intent = new Intent();
                        intent.putExtra(Constant.ORDER_ID, entity.orderId);
                        intent.putExtra(Constant.TYPE, 2);
                        AppManager.jump(PlantingPlanDetailsActivity.class, intent);                        break;
                    case R.id.tv_delete:
                        AlertUtil.show(mContext, "提示", "你确认要删除订单吗？", "取消", "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == DialogInterface.BUTTON_POSITIVE) {
                                    deleteOrder(entity.orderId, position);
                                }
                            }
                        });
                        break;
                    case R.id.iv_farm:
                    case R.id.tv_farm:
                        intent = new Intent();
                        intent.putExtra(Constant.FARM_ID, entity.farmId + "");
                        intent.putExtra(Constant.FARM_NAME, entity.farmName);
                        AppManager.jump(FarmDetailsActivity.class, intent);
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

    private void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("pageNo", page + "");
        map.put("pageSize", "10");
        PersonMyFarmRepository.getIns().getMyFarmBreedOrder(map)
                .subscribe(new DefaultSubscriber<List<PlantingOrderEntity>>(this, false) {
                    @Override
                    public void _onNext(List<PlantingOrderEntity> list) {
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
                        }
                    }
                });
    }


    @Override
    public BaseQuickAdapter getAdapter() {
        mList = new ArrayList<>();
        mAdapter = new MyFarmPlantingAdapter(mList, getActivity(), false);
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
