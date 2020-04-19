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
import com.google.gson.Gson;
import com.jobnew.farm.MyApplication;
import com.jobnew.farm.R;
import com.jobnew.farm.base.fragment.BaseFragment;
import com.jobnew.farm.base.fragment.BaseRefreshLoadFragment;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.bazaar.OrderBazaarBean;
import com.jobnew.farm.module.farm.activity.farmActivity.FarmDetailsActivity;
import com.jobnew.farm.module.farm.activity.farmActivity.ProductDetailsActivity;
import com.jobnew.farm.module.loginAndRegister.activity.LoginActivity;
import com.jobnew.farm.module.personal.activity.AfterReturnActivity;
import com.jobnew.farm.module.personal.activity.LogisticsActivity;
import com.jobnew.farm.module.personal.activity.OrderEvaluateActivity;
import com.jobnew.farm.module.personal.activity.order.OrderMoreEvaAndSafActivity;
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
 * Function：待评价
 * desc：
 */

public class EvaluationOrderFragment extends BaseRefreshLoadFragment {
    private MyOrderAdapter adapter;
    private List<OrderBazaarBean> dataAll;
    private int page = 1;//页数
    private int pageSize=5;//加载条数
    @Override
    protected int getLayout() {
        return R.layout.fragment_order_evaluation;
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
                    case R.id.txt_check_logistics_three:
                        AppManager.jump(LogisticsActivity.class,"orderId",adapter.getItem(position).getId());
                        break;
                    case R.id.txt_delete_order_two:
                        DeleteOrder(adapter.getItem(position).getId());
                        break;
                    case R.id.txt_evaluation:
                        //如果只有一个商品 就去评价那一个商品
                        if (adapter.getItem(position).getOrderItems().size()==1){
                            Intent intent=new Intent();
                            intent.putExtra("orderId",adapter.getItem(position).getId());
                            intent.putExtra("orderItemId",adapter.getItem(position).getOrderItems().get(0).getId());
                            AppManager.jump(OrderEvaluateActivity.class,intent);
                        }else{
                            List<OrderBazaarBean.OrderItemsEntity> orderItems = adapter.getItem(position).getOrderItems();
                            Intent intent=new Intent();
                            intent.putExtra("orderId",adapter.getItem(position).getId());
                            intent.putExtra("data",new Gson().toJson(adapter.getItem(position).getOrderItems()));
                            AppManager.jump(OrderMoreEvaAndSafActivity.class,intent);
                        }
                        break;
                    case R.id.txt_after_sales:
                        //如果只有一个商品 就去售后那一个商品
                        if (adapter.getItem(position).getOrderItems().size()==1){
                            Intent intent1=new Intent();
                            intent1.putExtra("orderItemId",adapter.getItem(position).getOrderItems().get(0).getId());
                            intent1.putExtra("money",adapter.getItem(position).getOrderItems().get(0).getPrice());
                            AppManager.jump(AfterReturnActivity.class,intent1);
                        }else{
                            List<OrderBazaarBean.OrderItemsEntity> orderItems = adapter.getItem(position).getOrderItems();
                            Intent intent=new Intent();
                            intent.putExtra("orderId",adapter.getItem(position).getId());
                            intent.putExtra("data",new Gson().toJson(adapter.getItem(position).getOrderItems()));
                            AppManager.jump(OrderMoreEvaAndSafActivity.class,intent);
                        }
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
        map.put("status","completed");
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
        adapter = new MyOrderAdapter(R.layout.item_my_order, dataAll, 4,getActivity());
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
