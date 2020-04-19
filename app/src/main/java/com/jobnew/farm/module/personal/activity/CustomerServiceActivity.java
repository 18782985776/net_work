package com.jobnew.farm.module.personal.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wc on 2017/6/7.
 * Function：客服界面
 * desc：
 */

public class CustomerServiceActivity extends BaseActivity {
    @BindView(R.id.txt_phone)
    TextView txtPhone;
    @BindView(R.id.txt_qq)
    TextView txtQq;

    @Override
    protected int getLayout() {
        return R.layout.activity_customer_service;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("客服", true);
    }

    @OnClick({R.id.rl_phone, R.id.rl_qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_phone:
                String replace = txtPhone.getText().toString().replace("-", "");
                Uri uri = Uri.parse("tel:"+replace);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;
            case R.id.rl_qq:
                if (checkApkExist(this, "com.tencent.mobileqq")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + txtQq.getText().toString() + "&version=1")));
                } else {
                    showMsg("本机未安装QQ应用");
                }
                break;
        }
    }

    public boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
