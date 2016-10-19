package com.li.rr.mvp.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.li.rr.base.activity.BaseSwipeBackActivity;
import com.li.rr.mvp.bean.FileModel;
import com.li.rr.mvp.presenter.activity.ActivityCategoryPresenter;
import com.li.rr.mvp.view.adapter.AdapterFileList;
import com.li.rr.mvp.view.adapter.AdapterPathList;
import com.li.rr.mvp.view.view.IActivityCategoryView;

import butterknife.InjectView;
import rr.li.com.rxjavaretrofit.R;

/**
 * Created by Administrator on 2016/6/2.
 */
public class ActivityCategory extends BaseSwipeBackActivity implements IActivityCategoryView,SwipeRefreshLayout.OnRefreshListener {
    @InjectView(R.id.lv_activity_category)
    ListView lv_activity_category;
    @InjectView(R.id.srl_activity_category)
    SwipeRefreshLayout srl_activity_category;
    ActivityCategoryPresenter activityCategoryPresenter=ActivityCategoryPresenter.getInstance(this);
    @Override
    protected boolean isApplyKitKatTranslucency() {
        getSupportActionBar().setTitle("音乐");
        return true;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void doBusiness(Context context) {
        AdapterFileList adapterPathList=AdapterFileList.getInstance(this);
        lv_activity_category.setAdapter(adapterPathList);
        srl_activity_category.setOnRefreshListener(this);
        srl_activity_category.setColorSchemeResources(R.color.sr_color_primary,R.color.colorAccent);
        activityCategoryPresenter.getMusicList(context);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_category_layout;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }





    //====================加载音乐列表=======================================
    @Override
    public void getMusicList(FileModel fileModel) {
        AdapterFileList.getInstance(this).addDatas(fileModel);
    }

    @Override
    public void onStartLoadMusicList() {
        if(AdapterFileList.getInstance(this).getDatas() != null && AdapterFileList.getInstance(this).getDatas().size()>0){
            AdapterFileList.getInstance(this).clearAllDatas();
        }
//            srl_activity_category.setRefreshing(true);
    }

    @Override
    public void onCompletLoadMusicList() {
        AdapterFileList.getInstance(this).notifyDataSetChanged();
//            srl_activity_category.setRefreshing(false);
    }

    @Override
    public void onLoadMusicError(String er) {
        Toast.makeText(this,er,Toast.LENGTH_SHORT).show();
            srl_activity_category.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        if(null == activityCategoryPresenter){
            AdapterPathList.getInstance(this).clearAllDatas();
        }
        activityCategoryPresenter.getMusicList(this);
    }
}
