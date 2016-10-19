package com.li.rr.mvp.presenter.fragment;

import com.li.rr.mvp.bean.FileModel;
import com.li.rr.mvp.interactor.FragmentPathListInteractorImpl;
import com.li.rr.mvp.view.view.IFragmentPathView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mingwei Li on 2016/5/26.
 */
public class FragmentPathPresenter {
   private static IFragmentPathView iFragmentPathView=null;
    private static FragmentPathListInteractorImpl fragmentPathListInteractor=null;
    private static FragmentPathPresenter fpp=null;
    private FragmentPathPresenter(){}
    protected FragmentPathPresenter (IFragmentPathView i){
        this.iFragmentPathView=i;
        fragmentPathListInteractor=new FragmentPathListInteractorImpl();
    }
    public static FragmentPathPresenter getInstance(IFragmentPathView i){
        if (null==fpp){
            synchronized (FragmentPathPresenter.class){
                if(null == fpp){
                    fpp=new FragmentPathPresenter(i);
                }
            }
        }
        return fpp;
    }

    public void getDirsList(String path)  {
        fragmentPathListInteractor.getDirsList(path)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FileModel>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        iFragmentPathView.onStartLoadDirList();
                    }
                    @Override
                    public void onCompleted() {
                        iFragmentPathView.onCompletLoadDirList();
                    }
                    @Override
                    public void onError(Throwable e) {
                        iFragmentPathView.onLoadDirError(e.getMessage());
                    }
                    @Override
                    public void onNext(FileModel fileModel) {
                        iFragmentPathView.getDirList(fileModel);
                    }
                });
    }
}
