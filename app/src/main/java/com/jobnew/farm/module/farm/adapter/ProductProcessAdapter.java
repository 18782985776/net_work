package com.jobnew.farm.module.farm.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.plan.ProductProcessEntity;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wufengqiao on 2017/6/1.
 * function：产品加工
 * desc：
 */

public class ProductProcessAdapter extends BaseQuickAdapter<ProductProcessEntity, ProductProcessAdapter.ViewHolder> {

    private final DecimalFormat mDecimalFormat;
    private int number;

    public ProductProcessAdapter(int layoutResId, List<ProductProcessEntity> data) {
        super(layoutResId, data);
        mDecimalFormat = new DecimalFormat("0.00");
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    protected void convert(ViewHolder helper, ProductProcessEntity item) {
        helper.tvProductProcessName.setText(item.farmArtName);
        helper.tvPpUnitPrive.setText("（¥" + mDecimalFormat.format(item.price) + "/" + item.unitName + "）");
        helper.tvPpPrice.setText("¥" + mDecimalFormat.format(item.price * number));
        helper.tvPpDescribe.setText(TextUtils.isEmpty(item.intro) ? "没有更多信息" : item.intro);
        helper.itemView.setSelected(item.isSelect);
    }

    @Override
    protected ViewHolder createBaseViewHolder(ViewGroup parent, int layoutResId) {
        View view = LayoutInflater.from(mContext).inflate(layoutResId, parent, false);
        return new ViewHolder(view);
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_product_process_select)
        ImageView ivProductProcessSelect;
        @BindView(R.id.tv_product_process_name)
        TextView tvProductProcessName;
        @BindView(R.id.tv_pp_unit_prive)
        TextView tvPpUnitPrive;
        @BindView(R.id.tv_pp_price)
        TextView tvPpPrice;
        @BindView(R.id.tv_pp_describe)
        TextView tvPpDescribe;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
