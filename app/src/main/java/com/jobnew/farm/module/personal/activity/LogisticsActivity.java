package com.jobnew.farm.module.personal.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseRefreshAndLoadActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.LogisticsBean;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.personal.adapter.LogisticsAdapter;
import com.jobnew.farm.utils.DateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wc on 2017/6/28.
 * Function：物流快递
 * desc：
 */

public class LogisticsActivity extends BaseRefreshAndLoadActivity {
    LogisticsAdapter adapter;
    @BindView(R.id.txt_order_num)
    TextView txtOrderNum;
    @BindView(R.id.txt_order_time)
    TextView txtOrderTime;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.txt_order_company)
    TextView txtOrderCompany;
    @BindView(R.id.txt_order_waybill)
    TextView txtOrderWaybill;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    private int orderId;
    private List<LogisticsBean.ExpressItemsEntity> dataAll;

    @Override
    protected int getLayout() {
        return R.layout.activity_logistics;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("物流", true);
        orderId = getIntent().getIntExtra("orderId", 0);
        adapter.loadMoreEnd(false);
        initData();
    }

    @Override
    public void errorChickData() {
        initData();
    }

    /**
     * 加载数据
     */
    private void initData() {
        adapter.setEnableLoadMore(false);
        Map<String, String> map = new HashMap<>();
        map.put("id", orderId + "");
        TestRepository.getIns().getLogistics(map).subscribe(new DefaultSubscriber<BaseEntity<LogisticsBean>>(this, "获取数据ing") {
            @Override
            public void _onNext(BaseEntity<LogisticsBean> entity) {
                LogisticsBean lb = entity.data;
                txtOrderNum.setText("订单编号："+lb.getOrderSn());
                txtOrderTime.setText("下单时间："+DateUtils.toString(lb.getCreateDate()+""));
                txtOrderCompany.setText("快递公司："+lb.getExpressName());
                if (!TextUtils.isEmpty(lb.getExpressNo())){
                    txtOrderWaybill.setText("货运单号："+lb.getExpressNo());
                }else{
                    txtOrderWaybill.setText("货运单号：商家配送中，请联系商家");
                    empty();
                    return;
                }
                if (lb.getExpressItems().size()==0){
                    empty();
                }else{
                    content();
                    dataAll.clear();
                    dataAll.addAll(lb.getExpressItems());
                    ptrLayout.refreshComplete();
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                error(msg);
            }
        });
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        dataAll = new ArrayList<>();
        adapter = new LogisticsAdapter(R.layout.activity_logistics_item, dataAll, mContext);
        return adapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    public void onLoadMoreRequested() {
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        initData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
