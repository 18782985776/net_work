package com.jobnew.farm;

import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.entity.LoginEntity;
import com.jobnew.farm.utils.SPUtils;
import com.jobnew.farm.widget.AppManager;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by wufengqiao on 2017/5/17.
 */

public class MyApplication extends MultiDexApplication {
    public static LoginEntity user = null;
    private static MyApplication instance = null;


    /*微信id*/
    public static String wxappid="wxbf8cec66f691fe6e";
    {
        Config.REDIRECT_URL = "http://sns.whalecloud.com/sina2/callback";
        PlatformConfig.setWeixin("wxbf8cec66f691fe6e", "7948faaba463620e8e90456c8e34c99b");
        PlatformConfig.setSinaWeibo("852884048", "f02df9ba6d1bce92fb77a4a560bef77a");
        PlatformConfig.setQQZone("1105979690", "VQ2xdsSgHMIX8K5I");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AppManager.getInstance().init(this);
        initUmeng();
        user = SPUtils.getUser();
        if (user != null) {
            Constant.token = user.getToken();
        }
    }


    /**
     * 初始化umeng
     */
    private void initUmeng() {
        Config.DEBUG = true;//开启调试模式
        UMShareAPI.get(this);//初始化友盟
        Config.isJumptoAppStore = true;//对应平台没有安装的时候跳转转到应用商店下载
        com.umeng.socialize.utils.Log.LOG = true; //关闭友盟的log
        Config.IsToastTip = false;//关闭友盟的toast
        Config.isNeedAuth = true;//每次登录都重新授权

    }
    public static MyApplication getInstance() {
        return instance;
    }
    /**
     * 判断用户是否登录
     * @return
     */
    public static boolean isLogin() {
        return !TextUtils.isEmpty(Constant.token);
    }
}
