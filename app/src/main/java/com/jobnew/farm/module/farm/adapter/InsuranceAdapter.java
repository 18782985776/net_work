package com.jobnew.farm.module.farm.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.InsuranceEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wufengqiao on 2017/6/1.
 * function：
 * desc：
 */

public class InsuranceAdapter extends BaseQuickAdapter<InsuranceEntity, InsuranceAdapter.ViewHolder> {

    public InsuranceAdapter(int layoutResId, List<InsuranceEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, InsuranceEntity item) {
        helper.tvInsuranceName.setText("基础保障");
        helper.tvInsuranceUnitPrive.setText("（¥1.00/㎡）");
        helper.tvInsurancePrice.setText("¥20.00");
        helper.tvInsuranceDescribe.setText("1.这里是用户租赁土地或者购买动物养殖，获得的农场 的基础保障条例，比如动物被农场弄丢了啊，死了啊等 等获得的赔偿等");
        helper.tvCompensationRatio.setText("50%");
        helper.itemView.setSelected(true);
    }

    @Override
    protected ViewHolder createBaseViewHolder(View view) {
        return new ViewHolder(view);
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_insurance_select)
        ImageView ivInsuranceSelect;
        @BindView(R.id.tv_insurance_name)
        TextView tvInsuranceName;
        @BindView(R.id.tv_insurance_unit_prive)
        TextView tvInsuranceUnitPrive;
        @BindView(R.id.tv_insurance_price)
        TextView tvInsurancePrice;
        @BindView(R.id.tv_insurance_describe)
        TextView tvInsuranceDescribe;
        @BindView(R.id.tv_compensation_ratio)
        TextView tvCompensationRatio;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
