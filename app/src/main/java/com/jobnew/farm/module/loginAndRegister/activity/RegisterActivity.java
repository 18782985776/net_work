package com.jobnew.farm.module.loginAndRegister.activity;

import android.app.Application;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.zxing.client.result.TextParsedResult;
import com.jobnew.farm.MyApplication;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.LoginEntity;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.utils.RegexUtil;
import com.jobnew.farm.utils.SPUtils;
import com.jobnew.farm.widget.AppManager;
import com.jobnew.farm.widget.ClearEditText;
import com.jobnew.farm.widget.TitleBarHelper;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/5/24.
 * 注册界面
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.et_user_phone)
    ClearEditText etUserPhone;
    @BindView(R.id.txt_send_code)
    TextView txtSendCode;
    @BindView(R.id.et_verification_code)
    ClearEditText etVerificationCode;
    @BindView(R.id.et_pws)
    ClearEditText etPws;
    @BindView(R.id.cb_agree)
    CheckBox cbAgree;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        super.setTitleBar(titleBar);
        setTitle("注册", true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_register, R.id.txt_agreement, R.id.txt_send_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_send_code:
                checkDate();
                break;
            case R.id.btn_register:
                if (checkRegisterData()){
                    goRegister();
                }
                break;
            case R.id.txt_agreement:
                AppManager.jump(UserAgreementActivity.class);
                break;
        }
    }
    /**
     * 检测是否注册条件
     * @return
     */
    private boolean checkRegisterData() {
        String phone = etUserPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            showMsg("请输入手机号！");
            return false;
        }
        if (!RegexUtil.isTelNum(phone)) {
            showMsg("请输入11位有效手机号！");
            return false;
        }
        String codeText = etVerificationCode.getText().toString();
        if (TextUtils.isEmpty(codeText)) {
            showMsg("请输入验证码！");
            return false;
        }
        String password = etPws.getText().toString();
        if (TextUtils.isEmpty(password)) {
            showMsg("请输入密码！");
            return false;
        }
        if (password.length() < 6) {
            showMsg("密码长度不能小于6位！");
            return false;
        }
        if (!cbAgree.isChecked()) {
            showMsg("请确认是否同意用户协议！");
            return false;
        }
        return true;

    }
    /**
     * 去注册
     */
    private void goRegister() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("account",etUserPhone.getText().toString());
        hashMap.put("passwd",etPws.getText().toString());
        hashMap.put("captcha",etVerificationCode.getText().toString());
        TestRepository.getIns().registerApp(hashMap).subscribe(new DefaultSubscriber<BaseEntity<LoginEntity>>(this,"正在注册中") {
            @Override
            public void _onNext(BaseEntity<LoginEntity> entity) {
                SPUtils.saveUser(entity.data);
                MyApplication.user = entity.data;
                Constant.token = entity.data.getToken();
                showMsg("注册成功");
                finish();
            }
        });
    }
    /**
     * 检查发送验证码数据
     */
    private void checkDate() {
        String phone = etUserPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            showMsg("请输入手机号！");
            return;
        }
        if (!RegexUtil.isTelNum(phone)) {
            showMsg("请输入11位有效手机号！");
            return;
        }
        txtSendCode.setEnabled(false);
        getCode(phone);
    }
    /**
     * 获取验证码
     */
    public void getCode(String phone) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("account",etUserPhone.getText().toString());
        TestRepository.getIns().getCode(hashMap).subscribe(new DefaultSubscriber<BaseEntity>(this,"获取验证码") {
            @Override
            public void _onNext(BaseEntity entity) {
                showMsg("获取验证码成功");
                txtSendCode.setText("60s后重新获取");
                startTimer();
                timer.start();
            }
            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                txtSendCode.setEnabled(true);
            }
        });
    }
    private CountDownTimer timer;
    /**
     * 开始计时器
     */
    private void startTimer() {
        if (timer == null) {
            timer = new CountDownTimer(60000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    txtSendCode.setEnabled(false);
                    txtSendCode.setText((millisUntilFinished / 1000) + "s后重新获取");
                }
                @Override
                public void onFinish() {
                    finishTimer();
                }
            };
        }
    }
    /**
     * 完成计时器
     */
    private void finishTimer() {
        txtSendCode.setEnabled(true);
        txtSendCode.setText("点击重新获取");
    }
    @Override
    protected void onDestroy() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        super.onDestroy();
    }
}
