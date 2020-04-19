package com.jobnew.farm.module.personal.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.ShoppingCar.ShoppingProductEntity;
import com.jobnew.farm.module.personal.ViewHolder.ComfirmOrderViewHolder;
import com.jobnew.farm.utils.FreightHelper;
import com.jobnew.farm.utils.GlideManager;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by wufengqiao on 2017/8/24.
 * function：
 * desc：
 */

public class ConfirmOrderAdapter extends BaseQuickAdapter<ShoppingProductEntity, ComfirmOrderViewHolder> {


    private final DecimalFormat format;

    public ConfirmOrderAdapter(int layoutResId, List<ShoppingProductEntity> data) {
        super(layoutResId, data);
        format = new DecimalFormat("0.00");
    }

    @Override
    protected void convert(ComfirmOrderViewHolder helper, ShoppingProductEntity item) {
        GlideManager.loadRoundImg(item.farmLogo, helper.getView(R.id.iv_farm));
        helper.setText(R.id.tv_farm_name, item.farmName);
        helper.mAdapter.setNewData(item.list);
        double freightPrice = 0;
        for (int i = 0; i < item.list.size(); i++) {
            freightPrice += FreightHelper.shippingFreight(item.list.get(i).shippingMethodEntity, item.list.get(i).quantity);
        }
        helper.setText(R.id.tv_post_cost, "（含运费：¥" + format.format(freightPrice) + "）");
        helper.setText(R.id.tv_total_price, format.format(item.productPrice + freightPrice));
    }

}
