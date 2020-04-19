package com.jobnew.farm.module.personal.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;

import java.util.List;

/**
 * Created by wc on 2017/7/14.
 * Function：
 * desc：
 */

public class LandAwayAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private int potionChick=-1;
    public LandAwayAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView imgChick = helper.getView(R.id.img_check);
        if (helper.getLayoutPosition()==potionChick){
            imgChick.setImageResource(R.mipmap.login_icon_checked);
        }else{
            imgChick.setImageResource(R.mipmap.ic_cb_nocheck);
        }
    }

    public void setPotionChick(int potionChick) {
        this.potionChick = potionChick;
        notifyDataSetChanged();
    }
}
