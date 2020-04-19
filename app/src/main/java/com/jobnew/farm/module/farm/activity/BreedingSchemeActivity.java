package com.jobnew.farm.module.farm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.module.farm.fragment.BreedingSchemeFragment;

import java.util.ArrayList;

/**
 * Created by wufengqiao on 2017/6/14.
 * function：
 * desc：
 */

public class BreedingSchemeActivity extends BaseActivity {

    private int mProductId;
    private int number;
    private int duration;
    private boolean isFirst;
    private ArrayList schemeList;

    @Override
    protected int getLayout() {
        return R.layout.activity_breed_scheme;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("养殖方案", true);
        Intent intent = getIntent();
        mProductId = intent.getIntExtra(Constant.PRODUCT_ID, -1);
        number = intent.getIntExtra(Constant.NUMBER,0);
        duration = intent.getIntExtra(Constant.DURATION,1);
        isFirst = intent.getBooleanExtra(Constant.SCHEME_TYPE, true);
        schemeList = intent.getParcelableArrayListExtra(Constant.SCHEME);
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_fragment, BreedingSchemeFragment.newInstance(mProductId,duration,number, isFirst,schemeList));
        transaction.commit();
    }
}
