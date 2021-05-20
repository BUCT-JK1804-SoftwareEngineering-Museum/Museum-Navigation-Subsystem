package com.example.maptest.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;


public class SharedPreUtils {
    private String path = "share";
    private final SharedPreferences sp;
    private static SharedPreUtils sharedPreUtils;

    public SharedPreUtils(Context context) {
        this.sp = context.getSharedPreferences(this.path, context.MODE_PRIVATE);
    }

    public static SharedPreUtils create(Context context) {
        if (sharedPreUtils == null) {
            Class var1 = SharedPreUtils.class;
            synchronized (SharedPreUtils.class) {
                if (sharedPreUtils == null) {
                    sharedPreUtils = new SharedPreUtils(context);
                }
            }
        }
        return sharedPreUtils;
    }

    public void putString(String key, String value) {
        this.sp.edit().putString(key, value).apply();
    }

    public String getString(String key, String defValue) {
        return this.sp.getString(key, defValue);
    }

    public String getString(String key) {
        return this.sp.getString(key,"");
    }

    public void clear() {
        if (this.sp != null) {
            this.sp.edit().clear().apply();
        }
    }

    /**
     * 获取List集合
     *
     * @param key     键
     * @param <E>     指定泛型
     * @return List集合
     */
    public  <E extends Serializable> List<E> getList(String key) {
        try {
            return (List<E>) get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    /**
     * 获取对象
     */
    private Object get(String key)
            throws IOException, ClassNotFoundException {
        String wordBase64 = getString(key);
        // 将base64格式字符串还原成byte数组
        if (TextUtils.isEmpty(wordBase64)) { //不可少，否则在下面会报java.io.StreamCorruptedException
            return null;
        }
        byte[] objBytes = Base64.decode(wordBase64.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(objBytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        // 将byte数组转换成product对象
        Object obj = ois.readObject();
        bais.close();
        ois.close();
        return obj;
    }

}
