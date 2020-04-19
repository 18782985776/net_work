package com.jobnew.farm.module.home.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jobnew.farm.MainActivity;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;
import com.jobnew.farm.data.DefaultSubscriber;
import com.jobnew.farm.data.downLoad.DownloadApi;
import com.jobnew.farm.data.downLoad.DownloadProgressHandler;
import com.jobnew.farm.data.downLoad.ProgressHelper;
import com.jobnew.farm.data.repository.MyFarmRepository;
import com.jobnew.farm.entity.GuidEntity;
import com.jobnew.farm.utils.AppInfoUtils;
import com.jobnew.farm.utils.SPUtils;
import com.jobnew.farm.widget.AppManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WelcomeActivity extends BaseActivity {


    @BindView(R.id.welcome_img)
    ImageView welcomeImg;
    @BindView(R.id.skip_tv)
    TextView skipTv;
    boolean isFirstInt;
    boolean isInstall = false;
    int majorVersion;//最新apk版本号
    int guideVersion;//最新引导图版本号
    long bodylength;//apk长度// Manifest.permission.CAMERA
    //    String[] permissionString={ Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE };
    String[] permissionString = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE};
    private String DOWNLOADPATH = "/apk/";
    int versionCode;
    boolean forceUpdate;
    CountDownTimer timer;//控制首页倒计时timer
    int skiptime = 4;

    /**
     * 吴更新情况控制跳转
     **/
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            skiptime--;
            switch (msg.what) {//倒计时结束的情况
                case 0://跳转
                    passWelecome();
                    break;
                case 1://倒计时
                    skipTv.setText("跳过 " + skiptime);
                    if (skiptime == 1) {
                        passWelecome();
                    }
                    break;
            }
            return true;
        }
    });

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    private static final int ERRORIMG = R.drawable.firsterror;
    private static final int LOADIMG = R.drawable.fristloading;
    public static final String APK_NAME = "NetFarm.apk";

    //// drawableTypeRequest.centerCrop()
