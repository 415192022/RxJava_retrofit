package com.li.rr.mvp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.li.rr.base.fragment.BaseFragmentV4;
import com.li.rr.mvp.view.adapter.AdapterXViewPager;
import com.li.rr.widget.XViewPager;

import rr.li.com.rxjavaretrofit.R;

/**
 * Created by Administrator on 2016/5/24.
 */
public class Fragment1 extends BaseFragmentV4 {
     TabLayout fragment1_XVP_tab;
    XViewPager fragment1_XVP;

    @Override
    public int bindLayout() {
        return R.layout.fragment_1;
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
        fragment1_XVP= (XViewPager) view.findViewById(R.id.fragment1_XVP);
        if (fragment1_XVP != null) {
            setupViewPager(fragment1_XVP);
        }
        fragment1_XVP_tab= (TabLayout) view.findViewById(R.id.fragment1_XVP_tab);
        fragment1_XVP_tab.setTabMode(TabLayout.MODE_FIXED);
        if (fragment1_XVP != null) {
            fragment1_XVP_tab.setupWithViewPager(fragment1_XVP);
        }
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    private void setupViewPager(ViewPager viewPager) {
        AdapterXViewPager adapter = new AdapterXViewPager(getActivity().getSupportFragmentManager());
        adapter.addFragment(new Fragment1OfChild1(), "1");
        adapter.addFragment(new Fragment1OfChild2(), "2");
        adapter.addFragment(new Fragment1OfChild3(), "3");
        adapter.addFragment(new Fragment1OfChild4(), "4");
        viewPager.setAdapter(adapter);
    }
}



