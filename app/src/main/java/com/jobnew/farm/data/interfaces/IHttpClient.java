package com.jobnew.farm.data.interfaces;

import retrofit2.Retrofit;

/**
 * Created by wufengqiao on 2017/4/6.
 * Function:
 * Desc:
 */
public interface IHttpClient {

    /**
     * 获取Retrofit
     * @param checkLogin  是否检查登录验证
     * @return
     */
    public Retrofit getClient(boolean checkLogin);

    /**
     * 获取不需要验证登录的service
     *
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T getService(Class<T> clazz);

    /**
     * 获取验证登录的service
     *
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T getLoginService(Class<T> clazz);

}
