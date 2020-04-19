package com.jobnew.farm.module.personal.activity.MyFarm;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.PersonMyFarmRepository;
import com.jobnew.farm.entity.myfarm.LandOrBreedInfoEntity;
import com.jobnew.farm.utils.DateUtils;
import com.jobnew.farm.widget.AppManager;
import com.marno.easystatelibrary.EasyStatusView;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wufengqiao on 2017/6/15.
 * function：
 * desc：
 */

public class LandInformationActivity extends BaseActivity {
    public static final String TYPE = "type";
    @BindView(R.id.stv_record_number)
    SuperTextView stvRecordNumber;
    @BindView(R.id.stv_land_number)
    SuperTextView stvLandNumber;
    @BindView(R.id.stv_land_area)
    SuperTextView stvLandArea;
    @BindView(R.id.stv_land_unit_price)
    SuperTextView stvLandUnitPrice;
    @BindView(R.id.stv_rental_period)
    SuperTextView stvRentalPeriod;
    @BindView(R.id.stv_land_affiliation)
    SuperTextView stvLandAffiliation;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.stv_land_address)
    SuperTextView stvLandAddress;
    @BindView(R.id.stv_land_valid_date)
    SuperTextView stvLandValidDate;
    @BindView(R.id.stv_land_name_card)
    SuperTextView stvLandNameCard;
    @BindView(R.id.stv_crop)
    SuperTextView stvCrop;
    @BindView(R.id.stv_steward)
    SuperTextView stvSteward;
    @BindView(R.id.tv_intro)
    TextView tvIntro;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.esv_main)
    EasyStatusView esvMain;
    private int type = -1;
    private int productId;
    private DecimalFormat mDecimalFormat;

    @Override
    protected int getLayout() {
        return R.layout.activity_land_information;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        type = getIntent().getIntExtra(TYPE, -1);
        productId = getIntent().getIntExtra(Constant.ORDER_ID, -1);
        mDecimalFormat = new DecimalFormat("0.00");
        loading();
        switch (type) {
            case 1:
                setTitle("土地资料", true);
                requestData();
//                initLandInfor();
                break;
            case 2:
                setTitle("动物资料", true);
                requestData();
                initAnimalInfor();
                break;

            case 3:
                setTitle("土地档案", true);
                requestData();
                initLandRecord();
                break;
            default:
                error("服务器错误，请稍后再试！");
                break;
        }

    }

    /**
     * 请求数据
     */
    private void requestData() {
        PersonMyFarmRepository.getIns().getOrderInfo(productId)
                .subscribe(new DefaultSubscriber<LandOrBreedInfoEntity>(this, false) {
                    @Override
                    public void _onNext(LandOrBreedInfoEntity entity) {
                        setLandInfor(entity);
                        content();
                    }
                });
    }

    //初始化土地档案界面
    private void initLandRecord() {
        stvLandUnitPrice.setLeftString("土地租价");
        stvRentalPeriod.setVisibility(View.VISIBLE);
        stvLandNameCard.setLeftString("租地用途");
        stvCrop.setLeftString("租赁说明");
        tvIntro.setVisibility(View.VISIBLE);
        stvSteward.setVisibility(View.GONE);
        tvSave.setVisibility(View.GONE);

    }

    //初始化动物资料界面
    private void initAnimalInfor() {
        stvLandNumber.setLeftString("动物编号");
        stvLandArea.setLeftString("养殖动物");
        stvLandUnitPrice.setLeftString("动物单价");
        stvLandAffiliation.setLeftString("动物归属");
        stvLandAddress.setLeftString("养殖地址");
        stvLandValidDate.setLeftString("养殖周期");
        stvLandNameCard.setLeftString("动物名片");
        stvSteward.setLeftString("执行管家");
        stvCrop.setVisibility(View.GONE);
        tvSave.setText("将动物赠送给好友");

//        stvRecordNumber.setRightString("2017060116250010");
//        stvLandNumber.setRightString("K007");
//        stvLandArea.setRightString("草泥马 X2");
//        stvLandUnitPrice.setRightString("10元/只");
//        stvLandAffiliation.setRightString("花果山农场");
//        tvAddress.setText("成都市武侯区天府二街");
//        stvLandValidDate.setRightString("2017-06-08至2017-06-09");
//        stvLandNameCard.setRightString("开心农场给小马养的马");
//        stvSteward.setRightString("张老汉");
    }

    /*//初始化土地资料界面
    private void initLandInfor() {
        stvRecordNumber.setRightString("0");
        stvLandNumber.setRightString("K007");
        stvLandArea.setRightString("5㎡");
        stvLandUnitPrice.setRightString("¥1.5/㎡/天");
        stvLandAffiliation.setRightString("花果山农场");
        tvAddress.setText("成都市武侯区天府二街");
        stvLandValidDate.setRightString("2017-06-08至2017-06-10");
        stvLandNameCard.setRightString("开心农场给小马种的土豆");
        stvCrop.setRightString("优质青椒 X10株");
        stvSteward.setRightString("张老汉");
    }*/

    private void setLandInfor(LandOrBreedInfoEntity entity) {
        if (type == 1) {
            stvLandArea.setRightString(entity.majorProductQuantity + "㎡");
            stvLandUnitPrice.setRightString("¥" + mDecimalFormat.format(entity.majorProductPrice) + "/㎡/天");
            stvCrop.setRightString(entity.minorProductName + "  X" + entity.minorProductQuantity + entity.minorProductUnit);
        } else if (type == 2) {
            stvLandArea.setRightString(entity.majorProductName + "  X" + entity.majorProductQuantity);
            stvLandUnitPrice.setRightString(mDecimalFormat.format(entity.majorProductPrice) + "/只");
        } else if (type == 3) {
            stvLandArea.setRightString(entity.majorProductQuantity + "㎡");
            stvLandUnitPrice.setRightString("¥" + mDecimalFormat.format(entity.majorProductPrice) + "/月");
            stvRentalPeriod.setRightString(entity.majorProductQuantity + "月");
            stvLandNameCard.setRightString(entity.fitValue);
            tvIntro.setText(entity.memo);
        }
        stvRecordNumber.setRightString(entity.orderSn);
        stvLandNumber.setRightString(entity.majorProductSn);
        stvLandAffiliation.setRightString(entity.farmName);
        tvAddress.setText(entity.farmAddress);
        if (entity.startDate != 0 && entity.endDate != 0) {
            stvLandValidDate.setRightString(DateUtils.DateToString(entity.startDate + "", "yyyy-MM-dd") + "至" + DateUtils.DateToString(entity.endDate + "", "yyyy-MM-dd"));
        } else {
            stvLandValidDate.setVisibility(View.GONE);
        }
        stvLandNameCard.setRightString(entity.orderName);
        stvSteward.setRightString(entity.managerName);
    }

    @OnClick({R.id.tv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                AppManager.jump(LandAwayActivity.class);
                break;
        }

    }
}
