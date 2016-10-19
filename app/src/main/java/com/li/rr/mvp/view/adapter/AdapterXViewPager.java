package com.li.rr.mvp.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/24.
 */
public class AdapterXViewPager extends FragmentPagerAdapter {
    List<Fragment> fragments=new ArrayList<>();
    List<String> fragmentTitles=new ArrayList<>();
    public void addFragment(Fragment fragment,String fragmentTitle){
        fragments.add(fragment);
        fragmentTitles.add(fragmentTitle);
    }

    public AdapterXViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }
}
