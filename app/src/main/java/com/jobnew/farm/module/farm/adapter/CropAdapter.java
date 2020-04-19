package com.jobnew.farm.module.farm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.plan.CropEntity;
import com.jobnew.farm.utils.GlideManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wufengqiao on 2017/6/2.
 * function：
 * desc：
 */

public class CropAdapter extends BaseQuickAdapter<CropEntity, CropAdapter.ViewHolder> {


    public CropAdapter(int layoutResId, List<CropEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, CropEntity item) {
        GlideManager.loadRoundImg(item.pImg,helper.ivCrop);
        helper.tvCropName.setText(item.name);
        helper.itemView.setSelected(item.isItemSelect);
    }

    @Override
    protected ViewHolder createBaseViewHolder(ViewGroup parent, int layoutResId) {
        View view = LayoutInflater.from(mContext)
                .inflate(layoutResId, parent, false);
        return new ViewHolder(view);
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_crop)
        ImageView ivCrop;
        @BindView(R.id.tv_crop_name)
        TextView tvCropName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
