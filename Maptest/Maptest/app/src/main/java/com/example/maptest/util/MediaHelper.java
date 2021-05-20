package com.example.maptest.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MediaHelper {
    public static String MEDIA_PATH = null;

    static {
        MEDIA_PATH = setPath();
    }

    private static String setPath() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getPath() + "/MediaZZ/";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdir();
                return dir.getAbsolutePath()+"/";
            } else {
                dir = null;
                return path;
            }

        } else {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        }

    }

    public static String getName(String id) {
        return new SimpleDateFormat("yyMMddHHmmss", Locale.CHINA).format(new Date()) +".aac"  ;
    }

    public static String getPath() {
        if (MEDIA_PATH.equals(Environment.getExternalStorageDirectory().getAbsolutePath())) {
            MEDIA_PATH = setPath();
        }
        return MEDIA_PATH;
    }

    public static void delete(ArrayList<String> keep) {
        File dir = new File(getPath());
        if (dir.exists()) {
            File[] files = dir.listFiles();
            boolean skip = (keep == null || keep.size() == 0);
            for (int i = 0; i < files.length; i++) {
                if (skip || !keep.contains(files[i].getName())) {
                    files[i].delete();
                }
            }
        }
    }
}
