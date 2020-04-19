package com.jobnew.farm.module.home.adapter;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.matchFeature.MatchEntity;
import com.jobnew.farm.utils.GlideManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wangwenlang on 2017/7/25.
 * title:
 * note:
 */

public class TakeMatchAdapter extends BaseQuickAdapter<MatchEntity,BaseViewHolder> {
    private SimpleDateFormat sf;
    public TakeMatchAdapter(int layoutResId, List<MatchEntity> data) {
        super(layoutResId, data);
        sf=new SimpleDateFormat("yyyy-MM-dd");
    }
    private int lodaTag;
    public void setLodaTag(int lodaTag){
        this.lodaTag=lodaTag;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void convert(BaseViewHolder helper, MatchEntity item) {
        ImageView tagImg=helper.getView(R.id.tag_img);
        LinearLayout tagLL = helper.getView(R.id.tag_ll);
        Resources resources = tagLL.getContext().getResources();
        TextView tagText = helper.getView(R.id.tag_text);
        switch (lodaTag){
            case 1 :
                tagLL.setBackground(resources.getDrawable(R.drawable.bg_take_match));
             //   GlideManager.loadImg(R.mipmap.farm_enrollment,tagImg);
                tagText.setText("报名中");
                tagText.setTextColor(Color.parseColor("#90b659"));
                tagImg.setVisibility(View.GONE);
                break;
            case 2 :
                tagLL.setBackground(resources.getDrawable(R.drawable.bg_matching));
                tagText.setText("火热进行中");
                tagText.setTextColor(Color.parseColor("#EE0B2B"));
              //  GlideManager.loadImg(R.mipmap.farm_fiery2,tagImg);
                break;
            case 3 :
                tagLL.setBackground(resources.getDrawable(R.drawable.bg_match_over));
             //  GlideManager.loadImg(R.mipmap.farm_end,tagImg);
                tagText.setText("已结束");
                tagText.setTextColor(Color.parseColor("#888888"));
                tagImg.setVisibility(View.GONE);
                break;
        }
        try {
            helper.setText(R.id.farmhouse_nametv,item.getName());//比赛名称
            helper.setText(R.id.textView2,"举办方："+item.getFarm().getName());//举办方
            helper.setText(R.id.people_num_tv,"人数"+item.getJoinNumber()+"/"+item.getNumber());
            helper.setText(R.id.date_tv,"活动日期"+sf.format(new Date(item.getStartDate()))+"至"+sf.format(new Date(item.getEndDate())));
            helper.setText(R.id.farm_houseAdreestv,item.getArea().getName()+item.getAddress());//地址
            helper.setText(R.id.competition_distance_tv,"距离"+(int)item.getDistance()/1000+"KM");
            ImageView matchImg = helper.getView(R.id.farmhouse_img);
            GlideManager.loadImg(item.getImg(),matchImg);//
        }catch (NullPointerException e){
            Log.d("空指针", "convert: 比赛适配器"+e.toString());
        }

    }
}
