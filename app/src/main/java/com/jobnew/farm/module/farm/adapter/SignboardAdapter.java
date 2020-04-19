package com.jobnew.farm.module.farm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.plan.SignboardEntity;
import com.jobnew.farm.utils.GlideManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wufengqiao on 2017/5/27.
 */

public class SignboardAdapter extends BaseQuickAdapter<SignboardEntity,SignboardAdapter.ViewHolder> {


    public SignboardAdapter(int layoutResId, List<SignboardEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, SignboardEntity item) {

        GlideManager.loadImg(item.pImg,helper.ivSignboard);
        helper.tvSignboardName.setText(item.name);
        helper.tvSignboardPrice.setText("¥"+item.price);
    }

    @Override
    protected ViewHolder createBaseViewHolder(ViewGroup parent, int layoutResId) {
        View view = LayoutInflater.from(mContext)
                .inflate(layoutResId, parent, false);
        return new ViewHolder(view);
    }

    static class ViewHolder extends BaseViewHolder{
        @BindView(R.id.iv_signboard)
        ImageView ivSignboard;
        @BindView(R.id.tv_signboard_name)
        TextView tvSignboardName;
        @BindView(R.id.tv_signboard_price)
        TextView tvSignboardPrice;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}





