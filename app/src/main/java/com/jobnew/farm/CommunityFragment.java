package com.jobnew.farm;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jobnew.farm.base.fragment.BaseFragment;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.helper.RetrofitHelp;
import com.jobnew.farm.data.repository.TestRepository;
import com.jobnew.farm.data.service.TestService;
import com.jobnew.farm.entity.base.BaseEntity;
import com.jobnew.farm.module.farm.activity.BreedDetailsActivity;
import com.jobnew.farm.module.farm.activity.LandDetailsActivity;
import com.jobnew.farm.module.farm.activity.PlantingPlanAcitivity;
import com.jobnew.farm.module.farm.activity.PlantingSchemeActivity;
import com.jobnew.farm.module.farm.activity.SelectCropActivity;
import com.jobnew.farm.module.farm.activity.SettingFrequencyActivity;
import com.jobnew.farm.module.farm.activity.StewardActivity;
import com.jobnew.farm.module.farm.adapter.SettingFrequencyAdapter;
import com.jobnew.farm.module.loginAndRegister.activity.LoginActivity;
import com.jobnew.farm.module.personal.activity.MyFarm.TaskRecordActivity;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.widget.AppManager;

import java.util.HashMap;
import java.util.Map;

import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangcheng on 2017/5/22.
 * title：社交界面
 * note：
 */

public class CommunityFragment extends BaseFragment {

    public static CommunityFragment newInstance() {
        return new CommunityFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_community;
    }

    @Override
    public void onResume() {
        if (MyApplication.isLogin()){
            showMsg("登录成功");
        }else{
            AppManager.jump(LoginActivity.class);
        }
        super.onResume();
    }

    @Override
    protected void initView(Bundle bundle, View view) {

    }
}
