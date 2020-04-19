package com.jobnew.farm.module.farm.adapter.farmAdapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.base.FarmEntity;
import com.jobnew.farm.module.farm.activity.farmActivity.FarmDetailsActivity;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.utils.StarLinearLayout;

import java.util.List;

/**
 * Created by wangwenlang on 2017/6/5.
 * title:
 * note:
 */

public class SecondFarmAdapter extends BaseQuickAdapter<FarmEntity,BaseViewHolder>{

    public SecondFarmAdapter(int layoutResId, List<FarmEntity> data) {
        super(layoutResId, data);
    }
//http://222.88.94.204:19100/images/image/image/a31c3064c368429a93697a30ecff48c4.jpg
    @Override
    protected void convert(BaseViewHolder helper, FarmEntity item) {
      //  ImageView view = (ImageView) helper.getView(R.id.farmlistview_item_img);
        GlideManager.loadImg(item.getImg(),(ImageView)helper.getView(R.id.farmlistview_item_img));
        helper.setText(R.id.farm_nametv,item.getName());//设置农场名字
        helper.setText(R.id.farmAdreestv,item.getProvince()+item.getCity()+item.getArea()+item.getAddress());
        helper.setText(R.id.storeTv,"土地库存 "+item.getLandStock());
        helper.setText(R.id.discussTv,"评论 "+item.getCommentCount());
        helper.setText(R.id.distanceTv,"距离 "+(int)(item.getDistance()/1000)+"KM");//后台传过来
        /** star:isEdit="false"
         star:score="4" />**/
        StarLinearLayout starLayout=(StarLinearLayout)helper.getView(R.id.star_score);
        starLayout.setScore((int) (item.getGrade()));
        GlideManager.loadImg(item.getIcon(),(ImageView)helper.getView(R.id.img_main_business));
       // setMainBusinessImg( helper,item);
    }
///**根据mainBusIness返回值设置图标**/
//    private void setMainBusinessImg(BaseViewHolder helper, FarmEntity item) {
//        switch (item.getMainBusiness()){
//            case "1"://种植
//                GlideManager.loadImg(R.mipmap.public_tab_farm2,(ImageView)helper.getView(R.id.img_main_business));
//                break;
//            case "2"://养殖
//                GlideManager.loadImg(R.mipmap.farm_icon_breed,(ImageView)helper.getView(R.id.img_main_business));
//                break;
//            case "3"://农产品
//                GlideManager.loadImg(R.mipmap.farm_icon_ap,(ImageView)helper.getView(R.id.img_main_business));
//                break;
//            case "4"://活动
//                GlideManager.loadImg(R.mipmap.farm_icon_orchard,(ImageView)helper.getView(R.id.img_main_business));
//                break;
//            case "5"://餐饮
//                GlideManager.loadImg(R.mipmap.farm_icon_restaurant,(ImageView)helper.getView(R.id.img_main_business));
//                break;
//            case "6"://住宿
//                GlideManager.loadImg(R.mipmap.farm_icon_hotel,(ImageView)helper.getView(R.id.img_main_business));
//                break;
//        }
//    }
}
