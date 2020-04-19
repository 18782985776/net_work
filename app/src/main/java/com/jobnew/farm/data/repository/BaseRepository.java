package com.jobnew.farm.data.repository;

import android.util.JsonReader;

import com.google.gson.Gson;
import com.jobnew.farm.data.event.Check$Transform;
import com.jobnew.farm.data.event.CheckNetwork;
import com.jobnew.farm.data.event.NoSepCheck$Transform;
import com.jobnew.farm.data.helper.RetrofitHelp;
import com.jobnew.farm.data.interfaces.IRepository;
import com.jobnew.farm.entity.base.BaseEntity;


import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * Create By wufengqiao on 2017/5/26.
 * Function: 网络请求仓库基类
 * Desc:
 */
public abstract class BaseRepository<SERVICE> implements IRepository<SERVICE> {


    public static final int DEFAULT_PAGE_SIZE = 10; // 默认页数


    /**
     * 网络判断 --> BaseEntity分离 --处理code
     *
     * @param observable
     * @param <T>
     * @return
     */
    protected <T> Observable<T> sepaCheckNet(Observable<BaseEntity<T>> observable) {
        return observable
                .compose(new CheckNetwork<>(false))
                .compose(new Check$Transform<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 网络判断 -- 默认检查处理code
     *
     * @param observable
     * @param <T>
     * @return
     */
    protected <T extends BaseEntity> Observable<T> checkNet(Observable<T> observable) {
        return observable
                .compose(new CheckNetwork<>(false))
                .compose(new NoSepCheck$Transform<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 网络判断 -- 是否检查处理code
     *
     * @param observable
     * @param <T>
     * @return
     */
    protected <T extends BaseEntity> Observable<T> checkNet(Observable<T> observable,boolean isCheck) {
        observable = observable.compose(new CheckNetwork<>(false));
        if (isCheck){
            observable = observable.compose(new NoSepCheck$Transform<>());
        }
        return observable.observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 网络判断  -- 判断是否是WiFi  -- 默认检查处理code
     *
     * @param observable
     * @param <T>
     * @return
     */
    protected <T extends BaseEntity> Observable<T> checkNet(boolean isWifi,Observable<T> observable) {
        return observable
                .compose(new CheckNetwork<T>(isWifi))
                .compose(new NoSepCheck$Transform<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 网络判断  -- 判断是否是WiFi -- 是否检查处理code
     *
     * @param observable
     * @param <T>
     * @return
     */
    protected <T extends BaseEntity> Observable<T> checkNet(boolean isWifi,Observable<T> observable,boolean isCheck) {
        observable = observable.compose(new CheckNetwork<>(isWifi));
        if (isCheck){
            observable = observable.compose(new NoSepCheck$Transform<>());
        }
        return observable.observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 不需判断登录的组合操作--> 网络判断 --> BaseEntity分离 --默认处理code
     *
     * @param isWifi     是否判断在wifi状态下操作(一般用户大文件上传、下载)
     * @param observable
     * @param <T>
     * @return
     */
    protected <T> Observable<T> sepaCheckNet(boolean isWifi, Observable<BaseEntity<T>> observable) {
        return observable
                .compose(new CheckNetwork<>(isWifi))
                .compose(new Check$Transform<>())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 根据绑定的servcie获取service实例
     * @return
     */
    @Override
    public SERVICE getService() {
        return RetrofitHelp.getIns()
                .getService(bindService());
    }

    /**
     * 获取clazz  service实例
     */
    @Override
    public <T> T getService(Class<T> clazz) {
        return RetrofitHelp.getIns()
                .getService(clazz);
    }
    /**
     * 需要登录--->根据绑定的servcie获取service实例
     *
     */
    @Override
    public SERVICE getLoginService() {
        return RetrofitHelp.getIns()
                .getLoginService(bindService());
    }

    /**
     * 需要登录--->获取clazz 的 service实例
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> T getLoginService(Class<T> clazz) {
        return RetrofitHelp.getIns()
                .getLoginService(clazz);
    }

    @Override
    public SERVICE getUploadService() {
        return RetrofitHelp.getIns().getLoginService(bindService());
    }

    @Override
    public <T> T getUploadService(Class<T> clazz) {
        return RetrofitHelp.getIns().getLoginService(clazz);
    }




    /**
     * 创建分页参数集合
     *
     * @return
     */
//    protected Map<String, Object> createParams(PageController controller) {
//        Map<String, Object> params =
//                createParams("pageLength", DEFAULT_PAGE_SIZE);
//        params.put("page",
//                controller == null ? 0 : ++controller.page);
//        return params;
//    }

}
