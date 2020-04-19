package com.jobnew.farm.module.personal.adapter.MyFarm;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.myfarm.PlantingOrderEntity;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.utils.GlideManager;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.jar.JarEntry;

/**
 * Created by wufengqiao on 2017/6/14.
 * function： 我的农场--种植界面-适配器
 * desc：
 */

public class MyFarmPlantingAdapter extends BaseMultiItemQuickAdapter<PlantingOrderEntity, BaseViewHolder> {

    private final List<PlantingOrderEntity> mList;
    private final DecimalFormat mDecimalFormat;
    private Context mContext;
    private SimpleDateFormat mSimpleFormat;
    private boolean isPlanting;

    public MyFarmPlantingAdapter(List<PlantingOrderEntity> data, Context context, boolean isPlanting) {
        super(data);
        mList = data;
        mDecimalFormat = new DecimalFormat("0.00");
        mSimpleFormat = new SimpleDateFormat("yyyy-MM-dd");
        mContext = context;
        this.isPlanting = isPlanting;
        addItemType(0, R.layout.item_my_farm_breed);
        if (isPlanting) {
            addItemType(1, R.layout.item_my_farm_planting);
        } else {
            addItemType(1, R.layout.item_my_farm_breed);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, PlantingOrderEntity item) {
        switch (helper.getItemViewType()) {
            case 1:
                helper.addOnClickListener(R.id.tv_look_monitor);
                break;
            case 0:

                break;
        }
        GlideManager.loadRoundImg(item.farmImg, helper.getView(R.id.iv_farm));
        helper.setText(R.id.tv_farm, item.farmName);
        helper.setText(R.id.tv_major, item.majorProductName);
        helper.setText(R.id.tv_minor, item.minorProductName);
        GlideManager.loadImg(item.majorProductImg, helper.getView(R.id.iv_land));
        if (isPlanting) {
            helper.setVisible(R.id.tv_minor_split, true);
            helper.setVisible(R.id.tv_minor, true);
            helper.setText(R.id.tv_land_area_name, "土地面积");
            helper.setText(R.id.tv_land_area, item.majorProductQuantity + "㎡");
            helper.setText(R.id.tv_land_unit_price, "¥" + mDecimalFormat.format(item.majorProductPrice) + "/㎡/天");
            helper.setText(R.id.tv_plant_plan, "种植计划");
        } else {
            helper.setVisible(R.id.tv_minor_split, false);
            helper.setVisible(R.id.tv_minor, false);
            helper.setText(R.id.tv_land_area_name, "动物数量");
            helper.setText(R.id.tv_land_area, item.majorProductQuantity + "只");
            helper.setText(R.id.tv_land_unit_price, "¥" + mDecimalFormat.format(item.majorProductPrice) + "/只");
            helper.setText(R.id.tv_plant_plan, "养殖计划");
            helper.setText(R.id.tv_progress, "查看进度");
        }
        setOrderStatus(helper, item);
        helper.addOnClickListener(R.id.tv_plant_plan);
        helper.addOnClickListener(R.id.tv_farm);
        helper.addOnClickListener(R.id.iv_farm);
    }

    private void setOrderStatus(BaseViewHolder helper, PlantingOrderEntity item) {
        TextView textView = helper.getView(R.id.tv_status);
        switch (item.orderStatus) {
            case "pendingPayment":  //等待付款
                textView.setText("待付款");
                helper.setText(R.id.tv_progress, "去付款");
                textView.setTextColor(mContext.getResources().getColor(R.color.c_txt_9E));
                helper.setVisible(R.id.tv_delete, true);
                helper.addOnClickListener(R.id.tv_delete);
                helper.setVisible(R.id.tv_contact, true);
                helper.addOnClickListener(R.id.tv_contact);
                helper.addOnClickListener(R.id.tv_progress);
                helper.setBackgroundRes(R.id.tv_progress, R.drawable.bg_select_yes2);
                break;
            case "pendingReview":   //等待审核
                textView.setText("审核中");
                textView.setTextColor(mContext.getResources().getColor(R.color.c_txt_9E));
                helper.setVisible(R.id.tv_delete, false);
                helper.setVisible(R.id.tv_contact, true);
                helper.addOnClickListener(R.id.tv_contact);
                helper.setBackgroundRes(R.id.tv_progress, R.drawable.bg_select_no3);
                break;
            case "growing":         //种植,养殖中
                helper.setBackgroundRes(R.id.tv_progress, R.drawable.bg_select_yes2);
                helper.setText(R.id.tv_date, mSimpleFormat.format(new Date(item.startDate)) + " 至 " + mSimpleFormat.format(new Date(item.endDate)));
                textView.setText(isPlanting ? "种植中" : "养殖中");
                textView.setTextColor(mContext.getResources().getColor(R.color.c_txt_9E));
                helper.setVisible(R.id.tv_delete, false);
                helper.setVisible(R.id.tv_contact, true);
                if (isPlanting) {
                    helper.addOnClickListener(R.id.tv_look_monitor);
                }
                helper.addOnClickListener(R.id.tv_contact);
                helper.addOnClickListener(R.id.tv_progress);
                break;
            case "pendingShipment": //等待发货
            case "shipped":         //已发货
            case "received":        //已收货
                helper.setBackgroundRes(R.id.tv_progress, R.drawable.bg_select_yes2);
                helper.setText(R.id.tv_date, mSimpleFormat.format(new Date(item.startDate)) + " 至 " + mSimpleFormat.format(new Date(item.endDate)));
                textView.setTextColor(mContext.getResources().getColor(R.color.c_txt_68));
                textView.setText("已收获");
                helper.setVisible(R.id.tv_delete, false);
                helper.setVisible(R.id.tv_contact, true);
                helper.addOnClickListener(R.id.tv_contact);
                helper.addOnClickListener(R.id.tv_progress);
                break;
            case "completed":       //已完成
                textView.setText("已完结");
                helper.setBackgroundRes(R.id.tv_progress, R.drawable.bg_select_yes2);
                helper.setText(R.id.tv_date, mSimpleFormat.format(new Date(item.startDate)) + " 至 " + mSimpleFormat.format(new Date(item.endDate)));
                textView.setTextColor(mContext.getResources().getColor(R.color.c_txt_68));
                helper.setVisible(R.id.tv_delete, true);
                helper.addOnClickListener(R.id.tv_delete);
                helper.addOnClickListener(R.id.tv_progress);
                helper.setVisible(R.id.tv_contact, false);
                break;
            case "failed":          //已失败
                textView.setText("已失败");
                setFailedView(helper, textView);
                break;
            case "canceled":          //已取消
                textView.setText("已取消");
                setFailedView(helper, textView);
                break;
            case "denied":          //已拒绝
                textView.setText("已拒绝");
                setFailedView(helper, textView);
                break;
            case "consum":          //待消费
                break;
        }
    }

    private void setFailedView(BaseViewHolder helper, TextView textView) {
        textView.setTextColor(mContext.getResources().getColor(R.color.c_txt_68));
        helper.setVisible(R.id.tv_delete, true);
        helper.addOnClickListener(R.id.tv_delete);
        helper.setVisible(R.id.tv_contact, false);
        helper.setBackgroundRes(R.id.tv_progress, R.drawable.bg_select_no3);
    }
}
