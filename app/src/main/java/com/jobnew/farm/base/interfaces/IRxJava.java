package com.jobnew.farm.base.interfaces;

import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/5/31.
 */

public interface IRxJava {
    public boolean addRxStop(Disposable disposable);

    public boolean addRxDestroy(Disposable disposable);

    public void remove(Disposable disposable);
}
