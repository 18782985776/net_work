package com.jobnew.farm.module.home.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.HomeEntertainment.HomeEntertainmentEntity;
import com.jobnew.farm.utils.GlideManager;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wangwenlang on 2017/7/3.
 * title:
 * note:
 */

public class EntertainmentAdapter extends BaseQuickAdapter<HomeEntertainmentEntity,BaseViewHolder> {
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    public EntertainmentAdapter(int resourceId, ArrayList<HomeEntertainmentEntity> arrayList){
        super(resourceId,arrayList);
    }
    @Override
    protected void convert(BaseViewHolder helper, HomeEntertainmentEntity item) {
            helper.setText(R.id.entertainment_nametv,item.getName());//活动名
           // helper.setText(R.id.cost_tv,item.getPrice()+"/"+item.getUnitName());
        helper.setText(R.id.cost_tv,item.getPrice()+"/人");//产品说活动单位写死  人
         helper.setText(R.id.entertainmentDescribetv,item.getCname());//介绍
        helper.setText(R.id.Tv,"人数"+(item.getMaxStock()-item.getStock())+"/"+item.getMaxStock());//人数
        helper.setText(R.id.distanceTv,(int)item.getDistance()/1000+"km");
        Date startdate = new Date(item.getSaleDate());//开始日期
        Date enddate = new Date(item.getSoldOutDate());//结束日期
        Date creatdate = new Date(item.getCreateDate());
        helper.setText(R.id.dateTv,"活动时间："+simpleDateFormat.format(startdate)+"至"+simpleDateFormat.format(enddate));
        ImageView img = helper.getView(R.id.entertainment_item_img);
        GlideManager.loadImg(item.getPImg(),img);
    }
}
