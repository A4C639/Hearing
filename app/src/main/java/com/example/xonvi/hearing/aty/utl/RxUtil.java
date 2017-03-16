package com.example.xonvi.hearing.aty.utl;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xonvi on 2017/3/6.
 */

public class RxUtil {

    private RxUtil(){}
    private static final Observable.Transformer transformer = new Observable.Transformer() {
        @Override
        public Object call(Object o) {
            return ((Observable)o)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread());
        }
    };


    public static <T>Observable.Transformer<T,T> transformer(){
        return (Observable.Transformer<T, T>) transformer;

    }




}
