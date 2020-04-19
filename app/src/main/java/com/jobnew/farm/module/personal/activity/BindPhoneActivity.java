package com.jobnew.farm.module.personal.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jobnew.farm.MyApplication;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.utils.RegexUtil;
import com.jobnew.farm.utils.SPUtils;
import com.jobnew.farm.widget.ClearEditText;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wc on 2017/6/29.
 * Function：绑定手机界面
 * desc：
 */

public class BindPhoneActivity extends BaseActivity {
    @BindView(R.id.et_user_phone)
    ClearEditText etUserPhone;
    @BindView(R.id.txt_send_code)
    TextView txtSendCode;
    @BindView(R.id.et_verification_code)
    ClearEditText etVerificationCode;
    @Override
    protected int getLayout() {
        return R.layout.activity_bind_phone;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("绑定手机",true);
    }
    @OnClick({R.id.txt_send_code, R.id.btn_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_send_code:
                checkDate();
                break;
            case R.id.btn_forget:
                if (checkChangeData()) {
                    goChange();
                }
                break;
        }
    }
    /**
     * 去改密码
     */
    private void goChange() {
        HashMap<String, String> params = new HashMap<>();
        params.put("phone", etUserPhone.getText().toString());
        params.put("captcha", etVerificationCode.getText().toString());
        TestRepository.getIns().BindPhone(params).subscribe(new DefaultSubscriber<BaseEntity>(this,"修改中...") {
            @Override
            public void _onNext(BaseEntity entity) {
                MyApplication.user.getUser().setPhone(etUserPhone.getText().toString());
                SPUtils.saveUser(MyApplication.user);
                showMsg("绑定成功");
                finish();
            }
        });
    }
    /**
     * 检测是否修改密码条件
     *
     * @return
     */
    private boolean checkChangeData() {
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
        return true;

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
