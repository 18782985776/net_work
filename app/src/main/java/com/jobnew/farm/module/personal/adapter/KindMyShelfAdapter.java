package com.jobnew.farm.module.personal.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.data.KindMyBean;
import com.jobnew.farm.utils.Arith;

import java.util.List;

/**
 * Created by wancheng on 2017/6/15.
 * title:
 * note:
 */

public class KindMyShelfAdapter extends BaseQuickAdapter<KindMyBean,BaseViewHolder> {
    private Context mContext;
    public KindMyShelfAdapter(int layoutResId, List<KindMyBean> data,Context mContext) {
        super(layoutResId, data);
        this.mContext=mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, KindMyBean item) {
            helper.setText(R.id.txt_title_farm,item.getFarm().getName());
        helper.setText(R.id.txt_title,item.getName()+"");
        helper.setText(R.id.txt_price,item.getPrice()+"");
        helper.setText(R.id.txt_price_unit,"/"+item.getUnitName());
        helper.setText(R.id.txt_location,"距离 "+Arith.div(item.getFarm().getDistance(),1000,2)+"KM");
    }
}
