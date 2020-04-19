package com.jobnew.farm.module.personal.adapter.MyFarm;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.utils.GlideManager;

import java.util.List;

/**
 * Created by wufengqiao on 2017/7/20.
 * function：
 * desc：
 */

public class ImageAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public ImageAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        GlideManager.loadImg(item,helper.getView(R.id.iv_image));
    }
}
