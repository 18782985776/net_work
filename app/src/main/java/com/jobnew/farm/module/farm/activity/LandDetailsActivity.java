package com.jobnew.farm.module.farm.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.FarmRepository;
import com.jobnew.farm.entity.plan.CropEntity;
import com.jobnew.farm.entity.ProductDetailsEntity;
import com.jobnew.farm.module.farm.activity.farmActivity.FarmDetailsActivity;
import com.jobnew.farm.module.farm.adapter.CropAdapter;
import com.jobnew.farm.module.personal.adapter.NetworkImageHolderView;
import com.jobnew.farm.utils.AlertUtil;
import com.jobnew.farm.utils.DateUtils;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.HorizontalLoadMoreView;
import com.jobnew.farm.widget.QuantityHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wufengqiao on 2017/6/1.
 * function：
 * desc：
 */

public class LandDetailsActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener {
    public static final int SELECT_CROP = 101;
    private static final int RESULT_PHONE = 201;
    //bannertu
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    //土地名称
    @BindView(R.id.tv_land_name)
    TextView tvLandName;

    //土地单价
    @BindView(R.id.tv_unit_prive)
    TextView tvUnitPrive;
    //更新日期
    @BindView(R.id.tv_update_date_valus)
    TextView tvUpdateDateValus;
    //库存面积
    @BindView(R.id.tv_land_size)
    TextView tvLandSize;
    //地址
    @BindView(R.id.tv_land_address)
    TextView tvLandAddress;

    //土地说明详情
    @BindView(R.id.tv_land_desc)
    TextView tvLandDesc;
    //评论数量
    @BindView(R.id.tv_comment_count)
    TextView tvCommentCount;
    //全部评价
    @BindView(R.id.tv_total_conment)
    TextView tvTotalConment;

    @BindView(R.id.tv_crop_name)
    TextView tvCropName;
    @BindView(R.id.tv_maturity_cycle)
    TextView tvMaturityCycle;
    @BindView(R.id.tv_crop_unit_prive)
    TextView tvCropUnitPrive;

    @BindView(R.id.tv_crop_unit)
    TextView tvCropUnit;

    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;

    @BindView(R.id.quantity_area)
    View view;
    //确定
    @BindView(R.id.tv_save)
    TextView tvSave;

    @BindView(R.id.rv_content)
    RecyclerView recyclerView;

    private ArrayList<String> mList;
    private String farmId = "-1";
    private int productId = 1;
    private ProductDetailsEntity farmEntity;
    private boolean isloadCompletion = false;
    private CropAdapter mAdapter;
    private List<CropEntity> data;
    private int page = 1;
    private QuantityHelper quHelper;
    private boolean isFirst = true;
    private CropEntity currentEntity;


    @Override
    protected int getLayout() {
        return R.layout.activity_land_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("土地详情", true);
        productId = getIntent().getIntExtra(Constant.PRODUCT_ID, -1);
        farmId = getIntent().getStringExtra(Constant.FARM_ID);
        requestData();
        data = new ArrayList<>();
        getCropSeed();
        setRecycleView();
        quHelper = new QuantityHelper(view, 1, 9999);
        quHelper.setOnCountChangeListener(new QuantityHelper.OnCountChangeListener() {
            @Override
            public void onCountChange(int count) {
                if (currentEntity != null)
                    setTotalPrice(currentEntity);
            }
        });
    }

