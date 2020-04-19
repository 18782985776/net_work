package com.jobnew.farm.module.farm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.AddressBean;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.FarmRepository;
import com.jobnew.farm.entity.plan.CommitItemEntity;
import com.jobnew.farm.entity.plan.CropEntity;
import com.jobnew.farm.entity.plan.CommitOrderEntity;
import com.jobnew.farm.entity.plan.OrderEntity;
import com.jobnew.farm.entity.plan.PlanInitDataEntity;
import com.jobnew.farm.entity.ProductDetailsEntity;
import com.jobnew.farm.entity.plan.ProductProcessEntity;
import com.jobnew.farm.entity.plan.RecommendSchemeEntity;
import com.jobnew.farm.entity.plan.SchemeEntity;
import com.jobnew.farm.entity.plan.SignboardEntity;
import com.jobnew.farm.entity.plan.StewardEntity;
import com.jobnew.farm.module.farm.activity.farmActivity.LoveDonationActivity;
import com.jobnew.farm.module.personal.activity.AddressManage;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.utils.SPUtils;
import com.jobnew.farm.utils.ScreenToolsUtils;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.QuantityHelper;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wufengqiao on 2017/5/23.
 * function: 租地种植订单（种植计划）
 */

public class PlantingPlanAcitivity extends BaseActivity {

    private static final int CROP = 101;
    private static final int STEWARD = 102;
    private static final int SIGNBOARD = 103;
    private static final int ADDRESS = 104;
    private static final int DONATE = 105;
    private static final int SCHEME = 106;
    private static final int PROCESS = 107;

    //选择种子
    @BindView(R.id.stv_select_seed)
    SuperTextView mStvSelectSeed;

    //种植计划
    @BindView(R.id.stv_planting_scheme)
    SuperTextView mStvPlantingScheme;
    @BindView(R.id.tv_planting_scheme)
    TextView mTvPlantingScheme;

    //标识牌
    @BindView(R.id.stv_signboard)
    SuperTextView mStvSignboard;
    @BindView(R.id.tv_signboard)
    TextView mTvSignboard;

    //执行管家
    @BindView(R.id.stv_execution_steward)
    SuperTextView mStvSteward;

    //摄像头
    @BindView(R.id.stv_monitor)
    SuperTextView mStvMonitor;

    //配送
    @BindView(R.id.stv_distribution)
    SuperTextView mStvDistribution;

    //配送地址
    @BindView(R.id.stv_distribution_addr)
    SuperTextView mStvDistributionAddr;

    //爱心捐赠
    @BindView(R.id.stv_donate)
    SuperTextView mStvDonate;

    //产品加工
    @BindView(R.id.Stv_product_processing)
    SuperTextView mStvProcessing;
    //
    @BindView(R.id.tv_product_processing)
    TextView mTvProcessing;

    //保险
    @BindView(R.id.stv_insurance)
    SuperTextView mStvInsurance;
    //总价
    @BindView(R.id.tv_total_price)
    TextView mTvTotalPrice;

