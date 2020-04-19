package com.jobnew.farm.module.personal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.AddressBean;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.FarmRepository;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.data.repository.PersonMyFarmRepository;
import com.jobnew.farm.data.rxbus.RxBus;
import com.jobnew.farm.entity.ShoppingCar.AddShoppingTrolleyOrderBean;
import com.jobnew.farm.entity.ShoppingCar.AddShoppingTrolleyOrderSuccessBaen;
import com.jobnew.farm.entity.ShoppingCar.ShippingMethodEntity;
import com.jobnew.farm.entity.ShoppingCar.ShoppingCarProductBean;
import com.jobnew.farm.entity.ShoppingCar.ShoppingProductEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.farm.activity.farmActivity.PayOrderActivity;
import com.jobnew.farm.module.personal.adapter.ConfirmOrderAdapter;
import com.jobnew.farm.utils.FreightHelper;
import com.marno.easystatelibrary.EasyStatusView;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wufengqiao on 2017/8/22.
 * function：
 * desc：
 */

public class ConfirmOrderActivity extends BaseActivity {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_receiver)
    TextView tvReceiver;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.iv_farm)
    ImageView ivFarm;
    @BindView(R.id.tv_farm_name)
    TextView tvFarmName;
    @BindView(R.id.ll_farm)
    LinearLayout llFarm;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.tv_order_total_price)
    TextView tvOrderTotalPrice;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.esv_main)
    EasyStatusView esvMain;
    private ArrayList<ShoppingProductEntity> data;
    private String type;  //提交订单需要传的参数
    private String payOrderType; //微信支付需要的参数，区分是否是新任务
    private ConfirmOrderAdapter mAdapter;
    private double totalPrice;
    private AddShoppingTrolleyOrderBean addShoppingTrolleyOrderBean;
    private String orderBody;
    private AddressBean mAddressBean;
    private Map<String, String> productMap;
    private double freightTotalPrice;

    @Override
    protected int getLayout() {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("确认订单", true);
        initIntent();
        productMap = new HashMap<>();
        llAddress.setVisibility(View.VISIBLE);
        llFarm.setVisibility(View.GONE);
        setRecycleView();
        requestData();
    }

    /**
     * 请求默认配送地址
     */
    private void requestData() {
        MyFarmRepository.getIns().queryDefalutAddress(new HashMap<>())
                .subscribe(new DefaultSubscriber<BaseEntity<AddressBean>>(this) {
                    @Override
                    public void _onNext(BaseEntity<AddressBean> entity) {
                        if (entity.code == 200) {
                            mAddressBean = entity.data;
                            initData();
                            requestShippingMethod();
                            setAddress();
                        } else {
                            showMsg(entity.msg);
                            finish();
                        }
                    }

                    @Override
                    public void _onError(Throwable e, String msg) {
                        super._onError(e, msg);
                        showMsg(msg);
                        finish();
                    }
                });
    }

    /**
     * 设置地址信息
     */
    private void setAddress() {
        tvReceiver.setText(mAddressBean.getContactName());
        tvPhone.setText(mAddressBean.getPhone());
        AddressBean.AreaEntity area = mAddressBean.getArea();
        String s = area.getMergerName() + area.getName() + mAddressBean.getAddress();
        tvAddress.setText(s.replace(",", ""));
    }

    /**
     * 查询产品运费计算方式
     */
    private void requestShippingMethod() {
        Map<String, String> map = new HashMap<>();
        String s = new Gson().toJson(productMap);
        Log.e("aaaaaa", "shipping" + s);
        map.put("data", s);
        PersonMyFarmRepository.getIns().getShippingMethod(map)
                .subscribe(new DefaultSubscriber<List<ShippingMethodEntity>>(this, "") {
                    @Override
                    public void _onNext(List<ShippingMethodEntity> list) {
                        freightTotalPrice = 0.00;
                        for (int i = 0; i < data.size(); i++) {
                            for (int j = 0; j < data.get(i).list.size(); j++) {
                                ShoppingProductEntity entity = data.get(i).list.get(j);
                                for (int k = 0; k < list.size(); k++) {
                                    if (list.get(k).productId == entity.productId) {
                                        entity.shippingMethodEntity = list.get(k);
                                        freightTotalPrice += FreightHelper.shippingFreight(entity.shippingMethodEntity, entity.quantity);
                                    }
                                }
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                        tvOrderTotalPrice.setText("¥" + new DecimalFormat("0.00").format(totalPrice + freightTotalPrice));
                    }
                });
    }

    private void setRecycleView() {
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ConfirmOrderAdapter(R.layout.item_confirm_order, data);
        rvContent.setAdapter(mAdapter);
    }

    /**
     * 处理 生成订单所需要的参数
     */
    private void initData() {
        addShoppingTrolleyOrderBean = new AddShoppingTrolleyOrderBean();
        addShoppingTrolleyOrderBean.type = type;
        addShoppingTrolleyOrderBean.userAddressId = mAddressBean.getId();
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).list.size(); j++) {
                ShoppingProductEntity entity = data.get(i).list.get(j);
                ShoppingCarProductBean bean = new ShoppingCarProductBean();
                bean.productId = entity.productId;
                productMap.put(entity.productId + "", mAddressBean.getId() + "");
                bean.quantity = entity.quantity;
                addShoppingTrolleyOrderBean.add(bean);
                totalPrice += data.get(i).list.get(j).productPrice * data.get(i).list.get(j).quantity; //计算商品总价
            }
        }
    }

    private void initIntent() {
        Intent intent = getIntent();
        data = intent.getParcelableArrayListExtra("data");
        type = intent.getStringExtra("type");
        payOrderType = intent.getStringExtra("payOrderType");
        orderBody = intent.getStringExtra(Constant.ORDER_BODY);
    }

    /**
     * 提交订单
     */
    private void commitOrder() {
        PersonMyFarmRepository.getIns().addShoppingTrolleyOrder(addShoppingTrolleyOrderBean)
                .subscribe(new DefaultSubscriber<AddShoppingTrolleyOrderSuccessBaen>(this, "正在提交订单") {
                    @Override
                    public void _onNext(AddShoppingTrolleyOrderSuccessBaen entity) {
                        Intent intent = new Intent(ConfirmOrderActivity.this, PayOrderActivity.class);
                        intent.putExtra(Constant.ORDER_SN, entity.sn);
                        intent.putExtra(Constant.TOTAL_PRICE, entity.totalAmount);
                        intent.putExtra(Constant.ORDER_BODY, orderBody);
                        intent.putExtra(Constant.TYPE, payOrderType);
                        startActivity(intent);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            if (requestCode == 104) {
                AddressBean address = (AddressBean) intent.getSerializableExtra(Constant.ADDRESS_MANAGE);
                if (address != null) {
                    mAddressBean = address;
                    addShoppingTrolleyOrderBean.userAddressId = mAddressBean.getId();
                    for (int i = 0; i < data.size(); i++) {
                        for (int j = 0; j < data.get(i).list.size(); j++) {
                            ShoppingProductEntity entity = data.get(i).list.get(j);
                            productMap.put(entity.productId + "", mAddressBean.getId() + "");
                        }
                    }
                    requestShippingMethod();
                    setAddress();
                }
            }
        }
    }

    @OnClick({R.id.tv_save, R.id.ll_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                commitOrder();
                break;
            case R.id.ll_address:
                Intent intent = new Intent(this, AddressManage.class);
                intent.putExtra(Constant.ADDRESS_MANAGE, 104);
                startActivityForResult(intent, 104);
                break;
        }
    }
}
