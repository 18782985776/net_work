package com.jobnew.farm.module.farm.activity.farmActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.module.farm.adapter.farmAdapter.ShippingAddressManageAdpter;
import com.jobnew.farm.widget.TitleBarHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShippingAddressMangeAcitivty extends BaseActivity {
    ArrayList arrayList;
    @BindView(R.id.tv_title_left)
    TextView tvTitleLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.title_bar)
    LinearLayout titleBar;
    @BindView(R.id.recycleView_shipping_address_manage)
    RecyclerView recycleViewShippingAddressManage;
    ShippingAddressManageAdpter shippingAddressManageAdpter;
    Unbinder unbinder;
    @Override
    protected int getLayout() {
        return R.layout.activity_shipping_address_mange_acitivty;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        unbinder=ButterKnife.bind(this);
        initArray();
        shippingAddressManageAdpter=new ShippingAddressManageAdpter(this,arrayList);
        recycleViewShippingAddressManage.setLayoutManager(new LinearLayoutManager(this));
        recycleViewShippingAddressManage.setAdapter(shippingAddressManageAdpter);
    }

    private void initArray() {
        arrayList=new ArrayList();
        for (int i=0;i<20;i++){
            arrayList.add(i);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        titleBar.setTitleMainText("收货地址管理");
        titleBar.getLeftTextView().setVisibility(View.VISIBLE);
        titleBar.getLeftTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        titleBar.setRightText("添加");
        titleBar.getRightTextView().setVisibility(View.VISIBLE);
        titleBar.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(),"添加",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
