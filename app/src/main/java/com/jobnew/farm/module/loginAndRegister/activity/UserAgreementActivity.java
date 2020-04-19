package com.jobnew.farm.module.loginAndRegister.activity;

import android.os.Bundle;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.widget.TitleBarHelper;

/**
 * Created by Administrator on 2017/5/24.
 * 用户协议界面
 */

public class UserAgreementActivity extends BaseActivity{
    @Override
    protected int getLayout() {
        return R.layout.activity_user_activity;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        setTitle("用户协议",true);
    }
}
