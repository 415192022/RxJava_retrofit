package com.li.rr.base.application;

import android.content.Context;
import android.os.Environment;

import com.li.rr.util.socket.SocketUtils;


/**
 * Created by Administrator on 2016/4/12.
 */
public class App extends AppBase {
    /***Activity之间数据传输数据对象Key**/
    public static final String ACTIVITY_DTO_KEY = "ACTIVITY_DTO_KEY";
    @Override
    public void exit() {
        removeAllActivity();
        System.exit(0);
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }
    public static App getApp(){
        return (App)getApplicationInstance();
    }
    /**
     * 关闭某个已经打开的activity
     * @param clzz Activity栈索引
     * @param <T>
     */
    public <T extends Context>void removeActivityByClass(Class<T> clzz){
        super.removeActivityByClass(clzz);
    }

    /**
     * 删除所有Activity
     * 退出应用程序
     */
    public  void removeAllActivity(){
        super.removeAllActivity();
    }

    /**
     * 判断actity是否被销毁
     * @param clzz
     * @param <T>
     * @return
     */
    public  <T extends Context> boolean activityIsOpen(Class<T> clzz){
        return  super.activityIsOpen(clzz);
    }

    /**
     * 得到某个没有被销毁的activity对象
     * @param clzz
     * @param <T>
     * @return
     */
    public <T extends Context> T getActivityObject(Class<T> clzz){
        return super.getActivityObject(clzz);
    }



}
