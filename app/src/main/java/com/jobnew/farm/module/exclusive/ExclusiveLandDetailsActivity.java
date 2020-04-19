package com.jobnew.farm.module.exclusive;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.model.Text;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.ExclusiveLandRepository;
import com.jobnew.farm.entity.ExclusiveLandDetailsEntity;
import com.jobnew.farm.entity.FarmEntity;
import com.jobnew.farm.entity.exclusive.ExclusiveLandEntity;
import com.jobnew.farm.entity.plan.CountItemEntity;
import com.jobnew.farm.module.farm.activity.LandDetailsActivity;
import com.jobnew.farm.module.farm.activity.ScanPictureActivity;
import com.jobnew.farm.module.farm.activity.farmActivity.FarmDetailsActivity;
import com.jobnew.farm.module.personal.activity.HelpActivity;
import com.jobnew.farm.module.personal.adapter.NetworkImageHolderView;
import com.jobnew.farm.utils.AlertUtil;
import com.jobnew.farm.utils.GlideManager;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.QuantityHelper;
import com.jobnew.farm.widget.TitleBarHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wufengqiao on 2017/8/14.
 * function：
 * desc：
 */

public class ExclusiveLandDetailsActivity extends BaseActivity {
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.tv_land_address)
    TextView tvLandAddress;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.iv_call)
    ImageView ivCall;
    @BindView(R.id.tv_farm_affiliation)
    TextView tvFarmAffiliation;
    @BindView(R.id.tv_farm_name)
    TextView tvFarmName;
    @BindView(R.id.iv_farm)
    ImageView ivFarm;
    @BindView(R.id.tv_area_title)
    TextView tvAreaTitle;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_unit_price_title)
    TextView tvUnitPriceTitle;
    @BindView(R.id.tv_unit_price)
    TextView tvUnitPrice;
    @BindView(R.id.tv_land_fit_title)
    TextView tvLandFitTitle;
    @BindView(R.id.tv_land_fit)
    TextView tvLandFit;
    @BindView(R.id.iv_land_fit)
    ImageView ivLandFit;
    @BindView(R.id.tv_intro)
    TextView tvInfro;
    @BindView(R.id.lease_time)
    View leaseTime;
    @BindView(R.id.tv_date_unit)
    TextView tvDateUnit;
    @BindView(R.id.tv_total_price_name)
    TextView tvTotalPriceName;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.iv_agreement)
    ImageView ivAgreement;
    @BindView(R.id.tv_use)
    TextView tvUse;
    @BindView(R.id.rg_use)
    RadioGroup rgUse;
    @BindView(R.id.rb_plant)
    RadioButton rbPlant;
    @BindView(R.id.rb_breed)
    RadioButton rbBreed;
    @BindView(R.id.rb_activity)
    RadioButton rbActivity;
    @BindView(R.id.rb_other)
    RadioButton rbOther;
    @BindView(R.id.tv_use_intro)
    EditText tvUseIntro;
    private ArrayList<String> mList;
    private int landId;
    private QuantityHelper leaseTimeHelper;
    private ExclusiveLandDetailsEntity mEntity;

    private static final int RESULT_PHONE = 201;

    @Override
    protected int getLayout() {
        return R.layout.activity_exclusive_land_details;
    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        setTitle("专属土地详情", true);
        titleBar.setRightTextDrawable(R.drawable.selector_collection, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = titleBar.getRightTextView();
                view.setSelected(!view.isSelected());
            }
        });
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initIntent();
        ivAgreement.setSelected(true);
        loading();
        requestData();
    }

    private void initIntent() {
        Intent intent = getIntent();
        landId = intent.getIntExtra(Constant.FARM_ID, -1);

    }


    private void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("longitude", Constant.LONGITUDE + "");
        map.put("latitude", Constant.LATITUDE + "");
        ExclusiveLandRepository.getIns().getExclusiveLandDetails(landId, map)
                .subscribe(new DefaultSubscriber<ExclusiveLandDetailsEntity>(this, false) {
                    @Override
                    public void _onNext(ExclusiveLandDetailsEntity entity) {
                        mEntity = entity;
                        List<ExclusiveLandDetailsEntity.ProductImagesBean> list = entity.productImages;
                        if (list != null) {
                            mList = new ArrayList<>();
                            for (int i = 0; i < list.size(); i++) {
                                mList.add(list.get(i).imgUrl);
                            }
                        }
                        initBanner();
                        setData(entity);
                        content();
                    }
                });
    }

    private void setData(ExclusiveLandDetailsEntity entity) {
        FarmEntity farm = entity.farm;
        GlideManager.loadImg(entity.categoryIcon, ivLandFit);
        tvLandAddress.setText(farm.province + farm.city + farm.area + farm.address);
        tvDistance.setText("距离" + (int) (entity.distance + 0.5) + "KM");
        tvFarmName.setText(entity.farm.name);
        tvArea.setText(entity.stock + "㎡");
        tvUnitPrice.setText("¥" + new DecimalFormat("0.00").format(entity.price));
        tvLandFit.setText(entity.categoryName);
        tvInfro.setText(entity.intro);
        leaseTimeHelper = new QuantityHelper(leaseTime, 1, 9999);
        leaseTimeHelper.setOnCountChangeListener(new QuantityHelper.OnCountChangeListener() {
            @Override
            public void onCountChange(int count) {
                tvTotalPrice.setText("¥" + new DecimalFormat("0.00").format(mEntity.price * leaseTimeHelper.getCount() * mEntity.stock));
            }
        });
        tvTotalPrice.setText("¥" + new DecimalFormat("0.00").format(mEntity.price * leaseTimeHelper.getCount() * mEntity.stock));
        rbPlant.setChecked(true);
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
                Intent scanIntent = new Intent(ExclusiveLandDetailsActivity.this, ScanPictureActivity.class);
                scanIntent.putStringArrayListExtra("pictures", mList);
                startActivity(scanIntent);
            }
        });
    }

    @OnClick({R.id.iv_agreement, R.id.tv_agreement, R.id.tv_save, R.id.iv_call, R.id.tv_land_address, R.id.rl_farm_affiliation})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tv_land_address:
                showMsg("地址");
                break;
            case R.id.rl_farm_affiliation:
                intent = new Intent(this, FarmDetailsActivity.class);
                intent.putExtra("farmId", mEntity.farmId);
                intent.putExtra("farmName", mEntity.farm.name);
                startActivity(intent);
                break;
            case R.id.iv_agreement:
                ivAgreement.setSelected(!ivAgreement.isSelected());
                break;
            case R.id.tv_agreement:
                intent = new Intent(this, HelpActivity.class);
                intent.putExtra(Constant.CATEGORY_SN, "LEASECLAUSE");
                startActivityForResult(intent, 104);
                break;
            case R.id.iv_call:
                showRequestPhone();
                break;
            case R.id.tv_save:
                if (leaseTimeHelper.getCount() < 1) {
                    showMsg("请选择租赁天数");
                    return;
                }
                if (!ivAgreement.isSelected()) {
                    showMsg("你还没有同意租赁条款");
                    return;
                }
                intent = new Intent();
                ExclusiveLandEntity entity = new ExclusiveLandEntity();
                entity.stock = mEntity.stock;
                entity.name = mEntity.name;
                entity.pImg = mEntity.pImg;
                entity.price = mEntity.price;
                entity.id = mEntity.id;
                intent.putExtra(Constant.EXCLUSIVE_LAND, entity);
                intent.putExtra(Constant.FARM_NAME, mEntity.farm.name);
                intent.putExtra(Constant.FARM_IMG, mEntity.farm.img);
                intent.putExtra(Constant.NUMBER, leaseTimeHelper.getCount());
                intent.putExtra("memo", tvUseIntro.getText().toString());
                int checkedRadioButtonId = rgUse.getCheckedRadioButtonId();
                String use = "";
                switch (checkedRadioButtonId) {
                    case R.id.rb_plant:
                        use = "种植";
                        break;
                    case R.id.rb_breed:
                        use = "养殖";
                        break;
                    case R.id.rb_activity:
                        use = "活动";
                        break;
                    case R.id.rb_other:
                        use = "其它";
                        break;
                }
                intent.putExtra("use", use);
                AppManager.jump(ConfirmExclusiveLandOrderActivity.class, intent);
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
                        if (ActivityCompat.checkSelfPermission(ExclusiveLandDetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(ExclusiveLandDetailsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, RESULT_PHONE);
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
        intent.setData(Uri.parse("tel:" + mEntity.farm.phone));
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            ivAgreement.setSelected(true);
        }
    }
}
