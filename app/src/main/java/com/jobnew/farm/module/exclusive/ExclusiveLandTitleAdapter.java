package com.jobnew.farm.module.exclusive;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.MyApplication;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.AllBusniessEntity;
import com.jobnew.farm.utils.GlideManager;

import java.util.List;

/**
 * Created by wufengqiao on 2017/8/1.
 * function：
 * desc：
 */

public class ExclusiveLandTitleAdapter extends BaseQuickAdapter<AllBusniessEntity.BusinessBean,BaseViewHolder> {

    public ExclusiveLandTitleAdapter(int layoutResId, List<AllBusniessEntity.BusinessBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllBusniessEntity.BusinessBean item) {
        helper.setText(R.id.tv_categorical, item.getName());
        GlideManager.loadImg(item.getIcon(),helper.getView(R.id.iv_icon));
        helper.itemView.setSelected(item.isSelect);

    }
}
