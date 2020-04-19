package com.jobnew.farm.module.personal.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.myfarm.CollectionLandEntity;
import com.jobnew.farm.utils.GlideManager;

import java.util.ArrayList;

/**
 * Created by wangwenlang on 2017/6/15.
 * title:
 * note:
 */

public class CollectionPersonalLandAdapter extends BaseQuickAdapter<CollectionLandEntity,BaseViewHolder> {

    //R.layout.item_collection_personal_land
    @Override
    protected void convert(BaseViewHolder helper, CollectionLandEntity item) {
        helper.setText(R.id.land_nametv,item.getName());
        helper.setText(R.id.price_tv,item.getPrice()+"");
        helper.setText(R.id.unitName,"/"+item.getUnitName());
       helper.setText(R.id.landAreatv,"总面积"+item.getMaxStock());
        GlideManager.loadImg(item.getPImg(),(ImageView) helper.getView(R.id.land_img));
        helper.setText(R.id.land_storetv,"库存"+item.getStock());
        ImageView typeImg = helper.getView(R.id.type_tv);
        if(item.getType().equals("普通出租")){
            typeImg.setImageResource(R.mipmap.farm_icon_grade1);
        }else {
            typeImg.setImageResource(R.mipmap.farm_icon_grade2);
        }
    }
    public CollectionPersonalLandAdapter(int resourceId, ArrayList arrayList){
        super(resourceId,arrayList);
    }
}
