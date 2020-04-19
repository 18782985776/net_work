package com.jobnew.farm.module.personal.adapter;

import android.app.FragmentManager;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.base.FarmEntity;
import com.jobnew.farm.module.home.activity.WelcomeActivity;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.utils.StarLinearLayout;

import java.util.ArrayList;

/**
 * Created by wangwenlang on 2017/6/15.
 * title:
 * note:
 */

public class CollectionHappyFarmAdapter extends BaseQuickAdapter<FarmEntity,BaseViewHolder> {
    private ArrayList<FarmEntity> arrayList;
    public CollectionHappyFarmAdapter(int resourceId,ArrayList<FarmEntity> arrayList){
        super(resourceId,arrayList);
        this.arrayList=arrayList;
    }
    @Override
    protected void convert(BaseViewHolder helper, FarmEntity item) {
        GlideManager.loadImg(item.getImg(),(ImageView)helper.getView(R.id.farmhouse_img));//farmhouse_img//大图片
        helper.setText(R.id.introduce_tv,item.getIntroduce()+"");//介绍
        helper.setText(R.id.farmhouse_nametv,item.getName());//设置农场名字
        helper.setText(R.id.farmAdreestv,item.getProvince()+item.getCity()+item.getArea()+item.getAddress());//地址
        StarLinearLayout starLayout=(StarLinearLayout)helper.getView(R.id.star_score);
        starLayout.setScore((int) (item.getGrade()));//星级
        helper.setText(R.id.distance_tv,"距离"+(int)(item.getDistance()/1000)+"km");//距离
    }
}
