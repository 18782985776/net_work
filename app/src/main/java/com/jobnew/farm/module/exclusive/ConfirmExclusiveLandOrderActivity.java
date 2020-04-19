package com.jobnew.farm.module.exclusive;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.ExclusiveLandRepository;
import com.jobnew.farm.entity.exclusive.ExclusiveLandEntity;
import com.jobnew.farm.entity.plan.OrderEntity;
import com.jobnew.farm.module.farm.activity.farmActivity.PayOrderActivity;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.QuantityHelper;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wufengqiao on 2017/8/16.
 * function：
 * desc：
 */

public class ConfirmExclusiveLandOrderActivity extends BaseActivity {

    @BindView(R.id.iv_farm)
    ImageView ivFarm;
    @BindView(R.id.tv_farm_name)
    TextView tvFarmName;
    @BindView(R.id.iv_product)
    ImageView ivProduct;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_unit_price)
    TextView tvUnitPrice;
    @BindView(R.id.tv_order_total_price)
    TextView tvOrderTotalPrice;
    @BindView(R.id.view_time)
    View time;

    private ExclusiveLandEntity mEntity;
    private String mFarmName;
    private String mFarmImg;
    private int count;
    private QuantityHelper timeHelper;
    private String memo = "";
    private String use = "";

    @Override
    protected int getLayout() {
        return R.layout.activity_confirm_exclusive_land_order;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initIntent();
        initData();
    }

    private void initIntent() {
        Intent intent = getIntent();
        mEntity = intent.getParcelableExtra(Constant.EXCLUSIVE_LAND);
        mFarmName = intent.getStringExtra(Constant.FARM_NAME);
        mFarmImg = intent.getStringExtra(Constant.FARM_IMG);
        count = intent.getIntExtra(Constant.NUMBER, 1);
        memo = intent.getStringExtra("memo");
        use = intent.getStringExtra("use");
    }

    private void initData() {
        GlideManager.loadRoundImg(mFarmImg, ivFarm);
        tvFarmName.setText(mFarmName);
        GlideManager.loadImg(mEntity.pImg, ivProduct);
        tvProductName.setText(mEntity.name);
        tvCount.setText(mEntity.stock + "㎡");
        tvUnitPrice.setText(new DecimalFormat("0.00").format(mEntity.price));
        timeHelper = new QuantityHelper(time, 1, 9999);
        timeHelper.setCount(count);
        timeHelper.setOnCountChangeListener(new QuantityHelper.OnCountChangeListener() {
            @Override
            public void onCountChange(int count) {
                tvOrderTotalPrice.setText("¥" + new DecimalFormat("0.00").format(mEntity.price * timeHelper.getCount() * mEntity.stock));
            }
        });
        tvOrderTotalPrice.setText("¥" + new DecimalFormat("0.00").format(mEntity.price * timeHelper.getCount() * mEntity.stock));
    }


    @OnClick(R.id.tv_save)
    public void onViewClicked() {
        Map<String, String> map = new HashMap<>();
        map.put("productId", mEntity.id + "");
        map.put("rate", timeHelper.getCount() + "");
        map.put("memo", memo);
        map.put("use", use);
        ExclusiveLandRepository.getIns().addLeaseOrder(map)
                .subscribe(new DefaultSubscriber<OrderEntity>(this, "正在提交订单") {
                    @Override
                    public void _onNext(OrderEntity entity) {
                        FarmToastUtils.show("订单提交成功");
                        Intent intent = new Intent();
                        intent.putExtra(Constant.CATEGORY_SN, entity.sn);
                        intent.putExtra(Constant.TOTAL_PRICE, entity.amount);
                        intent.putExtra(Constant.ORDER_BODY, "专属农场-租地");
                        intent.putExtra(Constant.TYPE, "NORMAL");
                        AppManager.jump(PayOrderActivity.class, intent);
                    }

                    @Override
                    public void _onError(Throwable e, String msg) {
                        super._onError(e, msg);
                        showMsg(msg);
                    }
                });
    }
}
