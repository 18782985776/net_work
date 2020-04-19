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
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.FarmRepository;
import com.jobnew.farm.entity.ProductDetailsEntity;
import com.jobnew.farm.module.personal.adapter.NetworkImageHolderView;
import com.jobnew.farm.utils.AlertUtil;
import com.jobnew.farm.utils.DateUtils;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.QuantityHelper;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wufengqiao on 2017/6/1.
 * function：
 * desc：
 */

public class BreedDetailsActivity extends BaseActivity {

    private static final int RESULT_PHONE = 201;
    //bannertu
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    //养殖名称
    @BindView(R.id.tv_breed_name)
    TextView tvBreedName;

    //单价
    @BindView(R.id.tv_unit_prive)
    TextView tvUnitPrive;

    //单位
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    //更新日期
    @BindView(R.id.tv_update_date_valus)
    TextView tvUpdateDateValus;
    //库存
    @BindView(R.id.tv_repertory)
    TextView tvRepertory;
    //地址
    @BindView(R.id.tv_address)
    TextView tvAddress;
    //养殖说明详情
    @BindView(R.id.tv_breed_desc)
    TextView tvBreedDesc;
    //评论数量
    @BindView(R.id.tv_comment_count)
    TextView tvCommentCount;
    //全部评价
    @BindView(R.id.tv_total_conment)
    TextView tvTotalConment;
    //付款
    @BindView(R.id.tv_save)
    TextView tvSave;
    private ProductDetailsEntity pdEntity;
    private ArrayList<String> mList;
    private ArrayList<ProductDetailsEntity> data;
    private int productId = 57;
    private DecimalFormat mDecimalFormat;

    @Override
    protected int getLayout() {
        return R.layout.activity_breed_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("养殖详情", true);
        productId = getIntent().getIntExtra(Constant.PRODUCT_ID,-1);
        mDecimalFormat = new DecimalFormat("0.00");
    }

    //初始化或数据
    private void initData(ProductDetailsEntity entity) {
        tvBreedName.setText(entity.name);
        tvUnitPrive.setText("¥" + mDecimalFormat.format(entity.price));
        tvUnit.setText("/"+entity.unitName);
        tvRepertory.setText(entity.stock + "");
        tvAddress.setText(entity.farm.province + entity.farm.city + entity.farm.area + entity.farm.address);
        String data = DateUtils.DateToString(entity.modifyDate + "", "yyyy-MM-dd");
        tvUpdateDateValus.setText(data);
        tvBreedDesc.setText(entity.intro);
        tvCommentCount.setText("评论（" /*+ entity.commentCount*/ + "）");
    }

    @Override
    protected void loadData() {
        requestData();
    }

    private void requestData() {
        loading();
        FarmRepository.getIns().
                getProductDetails("" + productId, new HashMap<>()).
                subscribe(new DefaultSubscriber<ProductDetailsEntity>(this,false) {
            @Override
            public void _onNext(ProductDetailsEntity entity) {
                pdEntity = entity;
                content();
                setBannerData(entity.productImages);
                initData(entity);
            }

                    @Override
                    public void _onError(Throwable e, String msg) {
                        error(msg);
                    }
                });
    }

    private void setBannerData(List<ProductDetailsEntity.ProductImages> list) {
        mList = new ArrayList<>();
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            String url = list.get(i).imgUrl;
            if (TextUtils.isEmpty(url)) {
                continue;
            }
            if (url.contains("http://") || url.contains("https://")) {
                mList.add(url);
            }
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
                Intent scanIntent=new Intent(BreedDetailsActivity.this, ScanPictureActivity.class);
                scanIntent.putStringArrayListExtra("pictures",mList);
                startActivity(scanIntent);
            }
        });
    }


    @OnClick({R.id.convenientBanner, R.id.tv_address, R.id.iv_call, R.id.tv_total_conment, R.id.tv_save})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.convenientBanner:

                break;
            case R.id.tv_address:

                break;
            case R.id.iv_call:
                showRequestPhone();
                break;
            case R.id.tv_total_conment:
                FarmToastUtils.show("全部评论");
                break;
            case R.id.tv_save:
                AppManager.jump(BreedingPlanActivity.class,Constant.FARM_PRODUCT,pdEntity);
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
                        if (ActivityCompat.checkSelfPermission(BreedDetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(BreedDetailsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, RESULT_PHONE);
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
        intent.setData(Uri.parse("tel:" + pdEntity.farm.phone));
        startActivity(intent);
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
