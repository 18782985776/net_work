package com.jobnew.farm.module.personal.activity;

import android.app.Activity;
import android.os.Bundle;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;

/**
 * Created by wc on 2017/6/21.
 * Function：添加支付宝账号
 * desc：
 */

public class AddAlipayActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_add_alipay;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("添加支付宝账号",true);
    }
}
