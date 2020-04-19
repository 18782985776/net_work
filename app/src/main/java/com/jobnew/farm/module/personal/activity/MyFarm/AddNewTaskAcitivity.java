package com.jobnew.farm.module.personal.activity.MyFarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.PersonMyFarmRepository;
import com.jobnew.farm.entity.myfarm.CommitNewTaskEntity;
import com.jobnew.farm.entity.plan.CommitItemEntity;
import com.jobnew.farm.entity.plan.OrderEntity;
import com.jobnew.farm.entity.plan.SchemeEntity;
import com.jobnew.farm.module.farm.activity.farmActivity.PayOrderActivity;
import com.jobnew.farm.module.personal.adapter.MyFarm.AddNewTaskAdapter;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.utils.SPUtils;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.TitleBarHelper;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wufengqiao on 2017/7/14.
 * function：
 * desc：
 */

public class AddNewTaskAcitivity extends BaseActivity {
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    private int number;
    private AddNewTaskAdapter mAdapter;
    private List<SchemeEntity> mList;
    private int productId;
    private int orderId;
    private SimpleDateFormat dateFormat;

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        setTitle("新任务", true);
        titleBar.setRightText("任务记录", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.jump(TaskRecordActivity.class, Constant.ORDER_ID, orderId);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_add_new_task;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        initIntent();
        setRecycleView();
        requestData();
        loading();
    }

    private void requestData() {
        PersonMyFarmRepository.getIns().getAddNewTaskProduct(productId + "")
                .subscribe(new DefaultSubscriber<List<SchemeEntity>>(this, false) {
                    @Override
                    public void _onNext(List<SchemeEntity> list) {
                        content();
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

                        long timeMillis = (System.currentTimeMillis() + 864000000) / 86400000 * 86400000;
                        String date = dateFormat.format(timeMillis);
                        //外层实体数据跟推荐实体数据一致
                        for (int i = 0; i < mList.size(); i++) {
                            SchemeEntity entity = mList.get(i);
                            entity.count = 1;
                            entity.checked = false;
                            entity.date = date;
                            if (entity.ctype == 1) {
                                for (int j = 0; j < entity.entities.size(); j++) {
                                    entity.entities.get(j).checked = false;
                                }
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                        mAdapter.notifyPriceChange();
                    }
                });
    }

    private void setRecycleView() {
        mList = new ArrayList<>();
        mAdapter = new AddNewTaskAdapter(this, mList, number);
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        rvContent.setAdapter(mAdapter);

        mAdapter.setOnPriceChangeListener(new AddNewTaskAdapter.OnPriceChangeListener() {
            @Override
            public void OnPriceChange(double price) {
                tvTotalPrice.setText(new DecimalFormat("0.00").format(price));
            }
        });
    }

    private void initIntent() {
        number = getIntent().getIntExtra(Constant.NUMBER, 1);
        productId = getIntent().getIntExtra(Constant.PRODUCT_ID, -1);
        orderId = getIntent().getIntExtra(Constant.ORDER_ID, -1);
    }


    @OnClick(R.id.tv_save)
    public void onViewClicked() {

        CommitNewTaskEntity commitNewTaskEntity = new CommitNewTaskEntity();
        commitNewTaskEntity.orderId = orderId;
        for (int i = 0; i < mList.size(); i++) {
            CommitItemEntity commitItemEntity = new CommitItemEntity();
            SchemeEntity schemeEntity = mList.get(i);
            commitItemEntity.productId = schemeEntity.artProductId;
            try {
                commitItemEntity.executeDate = dateFormat.parse(schemeEntity.date).getTime();
            } catch (ParseException e) {
                FarmToastUtils.show("日期错误！");
                return;
            }
            switch (schemeEntity.ctype) {
                case 1: //施肥
                    for (int j = 0; j < schemeEntity.entities.size(); j++) {
                        SchemeEntity entity = schemeEntity.entities.get(j);
                        if (entity.checked) {
                            commitItemEntity.quantity = 1;
                            commitNewTaskEntity.add(commitItemEntity);
                        }
                    }
                    break;
                case 0:
                case 2:
                    schemeEntity.count = 1;
                case 4: //拍照
                    if (schemeEntity.checked) {
                        commitItemEntity.quantity = schemeEntity.count;
                        commitNewTaskEntity.add(commitItemEntity);
                    }
                    break;
            }
        }
        PersonMyFarmRepository.getIns().addNewTaskProduct(commitNewTaskEntity)
                .subscribe(new DefaultSubscriber<OrderEntity>(this, "提交订单") {
                    @Override
                    public void _onNext(OrderEntity entity) {
                        SPUtils.put(Constant.PAY_TYPE,2);
                        SPUtils.put(Constant.ORDER_ID,orderId);
                        String body = "新任务";
                        Intent intent = new Intent();
                        intent.putExtra(Constant.ORDER_SN, entity.sn);
                        intent.putExtra(Constant.TOTAL_PRICE, entity.price);
                        intent.putExtra(Constant.ORDER_BODY, body);
                        intent.putExtra(Constant.TYPE, "NEWTASK");
                        AppManager.jump(PayOrderActivity.class, intent);
                    }
                });
    }
}
