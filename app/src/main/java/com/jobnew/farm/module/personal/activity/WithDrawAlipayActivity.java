package com.jobnew.farm.module.personal.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.module.personal.adapter.WithDrawAlipayAdapter;
import com.jobnew.farm.widget.AppManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wc on 2017/6/21.
 * Function：支付宝提现
 * desc：
 */

public class WithDrawAlipayActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    WithDrawAlipayAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_withdrwa_alipay;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("选择支付宝", true);
        initRecyclerView();
    }

    private void initRecyclerView() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            data.add("11111");
        }
        adapter = new WithDrawAlipayAdapter(R.layout.activity_withdrwa_alipay_item, data);
        LinearLayoutManager lin = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lin);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapterB, View view, int position) {
                adapter.setRefreshCheck(position);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.txt_add, R.id.submit_withdraw,R.id.ll_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_add:
            case R.id.ll_add:
                AppManager.jump(AddAlipayActivity.class);
                break;
            case R.id.submit_withdraw:
                break;
        }
    }
}
