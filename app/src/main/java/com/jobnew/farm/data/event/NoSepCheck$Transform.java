package com.jobnew.farm.data.event;


import com.jobnew.farm.data.exception.DefaultErrorException;
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
 * Function: 检查返回数据 -- 不检查data数据
 * Desc:
 */
public class NoSepCheck$Transform<T extends BaseEntity> implements ObservableTransformer<T, T> {
    private final boolean checkNull;

    public NoSepCheck$Transform() {
        this(true);
    }

    public NoSepCheck$Transform(boolean checkNull) {
        this.checkNull = checkNull;
    }

    @Override
    public ObservableSource<T> apply(@NonNull Observable<T> observable) {
            return observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(new Function<T, ObservableSource<T>>() {
                        @Override
                        public ObservableSource<T> apply(@NonNull T result) throws Exception {
                            if (result == null) {
                                return Observable.error(new NullDataException());
                            }
                            if (result.code == 200 ) {
                                return Observable.just(result);
                            } else {
                                return Observable.error(
                                        new DefaultErrorException(result.code, result.msg));
                            }
                        }
                    });
    }

}
