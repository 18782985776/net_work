package com.jobnew.farm.module.personal.adapter.MyFarm;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.myfarm.TaskRecordEntity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by wufengqiao on 2017/6/15.
 * function：任务记录界面-适配器
 * desc：
 */

public class TaskRecordAdapter extends BaseQuickAdapter<TaskRecordEntity, BaseViewHolder> {

    private final DecimalFormat format;
    private final SimpleDateFormat dateformat;

    public TaskRecordAdapter(int layoutResId, List<TaskRecordEntity> data) {
        super(layoutResId, data);
        format = new DecimalFormat("0.00");
        dateformat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskRecordEntity item) {
        helper.setText(R.id.tv_name, item.title);
        if (item.title.equals(item.artProductName)) {
            helper.setVisible(R.id.tv_minor_name, false);
        } else {
            helper.setVisible(R.id.tv_minor_name, true);
            helper.setText(R.id.tv_minor_name, item.artProductName);
        }
        helper.setText(R.id.tv_unit, item.artProductUnit);
        helper.setText(R.id.tv_unit_price, format.format(item.artProductPrice));
        helper.setText(R.id.tv_release_date, dateformat.format(item.createDate));
        helper.setText(R.id.tv_execute_date, dateformat.format(item.completeExecuteDate));
        helper.setText(R.id.tv_execute_date_name, item.title + "日期");
    }
}
