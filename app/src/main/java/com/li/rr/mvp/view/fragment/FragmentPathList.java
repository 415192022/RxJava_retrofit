package com.li.rr.mvp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.li.rr.base.fragment.BaseFragmentV4;
import com.li.rr.mvp.bean.FileModel;
import com.li.rr.mvp.presenter.fragment.FragmentPathPresenter;
import com.li.rr.mvp.view.adapter.AdapterPathList;
import com.li.rr.mvp.view.view.IFragmentPathView;
import com.li.rr.util.ApiConstants;
import com.li.rr.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import rr.li.com.rxjavaretrofit.R;

/**
 * Created by LMW on 2016/5/24.
 */
public class FragmentPathList extends BaseFragmentV4 implements IFragmentPathView,SwipeRefreshLayout.OnRefreshListener {
   //下拉刷新
    SwipeRefreshLayout srl_fragment3;
    //dir列表
    RecyclerView rv_fragment3;
    //dir列表适配器
    AdapterPathList adapterPathList=null;
    FragmentPathPresenter fragmentPathPresenter=null;
    @Override
    public int bindLayout() {
        return R.layout.fragment_path_list;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        srl_fragment3= (SwipeRefreshLayout) view.findViewById(R.id.srl_fragment3);
        //设置卷内的颜色
        srl_fragment3.setColorSchemeResources(R.color.sr_color_primary,R.color.colorAccent);
        List<Integer> list=new ArrayList<>();
        rv_fragment3= (RecyclerView) view.findViewById(R.id.rv_fragment3);
        rv_fragment3.setHasFixedSize(true);
        //线性列表
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
//        网格
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        rv_fragment3.setLayoutManager(mLinearLayoutManager);
        rv_fragment3.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true, true);
        rv_fragment3.addItemDecoration(dividerItemDecoration);
    }
    @Override
    public void doBusiness(Context mContext)  {
        adapterPathList=AdapterPathList.getInstance(mContext);
        //初始化P
        fragmentPathPresenter=FragmentPathPresenter.getInstance(this);
        //加载指定路径下的目录
        fragmentPathPresenter.getDirsList(ApiConstants.Paths.BASE_PATH);
        srl_fragment3.setOnRefreshListener(this);
        rv_fragment3.setAdapter(adapterPathList);
    }


    @Override
    public void getDirList(FileModel fileModel) {
        adapterPathList.addDatas(fileModel);

    }

    @Override
    public void onStartLoadDirList() {
        if(adapterPathList !=null && adapterPathList.getDatas().size()>0){
            adapterPathList.clearAllDatas();
        }
        srl_fragment3.setRefreshing(true);
    }
    @Override
    public void onCompletLoadDirList() {
        adapterPathList.notifyDataSetChanged();
        srl_fragment3.setRefreshing(false);
    }

    @Override
    public void onLoadDirError(String er) {
        Toast.makeText(getActivity(),er,Toast.LENGTH_LONG).show();
        srl_fragment3.setRefreshing(false);
    }

    /**
     * 刷新监听事件
     */
    @Override
    public void onRefresh() {
        if(adapterPathList !=null){
            adapterPathList.clearAllDatas();
        }
        fragmentPathPresenter.getDirsList(ApiConstants.Paths.BASE_PATH);
    }


}



