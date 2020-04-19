package com.jobnew.farm.module.personal.activity;

import android.os.Bundle;
import android.text.TextUtils;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.widget.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wc on 2017/5/31.
 * Function：修改密码
 * desc：
 */

public class ChangePwdActivity extends BaseActivity {
    @BindView(R.id.et_original)
    ClearEditText etOriginal;
    @BindView(R.id.et_new)
    ClearEditText etNew;
    @BindView(R.id.et_new_again)
    ClearEditText etNewAgain;

    @Override
    protected int getLayout() {
        return R.layout.activity_change_pwd;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("修改密码", true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.txt_change_pwd)
    public void onViewClicked() {
        changePwd();
    }
    /**
     * 修改密码
     */
    private void changePwd() {
        if (checkDate()){
            Map<String, String> map=new HashMap<>();
            map.put("passwd",etNewAgain.getText().toString());
            map.put("oldPasswd",etOriginal.getText().toString());
            TestRepository.getIns().UpdatePwd(map).subscribe(new DefaultSubscriber<BaseEntity>(this,"修改密码...") {
                @Override
                public void _onNext(BaseEntity entity) {
                    showMsg(entity.msg);
                    finish();
                }
            });
        }
    }
    /**
     * 检查数据
     */
    private boolean checkDate() {
        if (TextUtils.isEmpty(etOriginal.getText().toString())){
            showMsg("请填写原始密码");
            return false;
        }
        if (TextUtils.isEmpty(etNew.getText().toString())){
            showMsg("请填写新密码");
            return false;
        }
        if (TextUtils.isEmpty(etNewAgain.getText().toString())){
            showMsg("请再次填写确认新密码");
            return false;
        }
        if (etNew.getText().toString()!=null&&etNew.getText().toString().length()<6){
            showMsg("新密码不少于6位");
            return false;
        }
        if (etNewAgain.getText().toString()!=null&&etNewAgain.getText().toString().length()<6){
            showMsg("新密码不少于6位");
            return false;
        }
        if (etNew.getText().toString()!=null&&etNewAgain.getText().toString()!=null&&!(etNew.getText().toString().equals(etNewAgain.getText().toString()))){
            showMsg("确认密码有误");
            etNewAgain.setText("");
            return false;
        }
        return true;
    }
}
