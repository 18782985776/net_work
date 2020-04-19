package com.jobnew.farm.module.farm.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.entity.MonitorEntity;
import com.jobnew.farm.module.farm.adapter.MonitorAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/31.
 */

public class MonitorActivity extends BaseActivity {
    @BindView(R.id.rv_content)
    RecyclerView recyclerView;

    private List<MonitorEntity> mList;
    private MonitorAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_select;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("监控列表", true);
        mList = new ArrayList<>();
        mAdapter = new MonitorAdapter(R.layout.item_monitor_select, mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }

    protected void loadData() {
        loading();
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        content();
                        mList.add(new MonitorEntity());
                        mList.add(new MonitorEntity());
                        mList.add(new MonitorEntity());
                        mAdapter.setNewData(mList);

                    }
                });
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
