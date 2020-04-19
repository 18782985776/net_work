package com.jobnew.farm.data.event;


import com.jobnew.farm.MyApplication;
import com.jobnew.farm.data.exception.NoNetException;
import com.jobnew.farm.data.exception.NoWifiException;
import com.jobnew.farm.data.utils.NetworkUtils;
import com.jobnew.farm.entity.base.BaseEntity;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wufengqiao on 2017/4/7.
 * Function: 设置网络拦截
 * Desc:     justWifi 用于设置是否只能在wifi状态下请求, 多用于文件上传
 */
public class CheckNetwork<T extends BaseEntity> implements ObservableTransformer<T, T> {

    // 是否仅Wifi状态下联网(一般用于文件上传指定网络为wifi)
    private boolean justWifi = false;

    public CheckNetwork(boolean justWifi) {
        this.justWifi = justWifi;
    }

    @Override
    public ObservableSource<T> apply(@NonNull Observable<T> observable) {
        boolean hasNet = false;
        if (justWifi) {
            hasNet = NetworkUtils.isWifi(MyApplication.getInstance());
        } else {
            hasNet = NetworkUtils.isConnected(MyApplication.getInstance());
        }
        return hasNet ? observable.subscribeOn(Schedulers.io())
                : Observable.error(justWifi ? new NoWifiException() : new NoNetException());
    }
}
