package com.jobnew.farm.base.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aries.ui.widget.progress.BuildConfig;
import com.aries.ui.widget.progress.UIProgressView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jobnew.farm.R;
import com.jobnew.farm.widget.TitleBarHelper;
import com.marno.easystatelibrary.EasyStatusView;
import com.jobnew.farm.base.BaseCheckHelper;
import com.jobnew.farm.base.interfaces.IBaseStatusView;
import com.jobnew.farm.data.exception.EmptyDataException;
import com.jobnew.farm.data.exception.NoNetException;
import com.jobnew.farm.data.exception.NoWifiException;
import com.jobnew.farm.utils.AlertUtil;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.utils.LoadingUtil;
import com.jobnew.farm.widget.StatusBarUtil;
import com.orhanobut.logger.Logger;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wufengqiao on 2017/5/17 10:12
 * Function: 可以上拉加载更多下拉刷新的fragment
 * Desc:
 */
public abstract class BaseRefreshLoadFragment extends
        RapidRefreshLoadFragment implements IBaseStatusView {

    protected PtrFrameLayout ptrLayout;
    protected EasyStatusView easyStatusView;
    protected UIProgressView mLoadingDialog;
    protected Context mContext;
    protected Button btn_layout_data_empty_confim;
    private TitleBarHelper titleBar;

    public void setEasyStatusView(EasyStatusView easyStatusView) {
        this.easyStatusView = easyStatusView;
    }

    @Override
    protected void initView(View view, Bundle bundle) {
        mContext=getActivity();
        StatusBarUtil.setTranslucentForImageView(getActivity(),0,null);
        checkTitleBar(mContentView);
        easyStatusView = (EasyStatusView) view.findViewById(R.id.esv_main);
        /*if (easyStatusView != null) {
        *    btn_layout_data_empty_confim.setVisibility(View.GONE);
        }*/
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
        if (errorOnClick()) {
            BaseCheckHelper.checkEasyStatusView(easyStatusView, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    errorChickData();
                }
            });
        }
        ptrLayout = (PtrFrameLayout) view.findViewById(R.id.ptr_layout);
        initPtrLayout();

        initView(bundle,view);

        if (BuildConfig.DEBUG) {
            String name = this.getClass().getName();
            Logger.e("当前Fragment >>> " + name);
        }
    }
    /**
     * 展示错误界面时是否允许点击
     *
     * @return
     */
    protected boolean errorOnClick() {
        return true;
    }
    public void errorChickData(){

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
     * @param levle      1
     * @param text_title 中间文字描述
     * @param text_hint  底部文字描述
     */
    protected void setEmptyStatus(int levle, String text_title, String text_hint) {
        if (easyStatusView != null) {
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
     * 初始化下拉刷新
     */
    private void initPtrLayout() {
        if (ptrLayout != null) {
            ptrLayout.setDurationToClose(200);
        }
    }

    protected abstract void initView( Bundle bundle,View view);

    @Override
    public void onDestroy() {
        super.onDestroy();
        hideLoading();
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
/**全局加载**/
    @Override
    public void loading() {
        if (easyStatusView != null) {
            try {
                if (easyStatusView.getCurrentStatus() != 2) {
                    easyStatusView.loading();
                }
            } catch (Exception e) {
                e.printStackTrace();
                easyStatusView.loading();
            }
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

    public EasyStatusView getEasyStatusView() {
        return easyStatusView;
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

    @Override
    protected void loadData() {
        loadData(false);
    }

    /**
     * 集成上滑下拉加载
     *
     * @param loadMore 是否是加载更多
     */
    protected void loadData(boolean loadMore) {

    }
    public void showMsg(String msg){
        FarmToastUtils.show(msg);
    }
}