//                .dontAnimate()
//                .placeholder(sCommonPlaceholder)
//                .diskCacheStrategy(DiskCacheStrategy.RESULT)
//                .error(R.mipmap.ic_error)
//                .placeholder(R.mipmap.ic_default)
//                .into(iv);
    @Override
    protected void initView(Bundle savedInstanceState) {
        initDeviceId();
//       Glide.with(this).load("http://pic39.nipic.com/20140317/4021224_211624207109_2.jpg").dontAnimate().placeholder(LOADIMG).diskCacheStrategy(DiskCacheStrategy.ALL).error(ERRORIMG).centerCrop().into(welcomeImg);
        versionCode = AppInfoUtils.getInstance().getVersionCode(WelcomeActivity.this);
        isFirstInt = SPUtils.get("isFirstInt", true);
        File fileOldApk = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + APK_NAME);
        if (fileOldApk.exists()) {//删除以前的apk文件
            fileOldApk.delete();
        }

        checkNetworkState();
        //  aquirePerssion();
    }

    private void initDeviceId() {
        Constant.DEVICE_ID = SPUtils.get("deviceId", "");
        if (TextUtils.isEmpty(Constant.DEVICE_ID)) {
            Constant.DEVICE_ID = UUID.randomUUID().toString();
            SPUtils.put("deviceId",Constant.DEVICE_ID);
        }
    }


    /**
     * 权限管理
     **/
    private void aquirePerssion() {
        if (EasyPermissions.hasPermissions(this, permissionString)) {//有权限
            MyFarmRepository.getIns().getGuidInfo(new HashMap<>())
                    .subscribe(new DefaultSubscriber<GuidEntity>(this, false) {
                        @Override
                        public void _onNext(GuidEntity entity) {
                            upDateOrPass(entity);
                        }

                        @Override
                        public void _onError(Throwable e, String msg) {//错误
                            super._onError(e, msg);
                            startTimer();
                        }

                    });
        } else {//没有权限
            EasyPermissions.requestPermissions(this, "必要的权限", 0, permissionString);
        }
    }

    /***判断返回的数据确定
     * 1.强制更新
     * 2.是否更新
     * 3.开线程3秒跳转界面
     * 4.是否走引导页***/
    public void upDateOrPass(GuidEntity entity) {
        majorVersion = entity.getMajorVersion();
        forceUpdate = entity.getForceUpdate();
        if (majorVersion > versionCode) {//表示有新版本apk
            if (entity.getForceUpdate()) {//强制更新
                retrofitDownload(entity.getMajorUrl());
            } else {//选择性更新

                ShowMyDailog(entity);
            }
        } else {//没有版本更新的情况，欢迎页3秒跳转
            startTimer();
        }
        guideVersion = entity.getGuideVersion();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    /***开启倒计时，跳转界面***/
    public void startTimer() {
        if (timer == null) {
            timer = new CountDownTimer(4000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    handler.sendEmptyMessage(1);
                }

                @Override
                public void onFinish() {
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                    //  handler.sendEmptyMessage(0);
                }
            }.start();
        }
    }

    //下面两个方法是实现EasyPermissions的EasyPermissions.PermissionCallbacks接口
    //分别返回授权成功和失败的权限
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.i(TAG, "获取成功的权限" + perms);
        MyFarmRepository.getIns().getGuidInfo(new HashMap<>()).subscribe(new DefaultSubscriber<GuidEntity>(this, false) {
            @Override
            public void _onNext(GuidEntity entity) {
                upDateOrPass(entity);
            }

            @Override
            public void _onError(Throwable e, String msg) {
                super._onError(e, msg);
                startTimer();
            }
        });
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this, "请在设置里面开启相应权限，若无相应权限会影响使用")
                    .setPositiveButton("确定")
                    .setNegativeButton("取消", null /* click listener */)
                    .setRequestCode(123)
                    .build()
                    .show();
        }
    }

    /***提示更新apk**/
    private void ShowMyDailog(GuidEntity entity) {
        String majorUrl = entity.getMajorUrl();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(entity.getVersionName() + ": " + entity.getUpdateInfo());
        builder.setTitle("发现新版本！");
        builder.setCancelable(false);
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                retrofitDownload(majorUrl);//这里应该弹窗提升框下载或者取消
            }
        });
        builder.setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                startTimer();
            }
        });
        builder.create().show();
    }

    /**
     * 安装APK文件
     */
    private void installApk() {
        if (Build.VERSION.SDK_INT >= 24) {//判读版本是否在7.0以上
            Uri apkUri = FileProvider.getUriForFile(this, "com.jobnew.farm.fileprovider", fileApk);//在AndroidManifest中的android:authorities值
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//添加这一句表示对目标应用临时授权该Uri所代表的文件
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
            startActivityForResult(install, 668);
        } else {
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(fileApk), "application/vnd.android.package-archive");
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivityForResult(install, 668);
        }
    }

    File fileApk;
    Call<ResponseBody> call;//下载的请求

    /**
     * url  下载apk地址
     * majorVersion   最新apk版本
     * *
     **/
    private void retrofitDownload(String url) {
        //监听下载进度
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressNumberFormat("%1d KB/%2d KB");
        dialog.setTitle("下载");
        dialog.setMessage("正在下载，请稍后...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.show();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://msoftdl.360.cn");
        OkHttpClient.Builder builder = ProgressHelper.addProgress(null);
        DownloadApi retrofit = retrofitBuilder
                .client(builder.build())
                .build().create(DownloadApi.class);

        ProgressHelper.setProgressHandler(new DownloadProgressHandler() {
            @Override
            protected void onProgress(long bytesRead, long contentLength, boolean done) {
                Log.e("是否在主线程中运行", String.valueOf(Looper.getMainLooper() == Looper.myLooper()));
                Log.e("onProgress", String.format("%d%% done\n", (100 * bytesRead) / contentLength));
                Log.e("done", "--->" + String.valueOf(done));
                dialog.setMax((int) (contentLength / 1024));
                dialog.setProgress((int) (bytesRead / 1024));
                if (done && !isInstall) {
                    isInstall = true;
                    dialog.dismiss();
                }
            }
        });

        Call<ResponseBody> call = retrofit.retrofitDownload(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                FileOutputStream fos = null;
                InputStream is = null;
                BufferedInputStream bis = null;
                try {
                    is = response.body().byteStream();
                    // File file = new File(Environment.getExternalStorageDirectory(), "NetFarm.apk");
                    bodylength = response.body().contentLength();
                    fileApk = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/NetFarm.apk");
                    if (!fileApk.exists()) {
                        //  fileApk.mkdirs();
                        fileApk.createNewFile();
                    }
                    //Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator
                    fos = new FileOutputStream(fileApk);
                    bis = new BufferedInputStream(is);
                    byte[] buffer = new byte[1024];
                    int len;
//                    while ((len = bis.read(buffer)) != -1&&fos!=null) {
//                        fos.write(buffer, 0, len);
//                        fos.flush();
//                    }
//
//                    if(fos!=null)fos.close();
//                    if(bis!=null) bis.close();
//                    if(is!=null) is.close();
                    while ((len = bis.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fileApk.length() == bodylength) {
                        installApk();
                    } else {
                        showMsg("下载失败");
                    }
                    ;

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showMsg("下载失败");
                startTimer();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 668) {
            if (resultCode == RESULT_CANCELED && forceUpdate) {
                finish();
            } else if (resultCode == RESULT_CANCELED && !forceUpdate) {
                AppManager.jumpAndFinish(MainActivity.class);
            }
        } else if (requestCode == 363) {
            if (manager.getActiveNetworkInfo().isAvailable()) {
                aquirePerssion();
            } else {
                finish();
            }
        }
    }

    @OnClick(R.id.skip_tv)
    public void dealClick(View view) {
        passWelecome();
    }

    /****根据后代返回的引导图版本判断welecom的跳转**/
    public void passWelecome() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (isFirstInt | guideVersion > SPUtils.get("guideVersion", -1)) {//第一次进入app，或者guideVersion有变化
            AppManager.jumpAndFinish(GuideActivity.class);
        } else {//不是第一次进入，但是引导页没有变化
            AppManager.jumpAndFinish(MainActivity.class);
        }
        SPUtils.put("isFirstInt", false);
    }

    //判断网络连接
    ConnectivityManager manager;

    /**
     * 检测网络是否连接
     *
     * @return
     */
    private boolean checkNetworkState() {
        boolean flag = false;
        //得到网络连接信息
        manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //去进行判断网络是否连接
        if (manager.getActiveNetworkInfo() != null) {
            flag = manager.getActiveNetworkInfo().isAvailable();
        }
        if (!flag) {//无网络
            setNetwork();
        } else {
            isNetworkAvailable();
        }
        return flag;
    }


    /**
     * 网络未连接时，调用设置方法
     */
    private void setNetwork() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("网络提示信息");
        builder.setMessage("网络不可用，如果继续，请先设置网络！");
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = null;
                /**
                 * 判断手机系统的版本！如果API大于10 就是3.0+
                 * 因为3.0以上的版本的设置和3.0以下的设置不一样，调用的方法不同
                 */
                if (android.os.Build.VERSION.SDK_INT > 10) {
                    intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
                } else {
                    intent = new Intent();
                    ComponentName component = new ComponentName(
                            "com.android.settings",
                            "com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                // startActivity(intent);
                startActivityForResult(intent, 363);//设置网络
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showMsg("当前无网络！");
                startTimer();
            }
        });
        builder.create();
        builder.show();
    }

    /**
     * 网络已经连接，然后去判断是wifi连接还是GPRS连接
     * 设置一些自己的逻辑调用
     */
    private void isNetworkAvailable() {
        NetworkInfo.State gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        NetworkInfo.State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        aquirePerssion();
//        if(gprs == NetworkInfo.State.CONNECTED || gprs == NetworkInfo.State.CONNECTING){
//            Toast.makeText(this, "wifi is open! gprs", Toast.LENGTH_SHORT).show();
//        }
//        //判断为wifi状态下才加载广告，如果是GPRS手机网络则不加载！
//        if(wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING){
//            Toast.makeText(this, "wifi is open! wifi", Toast.LENGTH_SHORT).show();
//        }
    }
}
