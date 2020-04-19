package com.jobnew.farm.module.farm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.entity.plan.CommitOrderEntity;
import com.jobnew.farm.entity.plan.CountItemEntity;
import com.jobnew.farm.entity.plan.SchemeEntity;
import com.jobnew.farm.module.farm.adapter.SettingFrequencyAdapter;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.widget.QuantityHelper;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wufengqiao on 2017/6/13.
 * function：
 * desc：
 */

public class SettingFrequencyActivity extends BaseActivity {
    @BindView(R.id.tv_right_day)
    TextView tvRightDay;
    @BindView(R.id.stv_plant_cycle)
    SuperTextView stvPlantCycle;
    @BindView(R.id.layout_plant_cycle)
    View view;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    private QuantityHelper mFrequency;
    private SettingFrequencyAdapter mAdapter;
    private ArrayList<CountItemEntity> mList;
    private long date;
    public static final String COUNT = "count";
    private SchemeEntity mEntity;

    @Override
    protected int getLayout() {
        return R.layout.activity_setting_frequency;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        mEntity = intent.getParcelableExtra(COUNT);
        date = intent.getLongExtra(Constant.DATE, (System.currentTimeMillis() + 86400000) / 86400000 * 86400000);
        setTitle(mEntity.farmArtName + "次数", true);
        stvPlantCycle.setLeftString(mEntity.farmArtName + "次数");

        mFrequency = new QuantityHelper(view, 1, 1000);
        mFrequency.setMinimum(1);
        mFrequency.setOnCountChangeListener(new QuantityHelper.OnCountChangeListener() {
            @Override
            public void onCountChange(int count) {
                if (mList == null) {
                    return;
                }
                if (count > 0) {
                    while (mList.size() > count) {
                        mList.remove(mList.size() - 1);
                    }
                    while (mList.size() < count) {
                        if (mList.size() == 0) {
                            mList.add(new CountItemEntity("第1次" + mEntity.farmArtName + "日期", date));
                        } else {
                            mList.add(new CountItemEntity("第" + (mList.size() + 1) + "次" + mEntity.farmArtName + "日期", mList.get(mList.size() - 1).executeDate));
                        }
                    }
                    if (mAdapter != null) {
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        mList = new ArrayList();
        if (mEntity.countItemModels != null && mEntity.countItemModels.size() > 0) {
            mList.addAll(mEntity.countItemModels);
        }
        if (mEntity.count > 1) {
            mFrequency.setCount(mEntity.count);
        } else {
            mFrequency.setCount(1);
        }
        mAdapter = new SettingFrequencyAdapter(this, mList,date);
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        rvContent.setAdapter(mAdapter);
    }

    @OnClick(R.id.tv_save)
    public void onClick(View v) {
        long date = this.date;
        for (int i = 0; i < mList.size(); i++) {
            if (date > mList.get(i).executeDate) {
                if (i == 0) {
                    FarmToastUtils.show(mList.get(0).title + "日期不能早于播种日期！");
                } else {
                    FarmToastUtils.show(mList.get(i).title + "不能晚于" + mList.get(i - 1).title);
                }
                return;
            }
            date = mList.get(i).executeDate;
        }
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(Constant.DATE, mList);
        setResult(RESULT_OK, intent);
        finish();
    }

}
