package com.jobnew.farm.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.bazaar.BazaarSmall;
import com.jobnew.farm.utils.GlideManager;

import java.util.List;

/**
 * Created by wc on 2017/6/29.
 * Function：
 * desc：
 */

public class BazaarThreeAdapter extends BaseQuickAdapter<BazaarSmall,BaseViewHolder> {
    private Context mContext;
    public BazaarThreeAdapter(int layoutResId, List<BazaarSmall> data,Context mContext) {
        super(layoutResId, data);
        this.mContext=mContext;
    }
    @Override
    protected void convert(BaseViewHolder helper, BazaarSmall item) {
        ImageView imgIsBook=helper.getView(R.id.img_isbook);
        if (item.getSaleType().equals("NORMAL")){
            imgIsBook.setVisibility(View.GONE);
        }else{
            imgIsBook.setVisibility(View.VISIBLE);
        }
        helper.setText(R.id.txt_title,item.getName());
        helper.setText(R.id.txt_intro,item.getIntro());
        GlideManager.loadImg(item.getPImg(),helper.getView(R.id.img_title));
        helper.setText(R.id.txt_price,"¥"+item.getPrice());
        helper.setText(R.id.txt_unitName,"/"+item.getUnitName());
        if (item.getStock()>999){
            helper.setText(R.id.txt_stock,"商品库存 999+");
        }else{
            helper.setText(R.id.txt_stock,"商品库存 "+item.getStock());
        }
        double v = (int)item.getDistance() / 1000;
        helper.setText(R.id.txt_discuss,"距离 "+v+"km");
    }
}
