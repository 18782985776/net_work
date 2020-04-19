package com.jobnew.farm.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.bazaar.BazaarBig;

import java.util.List;

/**
 * Created by wc on 2017/6/29.
 * Function：
 * desc：
 */

public class BazaarOneAdapter extends BaseQuickAdapter<BazaarBig.ChildrenEntity,BaseViewHolder> {
    int position=0;
    public BazaarOneAdapter(int layoutResId, List<BazaarBig.ChildrenEntity> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, BazaarBig.ChildrenEntity item) {
        TextView txt=helper.getView(R.id.txt);
        txt.setText(item.getName());
        if (helper.getLayoutPosition()==position){
            helper.setTextColor(R.id.txt,0xff90b659);
            txt.setBackgroundResource(R.drawable.bg_color_main);
        }else{
            helper.setTextColor(R.id.txt,0xff888888);
            txt.setBackgroundResource(R.drawable.bg_color_68);
        }
    }
    public void setPosition(int position) {
        this.position = position;
        notifyDataSetChanged();
    }
}
