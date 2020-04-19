package com.jobnew.farm.module.loginAndRegister.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
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
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2017/5/24.
 * 登录界面
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_phone_num)
    ClearEditText etUsername;
    @BindView(R.id.et_pwd)
    ClearEditText etPassword;
    String[] mPermissionList = new String[]{
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private UMShareAPI mShareAPI;
    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
            setTitle("登录",true);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.txt_forget_pwd, R.id.txt_register, R.id.btn_login
            , R.id.img_qq, R.id.img_weixing, R.id.img_weibo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_forget_pwd:
                AppManager.jump(ForgetPwdActivity.class);
                break;
            case R.id.txt_register:
                AppManager.jump(RegisterActivity.class);
                break;
            case R.id.btn_login:
                checkLoginData();
                break;
            case R.id.img_qq:
                currentMEDIA=SHARE_MEDIA.QQ;
                typeCurrent="1";
                checkPermission(SHARE_MEDIA.QQ,"1");
                break;
            case R.id.img_weixing:
                currentMEDIA=SHARE_MEDIA.WEIXIN;
                typeCurrent="2";
                checkPermission(SHARE_MEDIA.WEIXIN,"2");
                break;
            case R.id.img_weibo:
                currentMEDIA=SHARE_MEDIA.SINA;
                typeCurrent="3";
                checkPermission(SHARE_MEDIA.SINA,"3");
                break;
        }
    }
    private SHARE_MEDIA currentMEDIA;
    private String typeCurrent;
    private void checkPermission(SHARE_MEDIA platform,String type) {
        if (EasyPermissions.hasPermissions(mContext, mPermissionList)) {
            other(platform,type);
        } else {
            EasyPermissions.requestPermissions(this, "需要开启必要的权限", 123, mPermissionList);
        }
    }
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        super.onPermissionsGranted(requestCode, perms);
        other(currentMEDIA,typeCurrent);
    }

    /**
     * weibo
     * 用户id：uid
     * accesstoken: accessToken （6.2以前用access_token）
     * refreshtoken: （6.2以前用refresh_token）
     * 过期时间：expiration （6.2以前用expires_in）
     * 用户名：name（6.2以前用screen_name）
     * 位置：location
     * 头像：iconurl（6.2以前用profile_image_url）
     * 性别：gender
     * 关注数：followers_count
     * 好友数：friends_count
     * weixin
     * openid:openid
     * unionid:（6.2以前用unionid）uid
     * accesstoken: accessToken （6.2以前用access_token）
     * refreshtoken: refreshtoken: （6.2以前用refresh_token）
     * 过期时间：expiration （6.2以前用expires_in）
     * name：name（6.2以前用screen_name）
     * 城市：city
     * 省份：prvinice
     * 国家：country
     * 性别：gender
     * 头像：iconurl（6.2以前用profile_image_url）
     * qq
     * name：name（6.2以前用screen_name）
     * 用户id：uid
     * accesstoken: accessToken （6.2以前用access_token）
     * 过期时间：expiration （6.2以前用expires_in）
     * 性别：gender
     * 头像：iconurl（6.2以前用profile_image_url）
     * 是否黄钻：is_yellow_year_vip
     * 黄钻等级：yellow_vip_level
     * 城市：city
     * 省份：province
     *
     * @param platform
     */
    private void other(final SHARE_MEDIA platform, final String type) {
        mShareAPI = UMShareAPI.get(this);
        mShareAPI.getPlatformInfo(LoginActivity.this, platform, new UMAuthListener() {
            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                android.util.Log.e("------",map.toString());
                int sexType=3;
                String name = map.get("gender");
                if (TextUtils.isEmpty(name)){
                    if (name.equals("男")){
                        sexType=1;
                    }else if (name.equals("女")){
                        sexType=2;
                    }else {
                        sexType=3;
                    }
                }else{
                    sexType=3;
                }
                threeLogin(type,map.get("uid"),map.get("iconurl"),sexType+"",map.get("name"));
            }
            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                showMsg("授权失败");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                showMsg("授权取消");
            }
        });
    }
    /**
     * 去三方登录
     * @param
     * @param
     */
    private void threeLogin(String type, String openId, String avatar, String sex, String name) {
        HashMap<String, String> params = new HashMap<>();
        params.put("avatar",avatar);
        params.put("sex",sex);
        params.put("name",name);
        params.put("type",type);
        params.put("openId",openId);
        TestRepository.getIns().ThreeLoginApp(params).subscribe(new DefaultSubscriber<BaseEntity<LoginEntity>>(this,"登录中...") {
            @Override
            public void _onNext(BaseEntity<LoginEntity> entity) {
                SPUtils.saveUser(entity.data);
                MyApplication.user = entity.data;
                Constant.token = entity.data.getToken();
                showMsg("登录成功");
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
    /**
     * 检查是否符合登录情况
     */
    private void checkLoginData() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(username)) {
            showMsg("请输入手机号！");
            return;
        }
        if (!RegexUtil.isTelNum(username)) {
            showMsg("请输入11位有效手机号!");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showMsg("请输入密码！");
        }
        if (password.length() < 6) {
            showMsg("密码长度不能小于6位!");
            return;
        }
        login(username,password);
    }
    /**
     * 去登录
     * @param username
     * @param password
     */
    private void login(String username, String password) {
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("account",etUsername.getText().toString());
        hashMap.put("passwd",etPassword.getText().toString());
        TestRepository.getIns().loginApp(hashMap).subscribe(new DefaultSubscriber<BaseEntity<LoginEntity>>(this,"登录中...") {
            @Override
            public void _onNext(BaseEntity<LoginEntity> entity) {
                SPUtils.saveUser(entity.data);
                MyApplication.user = entity.data;
                Constant.token = entity.data.getToken();
                showMsg("登录成功");
                finish();
            }
        });
    }
}
