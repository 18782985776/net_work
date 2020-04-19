package com.jobnew.farm.module.farm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.downLoad.Item;
import com.jobnew.farm.entity.plan.OrderEntity;
import com.jobnew.farm.entity.plan.OrderItemEntity;
import com.jobnew.farm.module.farm.activity.farmActivity.PayOrderActivity;
import com.jobnew.farm.module.farm.adapter.ConfirmOrderAdapter;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.utils.SPUtils;
import com.jobnew.farm.widget.AppManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wufengqiao on 2017/6/7.
 * function：种植养殖确认订单
 * desc：
 */

public class ConfirmOrderActivity extends BaseActivity {


    @BindView(R.id.tv_farm_name)
    TextView tvFarmName;
    @BindView(R.id.iv_farm)
    ImageView ivImageView;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.tv_order_total_price)
    TextView tvOrderTotalPrice;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private ConfirmOrderAdapter adapter;
    private List<OrderItemEntity> mList;
    private OrderEntity mOrderEntity;
    private DecimalFormat mDecimalFormat;
    private String body;
    private String orderType;

    @Override
    protected int getLayout() {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("确认订单", true);
        mDecimalFormat = new DecimalFormat("0.00");
        initIntent();
        initRecycleView();
    }

    private void initIntent() {
        Intent intent = getIntent();
        mOrderEntity = intent.getParcelableExtra(Constant.ORDER);//返回的实体类
        String name = intent.getStringExtra(Constant.NAME);//农场名字
        String img = intent.getStringExtra(Constant.FARM_IMG);//农场图片
        body = intent.getStringExtra(Constant.ORDER_BODY);//问王成，String
        orderType = intent.getStringExtra(Constant.TYPE);//支付类型
        GlideManager.loadRoundImg(img, ivImageView);
        tvFarmName.setText(name);
        tvOrderTotalPrice.setText("¥" + mDecimalFormat.format(mOrderEntity.amount));
    }

    private void initRecycleView() {
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        if (mOrderEntity != null && mOrderEntity.orderItems != null) {
            if (mOrderEntity.orderItems.size() == 0) {
                showMsg("服务器异常，请稍后再试");
                return;
            }
            OrderItemEntity itemEntity = null;
            for (int i = 0; i < mOrderEntity.orderItems.size(); i++) {
                OrderItemEntity entity = mOrderEntity.orderItems.get(i);
                switch (entity.type) {
                    case "PRODUCT":
                        entity.itemType = 2;
                        mList.add(entity);
                        break;
                    case "MAJOR":
                        if ("repast_agritmnt".equals(mOrderEntity.type)) {
                            entity.rate = 1;
                            entity.itemType = 4;
                        }else {
                            entity.itemType = 0;
                        }
                        mList.add(entity);
                        break;
                    case "MINOR":
                        entity.itemType = 1;
                        mList.add(entity);
                        break;
                    case "PLAN_PRODUCT":
                        if (itemEntity == null) {
                            itemEntity = new OrderItemEntity();
                        }
                        itemEntity.itemType = 2;
                        if (TextUtils.isEmpty(itemEntity.name)) {
                            itemEntity.name = entity.name;
                        } else {
                            if (itemEntity.name.split("、").length >= 2) {
                                itemEntity.name = itemEntity.name + "...";
                            } else {
                                itemEntity.name = itemEntity.name + "、" + entity.name;
                            }
                        }
                        itemEntity.price += entity.price * entity.quantity;
                        break;
                }
            }
            if (itemEntity != null) {
                mList.add(itemEntity);
            }
            if ("repast_agritmnt".equals(mOrderEntity.type)) {
            } else {
                OrderItemEntity schemeEntity = new OrderItemEntity();
                schemeEntity.itemType = 2;
                schemeEntity.price = mOrderEntity.programTotalPrice;
                if ("plant".equals(mOrderEntity.type)) {
                    schemeEntity.name = "种植方案";
                } else if ("grow".equals(mOrderEntity.type)) {
                    schemeEntity.name = "养殖方案";
                }
                mList.add(schemeEntity);
            }
            if (mOrderEntity != null && !TextUtils.isEmpty(mOrderEntity.address)) {
                OrderItemEntity addressEntity = new OrderItemEntity();
                addressEntity.itemType = 2;
                addressEntity.price = mOrderEntity.freight;
                addressEntity.name = "配送地址";
                mList.add(addressEntity);
                OrderItemEntity addressInfoEntity = new OrderItemEntity();
                addressInfoEntity.itemType = 3;
                addressInfoEntity.name = mOrderEntity.areaName + mOrderEntity.address;
                mList.add(addressInfoEntity);
            }
        }
        adapter = new ConfirmOrderAdapter(this, mList);
        rvContent.setAdapter(adapter);
        if (mList.size() == 0) {
            empty();
        }
    }


    @OnClick(R.id.tv_save)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_save:
                Intent intent = new Intent();
                intent.putExtra(Constant.ORDER_SN, mOrderEntity.sn);
                intent.putExtra(Constant.TOTAL_PRICE, mOrderEntity.amount);
                intent.putExtra(Constant.ORDER_BODY, body);
                intent.putExtra(Constant.TYPE, orderType);
                AppManager.jump(PayOrderActivity.class, intent);
                break;
        }
    }

}
