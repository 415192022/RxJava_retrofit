package com.li.rr.minterface;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2016/6/6.
 */
public interface ITest {
    Observable<List<String>> query(String url);
    Observable<String> getTitle(String url);
}
