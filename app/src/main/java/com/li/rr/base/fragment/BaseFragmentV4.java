package com.li.rr.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.li.rr.base.application.App;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/14.
 */
public abstract class BaseFragmentV4 extends Fragment implements IBaseFragment {
    /**
     * 当前Fragment渲染的视图View
     **/
    private View mContextView = null;
    /**
     * 依附的Activity
     **/
    protected Activity mContext = null;
    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();

    /***
     * 整个应用Applicaiton
     **/
    private App mApplication = null;

    private WeakReference<Fragment> fragmentWeakReference = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //缓存当前依附的activity
        mContext = activity;
        Log.d(TAG, "BaseFragmentV4-->onAttach()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "BaseFragmentV4-->onCreate()");
        mApplication = (App) App.getApplicationInstance();
        fragmentWeakReference = new WeakReference<Fragment>(this);
        mApplication.pushFragment(fragmentWeakReference, getClass());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "BaseFragmentV4-->onCreateView()");

        // 渲染视图View
        if (null == mContextView) {
            //初始化参数
            initParms(getArguments());
            View mView = bindView();
            if (null == mView) {
                mContextView = inflater.inflate(bindLayout(), container, false);
            } else {
                mContextView = mView;
            }
            ButterKnife.inject(mContextView,getActivity());
            // 控件初始化
            initView(mContextView);
            // 业务处理
            doBusiness(getActivity());
        }

        return mContextView;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "BaseFragmentV4-->onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "BaseFragmentV4-->onSaveInstanceState()");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "BaseFragmentV4-->onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "BaseFragmentV4-->onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "BaseFragmentV4-->onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "BaseFragmentV4-->onStop()");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "BaseFragmentV4-->onDestroy()");
        super.onDestroy();
        App.getApplicationInstance().removeAllFragment();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "BaseFragmentV4-->onDetach()");
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mContextView != null && mContextView.getParent() != null) {
            ((ViewGroup) mContextView.getParent()).removeView(mContextView);
        }
    }

    /**
     * 获取当前Fragment依附在的Activity
     *
     * @return
     */
    public Activity getContext() {
        return getActivity();
    }




    @Override
    public <T extends View> List<T> setListener(List<T> viewList) {
        return viewList;
    }
    //    @Override
//    public void setMenuVisibility(boolean menuVisible) {
//        // TODO Auto-generated method stub
//        System.out.println("Browser" + menuVisible);
//        super.setMenuVisibility(menuVisible);
//        if (getView() != null) {
//            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
//        }
//    }



}
