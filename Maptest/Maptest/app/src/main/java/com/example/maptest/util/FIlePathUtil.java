package com.example.maptest.util;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.Nullable;

public class FIlePathUtil {
    /**
     * 外置存储卡的路径
     *
     * @return
     */
    @Nullable
    public static String getExternalStorePath(Context context) {
        if (isExistExternalStore()) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//
//                return  context.getExternalFilesDir(null).getAbsolutePath();
//            }
//

            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }
    /**
     * 是否有外存卡
     *
     * @return
     */
    public static boolean isExistExternalStore() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
