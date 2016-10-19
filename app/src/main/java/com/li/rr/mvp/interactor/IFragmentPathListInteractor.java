package com.li.rr.mvp.interactor;

import com.li.rr.mvp.bean.FileModel;

import java.io.IOException;

import rx.Observable;

/**
 * Created by Administrator on 2016/5/26.
 */
public interface IFragmentPathListInteractor {
    Observable<FileModel> getDirsList(String path) throws IOException;
}
