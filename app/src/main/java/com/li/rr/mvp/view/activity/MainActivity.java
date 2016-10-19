package com.li.rr.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;

import com.li.rr.base.activity.swipeback.BaseActivityWithSlidMenu;
import com.li.rr.mvp.view.adapter.AdapterXViewPager;
import com.li.rr.mvp.view.fragment.Fragment1;
import com.li.rr.mvp.view.fragment.FragmentBrowsingByType;
import com.li.rr.mvp.view.fragment.FragmentPathList;
import com.li.rr.mvp.view.fragment.FragmentRemotManager;
import com.li.rr.util.socket.SocketUtils;
import com.li.rr.widget.XViewPager;

import butterknife.InjectView;
import rr.li.com.rxjavaretrofit.R;

/**
 * Created by Administrator on 2016/5/24.
 */
public class MainActivity extends BaseActivityWithSlidMenu implements View.OnClickListener{
    @InjectView(R.id.xvp_main)
     XViewPager xvp_main;
    @InjectView(R.id.MianActivity_XVP_tab)
    TabLayout MianActivity_XVP_tab;
    @InjectView(R.id.iv_main_menu)
    ImageView iv_main_menu;
    @Override
    public int setDraweLayoutId() {
        return R.id.home_drawer;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        xvp_main.setEnableScroll(true);

    }

    @Override
    protected void setListener() {
        iv_main_menu.setOnClickListener(this);
    }

    @Override
    protected void doBusiness(Context context) {
        AdapterXViewPager adapterXViewPager=new AdapterXViewPager(getSupportFragmentManager());
        adapterXViewPager.addFragment(new Fragment1(),"主页面1");
        adapterXViewPager.addFragment(new FragmentBrowsingByType(),"分类浏览");
        adapterXViewPager.addFragment(new FragmentPathList(),"目录列表");
        adapterXViewPager.addFragment(new FragmentRemotManager(),"远程管理");
        xvp_main.setAdapter(adapterXViewPager);
        MianActivity_XVP_tab.setTabMode(TabLayout.MODE_FIXED);
        if (xvp_main != null) {
            MianActivity_XVP_tab.setupWithViewPager(xvp_main);
        }
        MianActivity_XVP_tab.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
        SocketUtils.getInstance(this).openServer(Environment.getDataDirectory()+"/1.avi");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_main_menu:
                Intent intent=new Intent(MainActivity.this,ActivityAbout.class);
                startActivity(intent);
                break;
        }
    }
}
