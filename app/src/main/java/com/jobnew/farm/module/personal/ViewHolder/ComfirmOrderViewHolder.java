package com.jobnew.farm.module.personal.ViewHolder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.MyApplication;
import com.jobnew.farm.R;
import com.jobnew.farm.module.personal.adapter.ConfirmOrderProductAdapter;
import com.jobnew.farm.widget.QuantityHelper;

import java.util.ArrayList;

/**
 * Created by wufengqiao on 2017/8/24.
 * function：确认订单页面ViewHolder
 * desc：
 */

public class ComfirmOrderViewHolder extends BaseViewHolder {

    public final RecyclerView mRvContent;
    public final ConfirmOrderProductAdapter mAdapter;

    public ComfirmOrderViewHolder(View view) {
        super(view);
        mRvContent = (RecyclerView) itemView.findViewById(R.id.rv_product);
        mRvContent.setLayoutManager(new LinearLayoutManager(MyApplication.getInstance()));
        mAdapter = new ConfirmOrderProductAdapter(R.layout.item_confirm_order_product, new ArrayList<>());
        mRvContent.setAdapter(mAdapter);

    }
}
