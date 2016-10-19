package com.li.rr.mvp.interactor;

import android.content.Context;

import com.li.rr.mvp.bean.FileModel;
import com.li.rr.util.file.FileUtils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/6/2.
 */
public class ActivityCategoryInteractorImpl implements IActivityCategoryInteractor {
    @Override
    public Observable<FileModel> getMusicList(Context context) {
        return Observable.from(FileUtils.getInstance()
                .getMusicInfo(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
