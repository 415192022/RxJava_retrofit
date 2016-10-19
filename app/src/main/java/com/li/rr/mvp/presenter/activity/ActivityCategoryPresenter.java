package com.li.rr.mvp.presenter.activity;

import android.content.Context;

import com.li.rr.mvp.bean.FileModel;
import com.li.rr.mvp.interactor.ActivityCategoryInteractorImpl;
import com.li.rr.mvp.view.view.IActivityCategoryView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/6/2.
 */
public class ActivityCategoryPresenter {
    private IActivityCategoryView iActivityCategoryView;
    private ActivityCategoryInteractorImpl activityCategoryInteractor;
    private static ActivityCategoryPresenter activityCategoryPresenter;


    private ActivityCategoryPresenter(IActivityCategoryView iActivityCategoryView){
        this.iActivityCategoryView=iActivityCategoryView;
        activityCategoryInteractor=new ActivityCategoryInteractorImpl();
    }
    public static ActivityCategoryPresenter getInstance(IActivityCategoryView iActivityCategoryView){
        if(null == activityCategoryPresenter){
            synchronized (ActivityCategoryPresenter.class){
                if(null == activityCategoryPresenter){
                    activityCategoryPresenter=new ActivityCategoryPresenter(iActivityCategoryView);
                }
            }
        }
        return activityCategoryPresenter;
    }

    public void getMusicList(Context context){
        activityCategoryInteractor.getMusicList(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FileModel>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        iActivityCategoryView.onStartLoadMusicList();
                    }

                    @Override
                    public void onCompleted() {
                        iActivityCategoryView.onCompletLoadMusicList();
                    }

                    @Override
                    public void onError(Throwable e) {
                        iActivityCategoryView.onLoadMusicError(e.getMessage());
                    }

                    @Override
                    public void onNext(FileModel fileModel) {
                        iActivityCategoryView.getMusicList(fileModel);
                    }
                });
    }

}
