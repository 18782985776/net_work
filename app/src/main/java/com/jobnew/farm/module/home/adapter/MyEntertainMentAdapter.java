package com.jobnew.farm.module.home.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.FarmHappy.FarmHappyOrderSpendEntity;
import com.jobnew.farm.entity.HomeEntertainment.HomeEntertainmentEntity;
import com.jobnew.farm.utils.GlideManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wangwenlang on 2017/7/3.
 * title:
 * note:
 */

public class MyEntertainMentAdapter  extends BaseQuickAdapter<HomeEntertainmentEntity,BaseViewHolder>{
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    public MyEntertainMentAdapter(int layoutResId, List<HomeEntertainmentEntity> data) {
        super(layoutResId, data);
    }

  //  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void convert(BaseViewHolder helper, HomeEntertainmentEntity item) {
       // FarmHappyOrderSpendEntity.OrderItemsBean orderItemsBean = item.getOrderItems().get(0);

        ImageView iVactivity = helper.getView(R.id.entertainment_item_img);
        GlideManager.loadImg(item.getPImg(),iVactivity);//活动图片
       helper.setText(R.id.entertainment_nametv,item.getName());//活动名称
        TextView statusTv = helper.getView(R.id.tv_main_business);
        Context context = statusTv.getContext();
        Date startdate = new Date(item.getSaleDate());//开始日期
        Date enddate = new Date(item.getSoldOutDate());//结束日期
        if(item.getSoldOutDate()>Calendar.getInstance().getTimeInMillis()){//比较时间，判断活动是否结束
            statusTv.setText("已报名");
            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
                statusTv.setBackground( context.getDrawable(R.drawable.bg_join_activity));
            }else {
                statusTv.setBackgroundDrawable( context.getResources().getDrawable(R.drawable.bg_join_activity));
            }

        }else {
            statusTv.setText("结束");
            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
                statusTv.setBackground(context.getDrawable(R.drawable.bg_activity_over));
            }else {
                statusTv.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_activity_over));
            }

        }
        helper.setText(R.id.entertainmentDescribetv,item.getCname());//产品描述
       helper.setText(R.id.distanceTv,(int)(item.getDistance()/1000)+"km");
        helper.setText(R.id.cost_tv,item.getPrice()+"/人");
       // helper.setText(R.id.Tv,orderItemsBean.get)
        helper.setText(R.id.dateTv,"日期"+simpleDateFormat.format(startdate)+"至"+simpleDateFormat.format(enddate));//日期
        helper.setText(R.id.Tv,"人数"+(item.getMaxStock()-item.getStock())+"/"+item.getMaxStock());//人数
       // orderItemsBean.get
    }
}

