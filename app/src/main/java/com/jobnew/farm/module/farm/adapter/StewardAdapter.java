package com.jobnew.farm.module.farm.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jobnew.farm.R;
import com.jobnew.farm.entity.plan.StewardEntity;

import java.util.List;

/**
 * Created by wufengqiao on 2017/5/26.
 */

public class StewardAdapter extends BaseQuickAdapter<StewardEntity,StewardAdapter.StewardHolder> {

    public StewardAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }
    @Override
    protected StewardHolder createBaseViewHolder(ViewGroup parent, int layoutResId) {
        View view = LayoutInflater.from(mContext)
                .inflate(layoutResId, parent, false);
        return new StewardHolder(view);
    }

    @Override
    protected void convert(StewardHolder helper, StewardEntity item) {
        helper.mTvStewardName.setText(TextUtils.isEmpty(item.name) ? "姓名":item.name);
        helper.mTvStewardAge.setText(item.age+"岁");
        helper.mLlStewardSex.setBackgroundResource(item.sex == 2 ? R.mipmap.farm_icon_female1 : R.mipmap.farm_icon_male1);
        helper.mTvStewardIntro.setText(item.intro);
    }



    static class StewardHolder extends BaseViewHolder{

        public TextView mTvStewardName;
        public TextView mTvStewardAge;
        public LinearLayout mLlStewardSex;
        public TextView mTvStewardIntro;
        public StewardHolder(View view) {
            super(view);
            mTvStewardName = (TextView) convertView.findViewById(R.id.tv_steward_name);
            mTvStewardAge = (TextView) convertView.findViewById(R.id.tv_steward_age);
            mLlStewardSex = (LinearLayout) convertView.findViewById(R.id.ll_steward_sex);
            mTvStewardIntro = (TextView) convertView.findViewById(R.id.tv_steward_intro);
        }


    }
}
