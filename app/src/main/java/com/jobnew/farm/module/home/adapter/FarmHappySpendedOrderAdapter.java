package com.jobnew.farm.module.home.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.FarmHappy.FarmHappyOrderSpendEntity;
import com.jobnew.farm.module.home.activity.CateringDetailsActivity;
import com.jobnew.farm.module.home.activity.FarmHappyDetatilsActivity;
import com.jobnew.farm.module.home.activity.FarmHappyOrderCommentActivity;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.widget.AppManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wangwenlang on 2017/8/8.
 * title:
 * note:
 */

public class FarmHappySpendedOrderAdapter extends BaseQuickAdapter<FarmHappyOrderSpendEntity,BaseViewHolder> {
    private int tag;//2表示未消费；1表示已消费
    private List<FarmHappyOrderSpendEntity> data;
    SimpleDateFormat simpleF=new SimpleDateFormat("yyyy-MM-dd");

    public FarmHappySpendedOrderAdapter(int layoutResId, List<FarmHappyOrderSpendEntity> data, int tag) {
        super(layoutResId, data);
        this.tag=tag;
        this.data=data;
    }

    @Override
    public void remove(int position) {
        super.remove(position);
    }

    @Override
    protected void convert(BaseViewHolder helper, FarmHappyOrderSpendEntity item) {
        try {
            switchTag(helper,item);
            getParentPosition(item);
            ImageView farmimg = helper.getView(R.id.img_farm);
            GlideManager.loadImg(item.getFarm().getImg(),farmimg);//设置农场头像
            helper.setText(R.id.txt_farm_name,item.getFarm().getName());//农场名字
            ImageView talkImg=helper.getView(R.id.talk_img);//对话图标
            ImageView itemImg = helper.getView(R.id.item_picture_img);//item图标
            helper.setText(R.id.name_tv,item.getOrderItems().get(0).getName());
            GlideManager.loadImg(item.getOrderItems().get(0).getThumbnail(),itemImg);//产品预览图
            itemImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ;
                    Intent intent=new Intent();
                    intent.putExtra("farmId",item.getFarm().getId());
                    intent.putExtra("farmName",item.getFarm().getName());
                    intent.putExtra("key",item.getType());
                    AppManager.jump(CateringDetailsActivity.class,intent);
                  //  Toast.makeText(v.getContext(),"缺少key",Toast.LENGTH_SHORT).show();
                }
            });
            helper.getView(R.id.txt_farm_name).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    intent.putExtra("farmName",item.getFarm().getName());
                    intent.putExtra("farmId",item.getOrderItems().get(0).getProductId());
                    AppManager.jump(FarmHappyDetatilsActivity.class,intent);
                }
            });
            helper.setText(R.id.txt_price,"￥"+item.getOrderItems().get(0).getPrice());
            helper.setText(R.id.unitName,"/人");//后台还没给单位
            helper.setText(R.id.details_tv,item.getOrderItems().get(0).getCname());

            helper.setText(R.id.tv_num,item.getOrderItems().get(0).getQuantity()+"");
            helper.setText(R.id.order_item_time,"消费日期："+simpleF.format(new Date(item.getValidDate())));
            //合计 : ￥88(含运费 : ￥10)
            helper.setText(R.id.order_item_total,"合计 ："+"￥"+item.getAmount());
        }catch (NullPointerException e){
            Log.d("", "convert: ");
        }


    }

    private void switchTag(BaseViewHolder helper,FarmHappyOrderSpendEntity item) {

        switch (tag){
            case 1:
                helper.setText(R.id.txt_type,"已消费");
                helper.getView(R.id.ll_1).setVisibility(View.VISIBLE);
                helper.addOnClickListener(R.id.txt_delete_orde);
                helper.addOnClickListener(R.id.tv_comment);
                break;
            case 2:
                helper.setText(R.id.txt_type,"未消费");
                helper.getView(R.id.ll_2).setVisibility(View.VISIBLE);
              //  helper.setText(R.id.tv_comment,)
                helper.addOnClickListener(R.id.img_call);
                break;
        }
    }
}
