package com.jobnew.farm.module.personal.adapter.MyFarm;

import android.content.Context;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.myfarm.BreedingOrderEntity;
import com.jobnew.farm.entity.myfarm.PlantingOrderEntity;
import com.jobnew.farm.entity.myfarm.ReapOrderEntity;
import com.jobnew.farm.utils.GlideManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wufengqiao on 2017/6/14.
 * function：我的农场--已完成界面-适配器
 * desc：
 */

public class MyFarmReapAdapter extends BaseQuickAdapter<ReapOrderEntity, BaseViewHolder> {

    private Context mContext;
    private SimpleDateFormat mSimpleFormat;

    public MyFarmReapAdapter(int layoutResId, List<ReapOrderEntity> data, Context context) {
        super(layoutResId, data);
        mContext = context;
        mSimpleFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    protected void convert(BaseViewHolder helper, ReapOrderEntity item) {
        if (item.orderType.equals("Plant")) {
            GlideManager.loadRoundImg(item.majorProductImg, helper.getView(R.id.iv_product));
            helper.setText(R.id.tv_name, item.minorProductName);
        } else {
            helper.setText(R.id.tv_name, item.majorProductName);
            GlideManager.loadRoundImg(item.majorProductImg, helper.getView(R.id.iv_product));
        }
        String status = "";
        switch (item.orderStatus) {
            case "growing":         //种植,养殖中
                status = "种植中/养殖中";
                helper.setTextColor(R.id.tv_status, mContext.getResources().getColor(R.color.c_txt_9E));
                break;
            case "pendingShipment": //等待发货
                status = "待发货";
                helper.setTextColor(R.id.tv_status, mContext.getResources().getColor(R.color.c_txt_9E));
                break;
            case "shipped":         //已发货
                status = "配送中";
                helper.setTextColor(R.id.tv_status, mContext.getResources().getColor(R.color.c_txt_9E));
                break;
            case "received":        //已收货
            case "completed":       //已完成
                status = "已配送";
                helper.setTextColor(R.id.tv_status, mContext.getResources().getColor(R.color.c_txt_88));
                break;
            default:
                status = "未种植/未养殖";
                helper.setTextColor(R.id.tv_status, mContext.getResources().getColor(R.color.c_txt_88));
                break;
        }
        helper.setText(R.id.tv_status, status);
        helper.setText(R.id.tv_date, mSimpleFormat.format(new Date(item.endDate)));
        helper.addOnClickListener(R.id.iv_delete);
        helper.addOnClickListener(R.id.tv_comment);
        helper.addOnClickListener(R.id.tv_progress);
    }

}
