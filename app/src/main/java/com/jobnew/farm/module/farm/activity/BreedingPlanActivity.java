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
import com.jobnew.farm.entity.plan.CommitOrderEntity;
import com.jobnew.farm.entity.plan.OrderEntity;
import com.jobnew.farm.entity.plan.PlanInitDataEntity;
import com.jobnew.farm.entity.ProductDetailsEntity;
import com.jobnew.farm.entity.plan.ProductProcessEntity;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wufengqiao on 2017/6/26.
 * function：养殖订单（养殖计划）
 * desc：
 */

public class BreedingPlanActivity extends BaseActivity {

    private static final int STEWARD = 102;
    private static final int SIGNBOARD = 103;
    private static final int ADDRESS = 104;
    private static final int DONATE = 105;
    private static final int SCHEME = 106;
    private static final int PROCESS = 107;


    //养殖方案
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
    @BindView(R.id.quantity_num)
    View numView;

    private ImageView mStvIvDistAddr;
    private ImageView mStvIvDonate;
    private StewardEntity mStewardEntity;
    private AddressBean mAddressEntity;
    private ProductDetailsEntity breedEntity;  //动物实体
    private DecimalFormat mDecimalFormat;
    private QuantityHelper numberHelper; //动物数量
    private ArrayList<ProductProcessEntity> processEntityList; //产品加工集合
    private CommitOrderEntity commitOrderEntity;  //提交订单数据模型
    private SignboardEntity mSignboardEntity; //标志牌
    private ArrayList<SchemeEntity> schemeList; //方案选项
    public double mSchemePrice = 0.00;
    private double mFreight;  //配送单价

    @Override
    protected int getLayout() {
        return R.layout.activity_breed_plan;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("养殖订单", true);
        commitOrderEntity = new CommitOrderEntity();
        numberHelper = new QuantityHelper(numView, 1, 9999);
        mDecimalFormat = new DecimalFormat("0.00");

        initIntent();
        initData();
        initDistView();
        numberHelper.setOnCountChangeListener(new QuantityHelper.OnCountChangeListener() {
            @Override
            public void onCountChange(int count) {
                setAnimalTotalPrice();
                changeTotalPrice();
                setSchemeInfo();
            }
        });
        changeTotalPrice();
    }


    /**
     * 初始化数据
     */
    private void initData() {
        if (breedEntity != null) {
            commitOrderEntity.farmId = breedEntity.farmId;
            commitOrderEntity.isDefaultTemplate = true;
            commitOrderEntity.cycleTime = breedEntity.cycleTime;
            setAnimalTotalPrice();
            tvUnitPrice.setText("(¥" + mDecimalFormat.format(breedEntity.price) + "/" + breedEntity.unitName + ")");
            etName3.setText(breedEntity.name);
        }
    }

    private void initIntent() {
        Intent intent = getIntent();
        breedEntity = (ProductDetailsEntity) intent.getSerializableExtra(Constant.FARM_PRODUCT);
    }

    /**
     * 设置动物总价
     */
    private void setAnimalTotalPrice() {
        String totalPrice = mDecimalFormat.format(breedEntity.price * numberHelper.getCount());
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
                    Intent intent = new Intent(BreedingPlanActivity.this, AddressManage.class);
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
        if (breedEntity != null) {
            totalPrice += breedEntity.price * numberHelper.getCount();
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
                totalPrice += processEntityList.get(i).price * numberHelper.getCount();
            }
        }
        //作物保险

