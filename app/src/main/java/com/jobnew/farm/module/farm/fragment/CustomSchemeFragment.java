package com.jobnew.farm.module.farm.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.base.fragment.BaseFragment;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.FarmRepository;
import com.jobnew.farm.entity.plan.CommitOrderEntity;
import com.jobnew.farm.entity.plan.CountItemEntity;
import com.jobnew.farm.entity.plan.SchemeEntity;
import com.jobnew.farm.module.farm.activity.SettingFrequencyActivity;
import com.jobnew.farm.module.farm.adapter.CustomSchemeAdapter;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by wufengqiao on 2017/6/14.
 * function：
 * desc：
 */

public class CustomSchemeFragment extends BaseFragment {

    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private ArrayList<SchemeEntity> mList;
    private int mSeedId = -1;
    private CustomSchemeAdapter mAdapter;
    private int mDuration;
    private int number;
    private List<CountItemEntity> dates;
    private double mTotalPrice;

    public static CustomSchemeFragment newInstance(int seeId, int duration, int num, boolean isFirst, ArrayList schemeList) {
        Bundle args = new Bundle();
        //保存过自定义方案，再次打开需要传入方案数据，使其恢复
        if (!isFirst) {
            args.putParcelableArrayList(Constant.SCHEME, schemeList);
        }
        args.putInt(Constant.SEED_ID, seeId);
        args.putInt(Constant.DURATION, duration);
        args.putInt(Constant.NUMBER, num);
        CustomSchemeFragment customSchemeFragment = new CustomSchemeFragment();
        customSchemeFragment.setArguments(args);
        return customSchemeFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_scheme;
    }

    @Override
    protected void initView(Bundle bundle, View view) {
        initIntent();
        if (mList == null) {
            mList = new ArrayList<>();
            requestData();
        }
        rvContent.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new CustomSchemeAdapter(getActivity(), mList, number);
        rvContent.setAdapter(mAdapter);
        mAdapter.setOnPriceChangeListener(new CustomSchemeAdapter.OnPriceChangeListener() {
            @Override
            public void OnPriceChange(double totalPrice) {
                mTotalPrice = totalPrice;
                tvTotalPrice.setText("¥" + new DecimalFormat("0.00").format(totalPrice));
            }
        });
        mAdapter.setOnCountClickListener(new CustomSchemeAdapter.OnCountClickListener() {
            @Override
            public void onCountClick(View view, int postion) {
                Intent intent = new Intent(mContext, SettingFrequencyActivity.class);
                intent.putExtra(SettingFrequencyActivity.COUNT, mList.get(postion));
                long date = (new Date().getTime() + 86400000) / 86400000 * 86400000;
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).ctype == 2) {
                        try {
                            long time = format.parse(mList.get(i).date).getTime();
                            if (date < time){
                                date = time;
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
                intent.putExtra(Constant.DATE, date);
                startActivityForResult(intent, postion);
            }
        });
        mAdapter.notifyPriceChange();
    }

    private void initIntent() {
        Bundle args = getArguments();
        if (args != null) {
            mSeedId = args.getInt(Constant.SEED_ID, -1);
            mDuration = args.getInt(Constant.DURATION, 0);
            number = args.getInt(Constant.NUMBER, -1);
            mList = args.getParcelableArrayList(Constant.SCHEME);
        }
    }

    private void requestData() {
        loading();
        Map<String, String> map = new HashMap<>();
        map.put("productId", mSeedId + "");
        FarmRepository.getIns().getCustomScheme(map).subscribe(new DefaultSubscriber<List<SchemeEntity>>(this, false) {
            @Override
            public void _onNext(List<SchemeEntity> list) {
                if (list == null) {
                    error("服务器出错了");
                }
                if (list.size() == 0) {
                    empty();
                    return;
                }
                List<Integer> temps = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    SchemeEntity entity = list.get(i);
                    if (entity.ctype == 1) {
                        if (temps.contains(entity.categoryId)) {

                            for (int j = 0; j < mList.size(); j++) {
                                if (mList.get(j).categoryId == entity.categoryId) {
                                    mList.get(j).add(entity);
                                }
                            }

                        } else {
                            SchemeEntity clone = null;
                            try {
                                clone = (SchemeEntity) entity.clone();
                            } catch (CloneNotSupportedException e) {
                                e.printStackTrace();
                            }
                            if (clone == null) {
                                error("服务器错误");
                                return;
                            }
                            mList.add(clone);
                            clone.add(entity);
                            temps.add(entity.categoryId);
                        }
                    } else {
                        mList.add(entity);
                    }
                }

                //外层实体数据跟推荐实体数据一致
                for (int i = 0; i < mList.size(); i++) {
                    SchemeEntity entity = mList.get(i);
                    entity.count = 0;
                    entity.checked = false;
                    if (entity.ctype == 1) {
                        for (int j = 0; j < entity.entities.size(); j++) {
                            if (entity.entities.get(j).checked) {
                                entity.entities.get(i).checked = false;
                                entity.farmArtName = entity.entities.get(j).categoryName;
                            }
                        }
                    }
                }

                //添加两个实体，周期  --- 文字提示
                SchemeEntity entity = new SchemeEntity();
                SchemeEntity entity2 = new SchemeEntity();
                entity.ctype = 3;
                entity2.ctype = 5;
                entity.checked = false;
                if (mDuration != -1) {
                    entity.duration = mDuration;
                } else {
                    mDuration = 1;
                }
                if (mList.size() > 1) {
                    mList.add(1, entity);
                    mList.add(mList.size(), entity2);
                }
                mAdapter.notifyDataSetChanged();
                mAdapter.notifyPriceChange();
                content();
            }
        });
    }

    @OnClick({R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                mAdapter.notifyPriceChange();
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra(Constant.SCHEME, mList);
                intent.putExtra(Constant.TOTAL_PRICE, mTotalPrice);
                intent.putExtra(Constant.SCHEME_TYPE, false);
                getActivity().setResult(RESULT_OK, intent);
                getActivity().finish();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            dates = data.getParcelableArrayListExtra(Constant.DATE);
            if (dates != null) {
                if (requestCode < mList.size()) {
                    mList.get(requestCode).count = dates.size();
                    mList.get(requestCode).countItemModels = dates;
                }
                mAdapter.notifyDataSetChanged();
                mAdapter.notifyPriceChange();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
