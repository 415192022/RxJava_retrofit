package com.li.rr.base.activity.swipeback;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.li.rr.base.activity.BaseActivity;

import butterknife.InjectView;
import rr.li.com.rxjavaretrofit.R;

public abstract  class BaseActivityWithSlidMenu extends BaseActivity  {
    @InjectView(R.id.home_drawer)
    DrawerLayout mDrawerLayout;


    @Override
    protected boolean isApplyKitKatTranslucency() {
        return false;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }



    @Override
    protected View getLoadingTargetView() {
        return  null;
    }

    @Override
    protected void initViewsAndEvents() {
        if(0 != setDraweLayoutId()){
            mDrawerLayout= (DrawerLayout) findViewById(setDraweLayoutId());
            mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {
                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    setTitle(getString(R.string.app_name));
                }
                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                }
            };

            mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
            mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
            mActionBarDrawerToggle.syncState();
        }
    }


    public abstract int setDraweLayoutId();

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

    private ActionBarDrawerToggle mActionBarDrawerToggle = null;
    private static long DOUBLE_CLICK_TIME = 0L;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
            return true;
        } else if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                if ((System.currentTimeMillis() - DOUBLE_CLICK_TIME) > 2000) {
                    DOUBLE_CLICK_TIME = System.currentTimeMillis();
                    Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();
                } else {
//                    Intent intent=new Intent(BaseActivityWithSlidMenu.this,ActivityAbout.class);
//                    startActivity(intent);
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
