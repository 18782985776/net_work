package com.jobnew.farm.module.personal.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;

import java.util.List;

/**
 * Created by wc on 2017/6/21.
 * Function：
 * desc：
 */

public class WithDrawAlipayAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public WithDrawAlipayAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {
        int layoutPosition = helper.getLayoutPosition();
        ImageView view = helper.getView(R.id.img_check);
        if (layoutPosition==type){
            view.setImageResource(R.mipmap.ic_cb_check);
        }else{
            view.setImageResource(R.mipmap.ic_cb_nocheck);
        }
    }
    int type=0;
    public void setRefreshCheck(int position){
        this.type=position;
        notifyDataSetChanged();
    }
}
