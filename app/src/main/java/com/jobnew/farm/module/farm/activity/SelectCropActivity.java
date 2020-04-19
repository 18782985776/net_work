package com.jobnew.farm.module.farm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseRefreshAndLoadActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.exception.NullDataException;
import com.jobnew.farm.data.repository.FarmRepository;
import com.jobnew.farm.entity.plan.CropEntity;
import com.jobnew.farm.module.farm.adapter.CropAdapter;
import com.jobnew.farm.widget.QuantityHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wufengqiao on 2017/6/2.
 * function：
 * desc：
 */

public class SelectCropActivity extends BaseRefreshAndLoadActivity {

    @BindView(R.id.tv_crop_name)
    TextView tvCropName;
    @BindView(R.id.tv_unit_prive)
    TextView tvUnitPrive;
    @BindView(R.id.tv_maturity_cycle)
    TextView tvMaturityCycle;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;

    @BindView(R.id.quantity_area)
    View area;
    private QuantityHelper quantityHelper;
    private ArrayList<CropEntity> mList;
    private CropAdapter mAdapter;
    private int page = 1;
    private int size = 30;
    private int framId;
    private boolean isRefresh = false;
    private CropEntity selectCropEntity;
    private int number;
    private DecimalFormat decimalFormat;
    private int seedId;

    @Override
    protected int getLayout() {
        return R.layout.activity_selcet_crop;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("选择种植作物", true);
        quantityHelper = new QuantityHelper(area, 1, 9999);
        quantityHelper.setOnCountChangeListener(new QuantityHelper.OnCountChangeListener() {
            @Override
            public void onCountChange(int count) {
                setTotalPrice();
            }
        });
        framId = getIntent().getIntExtra(Constant.FARM_ID, -1);
        number = getIntent().getIntExtra(Constant.NUMBER, 1);
        seedId = getIntent().getIntExtra(Constant.SEED_ID, -1);
        decimalFormat = new DecimalFormat("0.00");
        //item点击事件
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).isItemSelect && i != position) {
                        mList.get(i).isItemSelect = false;
                        mAdapter.notifyItemChanged(i);
                    }
                }
                if (!mList.get(position).isItemSelect) {
                    mList.get(position).isItemSelect = true;
                    seedId = mList.get(position).id;
                    mAdapter.notifyItemChanged(position);
                    setSeedInfor(mList.get(position));
                }
            }
        });
    }


    @Override
    protected void loadData() {
        loading();
        requestData();
    }

    /**
     * 请求数据
     */
    private void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("farmId", framId + "");
        map.put("pageNo", "" + page);
        map.put("pageSize", "" + size);
        FarmRepository.getIns().getCropSeed(map).subscribe(new DefaultSubscriber<List<CropEntity>>(this, false) {
            @Override
            public void _onNext(List<CropEntity> list) {
                content();
                if (list == null) {
                    onError(new NullDataException());
                    return;
                }
                if (isRefresh) {
                    isRefresh = false;
                    ptrLayout.refreshComplete();
                    mList.clear();
                } else {
                    mAdapter.loadMoreComplete();
                }
                mList.addAll(list);
                if (mList.size() == 0) {
                    empty();
                }
                if (page == 1) {
                    if (seedId > 0) {
                        selectFirstSeed();
                        for (int i = 0; i < mList.size(); i++) {
                            if (mList.get(i).id == seedId) {
                                if (i != 0) {
                                    mList.get(0).isItemSelect = false;
                                    rvContent.smoothScrollToPosition(i);
                                    mList.get(i).isItemSelect = true;
                                    selectCropEntity = mList.get(i);
                                    setSeedInfor(selectCropEntity);
                                }
                            }
                        }
                    }
                }
                mAdapter.notifyDataSetChanged();
                if (list.size() < size) {
                    mAdapter.loadMoreEnd(true);
                }
            }
        });
    }

    private void selectFirstSeed() {
        if (mList.size() > 0) {
            mList.get(0).isItemSelect = true;
            selectCropEntity = mList.get(0);
            setSeedInfor(selectCropEntity);
        }
    }

    /**
     * 显示选择种子的详情
     *
     * @param cropEntity
     */
    private void setSeedInfor(CropEntity cropEntity) {
        if (cropEntity == null){
            return;
        }
        selectCropEntity = cropEntity;
        tvCropName.setText(cropEntity.name);
        tvUnitPrive.setText("（¥" + decimalFormat.format(cropEntity.price) + "/" + cropEntity.unitName + ") ");
        tvUnit.setText(cropEntity.unitName);
        tvMaturityCycle.setText(cropEntity.cycleTime + "天");
        quantityHelper.setCount(number);
        setTotalPrice();
    }

    private void setTotalPrice() {
        float totalPrice = selectCropEntity.price * quantityHelper.getCount();
        if (totalPrice < 0) totalPrice = 0;
        tvTotalPrice.setText("¥" + decimalFormat.format(totalPrice));
    }

    @OnClick(R.id.tv_save)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.putExtra(Constant.CROP_SEED, selectCropEntity);
        intent.putExtra(Constant.NUMBER, quantityHelper.getCount());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        mList = new ArrayList<>();
        mAdapter = new CropAdapter(R.layout.item_crop_select, mList);
        return mAdapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        requestData();
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        mList.clear();
        isRefresh = true;
        page = 1;
        requestData();
    }
}
