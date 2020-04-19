package com.jobnew.farm.module.personal.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.missmess.coverflowview.ACoverFlowAdapter;

import java.util.List;

/**
 * Created by wc on 2017/6/14.
 * Function：
 * desc：
 */

public class MyWalletAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public List<String> data;
    public MyWalletAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
        this.data=data;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }

}
