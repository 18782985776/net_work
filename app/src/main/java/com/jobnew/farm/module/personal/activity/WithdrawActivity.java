package com.jobnew.farm.module.personal.activity;

import android.os.Bundle;
import android.view.View;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.widget.AppManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wc on 2017/6/21.
 * Function：提现界面
 * desc：
 */

public class WithdrawActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("提现", true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_ali, R.id.rl_weixin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_ali:
                AppManager.jump(WithDrawAlipayActivity.class);
                break;
            case R.id.rl_weixin:
                showMsg("程序员正在拼命的开发ing");
                break;
        }
    }
}
