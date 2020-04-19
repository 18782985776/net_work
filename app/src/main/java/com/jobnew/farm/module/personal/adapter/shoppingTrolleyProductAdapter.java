package com.jobnew.farm.module.personal.adapter;


import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.ShoppingCar.ShoppingProductEntity;
import com.jobnew.farm.module.farm.activity.farmActivity.ProductDetailsActivity;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.QuantityHelper;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by wufengqiao on 2017/8/18.
 * function：
 * desc：
 */

class shoppingTrolleyProductAdapter extends BaseQuickAdapter<ShoppingProductEntity, shoppingTrolleyProductAdapter.ViewHolder> {

    private final DecimalFormat mFormat;
    public boolean isSelect = false;
    private boolean isEditStatusRefresh;

    public shoppingTrolleyProductAdapter(int layoutResId, List<ShoppingProductEntity> data) {
        super(layoutResId, data);
        mFormat = new DecimalFormat("0.00");
    }

    @Override
    protected void convert(ViewHolder helper, ShoppingProductEntity item) {
        helper.setVisible(R.id.rl_content, !item.isEditStatus);
        helper.setVisible(R.id.rl_edit, item.isEditStatus);
        helper.setText(R.id.tv_name, item.productName);
        helper.setText(R.id.tv_intro, item.productCname);
        helper.setText(R.id.tv_unit_price, mFormat.format(item.productPrice));
        helper.setText(R.id.tv_number, item.quantity + "");
        GlideManager.loadImg(item.productImg, helper.getView(R.id.iv_product));
        helper.addOnClickListener(R.id.iv_select);
        helper.getView(R.id.iv_select).setSelected(item.isSelect);
        helper.addOnClickListener(R.id.iv_delete);
        helper.numberHelper.setCount(item.quantity);
        helper.numberHelper.setOnCountChangeListener(new QuantityHelper.OnCountChangeListener() {
            @Override
            public void onCountChange(int count) {
                item.tempQuantity = count;
            }
        });
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.jump(ProductDetailsActivity.class, "id", item.productId);
            }
        });
    }

    public class ViewHolder extends BaseViewHolder {


        public final QuantityHelper numberHelper;
        public final View numberView;

        public ViewHolder(View view) {
            super(view);
            numberView = itemView.findViewById(R.id.ll_number);
            numberHelper = new QuantityHelper(numberView, 1, 9999);
        }
    }

//    /**
//     * 通知编辑状态改变
//     */
//    public void notifyEditStatusChange() {
//        isEditStatusRefresh = true;
//        notifyDataSetChanged();
//        isEditStatusRefresh = false;
//    }
//
//    public void notifySelectChange() {
//        isSelect = true;
//        notifyDataSetChanged();
//        isSelect = false;
//    }
}
