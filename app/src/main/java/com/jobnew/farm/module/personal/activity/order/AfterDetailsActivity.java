package com.jobnew.farm.module.personal.activity.order;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.entity.bazaar.AfterDetailsBean;
import com.jobnew.farm.module.personal.adapter.AfterDetailsAdatper;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wc on 2017/8/24.
 * Function：售后详情
 * desc：
 */

public class AfterDetailsActivity extends BaseActivity {
    @BindView(R.id.txt_is_get)
    TextView txtIsGet;
    @BindView(R.id.txt_why)
    TextView txtWhy;
    @BindView(R.id.txt_money)
    TextView txtMoney;
    @BindView(R.id.txt_intro)
    TextView txtIntro;
    @BindView(R.id.txt_pic)
    TextView txtPic;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    int id;
    @BindView(R.id.txt_is_why)
    TextView txtIsWhy;

    @Override
    protected int getLayout() {
        return R.layout.activity_after_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("售后详情", true);
        id = getIntent().getIntExtra("id", 0);
        requestData();
    }

    /**
     *
     */
    private void requestData() {
        TestRepository.getIns().getAfterDetail(id + "", new HashMap<String, String>())
                .subscribe(new DefaultSubscriber<BaseEntity<AfterDetailsBean>>(this, "获取中...") {
                    @Override
                    public void _onNext(BaseEntity<AfterDetailsBean> entity) {
                        if (entity.data.getReturnType().equals("refund")){
                            txtIsWhy.setText("退款原因");
                        }else{
                            txtIsWhy.setText("退货原因");
                        }
                        if (entity.data.getOrderStatus()==1){
                            txtIsGet.setText("已收到货");
                        }else{
                            txtIsGet.setText("未收到货");
                        }
                        txtMoney.setText(entity.data.getRefundAmount()+"元");
                        txtWhy.setText(entity.data.getReturnReason());
                        txtIntro.setText(entity.data.getReturnInfo());
                        if (entity.data.getReturnImg().equals("[]")){
                            txtPic.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.GONE);
                        }else{
                            List<String> dataImg=new Gson().fromJson(entity.data.getReturnImg(),new TypeToken<List<String>>(){}.getType());
                            AfterDetailsAdatper adapter=new AfterDetailsAdatper(R.layout.activity_order_evaluater_item,dataImg,mContext);
                            GridLayoutManager gm=new GridLayoutManager(mContext,4);
                            recyclerView.setLayoutManager(gm);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
