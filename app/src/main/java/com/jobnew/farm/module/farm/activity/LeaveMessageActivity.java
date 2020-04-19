package com.jobnew.farm.module.farm.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeaveMessageActivity extends BaseActivity {

    @BindView(R.id.edit_message)
    EditText editMessage;




    @Override
    protected int getLayout() {
        return R.layout.activity_leave_message;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        sendMessage();
    }

    private void sendMessage() {
        //  MyFarmRepository.getIns()
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
 //  @OnClick(R.id.edit_message)

}
