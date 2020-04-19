package com.jobnew.farm.module.personal.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.ShoppingCar.ShoppingProductEntity;
import com.jobnew.farm.utils.GlideManager;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by wufengqiao on 2017/8/25.
 * function：
 * desc：
 */

public class ConfirmOrderProductAdapter extends BaseQuickAdapter<ShoppingProductEntity, BaseViewHolder> {

    private final DecimalFormat mFormat;

    public ConfirmOrderProductAdapter(int layoutResId, List<ShoppingProductEntity> data) {
        super(layoutResId, data);
        mFormat = new DecimalFormat("0.00");
    }

    @Override
    protected void convert(BaseViewHolder helper, ShoppingProductEntity item) {
        GlideManager.loadImg(item.productImg, helper.getView(R.id.iv_product));
        helper.setText(R.id.tv_product, item.productName);
        helper.setText(R.id.tv_intro, item.productCname);
        helper.setText(R.id.tv_price, mFormat.format(item.productPrice));
        helper.setText(R.id.tv_product_num, item.quantity+"");
    }
}
