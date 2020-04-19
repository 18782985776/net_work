package com.jobnew.farm.module.personal.activity.MyFarm;

import android.content.Intent;
import android.media.TimedText;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.PersonMyFarmRepository;
import com.jobnew.farm.entity.myfarm.PlanDetailsEntity;
import com.jobnew.farm.utils.SPUtils;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.TitleBarHelper;

import java.text.DecimalFormat;

import butterknife.BindView;

/**
 * Created by wufengqiao on 2017/7/10.
 * function：我的农场-查看计划
 * desc：
 */

public class PlantingPlanDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_major_name)
    TextView tvMajorName;
    @BindView(R.id.tv_breed_num)
    TextView tvBreedNum;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_unit_price)
    TextView tvUnitPrice;
    @BindView(R.id.ll_plant_info)
    LinearLayout llPlantInfo;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_crop_cycle)
    TextView tvCropCycle;
    @BindView(R.id.stv_land_name_card)
    SuperTextView stvLandNameCard;
    @BindView(R.id.tv_select_seed)
    TextView tvSelectSeed;
    @BindView(R.id.stv_select_seed)
    SuperTextView stvSelectSeed;
    @BindView(R.id.tv_planting_scheme)
    TextView tvPlantingScheme;
    @BindView(R.id.stv_planting_scheme)
    SuperTextView stvPlantingScheme;
    @BindView(R.id.stv_execution_steward)
    SuperTextView stvExecutionSteward;
    @BindView(R.id.stv_monitor)
    SuperTextView stvMonitor;
    @BindView(R.id.tv_signboard)
    TextView tvSignboard;
    @BindView(R.id.stv_signboard)
    SuperTextView stvSignboard;
    @BindView(R.id.tv_product_processing)
    TextView tvProductProcessing;
    @BindView(R.id.Stv_product_processing)
    SuperTextView StvProductProcessing;
    @BindView(R.id.tv_insurance)
    TextView tvInsurance;
    @BindView(R.id.stv_insurance)
    SuperTextView stvInsurance;
    @BindView(R.id.tv_distributio_price)
    TextView tvDistributioPrice;
    @BindView(R.id.stv_distribution)
    SuperTextView stvDistribution;
    @BindView(R.id.tl_address)
    RelativeLayout tlAddress;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_intro)
    TextView tvIntro;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    private int orderId;
    private DecimalFormat mDecimalFormat;
    private int type;
    public int productId;
    public int productNumber;

    @Override
    protected int getLayout() {
        return R.layout.activity_planting_plan_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        type = getIntent().getIntExtra(Constant.TYPE, 0);
        orderId = getIntent().getIntExtra(Constant.ORDER_ID, -1);
        SPUtils.put(Constant.TYPE,type);
        mDecimalFormat = new DecimalFormat("0.00");
        switch (type) {
            case 1:
                loading();
                requestData();
                break;
            case 2:
                loading();
                requestData();
                initBreedData();
                break;
            default:
                error("");
                break;
        }
    }

    private void initBreedData() {
        tvMajorName.setText("养殖数量");
        tvBreedNum.setVisibility(View.VISIBLE);
        llPlantInfo.setVisibility(View.GONE);
        stvLandNameCard.setLeftString("动物名片");
        stvSelectSeed.setVisibility(View.GONE);
        stvPlantingScheme.setLeftString("养殖方案");
        stvInsurance.setLeftString("动物保险");
        stvMonitor.setVisibility(View.GONE);
    }

    /**
     * 请求数据
     */
    private void requestData() {
        PersonMyFarmRepository.getIns().getPlan(orderId)
                .subscribe(new DefaultSubscriber<PlanDetailsEntity>(this, false) {
                    @Override
                    public void _onNext(PlanDetailsEntity entity) {
                        content();
                        setData(entity);
                    }
                });
    }

    private void setData(PlanDetailsEntity entity) {
        if (type == 1) {
            productNumber = entity.minorProductQuantity;
            productId = entity.minorProductId;
            tvPrice.setText("¥" + mDecimalFormat.format(entity.majorProductPrice * entity.majorProductQuantity));
            tvUnitPrice.setText("(¥" + mDecimalFormat.format(entity.majorProductPrice) + "/㎡)");
            tvArea.setText(entity.majorProductQuantity + "㎡");
            tvCropCycle.setText(entity.cycleTime + "天");
            tvSelectSeed.setText(entity.minorProductName + "  X" + entity.minorProductQuantity);
            stvSelectSeed.setRightString("¥" + mDecimalFormat.format(entity.minorProductPrice * entity.minorProductQuantity));
            stvMonitor.setRightString(mDecimalFormat.format(entity.cameraPrice));

//            stvDistribution.setRightString("(¥+" + entity.freight + "/" + entity.minorProductPrice + ")")
        } else {
            productNumber = entity.majorProductQuantity;
            productId = entity.majorProductId;
            tvBreedNum.setText(entity.majorProductName + "  X" + entity.majorProductQuantity);
            tvPrice.setText("¥" + mDecimalFormat.format(entity.majorProductPrice * entity.majorProductQuantity));
            tvUnitPrice.setText("(¥" + mDecimalFormat.format(entity.majorProductPrice) + "/" + entity.majorProductUnit + ")");

//            stvDistribution.setRightString("(¥+" + entity.freight + "/" + entity.majorProductUnit + ")")；
        }
        stvLandNameCard.setRightString(entity.orderName);
        stvPlantingScheme.setRightString("¥" + mDecimalFormat.format(entity.programTotalPrice));
        tvPlantingScheme.setText(true ? "省心方案" : "自定义方案");
        stvExecutionSteward.setRightString(entity.managerName);
        stvSignboard.setRightString("¥" + mDecimalFormat.format(entity.signboardPrice));
        tvSignboard.setText(entity.signboardName);
        StvProductProcessing.setRightString("¥" + mDecimalFormat.format(entity.processTotalPrice));
        tvProductProcessing.setText(entity.processName);
//        stvInsurance.setRightString("¥"+mDecimalFormat.format(entity.))
        tvDistributioPrice.setText("¥" + mDecimalFormat.format(entity.freight));
        if (TextUtils.isEmpty(entity.shipConsignee)) {
            tlAddress.setVisibility(View.GONE);
            tvIntro.setVisibility(View.GONE);
        } else {
            tvName.setText(entity.shipConsignee);
            tvPhone.setText(entity.shipPhone);
            tvAddress.setText(entity.shipAddress);
            if (TextUtils.isEmpty(entity.shipConsigneeInfo)) {
                tvIntro.setText(entity.shipConsigneeInfo);
            }
        }
        tvTotalPrice.setText("¥" + mDecimalFormat.format(entity.orderAmount));

    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        setTitle("租地种植", true);
        titleBar.setRightText("新任务", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(Constant.PRODUCT_ID, productId);
                intent.putExtra(Constant.NUMBER, productNumber);
                intent.putExtra(Constant.ORDER_ID, orderId);
                intent.putExtra(Constant.TYPE, "NORMAL");
                AppManager.jump(AddNewTaskAcitivity.class, intent);
            }
        });
    }
}