    private void setTotalPrice(CropEntity entity) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        float totalPrice = entity.price * quHelper.getCount();
        if (totalPrice < 0) totalPrice = 0;
        tvTotalPrice.setText("¥" + decimalFormat.format(totalPrice));
    }

    private void setRecycleView() {
        mAdapter = new CropAdapter(R.layout.item_crop_select2, data);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.HORIZONTAL, false));
        mAdapter.setOnLoadMoreListener(this, recyclerView);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setLoadMoreView(new HorizontalLoadMoreView());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                currentEntity = data.get(position);
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).isItemSelect && i != position) {
                        data.get(i).isItemSelect = false;
                        mAdapter.notifyItemChanged(i);
                    }
                }
                if (!data.get(position).isItemSelect) {
                    data.get(position).isItemSelect = true;
                    mAdapter.notifyItemChanged(position);
                    setSeedInfo(data.get(position));
                }
            }
        });
    }

    /**
     * 设置种子信息
     *
     * @param entity
     */
    private void setSeedInfo(CropEntity entity) {
        tvCropName.setText(entity.name);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        tvCropUnitPrive.setText("(¥" + decimalFormat.format(entity.price) + "/" + entity.unitName + ")");
        tvCropUnit.setText(entity.unitName);
        setTotalPrice(entity);
        tvMaturityCycle.setText(entity.cycleTime + "天");
    }

    //获取土地详情接口
    private void requestData() {
        loading();
        FarmRepository.getIns().getProductDetails("" + productId, new HashMap<>())
                .subscribe(new DefaultSubscriber<ProductDetailsEntity>(this, false) {
                    @Override
                    public void _onNext(ProductDetailsEntity entity) {
                        if (isloadCompletion) {
                            content();
                        } else {
                            isloadCompletion = true;
                        }
                        setBannerData(entity.productImages);
                        farmEntity = entity;
                        initData(entity);
                    }

                    @Override
                    public void _onError(Throwable e, String msg) {
                        error(msg);
                    }
                });
    }


    /**
     * 获取种子接口
     */
    public void getCropSeed() {
        Map<String, String> map = new HashMap<>();
        map.put("farmId", farmId);
        map.put("pageNo", "" + page);
        map.put("pageSize", "" + 18);
        FarmRepository.getIns().getCropSeed(map).subscribe(new DefaultSubscriber<List<CropEntity>>(this, false) {
            @Override
            public void _onNext(List<CropEntity> list) {
                if (isloadCompletion) {
                    content();
                } else {
                    isloadCompletion = true;
                }
                if (list != null) {
                    if (isFirst && list.size() > 0) {
                        isFirst = false;
                        list.get(0).isItemSelect = true;
                        setSeedInfo(list.get(0));
                        currentEntity = list.get(0);
                    }
                    data.addAll(list);
                }
                mAdapter.notifyDataSetChanged();
                mAdapter.loadMoreComplete();
                if (list != null && list.size() < 18) {
                    mAdapter.loadMoreEnd(true);
                }
            }
        });
    }

    //初始化或数据
    private void initData(ProductDetailsEntity entity) {
        tvLandName.setText(entity.name);
        tvUnitPrive.setText("¥" + entity.price);
        tvLandSize.setText(entity.stock + "" + "/" + entity.maxStock + "㎡");
        tvLandAddress.setText(entity.farm.province + entity.farm.city + entity.farm.area + entity.farm.address);
        String data = DateUtils.DateToString(entity.modifyDate + "", "yyyy-MM-dd");
        tvUpdateDateValus.setText(data);
        tvLandDesc.setText(entity.intro);
        tvCommentCount.setText("评论（" /*+ entity.commentCount*/ + "）");

    }

    private void setBannerData(List<ProductDetailsEntity.ProductImages> images) {
        mList = new ArrayList<>();
        if (images == null) {
            return;
        }
        for (int i = 0; i < images.size(); i++) {
            String url = images.get(i).imgUrl;
            if (TextUtils.isEmpty(url)) {
                continue;
            }
            if (url.contains("http://") || url.contains("https://")) {
                mList.add(url);
            }
        }
        if (mList.size() == 0) {
            mList.add("");
        }
        initBanner();
    }

    /**
     * 初始化banner图
     */
    private void initBanner() {

        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, mList);
        convenientBanner.startTurning(5000);    //设置自动切换（同时设置了切换时间间隔）
        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent scanIntent=new Intent(LandDetailsActivity.this, ScanPictureActivity.class);
                scanIntent.putStringArrayListExtra("pictures",mList);
                startActivity(scanIntent);
            }
        });
    }


    @OnClick({R.id.convenientBanner, R.id.tv_land_address, R.id.iv_call, R.id.tv_total_conment, R.id.tv_save})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.convenientBanner:

                break;
            case R.id.tv_land_address:

                break;
            case R.id.iv_call:
                showRequestPhone();
                break;
            case R.id.tv_total_conment:
                FarmToastUtils.show("全部评论");
                break;
            case R.id.tv_save:
                if (currentEntity == null || farmEntity == null) {
                    FarmToastUtils.show("请选择种子！");
                    return;
                }
                intent = new Intent(this, PlantingPlanAcitivity.class);
                intent.putExtra(Constant.FARM_PRODUCT, farmEntity);
                intent.putExtra(Constant.CROP_SEED, currentEntity);
                intent.putExtra(Constant.NUMBER, quHelper.getCount());
                intent.putExtra(Constant.STOCK, farmEntity.stock);
                AppManager.jump(PlantingPlanAcitivity.class, intent);
                break;
        }
    }

    /**
     * 确认打电话
     */
    private void showRequestPhone() {
        AlertUtil.show(this, "提示", "你是否要拨打电话？", "取消", "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ActivityCompat.checkSelfPermission(LandDetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(LandDetailsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, RESULT_PHONE);
                            return;
                        }

                    }
                    callPhone();
                }
            }
        });
    }

    /**
     * 拨打电话
     */
    private void callPhone() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + farmEntity.farm.phone));
        startActivity(intent);
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        getCropSeed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == RESULT_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
