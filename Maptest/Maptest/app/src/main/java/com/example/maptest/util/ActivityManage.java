package com.example.maptest.util;

import android.app.Activity;


import com.example.maptest.SetActivity;
import com.example.maptest.base.BaseApplication;

import java.util.ArrayList;

public class ActivityManage {

    //保存所有创建的Activity
    private ArrayList<Activity> allActivities = new ArrayList<>();

    /**
     * 添加Activity到管理器
     *
     * @param activity activity
     */
    public void addActivity(Activity activity) {
        if (activity != null) {
            allActivities.add(activity);
        }
    }


    /**
     * 从管理器移除Activity
     *
     * @param activity activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            allActivities.remove(activity);
            activity.finish();
        }
    }

    /**
     * 关闭所有Activity
     */
    public void finishAll() {
        for (Activity activity : allActivities) {
            activity.finish();
        }
        SharedPreUtils.create(BaseApplication.getApplication()).clear();//清除所有缓存
    }

}
