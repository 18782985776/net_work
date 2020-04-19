package com.jobnew.farm.data.rxbus;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import javax.security.auth.Subject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by wc on 2017/6/6.
 * Function：
 * desc：
 */

public class RxBus {
    private final FlowableProcessor<Object> mBus;
    private RxBus(){
        mBus= PublishProcessor.create().toSerialized();
    }
    private static class Holder{
        private static RxBus instance=new RxBus();
    }
    public static RxBus getInstance(){
        return Holder.instance;
    }
    public void post(@NonNull Object object){
        mBus.onNext(object);
    }
    public <T>Flowable<T> register(Class<T> clz){
        return mBus.ofType(clz);
    }
    public void unregisterAll(){
        mBus.onComplete();//接触注册
    }
    public boolean hasSubscribers() {
        return mBus.hasSubscribers();
    }
}
