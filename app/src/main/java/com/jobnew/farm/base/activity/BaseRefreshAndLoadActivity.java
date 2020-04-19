package com.jobnew.farm.base.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.aries.ui.widget.progress.BuildConfig;
import com.aries.ui.widget.progress.UIProgressView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.data.exception.EmptyDataException;
import com.jobnew.farm.data.exception.NoNetException;
import com.jobnew.farm.data.exception.NoWifiException;
import com.jobnew.farm.utils.AlertUtil;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.utils.LoadingUtil;
import com.jobnew.farm.widget.StatusBarUtil;
import com.jobnew.farm.widget.TitleBarHelper;
import com.marno.easystatelibrary.EasyStatusView;
import com.jobnew.farm.base.BaseCheckHelper;
import com.jobnew.farm.base.interfaces.IBaseStatusView;
import com.orhanobut.logger.Logger;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;

/**
 * Created by wufengqiao on 2017/5/17
 * Function: 可下拉刷新和加载更多的activity
 * Desc:
 */
public abstract class BaseRefreshAndLoadActivity extends RapidRefreshLoadActivity implements IBaseStatusView {

    protected PtrFrameLayout ptrLayout;
    protected EasyStatusView easyStatusView;
    protected UIProgressView mLoadingDialog;
    protected TitleBarHelper mTitleBar;

    public void setEasyStatusView(EasyStatusView easyStatusView) {
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
        this.easyStatusView = easyStatusView;
        BaseCheckHelper.checkEasyStatusView(easyStatusView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorChickData();
            }
        });
    }

    public void errorChickData() {

    }

    public EasyStatusView getEasyStatusView() {
        return easyStatusView;
    }

    @Override
    protected void beforeInitView() {
        super.beforeInitView();
        StatusBarUtil.setTranslucentForImageView(this, 0, null);
        View view = findViewById(android.R.id.content).getRootView();
        //判断是否有titilBar,并初始化
        checkTitleBar(view);

        // 检查界面是否存在自定义EasyStatusView
//        checkEasyStatusView(view);

        easyStatusView = (EasyStatusView)
                view.findViewById(R.id.esv_main);

        setEasyStatusView(easyStatusView);

        ptrLayout = (PtrFrameLayout) view.findViewById(R.id.ptr_layout);
        initPtrLayout();

        if (BuildConfig.DEBUG) {
            String name = this.getClass().getName();
            Logger.e("当前Activity >>> " + name);
        }
    }

    /**
     * 初始化下拉刷新
     */
    private void initPtrLayout() {
        if (ptrLayout != null) {
            ptrLayout.setDurationToClose(200);
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
            setTitleBar(mTitleBar);
        }
    }

    /**
     * @param title    标题
     * @param backFlag 是否使用返回按钮点击事件
     */
    protected void setTitle(String title, boolean backFlag) {
        mTitleBar.setTitleMainText(title);
        if (backFlag) {
            mTitleBar.setLeftVisible(true);
            mTitleBar.setOnLeftTextClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }


    private void initTitleBar(View view) {
        mTitleBar = new TitleBarHelper(view);
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
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!manager.isActive()) {
            manager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
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
    public void error(String msg) {
        if (easyStatusView != null) {
            easyStatusView.error();
        }
        if (!TextUtils.isEmpty(msg)) {
            FarmToastUtils.show(msg);
        }
    }

    @Override
    public void loading() {
        if (easyStatusView != null) {
            easyStatusView.loading();
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
     * 判断当前列表是否有数据
     *
     * @return
     */
    protected boolean emptyAdapter() {
        BaseQuickAdapter adapter = getAdapter();
        return adapter
                .getData()
                .isEmpty();
    }

    /**
     * 展示分页错误
     *
     * @param e
     * @param msg
     */
    protected void showLoadMoreError(boolean loadMore, Throwable e, String msg) {
        boolean showFailure = emptyAdapter() || !loadMore;
        if (!showFailure) {
            if (e instanceof EmptyDataException) {
                getAdapter().loadMoreEnd(false);
            } else {
                getAdapter().loadMoreFail();
            }
            return;
        }
        if (e instanceof EmptyDataException) {
            if (showFailure) {
                empty();
            }
        } else if (e instanceof NoNetException) {
            if (showFailure) {
                noNet();
            }
        } else if (e instanceof NoWifiException) {
            AlertUtil.show(mContext, "正在使用移动网络, 确定继续当前操作吗?",
                    "取消", "继续", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == DialogInterface.BUTTON_POSITIVE) {
                                ignoreWifiLimit();
                            }
                        }
                    });
        } else {
            if (showFailure) {
                error(msg);
            }
        }
    }

    /**
     * 忽略wifi限制
     */
    protected void ignoreWifiLimit() {

    }

    public void showMsg(String msg) {
        FarmToastUtils.show(msg);
    }
}
