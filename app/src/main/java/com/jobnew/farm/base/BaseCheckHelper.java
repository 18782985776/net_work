package com.jobnew.farm.base;

import android.view.View;

import com.marno.easystatelibrary.EasyStatusView;

/**
 * Created by wufengqiao on 2017/5/17.
 * Function:
 * Desc:
 */
public class BaseCheckHelper {
    /**
     * 检查是否存在EasyStatusView,
     * 如存在添加失败点击重试
     *
     * @param easyStatusView
     */
    public static void checkEasyStatusView(EasyStatusView easyStatusView, View.OnClickListener l) {
        if (easyStatusView == null) {
            return;
        }
        View statusView = easyStatusView.getEmptyView();
        if (statusView != null) {
            statusView.setOnClickListener(l);
        }
        statusView = easyStatusView.getErrorView();
        if (statusView != null) {
            statusView.setOnClickListener(l);
        }
        statusView = easyStatusView.getNoNetworkView();
        if (statusView != null) {
            statusView.setOnClickListener(l);
        }
        statusView = easyStatusView.getLoadingView();
        if (statusView != null) {
            statusView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
