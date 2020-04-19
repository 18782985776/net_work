package com.jobnew.farm.module.personal.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.myfarm.CollectionProductEntity;
import com.jobnew.farm.utils.GlideManager;

import java.util.List;

/**
 * Created by wangwenlang on 2017/7/11.
 * title:
 * note:
 */

public class CollectionProductionAdapter extends BaseQuickAdapter<CollectionProductEntity,BaseViewHolder> {

    //R.layout.item_collection_production
    public CollectionProductionAdapter(int layoutResId, List<CollectionProductEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectionProductEntity item) {
        helper.setText(R.id.product_nametv,item.getName());
        helper.setText(R.id.price_tv,item.getPrice()+"");
        helper.setText(R.id.describe_tv,item.getIntro()+"");
        helper.setText(R.id.unitName,"/"+item.getUnitName());
        helper.setText(R.id.stole_tv,"商品库存"+item.getMaxStock());
        helper.setText(R.id.saler_Tv,"月售"+item.getSaleCount());
        helper.setText(R.id.discussTv,"评论"+item.getCommentCount());
        GlideManager.loadImg(item.getPImg(),(ImageView) helper.getView(R.id.collection_farmhouse_img));
    }
}
