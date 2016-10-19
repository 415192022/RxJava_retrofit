package com.li.rr.mvp.interactor;

import com.li.rr.mvp.bean.FileModel;
import com.li.rr.util.file.FileUtils;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/5/26.
 */
public class FragmentPathListInteractorImpl implements IFragmentPathListInteractor {
    @Override
    public Observable<FileModel> getDirsList(final String path) {
        return Observable
                .from(
                        FileUtils.getInstance()
                                .getDirBelowPath(path)
                )
                .observeOn(
                        Schedulers.newThread()
                )
                .subscribeOn(
                        Schedulers.newThread()
                );
    }
}
