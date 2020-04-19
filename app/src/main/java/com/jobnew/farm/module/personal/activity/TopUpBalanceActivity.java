package com.jobnew.farm.module.personal.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.utils.Arith;
import com.jobnew.farm.widget.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wc on 2017/6/19.
 * Function：充值余额
 * desc：
 */

public class TopUpBalanceActivity extends BaseActivity {
    @BindView(R.id.edit_text)
    ClearEditText editText;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.img_ali_pay)
    ImageView imgAliPay;
    @BindView(R.id.img_weixin_pay)
    ImageView imgWeixinPay;

    @Override
    protected int getLayout() {
        return R.layout.activity_top_up_balance;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("充值", true);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(editText.getText().toString())){
                    tvTotalPrice.setText("0.00");
                }else {
                    if (Double.valueOf(editText.getText().toString())<=50000.00){
                        tvTotalPrice.setText(Arith.div(Double.valueOf(editText.getText().toString()),Double.valueOf("1"),2)+"");
                    }else{
                        showMsg("最多充值五万元整哟");
                        editText.setText("50000.00");
                        editText.setSelection(editText.getText().length());
                    }
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_ali, R.id.rl_weixin, R.id.txt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_ali:
                shift(1);
                break;
            case R.id.rl_weixin:
                shift(2);
                break;
            case R.id.txt_submit:
                showMsg(type+"------"+editText.getText().toString());
                break;
        }
    }
    private int type=1;//1 代表支付宝  2 代表微信
    /**
     * 转换
     * @param a
     */
    private void shift(int a) {
        if (type==a) return;
        if (a==1){
            imgAliPay.setImageResource(R.mipmap.farm_icon_selected);
            imgWeixinPay.setImageResource(R.mipmap.farm_icon_notselected);
        }else if (a==2){
            imgAliPay.setImageResource(R.mipmap.farm_icon_notselected);
            imgWeixinPay.setImageResource(R.mipmap.farm_icon_selected);
        }
        type=a;
    }
}
