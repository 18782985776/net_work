package com.jobnew.farm.module.personal.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.MyApplication;
import com.jobnew.farm.R;
import com.jobnew.farm.base.fragment.BaseFragment;
import com.jobnew.farm.base.fragment.BaseRefreshLoadFragment;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.bazaar.OrderBazaarBean;
import com.jobnew.farm.module.farm.activity.farmActivity.FarmDetailsActivity;
import com.jobnew.farm.module.farm.activity.farmActivity.PayOrderActivity;
import com.jobnew.farm.module.farm.activity.farmActivity.ProductDetailsActivity;
import com.jobnew.farm.module.loginAndRegister.activity.LoginActivity;
import com.jobnew.farm.module.personal.adapter.MyOrderAdapter;
import com.jobnew.farm.utils.AlertUtil;
import com.jobnew.farm.widget.AppManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wc on 2017/6/8.
 * Function：待付款
 * desc：
 */

public class PaymentOrderFragment extends BaseRefreshLoadFragment {
    private MyOrderAdapter adapter;
    private List<OrderBazaarBean> dataAll;
    private int page = 1;//页数
    private int pageSize=5;//加载条数
    @Override
    protected int getLayout() {
        return R.layout.fragment_order_payment;
    }

    @Override
    protected void initView(Bundle bundle, View view) {
        initData(false,1);
        recycleOnChick();
    }
    /**
     * recycleView的点击事件
     */
    private void recycleOnChick() {
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter1, View view, int position) {
                switch (view.getId()){
                    case R.id.rl_farm:
                        if (MyApplication.isLogin()) {
                            Intent intent = new Intent();
                            intent.putExtra("farmId", adapter.getItem(position).getFarmId() + "");
                            intent.putExtra("farmName", adapter.getItem(position).getFarm().getName());
                            AppManager.jump(FarmDetailsActivity.class, intent);
                        }else{
                            AppManager.jump(LoginActivity.class);
                        }
                        break;
                    case R.id.txt_delete_order_one:
                        DeleteOrder(adapter.getItem(position).getId());
                        break;
                    case R.id.txt_payment:
                        Intent intent = new Intent();
                        intent.putExtra(Constant.ORDER_SN, adapter.getItem(position).getSn());
                        intent.putExtra(Constant.TOTAL_PRICE, adapter.getItem(position).getAmount());
                        intent.putExtra(Constant.ORDER_BODY, "网农公社—集市产品");
                        intent.putExtra(Constant.TYPE, "NORMAL");
                        AppManager.jump(PayOrderActivity.class,intent);
                        break;
                }
            }
        });
    }
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
        map.put("status","pendingPayment");
        map.put("pageNo",page+"");
        map.put("pageSize",pageSize+"");
        map.put("type","general");
        TestRepository.getIns().getOrder(map).subscribe(new DefaultSubscriber<BaseEntity<List<OrderBazaarBean>>>(this,false) {
            @Override
            public void _onNext(BaseEntity<List<OrderBazaarBean>> entity) {
                List<OrderBazaarBean> dataSmall = entity.data;
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
    /**
     * 确定是否删除订单
     * @param id
     */
    private void DeleteOrder(int id) {
        AlertUtil.show(mContext, "确定删除当前订单？", "取消", "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which==DialogInterface.BUTTON_POSITIVE){
                    GoDeleteOrder(id);
                }
            }
        });
    }
    /**
     * 删除订单
     * @param id
     */
    private void GoDeleteOrder(int id) {
        TestRepository.getIns().DeleteOrder(id+"",new HashMap<>()).subscribe(new DefaultSubscriber<BaseEntity>(this,"") {
            @Override
            public void _onNext(BaseEntity entity) {
                showMsg("删除成功");
                initData(false,2);
            }
        });
    }
    @Override
    public void errorChickData() {
        initData(false,1);
    }
    @Override
    public BaseQuickAdapter getAdapter() {
        dataAll = new ArrayList<>();
        adapter = new MyOrderAdapter(R.layout.item_my_order, dataAll, 1,getActivity());
        return adapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
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
