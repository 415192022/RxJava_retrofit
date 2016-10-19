package com.li.rr.mvp.interactor;

import com.li.rr.mvp.bean.FileModel;
import com.li.rr.util.ApiConstants;
import com.li.rr.util.file.FileUtils;

import rx.Observable;

/**
 * Created by Administrator on 2016/6/7.
 */
public class IActivityAboutInteractorImpl implements IActivityAboutInteractor {
    @Override
    public Observable<FileModel> getFiles(String path) {
        return Observable.from(FileUtils.getInstance().getDirBelowPath(ApiConstants.Paths.BASE_PATH))
                ;
    }
}
