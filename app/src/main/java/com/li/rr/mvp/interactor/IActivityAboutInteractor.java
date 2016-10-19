package com.li.rr.mvp.interactor;

import com.li.rr.mvp.bean.FileModel;

import rx.Observable;

/**
 * Created by Administrator on 2016/6/7.
 */
public interface IActivityAboutInteractor {
    Observable<FileModel> getFiles(String path);
}
