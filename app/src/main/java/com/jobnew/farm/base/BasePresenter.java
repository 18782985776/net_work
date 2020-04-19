package com.jobnew.farm.base;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Created by wufengqiao on 2017/5/17
 * Function：mvp基类
 * Desc：
 */

public class BasePresenter<T> {
    protected WeakReference<T> mView;

    /**
     * Presenter 和 View 进行绑定
     *
     * @param view
     */
    public void attachView(T view) {
        mView = new WeakReference<>(view);
    }

    /**
     * Presenter 和 View 解除绑定
     */
    public void detacheView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    public T getView() {
        if (mView != null) {
            return mView.get();
        } else {
            return null;
        }
    }

    public boolean isViewAttached() {
        return mView != null && mView.get() != null;
    }

    protected <K, V> HashMap<K, V> createParams() {
        return new HashMap<K, V>();
    }

    protected <K, V> HashMap<K, V> createParams(K key, V value) {
        HashMap<K, V> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
