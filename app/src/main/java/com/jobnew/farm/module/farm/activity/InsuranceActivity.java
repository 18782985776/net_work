package com.jobnew.farm.module.farm.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.entity.InsuranceEntity;
import com.jobnew.farm.module.farm.adapter.InsuranceAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wufengqiao on 2017/6/1.
 */

public class InsuranceActivity extends BaseActivity {

    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private InsuranceAdapter mAdapter;
    private List<InsuranceEntity> mList;

    @Override
    protected int getLayout() {
        return R.layout.activity_select;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("保险购买", true);
        mList = new ArrayList<>();
        mAdapter = new InsuranceAdapter(R.layout.item_insurance, mList);
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        rvContent.setAdapter(mAdapter);
    }

    @Override
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
                        mList.add(new InsuranceEntity());
                        mAdapter.setNewData(mList);
                    }
                });
            }
        }.start();
    }

    @OnClick(R.id.tv_save)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_save:

                break;
        }
    }


}
