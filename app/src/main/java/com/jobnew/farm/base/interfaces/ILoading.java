package com.jobnew.farm.base.interfaces;

/**
 * Created by wufenegqiao on 2017/5/17.
 * Function:
 * Desc:
 */
public interface ILoading extends IRxJava{

    /**
        * 显示加载dialog
     *
             * @param msg
     */
    void showLoading(String msg);

    /**
     * 隐藏加载dialog
     */
    void hideLoading();
}
