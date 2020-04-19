package com.jobnew.farm.module.farm.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.MonitorEntity;
import com.jobnew.farm.utils.FarmToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wufengqiao on 2017/5/31.
 */

public class MonitorAdapter extends BaseQuickAdapter<MonitorEntity,MonitorAdapter.ViewHolder> {


    public MonitorAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(MonitorAdapter.ViewHolder helper, MonitorEntity item) {
        helper.tvMonitorName.setText("监控3");
        helper.ivMonitor.setImageResource(R.drawable.ic_launcher);
        helper.tvMonitorLocaion.setText("优质土地3号");
        helper.tvMonitorBelong.setText("农场");
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FarmToastUtils.show(".....");
            }
        });
    }

    @Override
    protected MonitorAdapter.ViewHolder createBaseViewHolder(View view) {
        return new MonitorAdapter.ViewHolder(view);
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_monitor_name)
        TextView tvMonitorName;
        @BindView(R.id.iv_monitor)
        ImageView ivMonitor;
        @BindView(R.id.tv_monitor_locaion)
        TextView tvMonitorLocaion;
        @BindView(R.id.tv_monitor_belong)
        TextView tvMonitorBelong;
        @BindView(R.id.tv_demand)
        TextView tvDemand;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        @OnClick({R.id.tv_demand})
        public void onClick(View view){
            switch (view.getId()){
                case R.id.tv_demand:
                    FarmToastUtils.show("查看");
                    break;
            }
        }

    }
}
