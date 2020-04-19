package com.jobnew.farm.module.personal.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wancheng on 2017/6/15.
 * title:
 * note:
 */

public class AfterReturnAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public AfterReturnAdapter(int resourceId, List arrayList){
        super(resourceId,arrayList);
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.txt_why,item);
    }
}
