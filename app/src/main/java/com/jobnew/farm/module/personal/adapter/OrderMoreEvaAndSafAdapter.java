package com.jobnew.farm.module.personal.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.bazaar.OrderBazaarBean;
import com.jobnew.farm.utils.GlideManager;

import java.util.List;

/**
 * Created by wc on 2017/8/18.
 * Function：
 * desc：
 */

public class OrderMoreEvaAndSafAdapter extends BaseQuickAdapter<OrderBazaarBean.OrderItemsEntity,BaseViewHolder>{
    private Context mContext;
    public OrderMoreEvaAndSafAdapter(int layoutResId, List<OrderBazaarBean.OrderItemsEntity> data, Context mContext) {
        super(layoutResId, data);
        this.mContext=mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBazaarBean.OrderItemsEntity item) {
        GlideManager.loadImg(item.getThumbnail(),helper.getView(R.id.img_title_img));
        helper.setText(R.id.txt_title,item.getName());
        TextView tvSales = helper.getView(R.id.txt_after_sales);
        TextView tvEva = helper.getView(R.id.txt_evaluation);
        if (item.getCommentState().equals("pendingEvaluation")){
            helper.setTextColor(R.id.txt_evaluation,mContext.getResources().getColor(R.color.main_color));
            helper.setBackgroundRes(R.id.txt_evaluation,R.drawable.bg_color_main);
            helper.setText(R.id.txt_evaluation,"评价");
        }else{
            helper.setTextColor(R.id.txt_evaluation,mContext.getResources().getColor(R.color.c_txt_88));
            helper.setBackgroundRes(R.id.txt_evaluation,R.drawable.bg_color_bg);
            helper.setText(R.id.txt_evaluation,"已评价");
        }
        if (item.isCanRefund()){
            helper.setTextColor(R.id.txt_after_sales,mContext.getResources().getColor(R.color.main_color));
            helper.setBackgroundRes(R.id.txt_after_sales,R.drawable.bg_color_main);
            helper.setText(R.id.txt_after_sales,"售后退换");
        }else{
            helper.setTextColor(R.id.txt_after_sales,mContext.getResources().getColor(R.color.c_txt_88));
            helper.setBackgroundRes(R.id.txt_after_sales,R.drawable.bg_color_bg);
            helper.setText(R.id.txt_after_sales,"售后中");
        }
        helper.addOnClickListener(R.id.txt_evaluation);
        helper.addOnClickListener(R.id.txt_after_sales);
    }
}
