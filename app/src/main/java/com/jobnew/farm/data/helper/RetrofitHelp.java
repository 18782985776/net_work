package com.jobnew.farm.data.helper;



import com.jobnew.farm.BuildConfig;
import com.jobnew.farm.data.interfaces.IHttpClient;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wufenqiao on 2017/4/6.
 * Function:
 * Desc:
 */
public class RetrofitHelp implements IHttpClient {
    private static final String URL_BASE = BuildConfig.BASE_URL;

    private volatile static RetrofitHelp instance;

    private final Retrofit.Builder mBuilder;
    private final Retrofit.Builder mLoginBuilder;
    private Retrofit mLoginRetrofit;
    private Retrofit mRetrofit;

    private RetrofitHelp() {
        mBuilder = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        mLoginBuilder = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    public static RetrofitHelp getIns() {
        if (instance == null) {
            synchronized (RetrofitHelp.class) {
                if (instance == null) {
                    instance = new RetrofitHelp();
                }
            }
        }
        return instance;
    }


    @Override
    public Retrofit getClient(boolean checkLogin) {
        if (checkLogin){
            if (mLoginRetrofit == null) {
                mLoginRetrofit = mLoginBuilder
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(FarmOkHttpHelper.createLogin(checkLogin))
                        .build();
            }
            return mLoginRetrofit;

        }else {
            if (mRetrofit == null) {
                mRetrofit = mBuilder
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(FarmOkHttpHelper.createLogin(checkLogin))
                        .build();
            }
            return mRetrofit;
        }
    }


    @Override
    public <T> T getService(Class<T> clazz) {
        return getClient(false)
                .create(clazz);
    }

    @Override
    public <T> T getLoginService(Class<T> clazz) {
        return getClient(true)
                .create(clazz);
    }
}
