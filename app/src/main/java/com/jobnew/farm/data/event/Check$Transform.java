package com.jobnew.farm.data.event;


import com.jobnew.farm.data.exception.DefaultErrorException;
import com.jobnew.farm.data.exception.EmptyDataException;
import com.jobnew.farm.data.exception.NullDataException;
import com.jobnew.farm.data.utils.NullUtils;
import com.jobnew.farm.entity.base.BaseEntity;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wufengqiao on 2017/4/8.
 * Function: 检查返回数据 --> 去除BaseEntity
 * Desc:    checkNull: 是否检查BaseEntity中data是否为空
 */
public class Check$Transform<T> implements ObservableTransformer<BaseEntity<T>, T> {
    private  boolean checkNull;

    public Check$Transform() {
        this(true);
    }

    public Check$Transform(boolean checkNull) {
        this.checkNull = checkNull;
    }

    @Override
    public ObservableSource<T> apply(@NonNull Observable<BaseEntity<T>> observable) {
            return observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(new Function<BaseEntity<T>, ObservableSource<T>>() {
                        @Override
                        public ObservableSource<T> apply(@NonNull BaseEntity<T> result) throws Exception {
                            if (result == null) {
                                return Observable.error(new NullDataException());
                            }
                            if (result.code == 200) {
                                T data = result.data;
                                if (checkNull && NullUtils.isNull(data)) {
                                    return Observable.error(new EmptyDataException());
                                }
                                return Observable.just(data);
                            } else {
                                return Observable.error(
                                        new DefaultErrorException(result.code, result.msg));
                            }
                        }
                    });
    }
}
