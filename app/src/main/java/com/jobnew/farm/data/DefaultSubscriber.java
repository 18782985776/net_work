package com.jobnew.farm.data;


import android.accounts.NetworkErrorException;
import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;
import com.jobnew.farm.base.interfaces.IBaseStatusView;
import com.jobnew.farm.data.exception.DefaultErrorException;
import com.jobnew.farm.data.exception.EmptyDataException;
import com.jobnew.farm.data.exception.NoNetException;
import com.jobnew.farm.data.exception.NoWifiException;
import com.jobnew.farm.module.loginAndRegister.activity.LoginActivity;
import com.jobnew.farm.utils.FarmToastUtils;
import com.jobnew.farm.widget.AppManager;
import com.orhanobut.logger.Logger;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


/**
 * Create By wufengqiao on 2017/5/18.
 * Function:
 * Desc:
 */
public abstract class DefaultSubscriber<T> implements Observer<T> {


    private IBaseStatusView iBaseStatusView;
    private String msg = "加载中";
    private boolean isShow = true;


    public DefaultSubscriber(IBaseStatusView activity) {
        iBaseStatusView = activity;
    }

    public DefaultSubscriber(IBaseStatusView activity, String loadMessage) {
        iBaseStatusView = activity;
        if (!TextUtils.isEmpty(loadMessage)) msg = loadMessage;
    }

    public DefaultSubscriber(IBaseStatusView activity, boolean isShow) {
        iBaseStatusView = activity;
        this.isShow = isShow;
    }

    public DefaultSubscriber(IBaseStatusView activity, String loadMessage, boolean isShow) {
        iBaseStatusView = activity;
        this.isShow = isShow;
        if (!TextUtils.isEmpty(loadMessage)) msg = loadMessage;

    }
            /***这是Observer的方法***/
    @Override
    public final void onSubscribe(@NonNull Disposable d) {
        if (iBaseStatusView != null) {
            if (isShow) {
                iBaseStatusView.showLoading(msg);
            }
            iBaseStatusView.addRxDestroy(d);
        }
        _onSubscribe(d);
    }

    @Override
    public final void onError(Throwable e) {
        if (iBaseStatusView != null) {
            iBaseStatusView.hideLoading();
        }
        // 调起请求完成
        _onFinish();
        checkError(e);

        _onError(e,e.toString());
    }

    /**
     * 检查默认异常信息
     *
     * @param e
     */
    private final void checkError(Throwable e) {
        String reason;
        if (e instanceof JsonSyntaxException) {//数据格式化错误
            reason = "数据异常,请稍后重试!";
            if (iBaseStatusView != null && !isShow) {
                iBaseStatusView.error(reason);
            }
        } else if (e instanceof HttpException) {// http异常
            reason = "请求服务器异常, 请稍后再试!";
            if (iBaseStatusView != null && !isShow) {
                iBaseStatusView.error(reason);
            }
        } else if (e instanceof UnknownHostException
                || e instanceof ConnectException) {//未连接网络或DNS错误
            reason = "未连接网络或DNS错误,请检查你的网络稍后重试!";
            if (iBaseStatusView != null && !isShow) {
                iBaseStatusView.noNet();
            }
        } else if (e instanceof NetworkErrorException) {
            reason = "网络错误,请检查你的网络!";
        } else if (e instanceof NoNetException) {
            if (iBaseStatusView != null && !isShow) {
                iBaseStatusView.noNet();
            }
            reason = "网络错误,请检查你的网络!";
        } else if (e instanceof SocketException
                || e instanceof SocketTimeoutException) {
            reason = "请求超时, 请稍后再试!";
            if (iBaseStatusView != null && !isShow) {
                iBaseStatusView.noNet();
            }
        } else if (e instanceof DefaultErrorException) {
            reason = e.getMessage();
            if (((DefaultErrorException) e).code == 401) {
                AppManager.jumpAndFinish(LoginActivity.class);
                reason = "请登录";
            }
            if (((DefaultErrorException) e).code == 404) {
            }
            if (iBaseStatusView != null && !isShow) {
                iBaseStatusView.error(reason);
            }
        } else if (e instanceof IllegalStateException
                && !TextUtils.isEmpty(e.getMessage())
                && e.getMessage().contains("STRING")) {
            e = new EmptyDataException();
            reason = "暂无数据";
            if (iBaseStatusView != null && !isShow) {
                iBaseStatusView.error(reason);
            }
        } else if (e instanceof NullPointerException) {
            reason = "网络错误,请检查你的网络!";
            if (iBaseStatusView != null && !isShow) {
                iBaseStatusView.error(reason);
            }
        } else if (e instanceof NoWifiException) {
            reason = "当前不在wifi状态下, 继续将消耗流量";
        } else {
            reason = "未知错误,请稍后重试!";
            if (iBaseStatusView != null && !isShow) {
                iBaseStatusView.error(reason);
            }
        }
        Logger.e("error --" + e.getClass().getName() + "\n message = " + e.getMessage());
    }


    @Override
    public final void onComplete() {

    }

    @Override
    public final void onNext(T entity) {
        if (iBaseStatusView != null) {
            iBaseStatusView.hideLoading();
        }
        _onFinish();

        _onNext(entity);
    }

    /**
     * @param d
     */
    protected void _onSubscribe(Disposable d) {
    }

    ;

    /**
     * 回调异常
     *
     * @param e
     * @param msg
     */
    public void _onError(Throwable e, String msg) {
        FarmToastUtils.show(msg);
    }

    /**
     * 当请求结束前调用
     */
    public void _onFinish() {

    }

    /**
     * 请求成功回调
     *
     * @param entity
     */
    public abstract void _onNext(T entity);
}
