package com.jobnew.farm.module.farm.activity.farmActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.widget.TitleBarHelper;

public class ChoiceManagerActivity extends BaseActivity{

    @Override
    protected int getLayout() {
        return R.layout.activity_choice_manager;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void setTitleBar(TitleBarHelper titleBar) {
        titleBar.getLeftTextView().setVisibility(View.VISIBLE);
        titleBar.setTitleMainText("选择管家");
        titleBar.getLeftTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
