package com.jobnew.farm.module.farm.adapter;

import com.allen.library.SuperTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.plan.SchemeEntity;

import java.util.List;

/**
 * Created by wufengqiao on 2017/6/22.
 * function：
 * desc：
 */

public class SchemeNestAdapter extends BaseQuickAdapter<SchemeEntity,BaseViewHolder> {
    public SchemeNestAdapter(int layoutResId, List<SchemeEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SchemeEntity item) {
        if (helper.itemView instanceof SuperTextView){
            ((SuperTextView) helper.itemView).setLeftString(item.farmArtName);
        }
        helper.setText(R.id.tv_unit_price,"¥"+item.price);
        helper.setText(R.id.tv_unit, "/" + item.unitName);
        helper.itemView.setSelected(item.checked);
    }
}
