package com.example.maptest.util;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import com.example.maptest.base.BaseApplication;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

/**
 * @ClassName FileUtils
 * @Description   文件帮助类
 * @Author kaiguo
 * @Date 2020/3/17 14:57
 */


public class FileUtils {
    public static final String APPS_ROOT_DIR = getExternalStorePath() + File.separator + BaseApplication.getApplication().getPackageName();
    public static final String IMAGE_PATH = APPS_ROOT_DIR + "/Image";
    public static final String TEMP_PATH = APPS_ROOT_DIR + "/Temp";

    /**
     * AppCrash 文件保存路径
     */
    public static final String APP_CRASH_PATH = APPS_ROOT_DIR + "/AppCrash";
    public static final String FILE_PATH = APPS_ROOT_DIR + "/File";


    /**
     * 外置存储卡的路径
     *
     * @return
     */
    @Nullable
    public static String getExternalStorePath() {
          return FIlePathUtil.getExternalStorePath(BaseApplication.getApplication().getApplicationContext());
    }

    /**
     * 是否有外存卡
     *
     * @return
     */
    public static boolean isExistExternalStore() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    private static File create(String path) {
        if (!isExistExternalStore()) {
            Logger.e("储存卡已拔出");
            return null;
        }
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }
        return directory;
    }


    /**
     * 存储日志文件目录
     *
     * @return File
     */
    public static File getAppCrashPath() {
        return create(APP_CRASH_PATH);
    }


    /**
     * 7.0以上拍照 安装应用等文件问题
     *
     * @param context context
     * @param file    file
     * @return Uri
     */
    public static Uri getUriForFile(Context context, String packageName, File file) {
        Uri fileUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fileUri = FileProvider.getUriForFile(context,  packageName+ ".provider", file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }


    /**
     * 获取目录下的文件列表
     *
     * @param strPath strPath
     */
    public static LinkedList<File> listLinkedFiles(String strPath) {
        File dir = new File(strPath);
        File file[] = dir.listFiles();
        LinkedList<File> list = null;
        if (dir.exists() && file != null && file.length > 0) {
            list = new LinkedList<>();
            Collections.addAll(list, file);
        }
        return list;
    }



    /** 删除文件，可以是文件或文件夹
     * @param delFile 要删除的文件夹或文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String delFile) {
        File file = new File(delFile);
        if (!file.exists()) {

            return false;
        } else {
            if (file.isFile()) {
                return deleteSingleFile(delFile);
            } else {
                return deleteDirectory(delFile);
            }
        }
    }


    /** 删除单个文件
     * @param filePath$Name 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    private static boolean deleteSingleFile(String filePath$Name) {
        File file = new File(filePath$Name);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {

                return true;
            } else {

                return false;
            }
        } else {

            return false;
        }
    }

    /** 删除目录及目录下的文件
     * @param filePath 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    private static boolean deleteDirectory(String filePath) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator;
        }
        File dirFile = new File(filePath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {

            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (File file : files) {
            // 删除子文件
            if (file.isFile()) {
                flag = deleteSingleFile(file.getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
            // 删除子目录
            else if (file.isDirectory()) {
                flag = deleteDirectory(file
                        .getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {

            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {

            return true;
        } else {

            return false;
        }
    }

}
