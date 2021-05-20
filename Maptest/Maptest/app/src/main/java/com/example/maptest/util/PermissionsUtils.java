package com.example.maptest.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class PermissionsUtils {
    private static final String MARK = Build.MANUFACTURER.toLowerCase();
    /**
     * 授权所有权限
     *
     * @param activity activity
     */
    public static void authorizationAllPermissions(Activity activity) {
        XXPermissions.with(activity)
                .request(new OnPermission() {
                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                    }
                });
    }

    /**
     * 检查相机权限
     */
    public static Observable<Boolean> checkPermissions(Activity activity, String... permissions) {
        return Observable.<Boolean>create(
                subscriber -> {
                    if (XXPermissions.isHasPermission(activity, permissions)) {
                        subscriber.onNext(true);
                    } else {
                        XXPermissions.with(activity)
                                .permission(permissions)
                                .request(new OnPermission() {
                                    @Override
                                    public void hasPermission(List<String> granted, boolean isAll) {
                                        subscriber.onNext(true);
                                    }

                                    @Override
                                    public void noPermission(List<String> denied, boolean quick) {
                                        subscriber.onNext(false);
                                        Toast.makeText(activity, "您必须授权必要权限才可使用该功能！", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 检查相机权限
     */
    public static Observable<Boolean> checkCamera(Activity activity) {
        return checkPermissions(activity, Permission.CAMERA);
    }

    /**
     * 检查录音相关权限
     */
    public static Observable<Boolean> checkRecord(Activity activity) {
        String[] storage = {
                Permission.READ_EXTERNAL_STORAGE,
                Permission.WRITE_EXTERNAL_STORAGE,
                Permission.RECORD_AUDIO};
        return checkPermissions(activity, storage);
    }

}
