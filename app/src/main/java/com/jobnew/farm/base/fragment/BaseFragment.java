package com.jobnew.farm.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.jobnew.farm.base.interfaces.IPermissionsLinstener;
import com.jobnew.farm.data.exception.NoNetException;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.utils.LoadingUtil;
import com.jobnew.farm.widget.StatusBarUtil;
import com.jobnew.farm.widget.TitleBarHelper;
import com.marno.easystatelibrary.EasyStatusView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * Create by wfq on 2017/3/14 15:06
 * Function：基类
 * Desc：
 */
public abstract class BaseFragment extends BasicFragment implements IBaseStatusView ,EasyPermissions.PermissionCallbacks{

    protected EasyStatusView easyStatusView;
    private UIProgressView mLoadingDialog;
    protected Context mContext;
    private TitleBarHelper titleBar;

    //维护页面状态
    protected void setEasyStatusView(EasyStatusView easyStatusView) {
        this.easyStatusView = easyStatusView;
    }
    protected abstract int getLayout();

    @Override
    protected void initView(View view, Bundle bundle) {
        mContext=getActivity();
        StatusBarUtil.setTranslucentForImageView(getActivity(),0,null);
        checkTitleBar(mContentView);
        // 检查界面是否存在自定义EasyStatusView
        easyStatusView = (EasyStatusView) view.findViewById(R.id.esv_main);
        if (easyStatusView !=null) {
            View setNet = easyStatusView.findViewById(R.id.tv_setNet);
            setNet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                    startActivity(intent);
                }
            });
        }
        BaseCheckHelper.checkEasyStatusView(easyStatusView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorChickData();
            }
        });
        initView(bundle,view);
        if (BuildConfig.DEBUG) {
            String name = this.getClass().getName();
            Logger.e("当前Fragment >>> " + name);
        }
    }

    public  void errorChickData() {

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
        View titleView = view.findViewById(R.id.title_bar);
        if (titleView != null) {
            initTitleBar(titleView);
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
                    getActivity().finish();
                }
            });
        }
    }

    private void initTitleBar(View view){
        titleBar = new TitleBarHelper(view);
        view.setPadding(0,getStatusBarHeight(),0,0);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = layoutParams.height+getStatusBarHeight();
        view.setLayoutParams(layoutParams);
    };
    protected void setTitleBar(TitleBarHelper titleBar){

    }

    /**
     * 设置titlebar的状态栏高度
     * @param view
     */
    public void setTitleBarPadding(View view){
        view.setPadding(0,getStatusBarHeight(),0,0);
    }
    /**
     * 获取状态栏高度
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

    /**
     * 界面关闭的时候隐藏软键盘
     */
    public void hideSoftInput() {
        View view = getActivity().getWindow().peekDecorView();
        InputMethodManager inputManger = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
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
                InputMethodManager imm = (InputMethodManager)getActivity(). getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, 100);
    }
    /**
     * 权限适配申请
     */

    public static final int REQUEST_PERMISSION_CODE = 10086;
    private static IPermissionsLinstener mPermissionsLinstener;
    public static List<String> permissionList;

    public static void requestPresmision(IPermissionsLinstener permissionsLinstener, String... permission) {
        mPermissionsLinstener = permissionsLinstener;
        if (permissionsLinstener == null) {
            return;
        }
        permissionList = new ArrayList<>();
//        Observable.from(permission).subscribe(new Action1<String>() {
//            @Override
//            public void call(String p) {
//                if (ContextCompat.checkSelfPermission(StackUtil.getIns().current(), p) != PackageManager.PERMISSION_GRANTED) {
//                    permissionList.add(p);
//                }
//            }
//        });

//		for (String p : permission) {
//			if (ContextCompat.checkSelfPermission(StackUtil.getIns().current(), p) != PackageManager.PERMISSION_GRANTED) {
//				permissionList.add(p);
//			}
//		}
       /* if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(StackUtil.getIns().current(), permissionList.toArray(new String[permissionList.size()]), REQUEST_PERMISSION_CODE);
        } else {
            mPermissionsLinstener.permissionSuccess();
        }*/
    }

    /**
     * @param levle      1
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

    protected abstract void initView(Bundle bundle,View view);

    @Override
    public void onDestroy() {
        super.onDestroy();
        hideLoading();
    }


    protected void showKeyboard(Activity activity, boolean isShow) {
        activity = getActivity();
        if (activity == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        if (isShow) {
            if (activity.getCurrentFocus() == null) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            } else {
                imm.showSoftInput(activity.getCurrentFocus(), 0);
            }
        } else {
            if (getActivity().getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
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
            mLoadingDialog = LoadingUtil.show(getActivity(), msg);
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
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            // Do something after user returned from app settings screen, like showing a Toast.
            FarmToastUtils.show("请在设置里面开启相应权限");
        }
    }
    public void showMsg(String msg){
        FarmToastUtils.show(msg);
    }

}
