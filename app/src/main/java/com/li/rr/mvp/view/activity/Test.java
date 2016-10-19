package com.li.rr.mvp.view.activity;

import rx.Observable;
import rx.functions.Func0;

/**
 * Created by Administrator on 2016/6/3.
 */
public class Test {

    private String name;

    public Observable<String> setName(String name) {
        this.name = name;
        return Observable.just(name);
    }

    public Observable<String> valueObservable(){
        return Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just(name);
            }
        });
    }
}