        //配送价格
        if (mStvDistribution.isSelected()) {
            totalPrice += mFreight * numberHelper.getCount();
        }
        mTvTotalPrice.setText("¥" + mDecimalFormat.format(totalPrice));
    }

    /**
     * 请求初始化信息
     */
    private void requestData() {
        FarmRepository.getIns().getPlanData(breedEntity.id).subscribe(new DefaultSubscriber<PlanInitDataEntity>(this, "初始化数据") {
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
                mStvDistribution.setRightString("(¥" + mDecimalFormat.format(mFreight) + "/" + breedEntity.unitName + ")");
                tvDistributioPrice.setText("¥" + mDecimalFormat.format(mFreight * numberHelper.getCount()));
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
                        mSchemePrice += entity.price * entity.count * numberHelper.getCount();
                    }
                    break;
                case 1:
                    if (commitOrderEntity.isDefaultTemplate) {
                        if (entity.checked) {
                            mSchemePrice += entity.price * entity.count * numberHelper.getCount();
                        }
                    } else {
                        if (entity.checked) {
                            for (int j = 0; j < entity.entities.size(); j++) {
                                if (entity.entities.get(j).checked) {
                                    mSchemePrice += entity.entities.get(j).price * entity.count * numberHelper.getCount();
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    if (entity.checked) {
                        mSchemePrice += entity.price * numberHelper.getCount();
                    }
                    break;
                case 4:
                    if (entity.checked) {
                        mSchemePrice += entity.count * (int) ((entity.duration * 1.00) / entity.interval + 0.5) * entity.price;
                    }
                    break;
                case 6:
                    if (commitOrderEntity.isDefaultTemplate) {
                        if (entity.checked) {
                            mSchemePrice += entity.price * numberHelper.getCount();
                        }
                    } else {
                        for (int j = 0; j < entity.entities.size(); j++) {
                            if (entity.entities.get(j).checked) {
                                mSchemePrice += entity.entities.get(j).price * numberHelper.getCount();
                            }
                        }
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
    }

    @OnClick({R.id.stv_planting_scheme, R.id.stv_execution_steward, R.id.stv_signboard, R.id.stv_distribution, R.id.stv_donate, R.id.stv_distribution_addr, R.id.Stv_product_processing, R.id.stv_insurance, R.id.tv_save})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.stv_planting_scheme: //养殖方案
                intent = new Intent(this, BreedingSchemeActivity.class);
                if (!commitOrderEntity.isDefaultTemplate) {
                    intent.putExtra(Constant.SCHEME_TYPE, false);
                    intent.putExtra(Constant.SCHEME, schemeList);
                }
                intent.putExtra(Constant.PRODUCT_ID, breedEntity.id);
                intent.putExtra(Constant.NUMBER, numberHelper.getCount());
                intent.putExtra(Constant.DURATION, commitOrderEntity.cycleTime);
                startActivityForResult(intent, SCHEME);
                break;

            case R.id.stv_execution_steward: //执行管家
                intent = new Intent(this, StewardActivity.class);
                intent.putExtra(Constant.FARM_ID, breedEntity.farm.id);
                startActivityForResult(intent, STEWARD);
                break;

            case R.id.stv_signboard: //标识牌
                intent = new Intent(this, SignboardActivity.class);
                intent.putExtra(Constant.TYPE, 1);
                intent.putExtra(Constant.FARM_ID, breedEntity.farm.id);
                startActivityForResult(intent, SIGNBOARD);
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
                intent.putExtra(Constant.PRODUCT_ID, breedEntity.id);
                intent.putExtra(Constant.NUMBER, numberHelper.getCount());
                intent.putParcelableArrayListExtra(Constant.PRODUCT_PROCESS, processEntityList);
                startActivityForResult(intent, PROCESS);
                break;
            case R.id.stv_insurance:
                AppManager.jump(InsuranceActivity.class);
                break;
            case R.id.tv_save:
                if (setCommitOrder()) {
                    commitOrderEntity.type = "grow";
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
        String name1 = etName1.getText().toString();
        String name2 = etName2.getText().toString();
        String name3 = etName3.getText().toString();
        if (TextUtils.isEmpty(name1) || TextUtils.isEmpty(name2) || TextUtils.isEmpty(name3)) {
            FarmToastUtils.show("请填写名称");
            etName1.requestFocus();
            return false;
        }
        commitOrderEntity.name = name1 + "为" + name2 + "养的" + name3;
        commitOrderEntity.clear();
        if (!commitOrderEntity.isDefaultTemplate) {
            getSchemeList();
        }
        //土地产品
        CommitItemEntity entity = new CommitItemEntity();
        entity.productId = breedEntity.id;
        entity.type = "MAJOR";
        entity.quantity = numberHelper.getCount();
        commitOrderEntity.add(entity);


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
                Intent intent = new Intent();
                intent.putExtra(Constant.ORDER, entity);
                intent.putExtra(Constant.ORDER_BODY, "网农工社-养殖订单");
                intent.putExtra(Constant.NAME, breedEntity.farm.name);
                intent.putExtra(Constant.FARM_IMG, breedEntity.farm.img);
                intent.putExtra(Constant.TYPE, "NORMAL");
                SPUtils.put(Constant.PAY_TYPE, 1);
                AppManager.jump(ConfirmOrderActivity.class, intent);
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
            case SCHEME://方案
                ArrayList<SchemeEntity> schemeList = data.getParcelableArrayListExtra(Constant.SCHEME);
                if (schemeList == null) {
                    commitOrderEntity.isDefaultTemplate = true;
                } else {
                    this.schemeList = schemeList;
                    commitOrderEntity.isDefaultTemplate = false;
                    if (schemeList != null) {  //改变集合里种植周期
                        for (int i = 0; i < schemeList.size(); i++) {
                            switch (schemeList.get(i).ctype) {
                                case 3:
                                    commitOrderEntity.cycleTime = schemeList.get(i).duration;
                                    break;
                            }
                        }
                    }
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
                break;
            case PROCESS:  //产品加工地址
                processEntityList = data.getParcelableArrayListExtra(Constant.PRODUCT_PROCESS);
                if (setProcessTotalPrice()) return;
                changeTotalPrice();
                break;
        }
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
        mStvProcessing.setRightString("¥" + mDecimalFormat.format(price * numberHelper.getCount()));
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
                    if (entity.checked) {
                        for (int j = 0; j < entity.entities.size(); j++) {
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
                case 6:
                    for (int j = 0; j < entity.entities.size(); j++) {
                        if (entity.entities.get(j).checked) {
                            commitItemBean.productId = entity.entities.get(j).artProductId;
                            commitOrderEntity.add(commitItemBean);
                        }
                    }
                    break;
            }
        }
    }
}
