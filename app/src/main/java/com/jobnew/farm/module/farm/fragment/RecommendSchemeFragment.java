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
import com.jobnew.farm.entity.plan.RecommendSchemeEntity;
import com.jobnew.farm.entity.plan.SchemeEntity;
import com.jobnew.farm.module.farm.adapter.RecomSchemeAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by wufengqiao on 2017/6/13.
 * function：默认种植方案
 * desc：
 */

public class RecommendSchemeFragment extends BaseFragment {
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private ArrayList<SchemeEntity> mList;
    private int mSeedId = -1;
    private RecomSchemeAdapter mAdapter;
    private int mDuration;
    private int number;
    private double mTotalPrice = 0.00;

    public static RecommendSchemeFragment newInstance(int seeId, int duration, int num, boolean isFirst, ArrayList schemeList) {
        Bundle args = new Bundle();

        //保存过默认方案，再次打开需要传入方案数据，使其恢复
        if (!isFirst) {
            args.putParcelableArrayList(Constant.SCHEME, schemeList);
        }
        args.putInt(Constant.SEED_ID, seeId);
        args.putInt(Constant.DURATION, duration);
        args.putInt(Constant.NUMBER, num);
        RecommendSchemeFragment recommendSchemeFragment = new RecommendSchemeFragment();
        recommendSchemeFragment.setArguments(args);
        return recommendSchemeFragment;
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
        mAdapter = new RecomSchemeAdapter(getActivity(), mList, number, true);
        rvContent.setAdapter(mAdapter);
        mAdapter.setOnPriceChangeListener(new RecomSchemeAdapter.OnPriceChangeListener() {
            @Override
            public void OnPriceChange(double totalPrice) {
                mTotalPrice = totalPrice;
                tvTotalPrice.setText("¥" + new DecimalFormat("0.00").format(totalPrice));
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
        FarmRepository.getIns().getDefaultScheme(mSeedId).subscribe(new DefaultSubscriber<RecommendSchemeEntity>(this, false) {
            @Override
            public void _onNext(RecommendSchemeEntity recommendSchemeEntity) {
                List<SchemeEntity> list = recommendSchemeEntity.artOrderItemResults;
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
                    if (entity.ctype == 1) {
                        entity.count = 1;
                        for (int j = 0; j < entity.entities.size(); j++) {
                            if (entity.entities.get(j).checked) {
                                entity.checked = true;
                                entity.isCount = entity.entities.get(j).isCount;
                                if (entity.isCount) {
                                    entity.count = entity.entities.get(j).count;
                                } else {
                                    entity.count = 1;
                                }
                            }
                        }
                    }
                }

                //添加两个实体，周期  --- 文字提示
                SchemeEntity entity = new SchemeEntity();
                SchemeEntity entity2 = new SchemeEntity();
                entity.ctype = 3;
                entity.farmArtName = "种植周期";
                entity2.ctype = 5;
                entity.checked = true;
                if (mDuration > 0) {
                    entity.duration = mDuration;
                } else {
                    mDuration = 1;
                }
                if (mList.size() > 1) {
                    mList.add(1, entity);
                    mList.add(mList.size(), entity2);
                }
                if (mAdapter != null) {
                    mAdapter.notifyDataSetChanged();
                    mAdapter.notifyPriceChange();
                }
                content();
            }
        });
    }

    @OnClick({R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra(Constant.SCHEME, mList);
                intent.putExtra(Constant.SCHEME_TYPE, true);
                getActivity().setResult(RESULT_OK, intent);
                getActivity().finish();
                break;
        }
    }


}
