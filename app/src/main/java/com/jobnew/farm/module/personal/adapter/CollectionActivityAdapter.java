package com.jobnew.farm.module.personal.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

/**
 * Created by wangwenlang on 2017/6/15.
 * title:
 * note:
 */

public class CollectionActivityAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public CollectionActivityAdapter(int resourceId, ArrayList arrayList){
        super(resourceId,arrayList);
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
