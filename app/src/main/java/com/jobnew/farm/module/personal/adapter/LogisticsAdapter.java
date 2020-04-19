package com.jobnew.farm.module.personal.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.LogisticsBean;

import java.util.List;

/**
 * Created by wc on 2017/6/28.
 * Function：
 * desc：
 */

public class LogisticsAdapter extends BaseQuickAdapter<LogisticsBean.ExpressItemsEntity,BaseViewHolder> {
    private Context mContext;
    public LogisticsAdapter(int layoutResId, List<LogisticsBean.ExpressItemsEntity> data,Context mContext) {
        super(layoutResId, data);
        this.mContext=mContext;
    }
    @Override
    protected void convert(BaseViewHolder helper, LogisticsBean.ExpressItemsEntity item) {
        if (helper.getAdapterPosition()==0) {
            helper.setTextColor(R.id.txt_content,0XFF90b659);
            helper.setTextColor(R.id.txt_time,0XFF90b659);
            helper.setImageResource(R.id.img,R.mipmap.public_icon_dot);
        }else{
            helper.setTextColor(R.id.txt_content,0XFF686868);
            helper.setTextColor(R.id.txt_time,0XFF686868);
            helper.setImageResource(R.id.img,R.mipmap.public_icon_dot2);
        }
        helper.setText(R.id.txt_content,item.getContext());
        helper.setText(R.id.txt_time,item.getTime());
    }
}
