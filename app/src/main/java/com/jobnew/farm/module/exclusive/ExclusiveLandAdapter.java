package com.jobnew.farm.module.exclusive;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.exclusive.ExclusiveLandEntity;
import com.jobnew.farm.utils.GlideManager;

import java.util.List;

/**
 * Created by wufengqiao on 2017/7/31.
 * function：
 * desc：
 */

public class ExclusiveLandAdapter extends BaseQuickAdapter<ExclusiveLandEntity, BaseViewHolder> {
    public ExclusiveLandAdapter(int layoutResId, List<ExclusiveLandEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExclusiveLandEntity item) {
        helper.setText(R.id.tv_land_name, item.name);
        helper.setText(R.id.tv_area, item.stock + "㎡");
        helper.setText(R.id.tv_unit_prive, "¥" + item.price);
        helper.setText(R.id.tv_address, item.farm.province + item.farm.city + item.farm.area + item.farm.address);
        helper.setText(R.id.tv_address_distance, "距离" + (int) (item.distance + 0.5) + "KM");
        GlideManager.loadImg(item.pImg,helper.getView(R.id.iv_land));
    }

}
