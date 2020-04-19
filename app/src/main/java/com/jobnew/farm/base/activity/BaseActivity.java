package com.jobnew.farm.base.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.aries.ui.widget.progress.UIProgressView;
import com.jobnew.farm.BuildConfig;
import com.jobnew.farm.R;
import com.jobnew.farm.base.BaseCheckHelper;
import com.jobnew.farm.base.interfaces.IBaseStatusView;
import com.jobnew.farm.data.exception.NoNetException;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.utils.LoadingUtil;
import com.jobnew.farm.widget.StatusBarUtil;
import com.jobnew.farm.widget.TitleBarHelper;
import com.marno.easystatelibrary.EasyStatusView;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * Create by wufengqiao on 2017/5/17 11:33
 * Function：基类
 * Desc：
 */
public abstract class BaseActivity extends BasicActivity implements IBaseStatusView, EasyPermissions.PermissionCallbacks {
    protected Context mContext;
    protected EasyStatusView easyStatusView;

    private UIProgressView mLoadingDialog;
    private TitleBarHelper titleBar;

    @Override
    protected void beforeInitView() {
        mContext = this;
        StatusBarUtil.setTranslucentForImageView(this, 0, null);
        View view = findViewById(android.R.id.content).getRootView();
        //判断是否有titilBar,并初始化
        checkTitleBar(view);

        // 检查界面是否存在自定义EasyStatusView
//        checkEasyStatusView(view);
        easyStatusView = (EasyStatusView) view.findViewById(R.id.esv_main);
        if (easyStatusView != null) {
            View setNet = easyStatusView.findViewById(R.id.tv_setNet);
            setNet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                    startActivity(intent);

                }
            });
        }
        if (errorOnClick()) {
            BaseCheckHelper.checkEasyStatusView(easyStatusView, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    errorChickData();
                }
            });
        }
        if (BuildConfig.DEBUG) {
            String name = this.getClass().getName();
            Logger.e("当前Activity >>> " + name);
        }
    }

    public void errorChickData() {

    }

    /**
     * 设置titlebar的状态栏高度
     *
     * @param view
     */
    public void setTitleBarPadding(View view) {
        view.setPadding(0, getStatusBarHeight(), 0, 0);
    }

    /**
     * 展示错误界面时是否允许点击
     *
     * @return
     */
    protected boolean errorOnClick() {
        return true;
    }


    /**
     * /**
     *
     * @param levle
     * @param text_title 中间文字描述
     * @param text_hint  底部文字描述
     */
    protected void setEmptyStatus(int levle, String text_title, String text_hint) {
        if (easyStatusView != null) {
            ((ImageView) easyStatusView.getEmptyView().findViewById(R.id.data_empty_image)).setImageResource(R.drawable.level_list_set_empty);
            ((ImageView) easyStatusView.getEmptyView().findViewById(R.id.data_empty_image)).setImageLevel(levle);
            ((TextView) easyStatusView.getEmptyView().findViewById(R.id.data_empty_textview_title)).setText(text_title);
            ((TextView) easyStatusView.getEmptyView().findViewById(R.id.data_empty_textview_hint)).setText(text_hint);
        }
    }

    protected void setEmptyStatus(int levle, int text_title_id, int text_hint_id) {
        if (easyStatusView != null) {
            ((ImageView) easyStatusView.getEmptyView().findViewById(R.id.data_empty_image)).getDrawable().setLevel(levle);
            ((TextView) easyStatusView.getEmptyView().findViewById(R.id.data_empty_textview_title)).setText(text_title_id);
            ((TextView) easyStatusView.getEmptyView().findViewById(R.id.data_empty_textview_hint)).setText(text_hint_id);
        }
    }

    /**
     * 检查是否存在titleBar
     *
     * @param view
     */
    private void checkTitleBar(View view) {
        if (view == null) {
            return;
        }
        view = findViewById(R.id.title_bar);
        if (view != null) {
            initTitleBar(view);
            setTitleBar(titleBar);
        }
    }

    /**
     * @param title    标题
     * @param backFlag 是否使用返回按钮点击事件
     */
    protected void setTitle(String title, boolean backFlag) {
        titleBar.setTitleMainText(title);
        if (backFlag) {
            titleBar.setLeftVisible(true);
            titleBar.setOnLeftTextClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }


    private void initTitleBar(View view) {
        titleBar = new TitleBarHelper(view);
        view.setPadding(0, getStatusBarHeight(), 0, 0);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = layoutParams.height + getStatusBarHeight();
        view.setLayoutParams(layoutParams);
    }

    ;

    protected void setTitleBar(TitleBarHelper titleBar) {

    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideSoftInput();
        hideLoading();
    }

    /**
     * 界面关闭的时候隐藏软键盘
     */
    public void hideSoftInput() {
        View view = getWindow().peekDecorView();
        InputMethodManager inputManger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view != null) {
            view.requestFocus();
            inputManger.hideSoftInputFromWindow(view.getWindowToken(), 0);

        }
    }

    /**
     * 打开软键盘
     */
    public void showSoftInput() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, 100);
    }

    @Override
    public void noNet() {
        if (easyStatusView != null) {
            easyStatusView.noNet();
        }
    }

    @Override
    public void empty() {
        if (easyStatusView != null) {
            easyStatusView.empty();
        }
    }

    @Override
    public void loading() {
        if (easyStatusView != null) {
            easyStatusView.loading();
        }
    }

    @Override
    public void error(String msg) {
        if (easyStatusView != null) {
            easyStatusView.error();
        }
        if (!TextUtils.isEmpty(msg)) {
            FarmToastUtils.show(msg);
        }
    }

    @Override
    public void content() {
        if (easyStatusView != null) {
            easyStatusView.content();
        }
    }

    /**
     * 显示加载dialog
     *
     * @param msg
     */
    public void showLoading(String msg) {
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingUtil.show(this, msg);
            mLoadingDialog.setCancelable(true);
            mLoadingDialog.setCanceledOnTouchOutside(false);
        } else {
            mLoadingDialog.setMessage(msg);
            mLoadingDialog.show();
        }
    }

    /**
     * 隐藏加载dialog
     */
    public void hideLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    /**
     * 展示错误信息
     *
     * @param error
     * @param msg
     */
    public void showError(Throwable error, String msg) {
        if (error instanceof NoNetException) {
            noNet();
            if (!TextUtils.isEmpty(msg)) {
                FarmToastUtils.show(msg);
            }
        } else {
            error(msg);
        }
    }

    /**
     * 权限处理的返回类
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this, "请在设置里面开启相应权限，若无相应权限会影响使用")
                    .setPositiveButton("确定")
                    .setNegativeButton(getString(R.string.cancel), null /* click listener */)
                    .setRequestCode(123)
                    .build()
                    .show();
        }
    }

    /**
     * 处理成功后要重写的方法
     *
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            // Do something after user returned from app settings screen, like showing a Toast.
            FarmToastUtils.show("权限申请成功");
        }
    }

    public void showMsg(String msg) {
        FarmToastUtils.show(msg);
    }

}
