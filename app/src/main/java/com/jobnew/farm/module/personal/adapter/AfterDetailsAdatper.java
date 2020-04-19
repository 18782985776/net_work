package com.jobnew.farm.module.personal.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.utils.GlideManager;

import java.util.List;

/**
 * Created by wc on 2017/8/24.
 * Function：
 * desc：
 */

public class AfterDetailsAdatper extends BaseQuickAdapter<String,BaseViewHolder> {
    private Context mContext;
    public AfterDetailsAdatper(int layoutResId, List<String> data, Context mContext) {
        super(layoutResId, data);
        this.mContext=mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.getView(R.id.feekback_delter).setVisibility(View.GONE);
        GlideManager.loadImg(item,helper.getView(R.id.feekback_img));
    }
}
