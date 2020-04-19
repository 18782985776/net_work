package com.jobnew.farm.module.personal.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by wc on 2017/7/12.
 * Function：
 * desc：
 */

public class UserHomeImgAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public UserHomeImgAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
