package com.jobnew.farm.module.personal.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.data.rxbus.MsgEvent;
import com.jobnew.farm.data.rxbus.RxBus;
import com.jobnew.farm.entity.WalletBean;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.personal.adapter.MyWalletAdapter;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.TitleBarHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wc on 2017/6/14.
 * Function：我的钱包
 * desc：
 */

public class MyWalletActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.txt_balance)
    TextView txtBalance;
    private MyWalletAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_wallet;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("我的钱包", true);
        initRecyclerView();
    }

    @Override
    protected void onResume() {
        TestRepository.getIns().getBalance(new HashMap<String, String>()).subscribe(new DefaultSubscriber<BaseEntity<WalletBean>>(this, false) {
            @Override
            public void _onNext(BaseEntity<WalletBean> entity) {
                    txtBalance.setText(entity.data.getBalance()+"个");
            }
        });
        super.onResume();
    }

    private void initRecyclerView() {
        List<String> data = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            data.add("www");
//        }
        adapter = new MyWalletAdapter(R.layout.item_activity_my_wallet, data);
        LinearLayoutManager linearManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RxBus.getInstance().post(new MsgEvent(125, 1, "farm"));
                finish();
//                showMsg("程序员正在拼命滴加班中...");
            }
        });
    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        titleBar.setRightText("明细");
        titleBar.setOnRightTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.jump(DetailActivity.class);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.text1, R.id.text3, R.id.text2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text1://余额充值
//                AppManager.jump(TopUpBalanceActivity.class); 下版本打开在编写
                showMsg("程序员正在拼命滴加班中...");
                break;
            case R.id.text2://网农币充值
                AppManager.jump(TopUpFarmCurrencyActivity.class);
                break;
            case R.id.text3://余额提现
//                AppManager.jump(WithdrawActivity.class); 下版本打开在编写
                showMsg("程序员正在拼命滴加班中...");
                break;
        }
    }
}
