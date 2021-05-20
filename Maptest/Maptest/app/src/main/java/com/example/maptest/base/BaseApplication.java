package com.example.maptest.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;

import com.example.maptest.BuildConfig;
import com.example.maptest.util.ActivityManage;
import com.example.maptest.util.Constants;
import com.example.maptest.util.CrashHandler;
import com.example.maptest.util.CrashHandlerManage;
import com.example.maptest.util.net.OkGoConfig;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogcatLogStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

public class BaseApplication  extends Application {

    private static String TAG = "LifeCycleApplication";

    //全局唯一的context
    private static BaseApplication application;


    //Activity管理器
    private ActivityManage activityManage;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        application = this;

    }


    @Override
    public void onCreate() {
        super.onCreate();
        activityManage = new ActivityManage();
        //日志打印
        initLogger();
        //Crash
        initCrashManage();
        //网络请求
        OkGoConfig.initOkGo(this);
    }


    public int count = 0;

    private Activity currentActivity;


    /**
     * 初始化日志打印框架
     */
    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)                   //（可选）是否显示线程信息。 默认值为true
                .methodCount(2)                          //（可选）要显示的方法行数。 默认2
                .methodOffset(7)                         //（可选）设置调用堆栈的函数偏移值，0的话则从打印该Log的函数开始输出堆栈信息，默认是0
                .logStrategy(new LogcatLogStrategy())    //（可选）更改要打印的日志策略。 默认LogCat
                .tag(Constants.LOGGER)                              //（可选）每个日志的全局标记。 默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {

                return BuildConfig.DEBUG;
            }
        });

    }


    /**
     * 初始化崩溃管理器
     */
    private void initCrashManage() {
        if (BuildConfig.DEBUG) {
            CrashHandlerManage.getInstance()
                    .init(getApplicationContext());
            CrashHandler.getInstance().setDelayTime(1000 * 60 * 1).init(getApplicationContext());
        }

    }

    /**
     * 获取全局唯一上下文
     *
     * @return BaseApplication
     */
    public static BaseApplication getApplication() {
        return application;
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        Intent home=new Intent(Intent.ACTION_MAIN);
        home.addCategory(Intent.CATEGORY_HOME);
        home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(home);
    }

    /**
     * 返回Activity管理器
     */
    public ActivityManage getActivityManage() {
        if (activityManage == null) {
            activityManage = new ActivityManage();
        }
        return activityManage;
    }

    @Override
    public void onTerminate() {//终止
        // 程序终止的时候执行
        Log.d(TAG, "onTerminate");

        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        Log.d(TAG, "onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) { //推到后台
        // 程序在内存清理的时候执行（回收内存）
        // HOME键退出应用程序、长按MENU键，打开Recent TASK都会执行
        Log.d(TAG, "onTrimMemory");
        super.onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

    /**
     * 退出登录
     */
    public void logOut() {

    }

}
