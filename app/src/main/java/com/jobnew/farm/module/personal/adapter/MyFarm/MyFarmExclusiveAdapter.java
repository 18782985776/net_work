package com.jobnew.farm.module.personal.adapter.MyFarm;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.myfarm.MyExclusiveLandEntity;
import com.jobnew.farm.utils.GlideManager;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by wufengqiao on 2017/6/14.
 * function：我的农场，专属农场界面-适配器
 * desc：
 */

public class MyFarmExclusiveAdapter extends BaseQuickAdapter<MyExclusiveLandEntity, BaseViewHolder> {

    private final SimpleDateFormat mDateFormat;

    public MyFarmExclusiveAdapter(int layoutResId, List<MyExclusiveLandEntity> data) {
        super(layoutResId, data);
        mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    protected void convert(BaseViewHolder helper, MyExclusiveLandEntity item) {
        helper.itemView.setSelected(true);
        helper.addOnClickListener(R.id.tv_record);
        helper.addOnClickListener(R.id.iv_phone);
        GlideManager.loadRoundImg(item.farmImg, helper.getView(R.id.iv_farm));
        GlideManager.loadRoundImg(item.majorProductImg, helper.getView(R.id.iv_land));
        helper.setText(R.id.tv_farm, item.farmName);
        helper.setText(R.id.tv_land, item.majorProductName);
        helper.setText(R.id.tv_land_unit_price, item.majorProductPrice + "元/㎡/月");
        helper.setText(R.id.tv_land_area, item.address);
        helper.setText(R.id.tv_distance, "距离 " + (int) (item.distance + 0.5) + " km");
        helper.setText(R.id.tv_date, mDateFormat.format(item.startDate) + "至" + mDateFormat.format(item.endDate));
    }
}
