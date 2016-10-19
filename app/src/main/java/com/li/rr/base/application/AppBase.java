package com.li.rr.base.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.li.rr.util.socket.SocketUtils;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Administrator on 2016/4/12.
 */
public abstract class AppBase extends Application {
    private static AppBase app;
    /**
     * 整个应用全局可访问数据集合
     **/
    private static Map<String, Object> gloableData = new HashMap<String, Object>();
    /***
     * 寄存整个应用Activity
     **/
    private final Stack<WeakReference<Activity>> activitys = new Stack<WeakReference<Activity>>();
    /***
     * 寄存整个应用Fragment
     **/
    private final Stack<WeakReference<Fragment>> fragments = new Stack<WeakReference<Fragment>>();

    /**
     * 获得当前application唯一实例
     *
     * @return
     */
    public static AppBase getApplicationInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("=====appBase onCreate========");
        app = this;
        init();
    }

    /**
     * 初始化数据
     */
    public void init() {
        System.out.println("init");
    }

    /*******************************************************Application数据操作API（开始）********************************************************/

    /**
     * 往Application放置数据（最大不允许超过5个）
     *
     * @param strKey   存放属性Key
     * @param strValue 数据对象
     */
    public static void assignData(String strKey, Object strValue) {
        if (gloableData.size() > 5) {
            throw new RuntimeException("超过允许最大数");
        }
        gloableData.put(strKey, strValue);
    }

    /**
     * 从Applcaiton中取数据
     *
     * @param strKey 存放数据Key
     * @return 对应Key的数据对象
     */
    public static Object gainData(String strKey) {
        return gloableData.get(strKey);
    }

    /*
     * 从Application中移除数据
	 */
    public static void removeData(String key) {
        if (gloableData.containsKey(key)) gloableData.remove(key);
    }


    /*******************************************************Application数据操作API（结束）********************************************************/

    /*******************************************Application中存放的Fragment操作（压栈/出栈）API（开始）*****************************************/

    /**
     * 将Fragment压入Application栈
     *
     * @param fragmentWeakReference 将要压入栈的Fragment对象
     */
    public <T> void pushFragment(WeakReference<Fragment> fragmentWeakReference, Class<T> clzz) {
        if (!fragmentIsExsist(clzz)) {
            fragments.push(fragmentWeakReference);
        }
    }

    /**
     * 检查要进栈的frgment对象是否在栈中存在
     *
     * @param clzz
     * @param <T>
     * @return
     */
    public <T> boolean fragmentIsExsist(Class<T> clzz) {
        for (int i = 0; i < fragments.size(); i++) {
            if (clzz.getName().equals(fragments.get(i).get().getClass().getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据指定位置从栈中移除Fragment
     *
     * @param clzz Fragment类class
     */
    public <T extends Object> void removeFragmentByClass(Class<T> clzz) {
        for (int i = 0; i < fragments.size(); i++) {
            if (clzz.getName().equals(fragments.get(i).get().getClass().getName())) {
                Fragment fragment = fragments.get(i).get();
                if (fragment != null) {
                    fragments.remove(i);
                    fragment.onDetach();
                    fragment.onDestroy();
                    break;
                }
            }
        }
    }

    /**
     * 获取指定Fragment的打开关闭状态
     *
     * @param clzz
     * @param <T>
     * @return
     */
    public <T extends Context> boolean fragmentIsOpen(Class<T> clzz) {
        for (WeakReference<Fragment> fragmentWeakReference : fragments) {
            if (clzz.getName().equals(fragmentWeakReference.get().getClass().getName())) {
                Fragment fragment = fragmentWeakReference.get();
                if (null != fragment && !fragment.isDetached()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获得当前已被打开的Fragment对象
     *
     * @param clzz
     * @param <T>
     * @return
     */
    public <T extends Object> T getFragmentObject(Class<T> clzz) {
        for (WeakReference<Fragment> fragmentWeakReference : fragments) {
            if (clzz.getName().equals(fragmentWeakReference.get().getClass().getName())) {
                Fragment fragment = fragmentWeakReference.get();
                if (fragment != null) {
                    return (T) fragment;
                }
            }
        }
        return null;
    }

    /**
     * 获得当前应用程序所附加的fragment数量
     *
     * @return
     */
    public int getAllFragmentCout() {
        return fragments.size();
    }

    /**
     * 获得当前应用程序所附加的fragment栈
     *
     * @return
     */
    public Stack<WeakReference<Fragment>> getAllFragment() {
        return fragments;
    }


    /**
     * 将栈中Fragment移除至栈顶
     */
    public void removeFragmentToTop() {
        int end = fragments.size();
        int start = 1;
        for (int i = end - 1; i >= start; i--) {
            Fragment fragment = fragments.get(i).get();
            if (null != fragment && !fragment.isDetached()) {
                fragment.onDetach();
                fragment.onDestroy();
            }
        }
    }

    /**
     * 移除全部Fragment
     */
    public void removeAllFragment() {
        fragments.clear();
        System.out.println("fragments" + fragments.size());
    }


    /**
     * 此方法用于碎片的跳转
     *
     * @param fragmentActivity 需要一个fragmentActivity
     * @param root             需要一个FrameLayout
     */
    public <T extends Fragment> void skipFragmentMainActivity(FragmentActivity fragmentActivity,
                                                              int root, Class<T> clzz) {
        T t = null;
        try {
            if (fragmentIsExsist(clzz)) {
                t = getFragmentObject(clzz);
            } else {
                t = clzz.newInstance();
                //如果软引用栈中没有当前传入的Fragment对象，则添加到栈中
                WeakReference<Fragment> weakReference = new WeakReference<Fragment>(t);
                App.getApplicationInstance().pushFragment(weakReference, clzz);
            }
            FragmentTransaction fragmentTransaction = fragmentActivity
                    .getSupportFragmentManager().beginTransaction();
            System.out.println(t.getClass().getName());
            fragmentTransaction.replace(root, t);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

//        android.support.v4.app.FragmentManager fm = fragmentActivity
//                .getSupportFragmentManager();
//
//        int num = fm.getBackStackEntryCount();
//        if (num > 0) {
//            // 回退自身之上所有碎片，包括自身
//            fm.popBackStackImmediate(null, 1);
//        }
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        fragmentTransaction.replace(root, t);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*******************************************Application中存放的Fragment操作（压栈/出栈）API（结束）*****************************************/

    /*******************************************Application中存放的Activity操作（压栈/出栈）API（开始）*****************************************/

    /**
     * 将Activity压入Application栈
     *
     * @param task 将要压入栈的Activity对象
     */
    public void pushTask(WeakReference<Activity> task) {
        activitys.push(task);
    }

    /**
     * 将传入的Activity对象从栈中移除
     *
     * @param task
     */
    public void removeTask(WeakReference<Activity> task) {
        activitys.remove(task);
    }

    /**
     * 根据指定位置从栈中移除Activity
     *
     * @param taskIndex Activity栈索引
     */
    public void removeActivityByIndex(int taskIndex) {
        if (activitys.size() > taskIndex)
            activitys.remove(taskIndex);
    }

    /**
     * 根据指定位置从栈中移除Activity
     *
     * @param clzz Activity栈索引
     */
    public <T extends Context> void removeActivityByClass(Class<T> clzz) {
        for (int i = 0; i < activitys.size(); i++) {
            if (clzz.getName().equals(activitys.get(i).get().getClass().getName())) {
                Activity mActivity = activitys.get(i).get();
                activitys.remove(i);
                mActivity.finish();
                break;
            }
        }
    }

    /**
     * 获取指定Activity的打开关闭状态
     *
     * @param clzz
     * @param <T>
     * @return
     */
    public <T extends Context> boolean activityIsOpen(Class<T> clzz) {
        for (WeakReference<Activity> activityWeakReference : activitys) {
            if (clzz.getName().equals(activityWeakReference.get().getClass().getName())) {
                Activity mActivity = activityWeakReference.get();
                if (null != mActivity && !mActivity.isFinishing()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获得当前已被打开的Activity对象
     *
     * @param clzz
     * @param <T>
     * @return
     */
    public <T extends Context> T getActivityObject(Class<T> clzz) {
        for (WeakReference<Activity> activityWeakReference : activitys) {
            if (clzz.getName().equals(activityWeakReference.get().getClass().getName())) {
                Activity mActivity = activityWeakReference.get();
                if (mActivity != null) {
                    return (T) mActivity;
                }
            }
        }
        return null;
    }

    /**
     * 将栈中Activity移除至栈顶
     */
    public void removeActivityToTop() {
        int end = activitys.size();
        int start = 1;
        for (int i = end - 1; i >= start; i--) {
            Activity mActivity = activitys.get(i).get();
            if (null != mActivity && !mActivity.isFinishing()) {
                mActivity.finish();
            }
        }
    }

    /**
     * 移除全部（用于整个应用退出）
     */
    public void removeAllActivity() {
        //finish所有的Activity
        for (WeakReference<Activity> task : activitys) {
            Activity mActivity = task.get();
            if (null != mActivity && !mActivity.isFinishing()) {
                mActivity.finish();
            }
        }
        activitys.clear();
    }

    /**
     * 获得当前应用所有被打开的Activity的软引用栈
     *
     * @return
     */
    public Stack<WeakReference<Activity>> getAllActivity() {
        return activitys;
    }

    /**
     * 获得当前应用所有被打开的Activity的数量
     *
     * @return
     */
    public int getAllActivityCount() {
        return activitys.size();
    }

    /**
     * 退出整个APP，关闭所有activity/清除缓存等等
     */
    public abstract void exit();

    /*******************************************Application中存放的Activity操作（压栈/出栈）API（结束）*****************************************/

}
