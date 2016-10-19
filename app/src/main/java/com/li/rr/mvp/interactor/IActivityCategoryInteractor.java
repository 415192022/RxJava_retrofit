package com.li.rr.mvp.interactor;

import android.content.Context;

import com.li.rr.mvp.bean.FileModel;

import rx.Observable;

/**
 * Created by Administrator on 2016/6/2.
 */
public interface IActivityCategoryInteractor {
    Observable<FileModel> getMusicList(Context context);
}
