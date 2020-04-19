package com.jobnew.farm.module.personal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseRefreshAndLoadActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.data.repository.PersonMyFarmRepository;
import com.jobnew.farm.entity.ShoppingCar.AddShoppingTrolleyOrderBean;
import com.jobnew.farm.entity.ShoppingCar.AddShoppingTrolleyOrderSuccessBaen;
import com.jobnew.farm.entity.ShoppingCar.ShoppingCarProductBean;
import com.jobnew.farm.entity.ShoppingCar.ShoppingProductEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.personal.adapter.ShoppingTrolleyAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wufengqiao on 2017/8/17.
 * function： 购物车
 * desc：
 */

public class ShoppingTrolleyActivity extends BaseRefreshAndLoadActivity {

    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.ll_all_select)
    LinearLayout mLlAllSelect;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    private List<ShoppingProductEntity> mList;
    private ShoppingTrolleyAdapter mAdapter;
    private double mTotalPrice;
    private DecimalFormat mFormat;
    private boolean isEditStatus;

    @Override
    public BaseQuickAdapter getAdapter() {
        mList = new ArrayList<>();
        mAdapter = new ShoppingTrolleyAdapter(R.layout.item_shopping_trolley, mList, this);
        return mAdapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_shopping_trolley;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("购物车", true);
        mFormat = new DecimalFormat("0.00");
        setRecycleView();
        requestData();
    }

    private void setRecycleView() {
        rvContent.setAdapter(mAdapter);
        mAdapter.setEnableLoadMore(false);
        ((SimpleItemAnimator) rvContent.getItemAnimator()).setSupportsChangeAnimations(false);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingProductEntity entity = mList.get(position);
                switch (view.getId()) {
                    case R.id.tv_edit:
                        if (entity.isEditStatus) {
                            updatedata(position);
                            return;
                        }
                        isEditStatus = true;
                        List<ShoppingProductEntity> list = entity.list;
                        entity.isEditStatus = true;
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).isEditStatus = true;
                        }
                        mAdapter.notifyItemChanged(position);
                        break;
                }
            }


        });
        mAdapter.setSelectChangeListener(new ShoppingTrolleyAdapter.OnSelectChangeListener() {
            @Override
            public void onSelectChange(boolean selected) {
                if (checkAllSelect()) {
                    mLlAllSelect.setSelected(true);
                } else {
                    mLlAllSelect.setSelected(false);
                }
                notifyTotalChangePrice();
            }
        });
        mAdapter.setOnItemDeleteListener(new ShoppingTrolleyAdapter.OnItemDeleteListener() {
            @Override
            public void onItemDeleterListener(int position, int parentPostion, BaseQuickAdapter adapter) {
                deleteDate(position, parentPostion, adapter);
            }
        });
    }

    /**
     * 修改购物车数量
     */
    public void updatedata(int position) {
        ShoppingProductEntity shoppingProductEntity = mList.get(position);
        List<ShoppingCarProductBean> list = new ArrayList<>();
        for (int i = 0; i < shoppingProductEntity.list.size(); i++) {
            ShoppingProductEntity entity = shoppingProductEntity.list.get(i);
            if (entity.tempQuantity != entity.quantity && entity.tempQuantity > 0) {
                ShoppingCarProductBean shoppingCarUpdateBean = new ShoppingCarProductBean();
                shoppingCarUpdateBean.quantity = entity.tempQuantity;
                shoppingCarUpdateBean.productId = entity.productId;
                list.add(shoppingCarUpdateBean);
            }
        }
        if (list.size() == 0) {
            shoppingProductEntity.isEditStatus = false;
            for (int i = 0; i < shoppingProductEntity.list.size(); i++) {
                shoppingProductEntity.list.get(i).isEditStatus = false;
            }
            mAdapter.notifyItemChanged(position);
            isEditStatus = false;
            return;
        }
        PersonMyFarmRepository.getIns().updateShoppingTrolley(list)
                .subscribe(new DefaultSubscriber<BaseEntity>(this, "修改中") {
                    @Override
                    public void _onNext(BaseEntity successEntity) {
                        if (successEntity.code == 200) {

                            for (int i = 0; i < shoppingProductEntity.list.size(); i++) {
                                ShoppingProductEntity spEntity = shoppingProductEntity.list.get(i);
                                if (spEntity.tempQuantity > 0) {
                                    spEntity.quantity = spEntity.tempQuantity;
                                    spEntity.tempQuantity = 0;
                                }
                            }
                            List<ShoppingProductEntity> list = shoppingProductEntity.list;
                            isEditStatus = false;
                            shoppingProductEntity.isEditStatus = false;
                            for (int i = 0; i < list.size(); i++) {
                                list.get(i).isEditStatus = false;
                            }
                            mAdapter.notifyItemChanged(position);
                            notifyTotalChangePrice();
                            showMsg("修改成功");
                            notifyTotalChangePrice();
                        } else {
                            showMsg(successEntity.msg);
                        }
                    }

                    @Override
                    public void _onError(Throwable e, String msg) {
                        showLoading(msg);
                    }
                });
    }

   /* *//**
     * 判断是否是编辑状态
     *//*
    public void notifyEditStatusChange() {
        isEditStatus = false;
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).isEditStatus) {
                isEditStatus = true;
            }
        }
    }*/

    /**
     * 删除商品
     */
    public void deleteDate(int position, int parentPostion, BaseQuickAdapter adapter) {
        ShoppingProductEntity entity = mList.get(parentPostion);
        PersonMyFarmRepository.getIns().deleteShoppingTrolley(entity.list.get(position).productId)
                .subscribe(new DefaultSubscriber<BaseEntity>(this, "移除商品") {
                    @Override
                    public void _onNext(BaseEntity baseEntity) {
                        if (baseEntity.code == 200) {
                            showMsg("移除商品成功");
                            adapter.remove(position);
                            if (entity.list.size() == 0) {
                                mAdapter.remove(parentPostion);
                                if (mList.size() == 0) {
                                    empty();
                                }
                                notifyTotalChangePrice();
                            }
                            notifyTotalChangePrice();
                        } else {
                            showMsg(baseEntity.msg);
                        }
                    }
                });
    }

    /**
     * 设置总价
     */
    public void notifyTotalChangePrice() {
        mTotalPrice = 0.00;
        for (int i = 0; i < mList.size(); i++) {
            List<ShoppingProductEntity> list = mList.get(i).list;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).isSelect) {
                    mTotalPrice += list.get(j).productPrice * list.get(j).quantity;
                }
            }
        }
        tvTotalPrice.setText("¥" + mFormat.format(mTotalPrice));
    }

    private void requestData() {
        loading();
        PersonMyFarmRepository.getIns().getShoppingTrolley()
                .subscribe(new DefaultSubscriber<List<ShoppingProductEntity>>(this, false) {
                    @Override
                    public void _onNext(List<ShoppingProductEntity> list) {
                        /*if (ptrLayout.isRefreshing()) {
                            mList.clear();
                            ptrLayout.refreshComplete();
                        }*/
                        if (list.size() == 0) {
                            empty();
                            return;
                        } else {
                            content();
                        }
                        List<Integer> temps = new ArrayList<>();     //mList放农场集合，把同一个农场的产品放到农场内集合里
                        for (int i = 0; i < list.size(); i++) {
                            ShoppingProductEntity entity = list.get(i);
                            entity.tempQuantity = entity.quantity;
                            if (temps.contains(entity.farmId)) {  //判断产品归属农场是否出现过
                                for (int j = 0; j < mList.size(); j++) {
                                    if (mList.get(j).farmId == entity.farmId) {
                                        mList.get(j).add(entity);   //把产品放入农场 内集合
                                    }
                                }
                            } else {
                                temps.add(entity.farmId);
                                ShoppingProductEntity farmEntity = new ShoppingProductEntity();   //创建农场
                                farmEntity.farmName = entity.farmName;
                                farmEntity.farmLogo = entity.farmLogo;
                                farmEntity.farmId = entity.farmId;
                                farmEntity.add(entity);   //把产品放入农场 内集合
                                mList.add(farmEntity);    //把农场放到mList里
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });

    }

    @Override

    public void onLoadMoreRequested() {
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        requestData();
    }


    @OnClick({R.id.ll_all_select, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_all_select:
                if (checkAllSelect()) {
                    view.setSelected(false);
                    selectAll(false);
                } else {
                    selectAll(true);
                    view.setSelected(true);
                }
                break;
            case R.id.tv_save:
                if (isEditStatus) {
                    showMsg("请完成所有编辑！");
                    return;
                }
                if (mList.size() == 0) {
                    return;
                }
                startConfirmOrder(); //跳转订单
                break;
        }
    }

    private void startConfirmOrder() {
        ArrayList<ShoppingProductEntity> list = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            List<ShoppingProductEntity> speList = mList.get(i).list;
            ShoppingProductEntity entity = null;
            for (int j = 0; j < speList.size(); j++) {
                if (speList.get(j).isSelect) {
                    if (entity == null) {
                        entity = new ShoppingProductEntity();
                        entity.farmName = mList.get(i).farmName;
                        entity.farmLogo = mList.get(i).farmLogo;
                    }
                    entity.productPrice += speList.get(j).productPrice * speList.get(j).quantity;
                    entity.add(speList.get(j));
                }
            }
            if (entity != null && entity.list.size() > 0) {
                list.add(entity);
            }
        }
        Intent intent = new Intent(this, ConfirmOrderActivity.class);
        intent.putParcelableArrayListExtra("data", list);
        intent.putExtra("type", "general");
        intent.putExtra("payOrderType", "NORMAL");
        intent.putExtra(Constant.ORDER_BODY, "我的购物车");
        startActivity(intent);
    }


    /**
     * 检测是否选择全部
     *
     * @return
     */
    public boolean checkAllSelect() {
        for (int i = 0; i < mList.size(); i++) {
            if (!mList.get(i).isSelect) {
                return false;
            }
        }
        return true;
    }

    /**
     * 选择全部
     *
     * @param isSelect
     */
    public void selectAll(boolean isSelect) {
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).isSelect = isSelect;
            mList.get(i).setSelectAll(isSelect);
        }
        mAdapter.notifyDataSetChanged();
    }
}
