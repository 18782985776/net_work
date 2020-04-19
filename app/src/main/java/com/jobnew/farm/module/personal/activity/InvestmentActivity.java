package com.jobnew.farm.module.personal.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.entity.PayWeiXinInfo;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.utils.RegexUtil;
import com.jobnew.farm.widget.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wc on 2017/6/7.
 * Function：投资机会
 * desc：
 */

public class InvestmentActivity extends BaseActivity {
    @BindView(R.id.edt_name)
    ClearEditText edtName;
    @BindView(R.id.edt_phone)
    ClearEditText edtPhone;
    @BindView(R.id.et_detail)
    EditText etDetail;

    @Override
    protected int getLayout() {
        return R.layout.activity_investment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("投资机会", true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        if (CheckDate()){
            Map<String, String> map = new HashMap<>();
            map.put("name", edtName.getText().toString());
            map.put("phone", edtPhone.getText().toString());
            map.put("info", etDetail.getText().toString()+"");
            TestRepository.getIns().Invest(map).subscribe(new DefaultSubscriber<BaseEntity>(this,"提交中") {
                @Override
                public void _onNext(BaseEntity entity) {
                    showMsg("提交成功，稍后工作人员会与您联系");
                    finish();
                }
            });
        }
    }
    /**
     * 检查是否符合保存的条件
     * @return
     */
    private boolean CheckDate() {
        String s = edtName.getText().toString();
        String s1 = edtPhone.getText().toString();
        if (TextUtils.isEmpty(s)) {
            showMsg("请填写联系人姓名");
            return false;
        }
        if (!TextUtils.isEmpty(s) && s.length() < 2) {
            showMsg("联系人姓名不少于2个字");
            return false;
        }
        if (TextUtils.isEmpty(s1)) {
            showMsg("请输入手机号！");
            return false;
        }
        if (!RegexUtil.isTelNum(s1)) {
            showMsg("请输入11位有效手机号!");
            return false;
        }
        return true;
    }
}
