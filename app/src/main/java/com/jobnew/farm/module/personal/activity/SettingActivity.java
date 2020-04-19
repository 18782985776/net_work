package com.jobnew.farm.module.personal.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jobnew.farm.MyApplication;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.LoginEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.loginAndRegister.activity.LoginActivity;
import com.jobnew.farm.utils.DataCleanManager;
import com.jobnew.farm.utils.SPUtils;
import com.jobnew.farm.widget.AppManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wc on 2017/5/31.
 * Function：设置界面
 * desc：
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.rl_change_pwd)
    RelativeLayout rlChangePwd;
    @BindView(R.id.txt_num_phone)
    TextView txtNumPhone;
    @BindView(R.id.txt_phone)
    TextView txtPhone;

    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoginEntity.UserEntity user = MyApplication.user.getUser();
        if (!TextUtils.isEmpty(user.getQqOpenId()) || !TextUtils.isEmpty(user.getWbOpenId()) || !TextUtils.isEmpty(user.getWxOpenId())) {
            rlChangePwd.setVisibility(View.GONE);
        } else {
            rlChangePwd.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(user.getPhone())) {
            txtNumPhone.setText("未绑定");
            txtPhone.setVisibility(View.GONE);
        } else {
            txtNumPhone.setText("重新绑定");
            txtPhone.setVisibility(View.VISIBLE);
            String phone = user.getPhone();
            StringBuilder sb  =new StringBuilder();
            for (int i = 0; i < phone.length(); i++) {
                char c = phone.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            txtPhone.setText(sb.toString());
        }
    }
    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("设置", true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_abort_us, R.id.rl_change_pwd, R.id.rl_clear,
            R.id.rl_out_app, R.id.rl_bind_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_abort_us:
                AppManager.jump(AboutUsActivity.class);
                break;
            case R.id.rl_change_pwd:
                AppManager.jump(ChangePwdActivity.class);
                break;
            case R.id.rl_clear:
                DataCleanManager.cleanExternalCache(mContext);
                try {
                    String cacheSize = DataCleanManager.getCacheSize(mContext.getExternalCacheDir());
                    showMsg("清除缓存"+cacheSize);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.rl_bind_phone:
                AppManager.jump(BindPhoneActivity.class);
                break;
            case R.id.rl_out_app:
                TestRepository.getIns().OutApp(new HashMap<>()).subscribe(new DefaultSubscriber<BaseEntity>(this, "") {
                    @Override
                    public void _onNext(BaseEntity entity) {
                        Constant.token = "";
                        MyApplication.user = null;
                        SPUtils.remove("LoginEntity");
                        showMsg("退出登录成功");
                        AppManager.jumpAndFinish(LoginActivity.class);
                    }
                });
                break;
        }
    }
}
