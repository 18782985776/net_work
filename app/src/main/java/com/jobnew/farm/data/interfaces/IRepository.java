package com.jobnew.farm.data.interfaces;

import java.util.ArrayList;

/**
 * Create By wufengqiao on 2017/5/26.
 * Function:
 * Desc:
 */
public interface IRepository<SERVICE> {

    /**
     * 获取Service
     *
     * @return
     */
    SERVICE getService();

    /**
     * 获取指定类型的service
     * 用于在当前仓库调用另一仓库的接口
     *
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T getService(Class<T> clazz);

    /**
     * 获取当前仓库绑定的service
     * 需验证登录的
     *
     * @return
     */
    SERVICE getLoginService();

    /**
     * 获取验证登录并指定类型的service
     * 用于在当前仓库调用另一仓库的接口
     *
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T getLoginService(Class<T> clazz);

    /**
     * 获取上传图片service
     *
     * @param <T>
     * @return
     */
    <T> T getUploadService();

    /**
     * 获取上传图片service
     *
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T getUploadService(Class<T> clazz);

    /**
     * 向当前仓库绑定service
     *
     * @return
     */
    Class<SERVICE> bindService();
}