    //确定
    @BindView(R.id.tv_save)
    TextView mTvSave;

    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_unit_price)
    TextView tvUnitPrice;
    @BindView(R.id.et_name1)
    EditText etName1;
    @BindView(R.id.et_name2)
    EditText etName2;
    @BindView(R.id.et_name3)
    EditText etName3;
    @BindView(R.id.tv_distributio_price)
    TextView tvDistributioPrice;
    @BindView(R.id.tv_select_seed)
    TextView tvSeed;
    @BindView(R.id.quantity_area)
    View areaView;
    @BindView(R.id.quantity_time)
    View cycleView;
    @BindView(R.id.tv_crop_unit)
    TextView tvCropUnit;

    private ImageView mStvIvDistAddr;
    private ImageView mStvIvDonate;
    private StewardEntity mStewardEntity;
    private AddressBean mAddressEntity;
    private ProductDetailsEntity farmLandEntity;  //土地实体
    private CropEntity cropEntity;  //种子实体
    private DecimalFormat mDecimalFormat;
    private QuantityHelper numberHelper; //租地面积
    private QuantityHelper cycleHelper; //租地时间
    private int defaultNumber;  //种子数量
    private ArrayList<ProductProcessEntity> processEntityList; //产品加工集合
    private CommitOrderEntity commitOrderEntity;  //提交订单数据模型
    private SignboardEntity mSignboardEntity; //标志牌
    private ArrayList<SchemeEntity> schemeList; //方案选项
    private boolean isDefultScheme = true; //是否使用默认方案
    public double mSchemePrice = 0.00;
    private double mFreight;  //配送单价
    private int stock;

    @Override
    protected int getLayout() {
        return R.layout.activity_planting_plan;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("租地种植订单", true);
        commitOrderEntity = new CommitOrderEntity();
        numberHelper = new QuantityHelper(areaView, 1, 9999);
        cycleHelper = new QuantityHelper(cycleView, 1, 9999);
        mDecimalFormat = new DecimalFormat("0.00");

        initIntent();
        initData();
        initDistView();
        numberHelper.setOnCountChangeListener(new QuantityHelper.OnCountChangeListener() {
            @Override
            public void onCountChange(int count) {
                setLandTotalPrice();
                changeTotalPrice();
                if (count > stock) {
                    FarmToastUtils.show("超出面积，剩余土地面积:" + stock + "㎡");
                }
            }
        });
        cycleHelper.setOnCountChangeListener(new QuantityHelper.OnCountChangeListener() {
            @Override
            public void onCountChange(int count) {
                setLandTotalPrice();
                changeTotalPrice();
            }
        });
        changeTotalPrice();
    }


    /**
     * 初始化数据
     */
    private void initData() {

        setCropInfor();
        if (farmLandEntity != null) {
            setLandTotalPrice();
            tvUnitPrice.setText("(¥" + mDecimalFormat.format(farmLandEntity.price) + "/㎡)");
        }
    }

    /**
     * 设置作物信息
     */
    private void setCropInfor() {
        if (defaultNumber != -1 && cropEntity != null) {
            int area = (int) Math.ceil(new BigDecimal(defaultNumber).divide(new BigDecimal(cropEntity.cname)).doubleValue());
            numberHelper.setCount(area);
            tvSeed.setText(cropEntity.name + " X" + defaultNumber);
            mStvSelectSeed.setRightString("¥" + mDecimalFormat.format(cropEntity.price * defaultNumber));
            cycleHelper.setCount(commitOrderEntity.cycleTime);
        }
    }

    private void initIntent() {
        Intent intent = getIntent();
        farmLandEntity = (ProductDetailsEntity) intent.getSerializableExtra(Constant.FARM_PRODUCT);
        cropEntity = (CropEntity) intent.getSerializableExtra(Constant.CROP_SEED);
        defaultNumber = intent.getIntExtra(Constant.NUMBER, 1);
        stock = intent.getIntExtra(Constant.STOCK, -1);
        commitOrderEntity.farmId = farmLandEntity.farmId;
        commitOrderEntity.isDefaultTemplate = true;
        commitOrderEntity.cycleTime = cropEntity.cycleTime;
    }

    /**
     * 设置土地信息
     */
    private void setLandTotalPrice() {
        String totalPrice = mDecimalFormat.format(farmLandEntity.price * numberHelper.getCount() * cycleHelper.getCount());
        tvPrice.setText("¥" + totalPrice);

    }

    /**
     * 初始化点击配送地址相关控件
     */
    private void initDistView() {
        //配送地址控件
        TextView mTvRightm = (TextView) findViewById(mStvDistribution.getViewId(4));
        mTvRightm.setMaxLines(1);
        mTvRightm.setEllipsize(TextUtils.TruncateAt.MIDDLE);

        mStvDistribution.setSelected(false);
        setDistVisib(mStvDistribution.isSelected());

        //配送地址选择按钮
        mStvIvDistAddr = (ImageView) mStvDistributionAddr.getView(6);
        //爱心捐赠选择按钮
        mStvIvDonate = (ImageView) mStvDonate.getView(6);
        int padding = ScreenToolsUtils.dp2px(10);
        mStvIvDistAddr.setPadding(padding, padding, padding, padding);
        mStvIvDonate.setPadding(padding, padding, padding, padding);

        mStvIvDistAddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAddressEntity == null) {
                    Intent intent = new Intent(PlantingPlanAcitivity.this, AddressManage.class);
                    intent.putExtra(Constant.ADDRESS_MANAGE, ADDRESS);
                    startActivityForResult(intent, ADDRESS);
                } else {
                    mStvDistributionAddr.setSelected(true);
                    mStvDonate.setSelected(false);
                }
            }
        });
        mStvIvDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStvDistributionAddr.setSelected(false);
                mStvDonate.setSelected(true);
            }
        });

    }

    public void setDistVisib(boolean isVisib) {
        mStvDistributionAddr.setVisibility(isVisib ? View.VISIBLE : View.GONE);
        mStvDonate.setVisibility(isVisib ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void loadData() {
        super.loadData();
        requestData();
    }

    /**
     * 获取总价
     */
    private void changeTotalPrice() {
        double totalPrice = 0.00;
        //土地价格
        if (farmLandEntity != null) {
            totalPrice += farmLandEntity.price * numberHelper.getCount() * cycleHelper.getCount();
        }

        //种子的价格
        if (cropEntity != null) {
            totalPrice += cropEntity.price * defaultNumber;
        }
        //方案的总价
        getSchemePrice();
        totalPrice += mSchemePrice;
        //监控价格

        //标识牌价格
        if (mSignboardEntity != null) {
            totalPrice += mSignboardEntity.price * 1;
        }
        //产品加工价格
        if (processEntityList != null) {
            for (int i = 0; i < processEntityList.size(); i++) {
                totalPrice += processEntityList.get(i).price * defaultNumber;
            }
        }
        //作物保险

        //配送价格
        if (mStvDistribution.isSelected()) {
            totalPrice += mFreight * defaultNumber;
        }
        mTvTotalPrice.setText("¥" + mDecimalFormat.format(totalPrice));
    }

    /**
     * 请求初始化信息
     */
    private void requestData() {
        FarmRepository.getIns().getPlanData(cropEntity.id)
                .subscribe(new DefaultSubscriber<PlanInitDataEntity>(this, "初始化数据") {
                    @Override
                    public void _onNext(PlanInitDataEntity entity) {
                        schemeList = entity.artOrderItemResults;
                        setSchemeInfo();
                        if (mSignboardEntity == null) {
                            mSignboardEntity = new SignboardEntity();
                        }
                        mSignboardEntity.id = entity.defaultSignboardId;
                        mSignboardEntity.name = entity.defaultSignboardName;
                        mSignboardEntity.price = entity.defaultSignboardPrice;
                        setSignboardInfo();
                        mAddressEntity = new AddressBean();
                        if (mAddressEntity.getId() < 1) {
                            mAddressEntity.setId(entity.defaultUserAddressId);
                            mStvDistributionAddr.setRightString(entity.defaultUserAddressTitle);
                            mStvDistributionAddr.setSelected(true);
                            mStvDonate.setSelected(false);
                        }
                        mFreight = entity.freight;
                        commitOrderEntity.isDefaultTemplate = true;
                        isDefultScheme = true;
                        changeAddressPrice();
                        changeTotalPrice();
                    }
                });
    }

    /**
     * 得到方案总价
     */
    private void getSchemePrice() {
        mSchemePrice = 0.00;
        if (schemeList == null) {
            return;
        }
        for (int i = 0; i < schemeList.size(); i++) {
            SchemeEntity entity = schemeList.get(i);
            switch (entity.ctype) {
                case 0:
                    if (entity.checked) {
                        mSchemePrice += entity.price * entity.count * defaultNumber;
                    }
                    break;
                case 1:
                    if (commitOrderEntity.isDefaultTemplate) {
                        if (entity.checked) {
                            mSchemePrice += entity.price * entity.count * defaultNumber;
                        }
                    } else {
                        if (entity.checked) {
                            for (int j = 0; j < entity.entities.size(); j++) {
                                if (entity.entities.get(j).checked) {
                                    mSchemePrice += entity.entities.get(j).price * entity.count * defaultNumber;
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    if (entity.checked) {
                        mSchemePrice += entity.price * defaultNumber;
                    }
                    break;
                case 4:
                    if (entity.checked) {
                        mSchemePrice += entity.count * (int) ((entity.duration * 1.00) / entity.interval + 0.5) * entity.price;
                    }
                    break;
            }
        }
    }

    /**
     * 设置标志牌信息
     */
    private void setSignboardInfo() {
        if (mSignboardEntity != null && mSignboardEntity.id > 0) {
            mStvSignboard.setRightString("¥" + mSignboardEntity.price);
            mTvSignboard.setText(mSignboardEntity.name);
        } else {
            mStvSignboard.setRightString("¥0.00");
            mTvSignboard.setText("无");
        }
    }

    /**
     * 设置方案信息
     */
    private void setSchemeInfo() {
        getSchemePrice();
        mStvPlantingScheme.setRightString("¥" + mDecimalFormat.format(mSchemePrice));
        mTvPlantingScheme.setText(isDefultScheme ? "省心方案" : "自定义方案");
    }

    @OnClick({R.id.stv_select_seed, R.id.stv_planting_scheme, R.id.stv_execution_steward, R.id.stv_signboard, R.id.stv_monitor, R.id.stv_distribution, R.id.stv_donate, R.id.stv_distribution_addr, R.id.Stv_product_processing, R.id.stv_insurance, R.id.tv_save})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.stv_select_seed: //选择种子
                intent = new Intent(this, SelectCropActivity.class);
                intent.putExtra(Constant.FARM_ID, farmLandEntity.farm.id);
                if (cropEntity != null) {
                    intent.putExtra(Constant.SEED_ID, cropEntity.id);
                }
                intent.putExtra(Constant.NUMBER, defaultNumber);
                startActivityForResult(intent, CROP);
                break;
            case R.id.stv_planting_scheme: //种植方案
                intent = new Intent(this, PlantingSchemeActivity.class);
                if (commitOrderEntity.isDefaultTemplate) {
                    intent.putExtra(Constant.DURATION, cropEntity.cycleTime);
                } else {
                    intent.putExtra(Constant.SCHEME_TYPE, isDefultScheme);
                    intent.putExtra(Constant.SCHEME, schemeList);
                    if (commitOrderEntity.cycleTime > 0) {
                        intent.putExtra(Constant.DURATION, commitOrderEntity.cycleTime);
                    } else {
                        intent.putExtra(Constant.DURATION, cropEntity.cycleTime);
                    }
                }
                intent.putExtra(Constant.SEED_ID, cropEntity.id);
                intent.putExtra(Constant.NUMBER, defaultNumber);
                startActivityForResult(intent, SCHEME);
                break;

            case R.id.stv_execution_steward: //执行管家
                intent = new Intent(this, StewardActivity.class);
                intent.putExtra(Constant.FARM_ID, farmLandEntity.farm.id);
                startActivityForResult(intent, STEWARD);
                break;

            case R.id.stv_signboard: //标识牌
                intent = new Intent(this, SignboardActivity.class);
                intent.putExtra(Constant.TYPE, 0);
                intent.putExtra(Constant.FARM_ID, farmLandEntity.farm.id);
                startActivityForResult(intent, SIGNBOARD);
                break;

            case R.id.stv_monitor:  //监控
                mStvMonitor.setSelected(!mStvMonitor.isSelected());
                changeTotalPrice();
                break;
            case R.id.stv_distribution: //配送
                mStvDistribution.setSelected(!mStvDistribution.isSelected());
                setDistVisib(mStvDistribution.isSelected());
                changeTotalPrice();
                break;
            case R.id.stv_distribution_addr: //配送地址
                intent = new Intent(this, AddressManage.class);
                intent.putExtra(Constant.ADDRESS_MANAGE, ADDRESS);
                startActivityForResult(intent, ADDRESS);
                break;
            case R.id.stv_donate: //爱心捐赠地址
                intent = new Intent(this, LoveDonationActivity.class);
                intent.putExtra(Constant.ADDRESS_MANAGE, DONATE);
                startActivityForResult(intent, DONATE);
                break;
            case R.id.Stv_product_processing:   //产品加工
                intent = new Intent(this, ProductProcessActivity.class);
                intent.putExtra(Constant.PRODUCT_ID, cropEntity.id);
                intent.putExtra(Constant.NUMBER, defaultNumber);
                intent.putParcelableArrayListExtra(Constant.PRODUCT_PROCESS, processEntityList);
                startActivityForResult(intent, PROCESS);
                break;
            case R.id.stv_insurance:
                AppManager.jump(InsuranceActivity.class);
                break;
            case R.id.tv_save:
                if (setCommitOrder()) {
                    commitOrderEntity.type = "plant";
                    commitOrder(commitOrderEntity);
                }
                break;
        }
    }

    private boolean setCommitOrder() {
        if (mSignboardEntity == null) {
            FarmToastUtils.show("请选择标识牌");
            return false;
        }
        if (numberHelper.getCount() > stock) {
            FarmToastUtils.show("土地面积不足，剩余土地面积:" + stock + "㎡");
            return false;
        }
        String name1 = etName1.getText().toString();
        String name2 = etName2.getText().toString();
        String name3 = etName3.getText().toString();
        if (TextUtils.isEmpty(name1) || TextUtils.isEmpty(name2) || TextUtils.isEmpty(name3)) {
            FarmToastUtils.show("请填写名称");
            etName1.requestFocus();
            return false;
        }
        commitOrderEntity.name = name1 + "为" + name2 + "种的" + name3;
        commitOrderEntity.clear();
        if (!commitOrderEntity.isDefaultTemplate) {
            getSchemeList();
        }
        //土地产品
        CommitItemEntity entity = new CommitItemEntity();
        entity.productId = farmLandEntity.id;
        entity.type = "MAJOR";
        entity.quantity = numberHelper.getCount();
        entity.duration = cycleHelper.getCount();
        commitOrderEntity.add(entity);

        //作物种子
        CommitItemEntity entity2 = new CommitItemEntity();
        entity2.productId = cropEntity.id;
        entity2.type = "MINOR";
        entity2.quantity = defaultNumber;
        commitOrderEntity.add(entity2);

        //标识牌
        if (mSignboardEntity.id > 0) {
            CommitItemEntity entity3 = new CommitItemEntity();
            entity3.productId = mSignboardEntity.id;
            entity3.type = "PRODUCT";
            entity3.quantity = 1;
            commitOrderEntity.add(entity3);
        }
        if (processEntityList != null) {
            for (int i = 0; i < processEntityList.size(); i++) {
                ProductProcessEntity productProcessEntity = processEntityList.get(i);
                CommitItemEntity entity4 = new CommitItemEntity();
                entity4.productId = productProcessEntity.id;
                entity4.quantity = 1;
                entity4.type = "PLAN_PRODUCT";
                commitOrderEntity.add(entity4);
            }
        }
        if (mStvDistribution.isSelected()) {
            commitOrderEntity.userAddressId = mAddressEntity.getId();
            commitOrderEntity.isDelivery = true;
        } else {
            commitOrderEntity.userAddressId = -1;
            commitOrderEntity.isDelivery = false;
        }
        if (mStewardEntity == null) {
            commitOrderEntity.farmManagerId = -1;
        } else {
            commitOrderEntity.farmManagerId = mStewardEntity.id;
        }
        return true;
    }

    /**
     * 提交订单
     */
    private void commitOrder(CommitOrderEntity entity) {
        FarmRepository.getIns().commitOrder(entity).subscribe(new DefaultSubscriber<OrderEntity>(this, "生成订单") {
            @Override
            public void _onNext(OrderEntity entity) {
                if (entity == null || entity.orderItems == null || entity.orderItems.size() == 0) {
                    FarmToastUtils.show("服务器异常，请稍后再试");
                    return;
                }
                Intent intent = new Intent(PlantingPlanAcitivity.this, ConfirmOrderActivity.class);
                intent.putExtra(Constant.ORDER, entity);
                intent.putExtra(Constant.ORDER_BODY, "网农工社-租地种植");
                intent.putExtra(Constant.NAME, farmLandEntity.farm.name);
                intent.putExtra(Constant.FARM_IMG, farmLandEntity.farm.img);
                intent.putExtra(Constant.TYPE, "NORMAL");
                SPUtils.put(Constant.PAY_TYPE, 0);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case CROP://选择种子
                CropEntity entity = (CropEntity) data.getSerializableExtra(Constant.CROP_SEED);
                defaultNumber = data.getIntExtra(Constant.NUMBER, defaultNumber);
                if (entity != null) {
                    if (entity.id != cropEntity.id) {
                        cropEntity = entity;
                        mTvProcessing.setText("无");
                        mStvProcessing.setRightString("¥0.00");
                        processEntityList = null;
                        if (schemeList != null) {  //改变集合里种植周期
                            for (int i = 0; i < schemeList.size(); i++) {
                                switch (schemeList.get(i).ctype) {
                                    case 3:
                                        schemeList.get(i).duration = cropEntity.cycleTime;
                                        break;
                                }
                            }
                        }
                        commitOrderEntity.cycleTime = cropEntity.cycleTime;
                        requestData();
                    } else {
                        setProcessTotalPrice();
                        setSchemeInfo();
                        changeAddressPrice();
                        changeTotalPrice();
                    }
                    setCropInfor();
                }
                break;
            case SCHEME://方案
                ArrayList<SchemeEntity> arrayList = data.getParcelableArrayListExtra(Constant.SCHEME);
                isDefultScheme = data.getBooleanExtra(Constant.SCHEME_TYPE, true);
                if (arrayList == null) {
                    commitOrderEntity.isDefaultTemplate = true;
                    isDefultScheme = true;
                } else {
                    schemeList = arrayList;
                    if (schemeList != null) {  //改变集合里种植周期
                        for (int i = 0; i < schemeList.size(); i++) {
                            switch (schemeList.get(i).ctype) {
                                case 3:
                                    commitOrderEntity.cycleTime = schemeList.get(i).duration;
                                    break;
                            }
                        }
                    }
                    commitOrderEntity.isDefaultTemplate = false;
                }
                setSchemeInfo();
                changeTotalPrice();
                break;
            case STEWARD: //选择管家
                mStewardEntity = (StewardEntity) data.getSerializableExtra(Constant.STEWARD);

                if (mStewardEntity != null) {
                    mStvSteward.setRightString(mStewardEntity.name);
                } else {
                    mStvSteward.setRightString("系统自动分配");
                }
                break;
            case SIGNBOARD: //选择标识牌
                SignboardEntity signboardEntity = (SignboardEntity) data.getSerializableExtra(Constant.SIGNBOARD);
                if (signboardEntity != null) {
                    mSignboardEntity = signboardEntity;
                    setSignboardInfo();
                }
                changeTotalPrice();
                break;
            case ADDRESS:  //配送地址
                AddressBean addressBean = (AddressBean) data.getSerializableExtra(Constant.ADDRESS_MANAGE);
                if (addressBean != null) {
                    mAddressEntity = addressBean;
                    String[] area = mAddressEntity.getArea().getMergerName().split(",");
                    mStvDistributionAddr.setRightString(area[0] + area[1] + mAddressEntity.getArea().getName() + mAddressEntity.getAddress());
                    mStvDistributionAddr.setSelected(true);
                    mStvDonate.setSelected(false);
                }
                break;
            case DONATE: //爱心捐赠地址
                int donateId = data.getIntExtra(Constant.ADDRESS_ID, -1);
                String donate = data.getStringExtra(Constant.ADDRESS_MANAGE);
                mStvDonate.setRightString(donate);
                if (mAddressEntity == null) {
                    mAddressEntity = new AddressBean();
                }
                mAddressEntity.setId(donateId);
                mStvDistributionAddr.setSelected(false);
                mStvDonate.setSelected(true);
                break;
            case PROCESS:  //产品加工地址
                processEntityList = data.getParcelableArrayListExtra(Constant.PRODUCT_PROCESS);
                if (setProcessTotalPrice()) return;
                changeTotalPrice();
                break;
        }
    }

    /**
     * 地址价格
     */
    private void changeAddressPrice() {
        mStvDistribution.setRightString("(¥" + mDecimalFormat.format(mFreight) + "/" + cropEntity.unitName + ")");
        tvDistributioPrice.setText("¥" + mDecimalFormat.format(mFreight * defaultNumber));
    }

    private boolean setProcessTotalPrice() {
        if (processEntityList == null) {
            return true;
        }
        if (processEntityList.size() == 0) {
            mTvProcessing.setText("无");
            mStvProcessing.setRightString("¥0.00");
            return false;
        }
        String name = "";
        double price = 0;
        for (int i = 0; i < processEntityList.size(); i++) {
            if (i == 0) {
                name = processEntityList.get(i).farmArtName;
            }
            if (i == 1) {
                name = name + "、" + processEntityList.get(i).farmArtName;
            }

            price += processEntityList.get(i).price;
        }
        mTvProcessing.setText(name);
        mStvProcessing.setRightString("¥" + mDecimalFormat.format(price * defaultNumber));
        return false;
    }


    /**
     * 获取方案各项集合
     *
     * @return
     */
    private void getSchemeList() {
        for (int i = 0; i < schemeList.size(); i++) {
            SchemeEntity entity = schemeList.get(i);
            CommitItemEntity commitItemBean = new CommitItemEntity();
            commitItemBean.type = "PLAN";
            commitItemBean.quantity = entity.count;
            commitItemBean.countItemModels = entity.countItemModels;
            switch (entity.ctype) {
                case 1:
                    for (int j = 0; j < entity.entities.size(); j++) {
                        if (entity.checked) {
                            if (entity.entities.get(j).checked) {
                                commitItemBean.productId = entity.entities.get(j).artProductId;
                                commitOrderEntity.add(commitItemBean);
                            }
                        }
                    }
                    break;
                case 2:
                    if (commitItemBean.quantity < 1) {
                        commitItemBean.quantity = 1;
                    }
                case 0:
                    commitItemBean.productId = entity.artProductId;
                    if (entity.checked) {
                        commitOrderEntity.add(commitItemBean);
                    }
                    break;
                case 4:
                    commitItemBean.productId = entity.artProductId;
                    commitItemBean.duration = entity.duration;
                    commitItemBean.interval = entity.interval;
                    if (entity.count > 0 && entity.checked) {
                        commitOrderEntity.add(commitItemBean);
                    }
                    break;
            }
        }
    }

}
