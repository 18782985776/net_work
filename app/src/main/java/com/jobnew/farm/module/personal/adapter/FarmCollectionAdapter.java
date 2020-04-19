package com.jobnew.farm.module.personal.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.base.FarmEntity;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.utils.StarLinearLayout;

import java.util.List;

/**
 * Created by wangwenlang on 2017/6/15.
 * title:
 * note:
 */

public class FarmCollectionAdapter  extends BaseQuickAdapter<FarmEntity,BaseViewHolder>{
    public FarmCollectionAdapter(int layoutResId, List<FarmEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FarmEntity item) {
        GlideManager.loadImg(item.getImg(),(ImageView)helper.getView(R.id.farmlistview_item_img));
        helper.setText(R.id.farm_nametv,item.getName());//设置农场名字
        helper.setText(R.id.farmAdreestv,item.getProvince()+item.getCity()+item.getArea()+item.getAddress());
        helper.setText(R.id.storeTv,"土地库存 "+item.getLandStock());
        helper.setText(R.id.discussTv,"评论 "+item.getCommentCount());
        helper.setText(R.id.distanceTv,"距离 "+(int)(item.getDistance()/1000)+"KM");//后台传过来
        StarLinearLayout starLayout=(StarLinearLayout)helper.getView(R.id.star_score);
        starLayout.setScore((int) (item.getGrade()));
        GlideManager.loadImg(item.getIcon(),(ImageView)helper.getView(R.id.img_main_business));
    }
}
